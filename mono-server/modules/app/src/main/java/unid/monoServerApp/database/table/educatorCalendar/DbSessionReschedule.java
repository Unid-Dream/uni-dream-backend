package unid.monoServerApp.database.table.educatorCalendar;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.LanguageTable;
import unid.jooqMono.model.tables.SessionRescheduleTable;
import unid.jooqMono.model.tables.daos.LanguageDao;
import unid.jooqMono.model.tables.daos.SessionRescheduleDao;
import unid.monoServerApp.database.Db;

@Component
public class DbSessionReschedule extends Db<SessionRescheduleTable, SessionRescheduleDao> {

    public DbSessionReschedule(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.SESSION_RESCHEDULE, new SessionRescheduleDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(SessionRescheduleTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(SessionRescheduleTable table) {
        return null;
    }
}
