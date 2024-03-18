package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class PaymentTransactionRequest {
    private UUID transactionId;

    private AsiaPayPayload asiaPayPayload;

    @Data
    @Schema
    public static class AsiaPayPayload{
        private String currCode;
        private String payMethod;
    }
}
