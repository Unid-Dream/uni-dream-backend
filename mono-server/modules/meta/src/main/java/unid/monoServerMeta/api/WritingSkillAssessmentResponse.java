package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.cglib.core.Local;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.I18n;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class WritingSkillAssessmentResponse {

    private List<Item> list;

    @Data
    @FieldNameConstants
    public static class Item{
        private UUID id;
        private LocalDateTime submitTime;
        private I18n topicI18n;
        private BigDecimal price;
        private Currency currency;
        private LocalDateTime responseTime;

        private String fileUrl;

        private UploadedSupervisorReviewResponse content;
        private UploadedSupervisorReviewResponse composition;
        private UploadedSupervisorReviewResponse grammarAndExpression;

        private String[] recommendedActivity;
        private String recommendation;
    }

}
