package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.UserRole;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class UserResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @Nullable
    @Valid
    private I18n lastNameI18n;
    @Nullable
    @Valid
    private I18n firstNameI18n;
    @NotNull
    private UserRole userRole;

    private String profilePicture;

}
