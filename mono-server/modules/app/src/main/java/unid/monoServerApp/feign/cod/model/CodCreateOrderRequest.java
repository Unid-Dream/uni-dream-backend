package unid.monoServerApp.feign.cod.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.api.CodTransactionSubject;
import unid.monoServerMeta.api.CodWallet;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Validated
public class CodCreateOrderRequest {
    @JsonProperty("order_ref")
    @NotBlank
    private String orderRef;
    @JsonProperty("segment_id")
    @NotBlank
    private String segmentId;
    @JsonProperty("amount")
    @NotNull
    private BigDecimal amount;
    @JsonProperty("currency")
    @NotNull
    private CodCurrency currency;
    @JsonProperty("subject")
    @NotNull
    private CodTransactionSubject subject;
    @JsonProperty("timeout")
    @NotNull
    private Integer timeoutMinutes;
    @JsonProperty("wallet")
    @Nullable
    private CodWallet wallet;
    @JsonProperty("return_url")
    @Nullable
    private String returnUrl;
    @JsonProperty("payment_solution")
    @NotNull
    private CodPaymentSolution paymentSolution;
}
