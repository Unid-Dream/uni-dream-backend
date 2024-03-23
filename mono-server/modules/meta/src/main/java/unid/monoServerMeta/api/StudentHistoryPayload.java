package unid.monoServerMeta.api;

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
@Schema
public class StudentHistoryPayload {
     private UUID id;
     private StudentProfile studentProfile;
     private OffsetDateTime submissionTime;
     private TransactionItem transactionItem;
     private BookingStatus status;

     @Data
     @FieldNameConstants
     @Schema
     public static class StudentProfile{
         private UUID id;
         private I18n firstNameI18n;
         private I18n lastNameI18n;
     }
}
