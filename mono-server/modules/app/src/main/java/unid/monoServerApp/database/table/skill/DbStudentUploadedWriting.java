package unid.monoServerApp.database.table.skill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.SchoolIdentityTable;
import unid.jooqMono.model.tables.StudentUploadedWritingTable;
import unid.jooqMono.model.tables.daos.SchoolIdentityDao;
import unid.jooqMono.model.tables.daos.StudentUploadedWritingDao;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedWritingPojo;
import unid.jooqMono.model.tables.pojos.WritingTopicPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.model.I18n;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DbStudentUploadedWriting extends Db<StudentUploadedWritingTable, StudentUploadedWritingDao> {

    @Autowired
    public DbStudentUploadedWriting(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.STUDENT_UPLOADED_WRITING, new StudentUploadedWritingDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(StudentUploadedWritingTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(StudentUploadedWritingTable table) {
        return null;
    }

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
