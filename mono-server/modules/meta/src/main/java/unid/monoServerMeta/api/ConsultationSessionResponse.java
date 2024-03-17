package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.PaymentStatus;

import java.util.UUID;

@Data
@FieldNameConstants
public class ConsultationSessionResponse {
    private TransactionResponse paymentTransaction;
    private BookingStatus bookingStatus;

    @Data
    @FieldNameConstants
    @Schema
    public static class TransactionResponse{
        private UUID studentProfileId;
        private PaymentStatus paymentStatus;
        private BookingStatus processStatus;

    }
}
