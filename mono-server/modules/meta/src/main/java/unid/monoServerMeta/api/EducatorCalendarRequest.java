package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.Pattern;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorCalendarRequest implements Serializable {

    private List<TimeSlotPayload> slots;


    @Data
    @FieldNameConstants
    public static class TimeSlotPayload {
        private String startDateTimeUtc;
        private String endDateTimeUtc;
    }
}
