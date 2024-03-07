package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class LearningHubResponse {
    private UUID id ;
    private I18n titleI18n;
    private I18n descriptionI18n;
    private Integer enrollNumber;
    private List<EventTime> schedules;
    private AcademicMajorI18nResponse academic;
    private Long maxNumberOfStudent;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private BigDecimal price;
    private String posterImage;
    private EducatorResponse educator;
    private UUID educatorProfileId;
    private UUID academicMajorId;
    private String eventType;
    private String eventStatus;
    @JsonIgnore
    private String agenda;
    private I18n agendaI18n;


    @Data
    @FieldNameConstants
    public static class EventTime{
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }


}
