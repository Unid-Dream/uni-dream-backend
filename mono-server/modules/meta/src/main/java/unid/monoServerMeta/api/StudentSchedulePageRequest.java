package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@FieldNameConstants
@Schema
public class StudentSchedulePageRequest {

    private String startDate;
    private String endDate;
    private Integer pageNumber;
    private Integer pageSize;
}
