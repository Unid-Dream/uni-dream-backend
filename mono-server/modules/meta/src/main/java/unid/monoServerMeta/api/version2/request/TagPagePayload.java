package unid.monoServerMeta.api.version2.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema
public class TagPagePayload {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
}
