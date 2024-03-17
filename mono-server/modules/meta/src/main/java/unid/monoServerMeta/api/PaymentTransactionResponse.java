package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Schema
public class PaymentTransactionResponse {
    private AsiaPayPayload asiaPayPayload;

    @Data
    @Schema
    public static class AsiaPayPayload{
        private String merchantId;
        private String orderRef;
        private String currCode;
        private String amount;
        private String paymentType;
    }
}
