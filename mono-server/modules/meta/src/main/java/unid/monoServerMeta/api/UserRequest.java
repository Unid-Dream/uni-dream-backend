package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.UserRole;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class UserRequest {
    @NotNull
    @Valid
    private I18n lastNameI18n;
    @NotNull
    @Valid
    private I18n fistNameI18n;
    @NotNull
    private UserRole userRole;
}
