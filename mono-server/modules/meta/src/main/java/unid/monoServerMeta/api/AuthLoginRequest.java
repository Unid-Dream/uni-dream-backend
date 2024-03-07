package unid.monoServerMeta.api;

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
public class AuthLoginRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String loginPassword;
}
