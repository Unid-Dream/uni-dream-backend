package unid.monoServerApp.database.table.skill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedWritingPojo;
import unid.jooqMono.model.tables.pojos.WritingTopicPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.model.I18n;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DbStudentUploadedWriting {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentUploadedWritingPojo implements Serializable {
        private DbI18N.Result topicI18n;
        private BigDecimal price;

        private BigDecimal score;
        private DbStudentUploadedSupervisorReview.Result grammarAndExpression;
        private DbStudentUploadedSupervisorReview.Result content;
        private DbStudentUploadedSupervisorReview.Result composition;

    }
}
