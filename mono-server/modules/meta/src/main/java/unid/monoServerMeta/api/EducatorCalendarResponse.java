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
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.BookingStatus;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorCalendarResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = unid.monoServerMeta.Pattern.DATE)
    @Schema(implementation = String.class, pattern = Pattern.DATE)
    @JsonProperty("dateUtc")
    private LocalDate date;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = unid.monoServerMeta.Pattern.TIME_CALENDAR)
    @Schema(implementation = String.class, pattern = Pattern.TIME_CALENDAR)
    @JsonProperty("hourStartUtc")
    private LocalTime hourStart;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Pattern.TIME_CALENDAR)
    @Schema(implementation = String.class, pattern = Pattern.TIME_CALENDAR)
    @JsonProperty("hourEndUtc")
    private LocalTime hourEnd;
    @Nullable
    @Valid
    private StudentProfileResponse bookedByStudentProfile;
    @NotNull
    private BookingStatus bookingStatus;
}
