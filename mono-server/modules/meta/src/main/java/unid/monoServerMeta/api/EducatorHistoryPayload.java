package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.TransactionItem;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@FieldNameConstants
public class EducatorHistoryPayload {
    private UUID id;
    private String serialNumber;
    private OffsetDateTime submissionTime;
    private TransactionItem transactionItem;
    private String status;

    @JsonIgnore
    private Integer total;







}
