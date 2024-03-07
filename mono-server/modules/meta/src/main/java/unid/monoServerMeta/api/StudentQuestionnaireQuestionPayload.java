package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class StudentQuestionnaireQuestionPayload {
    @NotNull
    private Integer order;
    @NotNull
    @Valid
    private I18n descriptionI18n;
    @NotNull
    private Boolean mandatory;
    @NotNull
    private Boolean singleChoice;
    @NotEmpty
    @Valid
    private List<StudentQuestionnaireAnswerPayload> answers;
}
