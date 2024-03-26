package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.ReviewStatus;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class WritingSkillPayload {

    @NotNull
    private UUID id;
    private String uploadedFile;
    
    private StudentProfile studentProfile;
    private OffsetDateTime submissionTime;
    private ReviewStatus status;
    private StudentUploadedSupervisorReview grammarAndExpression;
    private StudentUploadedSupervisorReview content;
    private StudentUploadedSupervisorReview composition;



    @JsonIgnore
    private Integer total;


    @Data
    @FieldNameConstants
    @Schema
    public static class StudentProfile {
        @NotNull
        private UUID id;
        @NotNull
        private I18n firstNameI18n;
        @NotNull
        private I18n lastNameI18n;
        private Country country;
        private String email;
        private String phone;
        private String profilePicture;


        @Data
        @FieldNameConstants
        @Schema
        public static class Country{
            private UUID id;
            private I18n i18n;
        }

        @Data
        @FieldNameConstants
        @Schema
        public static class SecondarySchool{
            private UUID id;
            private I18n i18n;
        }
    }

    @Data
    @FieldNameConstants
    @Schema
    public static class WritingTopic{
        private UUID id;
        private I18n titleI18n;
        private I18n descriptionI18n;
    }

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
