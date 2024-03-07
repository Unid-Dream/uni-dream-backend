package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
//@Validated
public class EducatorProfileResponse extends BaseResponse {
//    @NotNull
    private UUID id;
//    @NotNull
    private UUID userId;
//    @NotNull
//    @Valid
    private CountryResponse country;
    private CityResponse city;
//    @Nullable
    private String profilePicture;
//    @NotBlank
//    @Pattern(regexp = unid.monoServerMeta.Pattern.COUNTRY_CODE)
    private String phoneCountryCode;
//    @NotBlank
//    @Pattern(regexp = unid.monoServerMeta.Pattern.PHONE_NUMBER)
    private String phone;
//    @Nullable
    private Integer hourlyRate;
//    @NotNull
//    @Valid
    private SchoolResponse university;
//    @NotNull
//    @Valid
    private EducationLevelResponse universityEducationLevel;
//    @NotNull
//    @Valid
    private SchoolIdentityResponse universityIdentity;
//    @NotEmpty
//    @Valid
    private List<LanguageResponse> languages;
//    @NotEmpty
    @Valid
    private List<ExpertiseResponse> expertises;
    /** 新增的字段 **/
    private ExpertiseResponse expertise;
    private String description;
    private List<EducatorSchoolResponse> schoools;
    private I18n firstNameI18n;
    private I18n lastNameI18n;

    private List<I18n> subjects;




}
