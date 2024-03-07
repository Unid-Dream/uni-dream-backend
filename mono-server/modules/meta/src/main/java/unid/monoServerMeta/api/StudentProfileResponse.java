package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.Gender;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class StudentProfileResponse extends BaseResponse {
    private I18n lastNameI18n;
    private I18n firstNameI18n;

    @NotNull
    private UUID id;
    @NotNull
    private UUID userId;
    @Nullable
    private Gender gender;
    @Nullable
    private String email;
    @Nullable
    @Valid
    @JsonIgnore
    private CountryResponse country;
    private UUID countryId;
    @Nullable
    private String profilePicture;
    @Nullable
//    @Pattern(regexp = unid.monoServerMeta.Pattern.COUNTRY_CODE)
    private String phoneCountryCode;
    @Nullable
//    @Pattern(regexp = unid.monoServerMeta.Pattern.PHONE_NUMBER)
    private String phone;
    @Nullable
    @Valid
    @JsonIgnore
    private SchoolResponse secondarySchool;
    private UUID secondarySchoolId;

    private String secondarySchoolGraduationYear;
    private UUID[] preferredOtherUniversityId;
    @Nullable
    @Valid
    @JsonIgnore
    private SchoolResponse preferredUniversity_1;
    private UUID preferredUniversity_1Id;
    @Nullable
    @Valid
    @JsonIgnore
    private SchoolResponse preferredUniversity_2;
    private UUID preferredUniversity_2Id;
    @Nullable
    @Valid
    @JsonIgnore
    private SchoolResponse preferredUniversity_3;
    private UUID preferredUniversity_3Id;
    @Nullable
    @Valid
    private List<StudentProfileSchoolReportPayload> studentProfileSchoolReports;
}
