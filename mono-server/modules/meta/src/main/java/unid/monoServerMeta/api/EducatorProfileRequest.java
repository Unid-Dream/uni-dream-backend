package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorProfileRequest {
    @NotNull
    @Valid
    private I18n firstName;
    @NotNull
    @Valid
    private I18n lastName;
    @NotNull
    private UUID countryId;
    @Nullable
    private String profilePicture;
    @NotBlank
    @Pattern(regexp = unid.monoServerMeta.Pattern.COUNTRY_CODE)
    private String phoneCountryCode;
    @NotBlank
    @Pattern(regexp = unid.monoServerMeta.Pattern.PHONE_NUMBER)
    private String phone;
    @NotNull
    private UUID universityId;
    @NotNull
    private UUID universityEducationLevelId;
    @NotNull
    private UUID universityIdentityId;
    @NotEmpty
    private List<UUID> languages;
    @NotEmpty
    @Valid
    private List<EducatorProfileExpertiseRequestPayload> expertises;
}
