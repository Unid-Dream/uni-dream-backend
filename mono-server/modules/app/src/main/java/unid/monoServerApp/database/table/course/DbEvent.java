package unid.monoServerApp.database.table.course;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.CountryTable;
import unid.jooqMono.model.tables.EventTable;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;
import unid.jooqMono.model.tables.daos.CountryDao;
import unid.jooqMono.model.tables.daos.EventDao;
import unid.jooqMono.model.tables.pojos.CountryPojo;
import unid.jooqMono.model.tables.pojos.EventPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;
import java.util.List;

import static unid.jooqMono.model.Tables.*;

@Component
public class DbEvent extends Db<EventTable, EventDao> {
    private final DbEducatorProfile dbEducatorProfile;
    @Autowired
    public DbEvent(DSLContext dslContext,DbEducatorProfile dbEducatorProfile) {
        super(dslContext, Public.PUBLIC.EVENT, new EventDao(dslContext.configuration()));
        this.dbEducatorProfile = dbEducatorProfile;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EventTable alias) {
         return null;
    }


    public SelectJoinStep<Record> getSimpleQuery(EventTable alias){
        return DSL.select(
                        EVENT.asterisk(),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(EVENT.TITLE_I18N_ID))
                        ).as(SimpleResult.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(EVENT.DESCRIPTION_I18N_ID))
                        ).as(SimpleResult.Fields.descriptionI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(EVENT.AGENDA_I18N_ID))
                        ).as(SimpleResult.Fields.agendaI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(EVENT.AGENDA_I18N_ID))
                        ).as(SimpleResult.Fields.agendaI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                        DSL.multiset(
                                DSL.select().from(EVENT_SCHEDULE_TIME).where(EVENT_SCHEDULE_TIME.REF_EVENT_ID.eq(EVENT.ID))
                        ).as(SimpleResult.Fields.eventScheduleTimes).convertFrom(r->r.isEmpty()?null:r.into(DbEventScheduleTime.Result.class)),
                        DSL.multiset(
                                dbEducatorProfile.getSimpleQuery(dbEducatorProfile.getTable())
                                        .where(dbEducatorProfile.getTable().ID.eq(alias.EDUCATOR_PROFILE_ID))
                        ).as(SimpleResult.Fields.educator).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbEducatorProfile.SimpleResult.class))
                 )
                .from(
                        alias
                );
    }

    @Override
    public Condition validateCondition(EventTable table) {
        return null;
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class SimpleResult extends EventPojo implements Serializable {
        private DbI18N.Result titleI18n;
        private DbI18N.Result descriptionI18n;
        private DbI18N.Result agendaI18n;
        private DbAcademicMajor.Result academic;
        private DbEducatorProfile.SimpleResult educator;
        private List<DbEventScheduleTime.Result> eventScheduleTimes;
    }
}
