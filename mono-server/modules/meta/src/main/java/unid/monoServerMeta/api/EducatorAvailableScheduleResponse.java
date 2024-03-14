package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducatorAvailableScheduleResponse {
    //时间槽
    private List<EducatorCalendarTimeSlotResponse> slots;
}
