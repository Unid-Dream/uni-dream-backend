package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class StudentProfileSchoolReportPayload {
    @Nullable
    private UUID id; // not null = update
    @Nullable
    private String secondarySchoolReport;
    @Nullable
    private String secondarySchoolReportAcademicYear;
    @Nullable
    private String secondarySchoolReportSemester;
}
