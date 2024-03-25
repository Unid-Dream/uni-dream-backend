package unid.monoServerMeta.api.version2.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.ReviewStatus;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InterviewSkillUpdateRequest {

    @NotNull
    private UUID id;
    private String uploadedFile;



    private I18n topic;
    private StudentProfile studentProfile;
    private OffsetDateTime submissionTime;
    private ReviewStatus status;
    private StudentUploadedSupervisorReview clarity;
    private StudentUploadedSupervisorReview content;
    private StudentUploadedSupervisorReview charisma;



    @JsonIgnore
    private Integer total;


    @Data
    @FieldNameConstants
    @Schema
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    public static class StudentUploadedSupervisorReview{
        private UUID id;
        private Integer score;
        private String strength;
        private String weakness;
        private String improvement;
        private String warpUp;
    }


}
