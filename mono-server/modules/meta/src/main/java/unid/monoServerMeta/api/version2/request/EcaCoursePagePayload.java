package unid.monoServerMeta.api.version2.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;

@Data
@Schema
@FieldNameConstants
public class EcaCoursePagePayload {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;

}
