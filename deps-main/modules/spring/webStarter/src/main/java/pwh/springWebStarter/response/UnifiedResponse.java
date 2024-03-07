package pwh.springWebStarter.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@FieldNameConstants
@Validated
public class UnifiedResponse<Data> implements Serializable {
    @Schema(description = "Timestamp value in milliseconds")
    @NotNull(groups = {ValidationGroup.class})
    private final Long timestamp;
    @Schema(description = "Reference ID of the API call")
    @NotBlank(groups = {ValidationGroup.class})
    private final String requestId;
    @Schema(description = "Exists when the API call has error")
    @Valid
    private Error error;
    @Schema(description = "Response data")
    @Valid
    private Data data;

    public static <Data> UnifiedResponse<Data> of(Data data) {
        // filled by customizer
        return new UnifiedResponse<>(null, null, null, data);
    }

    @JsonIgnore
    public boolean hasError() {
        return ObjectUtils.isNotEmpty(getError());
    }

    @JsonIgnore
    public boolean hasData() {
        return ObjectUtils.isNotEmpty(getData());
    }

    public static class ValidationGroup {
    }

    @Getter
    @RequiredArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Validated
    public static class Error implements Serializable {
        @Schema(description = "Error code")
        @NotBlank(groups = {ValidationGroup.class})
        private final String code;
        @Schema(description = "Requested API path")
        @NotBlank(groups = {ValidationGroup.class})
        private final String path;
        @Schema(description = "Brief description of the error")
        private final List<String> description;
    }
}
