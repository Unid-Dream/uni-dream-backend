package unid.monoServerApp.database.table.educatorSessionNote;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.EducatorSessionNoteMapTable;
import unid.jooqMono.model.tables.daos.EducatorSessionNoteMapDao;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteMapPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DbEducatorSessionNoteMap extends Db<EducatorSessionNoteMapTable, EducatorSessionNoteMapDao> {
    private final DbEducatorCalendar dbEducatorCalendar;
    private final DbEducatorSessionNote dbEducatorSessionNote;
    private final DbEducatorSessionNoteItem dbEducatorSessionNoteItem;

    @Autowired
    public DbEducatorSessionNoteMap(
            DSLContext dslContext,
            DbEducatorCalendar dbEducatorCalendar,
            DbEducatorSessionNote dbEducatorSessionNote,
            DbEducatorSessionNoteItem dbEducatorSessionNoteItem
    ) {
        super(dslContext, Public.PUBLIC.EDUCATOR_SESSION_NOTE_MAP, new EducatorSessionNoteMapDao(dslContext.configuration()));
        this.dbEducatorCalendar = dbEducatorCalendar;
        this.dbEducatorSessionNote = dbEducatorSessionNote;
        this.dbEducatorSessionNoteItem = dbEducatorSessionNoteItem;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorSessionNoteMapTable alias) {
        var calendar = dbEducatorCalendar.getTable().as(combineAlias(alias, dbEducatorCalendar.getTable().as("a")));
        var calendarQ = dbEducatorCalendar.getQuery(calendar);
        var item = dbEducatorSessionNoteItem.getTable().as(combineAlias(alias, dbEducatorSessionNoteItem.getTable().as("c")));
        var itemQ = dbEducatorSessionNoteItem.getQuery(item);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                calendarQ.where(alias.EDUCATOR_CALENDAR_ID.eq(calendar.ID))
                        ).as(Result.Fields.educatorCalendar).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                itemQ.where(alias.EDUCATOR_SESSION_NOTE_ITEM_ID.eq(item.ID))
                        ).as(Result.Fields.educatorSessionNoteItem).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }





    public List<Map> getMap(
            EducatorSessionNoteMapTable alias,
            Function<SelectJoinStep<Record>, SelectForStep<Record>> sql
    ) {
//        var calendar = dbEducatorCalendar.getTable().as(combineAlias(alias, dbEducatorCalendar.getTable().as("a")));
//        var calendarQ = dbEducatorCalendar.getQuery(calendar);
//        var note = dbEducatorSessionNote.getTable().as(combineAlias(alias, dbEducatorSessionNote.getTable().as("b")));
//        var noteQ = dbEducatorSessionNote.getQuery(note);
//        var item = dbEducatorSessionNoteItem.getTable().as(combineAlias(alias, dbEducatorSessionNoteItem.getTable().as("c")));
//        var itemQ = dbEducatorSessionNoteItem.getQuery(item);
//        var q = dsl
//                .select().from(alias)
//                .join(itemQ)
//                .on(item.EDUCATOR_SESSION_NOTE_ID.eq(alias.EDUCATOR_SESSION_NOTE_ITEM_ID))
//                .join(noteQ)
//                .on(item.EDUCATOR_SESSION_NOTE_ID.eq(note.ID));
//        var finalSql = sql.apply(q);
//        log.info("SQL: {}", finalSql.getSQL(ParamType.INLINED));

        var calendar = dbEducatorCalendar.getTable().as(combineAlias(alias, dbEducatorCalendar.getTable().as("a")));
        var calendarQ = dbEducatorCalendar.getQuery(calendar);
        var item = dbEducatorSessionNoteItem.getTable().as(combineAlias(alias, dbEducatorSessionNoteItem.getTable().as("c")));
        var itemQ = dbEducatorSessionNoteItem.getQuery(item);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                calendarQ.where(alias.EDUCATOR_CALENDAR_ID.eq(calendar.ID))
                        ).as(Map.Fields.educatorCalendar).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                itemQ.where(alias.EDUCATOR_SESSION_NOTE_ITEM_ID.eq(item.ID))
                        ).as(Map.Fields.items)
                ).from(alias);
        var finalSql = sql.apply(q);
        var result = finalSql
                .fetch()
                .intoGroups(calendar)
                .entrySet().stream().map(entry -> {
                    var map = new Map();
                    map.setEducatorCalendar(entry.getKey().into(DbEducatorCalendar.Result.class));
                    var val = entry.getValue();
//                    map.setEducatorSessionNote(
//                            val.into(note).get(0).into(DbEducatorSessionNote.Result.class)
//                    );
                    map.setItems(
                            val.into(item).map(r -> r.into(DbEducatorSessionNoteItem.Result.class))
                    );
                    return map;
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Condition validateCondition(EducatorSessionNoteMapTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EducatorSessionNoteMapPojo {
        private DbEducatorCalendar.Result educatorCalendar;
        private DbEducatorSessionNoteItem.Result educatorSessionNoteItem;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Map {
        private DbEducatorCalendar.Result educatorCalendar;
        private DbEducatorSessionNote.Result educatorSessionNote;
        private List<DbEducatorSessionNoteItem.Result> items;
    }
}
