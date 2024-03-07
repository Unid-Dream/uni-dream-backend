package unid.monoServerApp.feign.cod.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.Pattern;
import unid.monoServerMeta.api.CodWallet;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
public class CodNotificationPayment {
    @JsonProperty("transaction_id")
    @NotBlank
    private String transactionId;
    @JsonProperty("amount")
    @NotNull
    private BigDecimal amount;
    @JsonProperty("currency")
    @NotNull
    private CodCurrency currency;
    @JsonProperty("payment_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Pattern.COD_NOTIFICATION_DATE)
    @NotNull
    private OffsetDateTime paymentTime;
    @JsonProperty("merchant_id")
    @NotBlank
    private String merchantId;
    @JsonProperty("segment_id")
    @NotBlank
    private String segmentId;
    @JsonProperty("out_trade_no")
    @NotBlank
    private String outTradeNo;
    @JsonProperty("status")
    @NotNull
    private CodPaymentStatus status;
    @JsonProperty("pid")
    @NotBlank
    private String pid;
    @JsonProperty("subject")
    @NotBlank
    private String subject;
    @JsonProperty("order_ref")
    @NotBlank
    private String orderRef;
    @JsonProperty("wallet")
    @Nullable
    private CodWallet wallet;
}
