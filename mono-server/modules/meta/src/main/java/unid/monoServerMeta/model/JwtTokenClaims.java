package unid.monoServerMeta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@SuperBuilder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@FieldNameConstants
@Validated
public class JwtTokenClaims implements Serializable {
    private UUID educatorProfileId;
    @NotNull
    private UUID userId;
    @NotNull
    private UserRole userRole;
    @Nullable
    private UUID userProfileId;
    @Nullable
    private I18n firstNameI18n;
    @Nullable
    private I18n lastNameI18n;
    @Nullable
    private String email;
    private String profilePicture;
    private String applicationApproval;
    private String timezone;
}
