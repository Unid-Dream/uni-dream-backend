package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@FieldNameConstants
public class StudentProfileSchoolReportRequest {
    private List<StudentProfileSchoolReportPayload> studentProfileSchoolReports;
}
