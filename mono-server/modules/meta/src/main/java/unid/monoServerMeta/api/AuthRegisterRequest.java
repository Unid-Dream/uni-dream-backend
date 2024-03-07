package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class AuthRegisterRequest {
    @NotBlank
    @Pattern(regexp = unid.monoServerMeta.Pattern.USER_PASSWORD)
    private String loginPassword;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private UserRole userRole;
}
