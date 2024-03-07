package unid.monoServerApp.cache.auth.register;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.validation.annotation.Validated;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Properties;
import unid.monoServerMeta.api.AuthRegisterRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@RedisHash(value = "NewRegisterUser")
@Validated
public class NewRegisterUser {
    @Id
    private UUID id;
    @NotBlank
    private String otp;
    @NotNull
    private Instant otpIssuedAtUtc;
    @NotNull
    private Integer maxResendOtpTimes;
    @NotNull
    private Integer resentOtpTimes;
    @NotNull
    private AuthRegisterRequest request;
    @Indexed
    @NotBlank
    private String email;
    @NotNull
    private UserRoleEnum userRole;
    @TimeToLive(unit = TimeUnit.MINUTES)
    @NotNull
    private Long ttl;

    @JsonIgnore
    public OffsetDateTime getOtpIssuedAt() {
        return OffsetDateTime.ofInstant(getOtpIssuedAtUtc(), ZoneOffset.UTC);
    }

    @JsonIgnore
    public Integer getOtpRemainedResendCounts() {
        return maxResendOtpTimes - resentOtpTimes;
    }

    @JsonIgnore
    public boolean canResendOtp() {
        return resentOtpTimes < maxResendOtpTimes
                && getOtpIssuedAt().isBefore(OffsetDateTime.now());
    }

    @JsonIgnore
    public Long getCanResendOtpAfterSeconds(Properties properties) {
        return NumberUtils.max(
                ChronoUnit.SECONDS.between(
                        OffsetDateTime.now(),
                        getOtpIssuedAt().plusSeconds(properties.getRegisterUserOtpResendSeconds())
                ),
                0
        );
    }

    @JsonIgnore
    public Long getOtpValidWithinSeconds() {
        return NumberUtils.max(
                ChronoUnit.SECONDS.between(
                        OffsetDateTime.now(),
                        getOtpIssuedAt().plusMinutes(getTtl())
                ),
                0
        );
    }
}
