package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducatorCalendarSimpleResponse implements Serializable {
    private List<TimeSlotPayload> slots;

    @Data
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TimeSlotPayload{
        private UUID id;
        private List<StudentProfileSimplePayload> studentProfiles;
        @NotNull
        @Valid
        private OffsetDateTime startDateTimeUtc;
        @NotNull
        @Valid
        private OffsetDateTime endDateTimeUtc;
    }




    @Data
    @FieldNameConstants
    public static class StudentProfileSimplePayload{
        private UUID id;
        private I18n lastNameI18n;
        private I18n firstNameI18n;
        private Country country;
        private SecondarySchool secondarySchool;
        private SecondarySchoolEducationLevel secondarySchoolEducationLevel;
    }


    @Data
    @FieldNameConstants
    public static class Country{
        private UUID id;
        private I18n nameI18n;
    }

    @Data
    @FieldNameConstants
    public static class SecondarySchool{
        private UUID id;
        private I18n nameI18n;
    }

    @Data
    @FieldNameConstants
    public static class SecondarySchoolEducationLevel{
        private String grade;
    }


}
