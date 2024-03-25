package unid.monoServerApp.database.table.milestone;

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
import unid.jooqMono.model.tables.StudentMilestoneQuestionnaireTable;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;
import unid.jooqMono.model.tables.daos.StudentMilestoneQuestionnaireDao;
import unid.jooqMono.model.tables.daos.StudentPaymentTransactionDao;
import unid.jooqMono.model.tables.pojos.OpportunityPojo;
import unid.jooqMono.model.tables.pojos.StudentMilestoneQuestionnairePojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbMilestoneQuestionnaire extends Db<StudentMilestoneQuestionnaireTable, StudentMilestoneQuestionnaireDao> {

    @Autowired
    public DbMilestoneQuestionnaire(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.STUDENT_MILESTONE_QUESTIONNAIRE, new StudentMilestoneQuestionnaireDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(StudentMilestoneQuestionnaireTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(StudentMilestoneQuestionnaireTable table) {
        return null;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentMilestoneQuestionnairePojo implements Serializable {
        private DbI18N.Result questionI18n;
    }
}
