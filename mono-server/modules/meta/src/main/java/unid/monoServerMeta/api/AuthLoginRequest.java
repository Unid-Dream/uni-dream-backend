package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
@Schema(title = "data", description = "响应数据", accessMode = Schema.AccessMode.READ_ONLY)
public class AuthLoginRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String loginPassword;
}
