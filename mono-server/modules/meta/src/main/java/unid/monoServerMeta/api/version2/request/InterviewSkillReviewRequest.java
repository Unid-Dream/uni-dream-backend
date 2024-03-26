package unid.monoServerMeta.api.version2.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.api.InterviewSkillPayload;
import unid.monoServerMeta.api.WritingSkillPayload;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.ReviewStatus;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@FieldNameConstants
public class InterviewSkillReviewRequest {
    @NotNull
    private UUID id;

    private InterviewSkillPayload.StudentUploadedSupervisorReview clarity;
    private InterviewSkillPayload.StudentUploadedSupervisorReview content;
    private InterviewSkillPayload.StudentUploadedSupervisorReview charisma;

    @Data
    @FieldNameConstants
    @Schema
    public static class StudentUploadedSupervisorReview{
        private UUID id;
        private Integer score;
        private String strength;
        private String weakness;
        private String improvement;
        private String wrapUp;
    }
}
