package unid.monoServerApp.database.table.ecaProfile;

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
import unid.jooqMono.model.tables.StudentEcaProfileMapTable;
import unid.jooqMono.model.tables.daos.StudentEcaProfileMapDao;
import unid.jooqMono.model.tables.daos.StudentPaymentTransactionDao;
import unid.jooqMono.model.tables.pojos.EcaProfileSectionPojo;
import unid.jooqMono.model.tables.pojos.StudentEcaProfileMapPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;

import java.io.Serializable;
import java.util.List;

@Component
public class DbStudentEcaProfileMap extends Db<StudentEcaProfileMapTable, StudentEcaProfileMapDao> {

    @Autowired
    public DbStudentEcaProfileMap(
            DSLContext dslContext
    ) {
        super(dslContext, Public.PUBLIC.STUDENT_ECA_PROFILE_MAP, new StudentEcaProfileMapDao(dslContext.configuration()));
    }


    @Override
    public SelectJoinStep<Record> getQuery(StudentEcaProfileMapTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(StudentEcaProfileMapTable table) {
        return null;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentEcaProfileMapPojo implements Serializable {
        private DbI18N.Result section;
        private Integer score;
    }
}
