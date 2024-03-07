package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.TagCategory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class TagRequest {
    @NotNull
    @Valid
    private I18n descriptionI18n;
    @NotNull
    private TagCategory tagCategory;
}
