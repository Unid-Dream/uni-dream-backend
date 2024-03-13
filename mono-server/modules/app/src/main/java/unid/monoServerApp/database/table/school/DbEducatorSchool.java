package unid.monoServerApp.database.table.school;

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
import unid.jooqMono.model.tables.EducatorSchoolTable;
import unid.jooqMono.model.tables.daos.EducatorSchoolDao;
import unid.jooqMono.model.tables.daos.SchoolDao;
import unid.jooqMono.model.tables.pojos.EducatorSchoolPojo;
import unid.jooqMono.model.tables.pojos.SchoolPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;

@Component
public class DbEducatorSchool extends Db<EducatorSchoolTable, EducatorSchoolDao> {

    @Autowired
    public DbEducatorSchool(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.EDUCATOR_SCHOOL,  new EducatorSchoolDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorSchoolTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(EducatorSchoolTable table) {
        return null;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EducatorSchoolPojo implements Serializable {
    }
}
