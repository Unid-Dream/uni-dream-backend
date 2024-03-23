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
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;
import unid.jooqMono.model.tables.StudentUploadedSupervisorReviewTable;
import unid.jooqMono.model.tables.daos.StudentPaymentTransactionDao;
import unid.jooqMono.model.tables.daos.StudentUploadedInterviewDao;
import unid.jooqMono.model.tables.daos.StudentUploadedSupervisorReviewDao;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedSupervisorReviewPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class DbStudentUploadedSupervisorReview extends Db<StudentUploadedSupervisorReviewTable, StudentUploadedSupervisorReviewDao> {


    public DbStudentUploadedSupervisorReview(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.STUDENT_UPLOADED_SUPERVISOR_REVIEW, new StudentUploadedSupervisorReviewDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(StudentUploadedSupervisorReviewTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(StudentUploadedSupervisorReviewTable table) {
        return null;
    }

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
