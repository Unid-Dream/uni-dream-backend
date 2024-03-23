package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class AcademicMajorPageRequest {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;

    private String searchKey;
}
