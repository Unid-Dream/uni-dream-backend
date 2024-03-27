package unid.monoServerMeta.api.version2.request;


import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@FieldNameConstants
public class PendingSessionRemindEducatorEmailPayload {
    private I18n educatorFirstName;
    private I18n educatorLastName;
    private String email;
    private OffsetDateTime startTimeUtc;
    private OffsetDateTime endTimeUtc;



}
