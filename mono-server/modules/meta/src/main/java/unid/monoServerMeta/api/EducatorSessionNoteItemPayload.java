package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorSessionNoteItemPayload {
    @NotNull
    private UUID id;
    @NotNull
    @Min(value = 1L)
    private Integer order;
    @NotNull
    @Valid
    private I18n titleI18n;
    @NotNull
    private Boolean mandatory;
}
