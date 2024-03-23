package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Schema
public class StudentProfilePageRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String searchKey;

}
