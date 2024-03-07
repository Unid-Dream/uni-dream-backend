package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.MilestoneOptionType;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Validated
public class StudentMilestonePayload {

    private UUID id;

    private I18n questionI18n;
    private boolean singleChoice;
    private List<Answer> answerItems;


    @Data
    @FieldNameConstants
    public static class Answer{
        private UUID id;
        private I18n answerI18n;
        private MilestoneOptionType type;
        private Object optionValue;
    }
}
