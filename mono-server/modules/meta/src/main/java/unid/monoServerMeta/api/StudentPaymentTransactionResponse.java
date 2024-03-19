package unid.monoServerMeta.api;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.PaymentStatus;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
@Validated
public class StudentPaymentTransactionResponse {
    @NotNull
    private UUID id;
    @NotNull
    private StudentProfileResponse studentProfile;
    @NotNull
    private BigDecimal transactionAmount;
    @NotNull
    private Currency transactionCurrency;
    private PaymentStatus paymentStatus;

    @Nullable
    private ConsultationSessionResponse session;
    @Nullable
    private CourseEventResponse event;


    @Data
    @FieldNameConstants
    @Schema
    @Validated
    public static class StudentProfileResponse {
        @NotNull
        private UUID id;
        @NotNull
        private I18n firstNameI18n;
        @NotNull
        private I18n lastNameI18n;
    }

    @Data
    @FieldNameConstants
    @Schema
    @Validated
    public static class ConsultationSessionResponse{
        private UUID id;
        private EducatorProfileSimpleResponse educatorProfile;
        private EducatorSessionNoteCommentResponse comment;
        private OffsetDateTime startTimeUtc;
        private OffsetDateTime endTimeUtc;
        private String meetingUrl;
        private String meetingId;
        private BookingStatus bookingStatus;
    }


    @Data
    @FieldNameConstants
    @Schema
    @Validated
    public static class CourseEventResponse {
        @NotNull
        private UUID id;
        @NotNull
        private I18n titleI18n;
        @NotNull
        private I18n descriptionI18n;
        @NotNull
        private String eventType;
        @NotNull
        private Integer maxNumberOfStudent;
        @NotNull
        private String posterImage;
        @NotNull
        private EducatorProfileSimpleResponse educator;
        @NotNull
        private I18n agendaI18n;
        @NotNull
        private String eventStatus;
        @NotNull
        private List<EventScheduleTime> eventScheduleTimes;
    }

    @Data
    @FieldNameConstants
    public static class EventScheduleTime{
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }


}
