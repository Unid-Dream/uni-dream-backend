package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class AcademicMajorResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @NotNull
    @Valid
    private I18n titleI18n;
    @Nullable
    @Valid
    private I18n descriptionI18n;
    @NotNull
    private TagResponse tag;
    @Nullable
    private List<AcademicSubjectResponse> subjects;
}
