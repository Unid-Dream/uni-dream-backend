package unid.monoServerMeta.api;

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
public class EducatorAvailableScheduleResponse {

    //时间槽
    private List<EducatorCalendarTimeSlot> slots;
    //槽状态
    private BookingStatus status;
    //如果是预定状态,则展示学生的相关信息
    private List<StudentProfile> studentProfiles;

    @Data
    @FieldNameConstants
    public static class StudentProfile{
        private UUID studentProfileId;
        private I18n nameI18n;
    }
}
