package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.EventType;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Validated
@Schema
public class PromotionEventPayload {
    @NotNull
    private UUID id;
    @NotNull
    private I18n titleI18n;
    @NotNull
    private EventType eventType;

    private List<ScheduleTime> times;

    @JsonIgnore
    private Integer total;

    @Data
    @Schema
    @FieldNameConstants
    public static class ScheduleTime{
        private OffsetDateTime startTimeUtc;
        private OffsetDateTime endTimeUtc;
    }
}
