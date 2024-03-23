package unid.monoServerApp.database.table.course;

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
import unid.jooqMono.model.tables.EventScheduleTimeTable;
import unid.jooqMono.model.tables.EventTable;
import unid.jooqMono.model.tables.daos.EventDao;
import unid.jooqMono.model.tables.daos.EventScheduleTimeDao;
import unid.jooqMono.model.tables.pojos.EventPojo;
import unid.jooqMono.model.tables.pojos.EventScheduleTimePojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbEventScheduleTime extends Db<EventScheduleTimeTable, EventScheduleTimeDao> {


    @Autowired
    public DbEventScheduleTime(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.EVENT_SCHEDULE_TIME, new EventScheduleTimeDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(EventScheduleTimeTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(EventScheduleTimeTable table) {
        return null;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EventScheduleTimePojo implements Serializable {
    }
}
