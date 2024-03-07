package unid.monoServerApp.feign.cod.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
public class CodRequestPayload<T> {
    @JsonProperty("request_uuid")
    @NotNull
    private UUID requestUuid;
    @JsonProperty("request_time")
    @NotBlank
    private String requestTimeEpoch;
    @JsonProperty("service")
    @NotNull
    private CodService service;
    @JsonProperty("merchant_id")
    @NotBlank
    private String merchantId;
    @JsonProperty("parameters")
    @NotNull
    @Valid
    private T requestObject;
}
