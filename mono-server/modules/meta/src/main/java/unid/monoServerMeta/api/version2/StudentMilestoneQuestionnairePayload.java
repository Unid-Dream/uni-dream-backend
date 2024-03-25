package unid.monoServerMeta.api.version2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.MilestoneOptionType;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class StudentMilestoneQuestionnairePayload {

    private UUID id;
    private Boolean singleChoice;
    @NotNull
    private I18n questionI18n;
    private Integer sortId;
    private List<StudentMilestoneAnswerPayload> answers;

    @Data
    @Schema
    @FieldNameConstants
    public static class StudentMilestoneAnswerPayload {
        private UUID id;
        private MilestoneOptionType optionType;
        private I18n answerI18n;
        private Integer sortId;
    }
}
