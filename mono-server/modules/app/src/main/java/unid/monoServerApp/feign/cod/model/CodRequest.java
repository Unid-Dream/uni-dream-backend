package unid.monoServerApp.feign.cod.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
public class CodRequest {
    @JsonProperty("merchant_id")
    @NotBlank
    private String merchantId;
    @JsonProperty("message")
    @NotBlank
    private String message;
    @JsonProperty("cipher_suite")
    @NotBlank
    private String cipherSuite;
    @JsonProperty("nonce")
    @NotBlank
    private String nonce;
    @JsonProperty("tag")
    @NotBlank
    private String tag;
}
