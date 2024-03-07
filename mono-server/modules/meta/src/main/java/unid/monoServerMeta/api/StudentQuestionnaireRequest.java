package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class StudentQuestionnaireRequest {
    @NotNull
    @Valid
    private I18n titleI18nId;
    @Nullable
    @Valid
    private I18n subTitleI18nId;
    @Nullable
    @Valid
    private I18n descriptionI18nId;
    @NotNull
    private Boolean mandatoryOnNewJoinStudent;
    @NotEmpty
    @Valid
    private List<StudentQuestionnaireQuestionPayload> questions;
}
