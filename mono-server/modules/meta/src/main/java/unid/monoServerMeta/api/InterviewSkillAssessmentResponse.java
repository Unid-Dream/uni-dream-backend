package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class InterviewSkillAssessmentResponse {

    private List<Item> list;


    @Data
    @FieldNameConstants
    public static class Item{
        private UUID id;
        private LocalDateTime submitTime;
        private LocalDateTime responseTime;
        private I18n topicI18n;
        private BigDecimal price;
        private String fileUrl;

        private UploadedSupervisorReviewResponse content;
        private UploadedSupervisorReviewResponse clarity;
        private UploadedSupervisorReviewResponse charisma;

        private String[] recommendedActivity;
        private String recommendation;
    }


}
