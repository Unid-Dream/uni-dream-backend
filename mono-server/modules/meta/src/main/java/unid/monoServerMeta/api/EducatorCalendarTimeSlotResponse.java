package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorCalendarTimeSlotResponse implements Serializable {
    private UUID id;
    @NotNull
    @Valid
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime startDateTimeUtc;
    @NotNull
    @Valid
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime endDateTimeUtc;



}
