//package uind.monoServerMeta.api;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldNameConstants;
//import org.springframework.validation.annotation.Validated;
//import uind.monoServerMeta.model.BaseResponse;
//import uind.monoServerMeta.model.Gender;
//
//import javax.annotation.Nullable;
//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.UUID;
//
//@EqualsAndHashCode(callSuper = true)
//@Data
//@NoArgsConstructor
//@FieldNameConstants
//@Validated
//public class StudentProfilePredicatedGradeResponse extends BaseResponse {
//    @NotNull
//    private UUID studentProfileId;
//    @NotNull
//    private AcademicSubjectResponse academicSubject;
//    @NotBlank
//    private String grade;
//}
