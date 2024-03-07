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
public class CodNotification {
    @JsonProperty("data")
    @NotBlank
    private String data;
    @JsonProperty("signature")
    @NotBlank
    private String signatureBase64;
    @JsonProperty("algorithm")
    @NotBlank
    private String algorithm;
}
