package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.Pattern;
import unid.monoServerMeta.model.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class StudentScheduleResponse extends BaseResponse {
    @NotNull
    private UUID id;
    private I18n titleI18n;
    @NotNull
    private BigDecimal transactionAmount;
    @NotNull
    private Currency transactionCurrency;
    @NotNull
    private TransactionItem transactionItem;
    @Nullable
    private PaymentMethod paymentMethod;
    @NotNull
    private PaymentStatus paymentStatus;
    @NotNull
    @Valid
    private EducatorResponse educator;
    @NotNull
    @Valid
    private Calendar calendar;
    private LocalDateTime requestSubmissionTime;

    private LearningHubResponse learningHubResponse;

    @JsonProperty("isReadyToPay")
    @NotNull
    public Boolean isReadyToPay() {
        return PaymentStatus.PENDING.equals(paymentStatus)
                && Optional.ofNullable(calendar)
                .map(c ->
                        BookingStatus.ACCEPTED.equals(c.getBookingStatus())
                ).orElse(false);
    }

    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @Validated
    public static class Calendar {
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = unid.monoServerMeta.Pattern.DATE)
        @Schema(implementation = String.class, pattern = unid.monoServerMeta.Pattern.DATE)
        private LocalDate date;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Pattern.TIME_CALENDAR)
        @Schema(implementation = String.class, pattern = unid.monoServerMeta.Pattern.TIME_CALENDAR)
        private LocalTime hourStart;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = unid.monoServerMeta.Pattern.TIME_CALENDAR)
        @Schema(implementation = String.class, pattern = unid.monoServerMeta.Pattern.TIME_CALENDAR)
        private LocalTime hourEnd;
        @NotNull
        private BookingStatus bookingStatus;
        @Nullable
        private String meetingUrl;
        @NotNull
        private UUID educatorProfileId;
    }
}
