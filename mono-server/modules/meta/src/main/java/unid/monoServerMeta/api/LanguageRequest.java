package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class LanguageRequest {
    @NotNull
    @Valid
    private I18n descriptionI18n;
    @NotNull
    private UUID tagId;
}
