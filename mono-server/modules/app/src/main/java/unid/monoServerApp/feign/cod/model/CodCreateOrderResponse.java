package unid.monoServerApp.feign.cod.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
public class CodCreateOrderResponse {
    @JsonProperty("version")
    @NotNull
    private CodVersion version;
    @JsonProperty("url")
    @Nullable
    private String url;
    @JsonProperty("alipay_order_string")
    @NotBlank
    private String alipayOrderString;
    @JsonProperty("out_trade_no")
    @NotBlank
    private String outTradeNo;
}
