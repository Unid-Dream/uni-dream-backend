package pwh.springStarter.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;

@Slf4j
public class AopUtil {
    public static <A extends Annotation> Object getMethodParamAnnotatedWith(Class<A> annotationClass, JoinPoint joinPoint) {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        if (methodSignature == null) {
            return null;
        }
        var method = methodSignature.getMethod();
        if (method == null) {
            return null;
        }
        for (var i = 0; i < method.getParameterCount(); i++) {
            var param = method.getParameters()[i];
            log.debug("Identifying '{}' param with annotation '{}' in method '{}'",
                    param.getName(),
                    annotationClass,
                    joinPoint.getSignature().toLongString());
            var annotation = param.getAnnotation(annotationClass);
            if (annotation != null) {
                log.debug("Identified: {} | {}", annotation, param);
                return joinPoint.getArgs()[i];
            }
        }
        return null;
    }
}
