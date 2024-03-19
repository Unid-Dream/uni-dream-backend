package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Nullable;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class StudentSchedulePageRequest {

    private String startDate;
    private String endDate;
    @JsonIgnore
    private Integer pageNumber;
    @JsonIgnore
    private Integer pageSize;
    @Nullable
    private UUID transactionId;
}
