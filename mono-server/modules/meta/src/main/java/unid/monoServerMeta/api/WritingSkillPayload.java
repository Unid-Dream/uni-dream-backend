package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.ReviewStatus;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class WritingSkillPayload {

    @NotNull
    private UUID id;
    private StudentProfile studentProfile;
    private OffsetDateTime submissionTime;
    private ReviewStatus status;
    private Integer total;


    @Data
    @FieldNameConstants
    public static class StudentProfile {
        @NotNull
        private UUID id;
        @NotNull
        private I18n firstNameI18n;
        @NotNull
        private I18n lastNameI18n;
    }

}
