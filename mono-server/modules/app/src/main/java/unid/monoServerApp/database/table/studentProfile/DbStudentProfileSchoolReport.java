package unid.monoServerApp.database.table.studentProfile;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.StudentProfileSchoolReportTable;
import unid.jooqMono.model.tables.daos.StudentProfileSchoolReportDao;
import unid.jooqMono.model.tables.pojos.StudentProfileSchoolReportPojo;
import unid.monoServerApp.database.Db;

@Component
public class DbStudentProfileSchoolReport extends Db<StudentProfileSchoolReportTable, StudentProfileSchoolReportDao> {

    @Autowired
    public DbStudentProfileSchoolReport(
            DSLContext dslContext
    ) {
        super(dslContext, Public.PUBLIC.STUDENT_PROFILE_SCHOOL_REPORT, new StudentProfileSchoolReportDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(StudentProfileSchoolReportTable alias) {
        @Cleanup var q = dsl
                .select(
                        alias.asterisk()
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(StudentProfileSchoolReportTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
//    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentProfileSchoolReportPojo {
    }
}
