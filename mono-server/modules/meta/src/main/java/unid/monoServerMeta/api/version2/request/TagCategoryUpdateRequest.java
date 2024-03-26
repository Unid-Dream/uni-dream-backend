package unid.monoServerMeta.api.version2.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Schema
public class TagCategoryUpdateRequest {
    @NotNull
    @Valid
    private UUID id;
    @NotNull
    @Valid
    private I18n descriptionI18n;
}
