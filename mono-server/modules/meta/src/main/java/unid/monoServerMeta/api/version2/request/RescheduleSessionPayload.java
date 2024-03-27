package unid.monoServerMeta.api.version2.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@FieldNameConstants
public class RescheduleSessionPayload {
    private UUID id;
    private StudentProfile student;
    private EducatorProfile educator;
    private OffsetDateTime startTimeUtc;
    private OffsetDateTime endTimeUtc;
    private String status;

    @JsonIgnore
    private Integer total;

    @Data
    @FieldNameConstants
    public static class StudentProfile{
        private I18n firstNameI18n;
        private I18n lastNameI18n;
    }

    @Data
    @FieldNameConstants
    public static class EducatorProfile{
        private I18n firstNameI18n;
        private I18n lastNameI18n;
    }
}
