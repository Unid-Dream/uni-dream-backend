package unid.monoServerApp.database.table.ecaProfile;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.EcaCourseTable;
import unid.jooqMono.model.tables.I18nTable;
import unid.jooqMono.model.tables.daos.EcaCourseDao;
import unid.jooqMono.model.tables.daos.I18nDao;
import unid.monoServerApp.database.Db;

@Component
public class DbEcaCourse extends Db<EcaCourseTable, EcaCourseDao> {

    @Autowired
    public DbEcaCourse(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.ECA_COURSE, new EcaCourseDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(EcaCourseTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(EcaCourseTable table) {
        return null;
    }
}
