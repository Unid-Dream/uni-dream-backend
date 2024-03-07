package unid.monoServerApp.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.service.SessionService;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(1) // e.g. before cache
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class Aop {
    private final SessionService sessionService;

    @Pointcut("within(unid.monoServerApp.api..*)")
    public void controllers() {
    }

    @Pointcut("@annotation(unid.monoServerApp.api.ACL)")
    public void acl() {
    }

    @Around("controllers() && acl()")
    public Object interceptControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodSignature = ((MethodSignature) joinPoint.getSignature());
        var acl = methodSignature.getMethod().getAnnotation(ACL.class);
        if (acl.authed()) {
            sessionService.allowAuthedOnly();
        } else if (acl.noAuthed()) {
            sessionService.allowUnAuthedOnly();
        }
        if (acl.allowedRoles().length > 0) {
            sessionService.allowAuthedOnly(acl.allowedRoles());
        }
        if (shouldDoMatching(acl.matchingSessionProfileId(), acl)) {
            var ids = Arrays.stream(methodSignature.getMethod().getParameters())
                    .filter(arg -> arg.getAnnotation(ACL.ProfileId.class) != null)
                    .collect(Collectors.toList());
            if (ids.size() != 1) {
                throw Exceptions.unknownError("Multiple or None Found: {}", ACL.ProfileId.class.getSimpleName());
            }
            var param = joinPoint.getArgs()[ids.get(0).getModifiers()];
            sessionService.allowMatchedProfileId((UUID) param);
        }
        if (shouldDoMatching(acl.matchingSessionUserId(), acl)) {
            var ids = Arrays.stream(methodSignature.getMethod().getParameters())
                    .filter(arg -> arg.getAnnotation(ACL.UserId.class) != null)
                    .collect(Collectors.toList());
            if (ids.size() != 1) {
                throw Exceptions.unknownError("Multiple or None Found: {}", ACL.UserId.class.getSimpleName());
            }
            var param = joinPoint.getArgs()[ids.get(0).getModifiers()];
            sessionService.allowMatchedUserId((UUID) param);
        }
        if (acl.educatorProfileApproved()) {
            sessionService.allowProfiledAndApprovedEducator();
        }
        return joinPoint.proceed();
    }

    private boolean shouldDoMatching(boolean matching, ACL acl) {
        if (matching && acl.skipMatchingForAdministrative()) {
            return !sessionService.isAdministrativeRole();
        }
        return matching;
    }
}
