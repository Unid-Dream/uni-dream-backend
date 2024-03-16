package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class ConsultationSessionCommentResponse {
    private UUID id;
    private I18n titleI18n;
    private List<CommentItem> items;

    @Data
    @FieldNameConstants
    public static class CommentItem {
        private UUID id;
        private I18n titleI18n;
        private String value;
    }

}
