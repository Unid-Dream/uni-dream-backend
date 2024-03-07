package unid.monoServerApp.feign.cod.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Validated
public class CodOrderDetailRequest {
    @JsonProperty("order_ref")
    @NotBlank
    private String orderRef;
}
