package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.TransactionItem;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@FieldNameConstants
public class StudentHistoryPayload {
    private UUID id;
    private String serialNumber;
    private OffsetDateTime submissionTime;
    private TransactionItem transactionItem;
    private String status;

    @JsonIgnore
    private Integer total;







}
