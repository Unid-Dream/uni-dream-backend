package unid.monoServerApp.feign.cod.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
public class CodResponse<T> {
    @JsonProperty("success")
    @NotNull
    private Boolean success;
    @JsonProperty("reference_id")
    @NotBlank
    private String referenceId;
    @JsonProperty("result")
    @Nullable
    @Valid
    private Result result;
    @JsonProperty("error")
    @Nullable
    private String error;
    @JsonProperty("error_code")
    @Nullable
    private String errorCode;
    @Nullable
    private T resultObj;

    public Boolean hasError() {
        return StringUtils.isNotBlank(errorCode);
    }

    @Data
    @SuperBuilder
    @Jacksonized
    @NoArgsConstructor
    @FieldNameConstants
    @Validated
    public static class Result {
        @JsonProperty("nonce")
        @NotBlank
        private String nonce;
        @JsonProperty("message")
        @NotBlank
        private String base64EncodedEncryptedMessage;
    }
}
