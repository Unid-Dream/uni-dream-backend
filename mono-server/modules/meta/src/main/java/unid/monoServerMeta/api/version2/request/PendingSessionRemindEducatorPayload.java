package unid.monoServerMeta.api.version2.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
public class PendingSessionRemindEducatorPayload {
    private UUID transactionId;


}
