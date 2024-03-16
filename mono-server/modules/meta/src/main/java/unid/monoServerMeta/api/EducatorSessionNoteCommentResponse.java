package unid.monoServerMeta.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.UUID;

@Data
@FieldNameConstants
public class EducatorSessionNoteCommentResponse {
    private UUID id;
    private I18n titleI18n;
    private String comment;


}
