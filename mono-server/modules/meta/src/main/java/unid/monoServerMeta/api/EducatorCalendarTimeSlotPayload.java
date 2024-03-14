package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorCalendarTimeSlotPayload implements Serializable {

    @NotNull
    @Valid
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String startDateTimeUtc;
    @NotNull
    @Valid
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String endDateTimeUtc;



}
