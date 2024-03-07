package unid.monoServerApp.database.table.skill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedSupervisorReviewPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class DbStudentUploadedSupervisorReview {


    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentUploadedSupervisorReviewPojo implements Serializable {
    }
}
