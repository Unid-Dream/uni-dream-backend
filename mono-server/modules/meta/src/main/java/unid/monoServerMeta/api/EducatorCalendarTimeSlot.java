package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorCalendarTimeSlot {

    @NotNull
    @JsonProperty("startDateTimeUtc")
    private OffsetDateTime startDateTimeUtc;
    @NotNull
    @JsonProperty("endDateTimeUtc")
    private OffsetDateTime endDateTimeUtc;



}
