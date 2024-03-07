package unid.monoServerMeta.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@FieldNameConstants(innerTypeName = "BaseResponseFields")
@Validated
public class BaseResponse implements Serializable {
    @Nullable
    @Schema(implementation = Long.class, pattern = "Epoch Time")
    private Long createdOnUtc;
    @Nullable
    @Schema(implementation = Long.class, pattern = "Epoch Time")
    private Long updatedOnUtc;
}
