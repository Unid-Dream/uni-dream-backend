package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.CourseEventType;
import unid.monoServerMeta.model.EventType;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Validated
@Schema
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CourseEventPayload {
    @NotNull
    private UUID id;
    @NotNull
    private I18n titleI18n;
    @NotNull
    private CourseEventType eventType;
    private String posterImage;
    private I18n descriptionI18n;
    private Integer maxNumberOfStudent;
    private BigDecimal price;
    private UUID educatorProfileId;
    private I18n agendaI18n;
    private List<UUID> academicMajorIds;
    private List<Duration> duration;

    @JsonIgnore
    private Integer total;

    @Data
    @Schema
    @FieldNameConstants
    public static class Duration{
        private OffsetDateTime startTimeUtc;
        private OffsetDateTime endTimeUtc;
    }
}
