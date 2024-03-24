package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;

@Data
@FieldNameConstants
@Schema
public class UniversityPageRequest {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;

    private String searchKey;
}
