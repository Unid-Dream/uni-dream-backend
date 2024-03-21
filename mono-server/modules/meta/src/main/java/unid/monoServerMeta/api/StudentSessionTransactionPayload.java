package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.PaymentStatus;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;



@Data
@FieldNameConstants
@Validated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudentSessionTransactionPayload {
    @NotNull
    private UUID id;
    @NotNull
    private StudentProfile studentProfile;
    @NotNull
    private EducatorProfile educatorProfile;
    @NotNull
    private OffsetDateTime startTimeUtc;
    @NotNull
    private OffsetDateTime endTimeUtc;

    @NotNull
    private PaymentStatus paymentStatus;
    @NotNull
    private BookingStatus processStatus;
    @Nullable
    private String rejectReason;
    @NotNull
    private BookingStatus bookingStatus;
    @NotNull
    private OffsetDateTime submissionTime;


    @JsonIgnore
    private Integer total;

    @Data
    @FieldNameConstants
    public static class StudentProfile{
        @NotNull
        private UUID id;
        @NotNull
        private I18n firstNameI18n;
        @NotNull
        private I18n lastNameI18n;
        @Nullable
        private String profilePicture;
        @NotNull
        private SecondarySchool secondarySchool;
        @NotNull
        private Country country;
        @NotNull
        private String email;
        @NotNull
        private String phone;
        @NotNull
        private String timezone;

        @Data
        @FieldNameConstants
        public static class SecondarySchool{
            private UUID id;
            private I18n i18n;
        }
    }



    @Data
    @FieldNameConstants
    public static class EducatorProfile{
        private UUID id;
        private I18n firstNameI18n;
        private I18n lastNameI18n;
        @Nullable
        private String profilePicture;
        @NotNull
        private University university;
        @NotNull
        private Country country;
        @NotNull
        private String email;
        @NotNull
        private String phone;
        @NotNull
        private String timezone;
        @Data
        @FieldNameConstants
        public static class University{
            private UUID id;
            private I18n i18n;
        }
    }




    @Data
    @FieldNameConstants
    public static class Country{
        private UUID id;
        private I18n i18n;
    }



}
