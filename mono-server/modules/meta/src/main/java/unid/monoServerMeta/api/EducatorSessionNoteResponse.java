package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorSessionNoteResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @NotNull
    @Valid
    private I18n titleI18n;
    @NotNull
    @Valid
    private I18n subTitleI18n;
    @NotNull
    @Valid
    private I18n descriptionI18n;
    @NotNull
    private Boolean mandatory;
    @NotNull
    private Boolean obsoleted;
    @NotEmpty
    @Valid
    private List<EducatorSessionNoteItemPayload> items;
}
