package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.*;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;



@Data
@FieldNameConstants
@Validated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudentSessionTransactionPayload implements Serializable {
    @NotNull
    private UUID transactionId;
    @NotNull
    private StudentProfile studentProfile;
    @NotNull
    private EducatorProfile educatorProfile;
    @NotNull
    private OffsetDateTime startTimeUtc;
    @NotNull
    private OffsetDateTime endTimeUtc;

    private BookingStatus status;

    private OffsetDateTime submissionTime;

    private List<EventOpLog> eventOpLogs;

    private Reschedule reschedule;

    @JsonIgnore
    private Integer total;


    @Data
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Reschedule{
        private OffsetDateTime startTimeUtc;
        private OffsetDateTime endTimeUtc;
    }


    @Data
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class StudentProfile{
        @NotNull
        private UUID profileId;
        @NotNull
        private I18n firstNameI18n;
        @NotNull
        private I18n lastNameI18n;
        @Nullable
        private String profilePicture;
        @NotNull
        private TagResponse secondarySchool;
        @NotNull
        private TagResponse country;
        @NotNull
        private String email;
        @NotNull
        private String phone;
        @NotNull
        private String timezone;

    }



    @Data
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class EducatorProfile{
        private UUID profileId;
        private I18n firstNameI18n;
        private I18n lastNameI18n;
        @Nullable
        private String profilePicture;
        @NotNull
        private TagResponse university;
        @NotNull
        private TagResponse country;
        @NotNull
        private String email;
        @NotNull
        private String phone;
        @NotNull
        private String timezone;

        private List<TagResponse> expertises;
        private List<TagResponse> languages;

    }

    @Data
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class TagResponse{
        private UUID id;
        private I18n i18n;
    }


    @Data
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class EventOpLog {
        private UUID id;
        private OffsetDateTime timeUtc;
        private User user;
        private BookingStatus status;
        private SessionOpType opType;

        @Data
        @FieldNameConstants
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public static class User {
            private UUID id;
            private I18n firstNameI18n;
            private I18n lastNameI18n;
            private UserRole userRole;
        }
    }

}
