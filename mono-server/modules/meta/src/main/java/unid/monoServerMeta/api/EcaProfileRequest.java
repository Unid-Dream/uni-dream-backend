package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class EcaProfileRequest {
    private List<EcaProfileSectionPayload> payload;

    @Data
    @FieldNameConstants
    public static class EcaProfileSectionPayload{
        private UUID id;
        private UUID[] options;
    }
}
