package unid.monoServerApp.api.auth.login;

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
import pwh.springWebStarter.response.UnifiedResponse;
import unid.monoServerApp.ErrorCode;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.service.JwtTokenService;
import unid.monoServerMeta.api.AuthEducatorLoginRequest;
import unid.monoServerMeta.api.AuthLoginRequest;
import unid.monoServerMeta.api.AuthLoginResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Login")
@Slf4j
public class AuthLoginController {
    private final AuthLoginService authLoginService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("student/auth/login")
    @Transactional
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Login"
    )
    public @Valid UnifiedResponse<AuthLoginResponse> login(
            @RequestBody @Valid
            AuthLoginRequest payload
    ) {
        var existUserRecord = authLoginService.getExistUser(payload)
                .orElseThrow(() -> new UnifiedException(
                        ErrorCode.INCORRECT_CREDENTIAL,
                        "User Does Not Exist",
                        HttpStatus.NOT_FOUND.value()
                ));
        if (
                !authLoginService.isUserPasswordCorrect(
                        payload.getLoginPassword(),
                        existUserRecord
                )
        ) {
            throw new UnifiedException(
                    ErrorCode.INCORRECT_CREDENTIAL,
                    "User Password Incorrect",
                    HttpStatus.FORBIDDEN.value()
            );
        }
        return UnifiedResponse.of(
                AuthLoginResponse.builder()
                        .token(jwtTokenService.generateTokenForUser(existUserRecord))
                        .build()
        );
    }


    @PostMapping("educator/auth/login")
    @Transactional
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Login"
    )
    public @Valid UnifiedResponse<AuthLoginResponse> login(
            @RequestBody @Valid
            AuthEducatorLoginRequest payload
    ) {
        var existUserRecord = authLoginService.getExistUser(payload)
                .orElseThrow(() -> new UnifiedException(
                        ErrorCode.INCORRECT_CREDENTIAL,
                        "User Does Not Exist",
                        HttpStatus.NOT_FOUND.value()
                ));
        if (
                !authLoginService.isUserPasswordCorrect(
                        payload.getLoginPassword(),
                        existUserRecord
                )
        ) {
            throw new UnifiedException(
                    ErrorCode.INCORRECT_CREDENTIAL,
                    "User Password Incorrect",
                    HttpStatus.FORBIDDEN.value()
            );
        }
        return UnifiedResponse.of(
                AuthLoginResponse.builder()
                        .token(jwtTokenService.generateTokenForUser(existUserRecord))
                        .build()
        );
    }
}
