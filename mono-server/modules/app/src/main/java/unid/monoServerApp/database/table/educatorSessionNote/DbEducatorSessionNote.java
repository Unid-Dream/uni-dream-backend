package unid.monoServerApp.database.table.educatorSessionNote;

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
import unid.jooqMono.model.tables.EducatorSessionNoteTable;
import unid.jooqMono.model.tables.daos.EducatorSessionNoteDao;
import unid.jooqMono.model.tables.pojos.EducatorSessionNotePojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.util.List;

@Component
public class DbEducatorSessionNote extends Db<EducatorSessionNoteTable, EducatorSessionNoteDao> {
    private final DbI18N dbI18N;
    private final DbEducatorSessionNoteItem dbEducatorSessionNoteItem;

    @Autowired
    public DbEducatorSessionNote(
            DSLContext dslContext,
            DbI18N dbI18N,
            DbEducatorSessionNoteItem dbEducatorSessionNoteItem
    ) {
        super(dslContext, Public.PUBLIC.EDUCATOR_SESSION_NOTE, new EducatorSessionNoteDao(dslContext.configuration()));
        this.dbI18N = dbI18N;
        this.dbEducatorSessionNoteItem = dbEducatorSessionNoteItem;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorSessionNoteTable alias) {
        var title = dbI18N.getTable().as(combineAlias(alias, dbI18N.getTable().as("a")));
        var titleQ = dbI18N.getQuery(title);
        var subtitle = dbI18N.getTable().as(combineAlias(alias, dbI18N.getTable().as("b")));
        var subtitleQ = dbI18N.getQuery(subtitle);
        var desc = dbI18N.getTable().as(combineAlias(alias, dbI18N.getTable().as("c")));
        var descQ = dbI18N.getQuery(desc);
        var item = dbEducatorSessionNoteItem.getTable().as(combineAlias(alias, dbEducatorSessionNoteItem.getTable()));
        var itemQ = dbEducatorSessionNoteItem.getQuery(item);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                titleQ.where(alias.TITLE_I18N_ID.eq(title.ID))
                        ).as(Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                subtitleQ.where(alias.SUB_TITLE_I18N_ID.eq(subtitle.ID))
                        ).as(Result.Fields.subTitleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                descQ.where(alias.DESCRIPTION_I18N_ID.eq(desc.ID))
                        ).as(Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                itemQ.where(item.EDUCATOR_SESSION_NOTE_ID.eq(alias.ID))
                        ).as(Result.Fields.items)
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(EducatorSessionNoteTable table) {
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
    public static final class Result extends EducatorSessionNotePojo {
        private DbI18N.Result titleI18n;
        private DbI18N.Result subTitleI18n;
        private DbI18N.Result descriptionI18n;
        private List<DbEducatorSessionNoteItem.Result> items;
    }
}
