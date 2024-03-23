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
import unid.jooqMono.model.tables.StudentUploadedInterviewTable;
import unid.jooqMono.model.tables.StudentUploadedWritingTable;
import unid.jooqMono.model.tables.daos.StudentUploadedInterviewDao;
import unid.jooqMono.model.tables.daos.StudentUploadedWritingDao;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedWritingPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class DbStudentUploadedInterview extends Db<StudentUploadedInterviewTable, StudentUploadedInterviewDao> {

    @Autowired
    public DbStudentUploadedInterview(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.STUDENT_UPLOADED_INTERVIEW, new StudentUploadedInterviewDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(StudentUploadedInterviewTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(StudentUploadedInterviewTable table) {
        return null;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentUploadedInterviewPojo implements Serializable {
        private DbI18N.Result topicI18n;
        private BigDecimal price;

        private BigDecimal score;
        private DbStudentUploadedSupervisorReview.Result content;
        private DbStudentUploadedSupervisorReview.Result clarity;
        private DbStudentUploadedSupervisorReview.Result charisma;

    }
}
