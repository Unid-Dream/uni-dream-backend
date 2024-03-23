package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Schema
public class WritingSkillPageRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String searchKey;

}
