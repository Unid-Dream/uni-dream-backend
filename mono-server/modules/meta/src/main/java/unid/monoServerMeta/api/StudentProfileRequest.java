package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
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

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class StudentProfileRequest {
    @Nullable
    @Valid
    private I18n firstNameI18n;
    @Nullable
    @Valid
    private I18n lastNameI18n;
//    @Nullable
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = unid.monoServerMeta.Pattern.DATE)
//    @Schema(implementation = String.class, pattern = unid.monoServerMeta.Pattern.DATE)
//    private LocalDate dateOfBirth;
    @Nullable
    private Gender gender;
    @Nullable
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
    private UUID secondarySchoolId;
    @Nullable
    private UUID secondarySchoolEducationLevelId;
    @Nullable
    private UUID secondarySchoolCurriculumId;
    @Nullable
    private UUID preferredUniversity_1Id;
    @Nullable
    private UUID preferredUniversity_2Id;
    @Nullable
    private UUID preferredUniversity_3Id;
    @Nullable
    private UUID[] preferredOtherUniversityId;
    @Nullable
    @Valid
    private List<StudentProfileSchoolReportPayload> studentProfileSchoolReports;
    private String secondarySchoolGraduationYear;
//    @Nullable
//    @Valid
//    private List<StudentProfilePredicatedGradeRequest> predicatedGrades;

    private String timezone;
}
