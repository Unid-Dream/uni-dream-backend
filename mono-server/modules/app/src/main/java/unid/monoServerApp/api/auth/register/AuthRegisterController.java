package unid.monoServerApp.api.auth.register;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.ErrorCodeGlobal;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.monoServerApp.ErrorCode;
import unid.monoServerApp.Properties;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.cache.auth.register.NewRegisterUser;
import unid.monoServerApp.cache.auth.register.NewRegisterUserRepository;
import unid.monoServerApp.mapper.UserRoleMapper;
import unid.monoServerApp.service.EmailService;
import unid.monoServerApp.service.JwtTokenService;
import unid.monoServerMeta.api.AuthLoginResponse;
import unid.monoServerMeta.api.AuthRegisterConfirmRequest;
import unid.monoServerMeta.api.AuthRegisterRequest;
import unid.monoServerMeta.api.AuthRegisterResponse;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "User Registration")
@Slf4j
public class AuthRegisterController {
    private final AuthRegisterService authRegisterService;
    private final UserRoleMapper userRoleMapper;
    private final EmailService emailService;
    private final NewRegisterUserRepository newRegisterUserRepository;
    private final Properties properties;
    private final JwtTokenService jwtTokenService;

    private void checkRegistration(AuthRegisterRequest payload) {
        var existDbRecord = authRegisterService.getExistRecordFromDb(payload);
        if (existDbRecord.isPresent()) {
            if (payload.getEmail().equalsIgnoreCase(existDbRecord.get().getEmail())) {
                throw new UnifiedException(
                        ErrorCode.DUPLICATED_USER_EMAIL,
                        "Duplicated Email",
                        HttpStatus.BAD_REQUEST.value()
                );
            }
            if (payload.getEmail().equalsIgnoreCase(existDbRecord.get().getLoginId())) {
                throw new UnifiedException(
                        ErrorCode.DUPLICATED_USER_LOGIN_ID,
                        "Duplicated Login ID",
                        HttpStatus.BAD_REQUEST.value()
                );
            }
        }
    }

    @PostMapping("student/auth/register")
    @Transactional
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Register"
    )
    public @Valid UnifiedResponse<AuthRegisterResponse> register(
            @RequestBody @Valid
            AuthRegisterRequest payload
    ) {
        var userRole = userRoleMapper.toEnum(payload.getUserRole());
        checkRegistration(payload);
        var result = authRegisterService.getExistRecordFromCache(payload).orElseGet(() -> {
            var newResult = authRegisterService.createTempNewUser(userRole, payload);
            emailService.requestNewUserRegistrationOtp(newResult);
            return newResult;
        });
        return UnifiedResponse.of(toResponse(result));
    }

    @PutMapping("student/auth/register")
    @Transactional
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Confirm One New Registration"
    )
    public @Valid UnifiedResponse<AuthLoginResponse> confirm(
            @RequestBody @Valid
            AuthRegisterConfirmRequest payload
    ) {
        var request = newRegisterUserRepository.findById(payload.getRefId()).orElseThrow(() ->
                new UnifiedException(
                        ErrorCodeGlobal.ERROR_NOT_FOUND,
                        "User Registration Record Not Found",
                        HttpStatus.NOT_FOUND.value()
                )
        );
        if (!request.getOtp().equals(payload.getOtp())) {
            throw new UnifiedException(
                    ErrorCodeGlobal.ERROR_INVALID_INPUT,
                    "OPT is invalid",
                    HttpStatus.UNAUTHORIZED.value()
            );
        }
        var user = authRegisterService.createNewUser(request.getRequest());
        newRegisterUserRepository.deleteById(payload.getRefId());
        return UnifiedResponse.of(
                AuthLoginResponse.builder()
                        .token(jwtTokenService.generateTokenForUser(user))
                        .build()
        );
    }

    @PutMapping("student/auth/register/{refId}")
    @Transactional
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Refresh During A New Registration"
    )
    public @Valid UnifiedResponse<AuthRegisterResponse> refresh(
            @PathVariable("refId") UUID refId
    ) {
        var record = newRegisterUserRepository.findById(refId).orElseThrow(() ->
                new UnifiedException(
                        ErrorCodeGlobal.ERROR_NOT_FOUND,
                        "User Registration Record Not Found",
                        HttpStatus.NOT_FOUND.value()
                )
        );
        if (!record.canResendOtp()) {
            throw new UnifiedException(
                    ErrorCodeGlobal.ERROR_INVALID_INPUT,
                    "Cannot Resend OTP",
                    HttpStatus.FORBIDDEN.value()
            );
        }
        var result = authRegisterService.refreshTempUser(record);
        emailService.requestNewUserRegistrationOtp(result);
        return UnifiedResponse.of(toResponse(result));
    }

    private AuthRegisterResponse toResponse(NewRegisterUser newRegisterUser) {
        return AuthRegisterResponse.builder()
                .refId(newRegisterUser.getId())
                .otpValidInSeconds(newRegisterUser.getOtpValidWithinSeconds())
                .otpRemainedResendCounts(newRegisterUser.getOtpRemainedResendCounts())
                .otpCanResendAfterSeconds(newRegisterUser.getCanResendOtpAfterSeconds(properties))
                .build();
    }
}
