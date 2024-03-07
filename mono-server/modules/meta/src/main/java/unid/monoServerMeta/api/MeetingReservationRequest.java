package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.Pattern;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class MeetingReservationRequest {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Pattern.DATE)
    @Schema(implementation = String.class, pattern = Pattern.DATE)
    @JsonProperty("dateUtc")
    private LocalDate date;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Pattern.TIME_CALENDAR)
    @Schema(implementation = String.class, pattern = Pattern.TIME_CALENDAR)
    @JsonProperty("hourStartUtc")
    private LocalTime hourStart;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Pattern.TIME_CALENDAR)
    @Schema(implementation = String.class, pattern = Pattern.TIME_CALENDAR)
    @JsonProperty("hourEndUtc")
    private LocalTime hourEnd;
}
