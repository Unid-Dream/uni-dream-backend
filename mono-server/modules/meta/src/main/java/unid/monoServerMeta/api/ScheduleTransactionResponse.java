package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
public class ScheduleTransactionResponse {
    private UUID id;
}
