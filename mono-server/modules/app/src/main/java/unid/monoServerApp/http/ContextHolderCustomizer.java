package unid.monoServerApp.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pwh.springStarter.service.ValidationService;
import pwh.springWebStarter.customizer.WebContextHolderCustomizer;
import pwh.springWebStarter.holder.WebContextHolder;
import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.monoServerApp.database.service.UserCacheService;
import unid.monoServerApp.mapper.UserPayloadMapper;
import unid.monoServerApp.service.JwtTokenService;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ContextHolderCustomizer implements WebContextHolderCustomizer {
    private final JwtTokenService jwtTokenService;
    private final ValidationService validationService;
    private final UserPayloadMapper userPayloadMapper;
    private final UserCacheService userCacheService;

    @Override
    public RequestHolder customize(WebContextHolder.ContextHolder contextHolder) {
        var holder = new RequestHolder(
                contextHolder.getTraceId(),
                contextHolder.getHttpRequest(),
                contextHolder.getHttpResponse()
        );
        if (StringUtils.isNotBlank(holder.getAuthToken())) {
            var tokenClaim = jwtTokenService.getClaimsObject(holder.getAuthToken());
            if (
                    tokenClaim.isPresent()
                            && jwtTokenService.validateToken(holder.getAuthToken(), tokenClaim.get())
            ) {
                var payload = userPayloadMapper.toPayload(tokenClaim.get());
                payload.setProfileApproved(false);
                switch (payload.getUserRole()) {
                    case EDUCATOR:
                        if (payload.getUserProfileId() != null) {
                            userCacheService.getEducatorProfileByUserId(payload.getUserId())
                                    .ifPresent(profile -> {
                                        payload.setProfileApproved(ApplicationApprovalEnum.APPROVED.equals(profile.getApplicationApproval()));
                                    });
                        }
                        break;
                    default:
                        payload.setProfileApproved(true);
                }
                validationService.validate(payload);
                holder.setUser(payload);
            }
        }
        return holder;
    }
}
