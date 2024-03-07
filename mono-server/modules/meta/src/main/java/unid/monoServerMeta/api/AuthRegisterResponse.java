package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
public class AuthRegisterResponse {
    @NotNull
    private UUID refId;
    @NotNull
    private Long otpValidInSeconds;
    @NotNull
    private Integer otpRemainedResendCounts;
    @NotNull
    private Long otpCanResendAfterSeconds;
}
