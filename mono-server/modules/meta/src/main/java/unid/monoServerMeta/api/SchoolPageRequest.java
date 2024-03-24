package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.SchoolLevel;

import javax.validation.constraints.NotNull;

@Data
@FieldNameConstants
@Schema
public class SchoolPageRequest {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;

    private String searchKey;
}
