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
import unid.jooqMono.model.tables.EducatorSessionNoteItemTable;
import unid.jooqMono.model.tables.daos.EducatorSessionNoteItemDao;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteItemPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

@Component
public class DbEducatorSessionNoteItem extends Db<EducatorSessionNoteItemTable, EducatorSessionNoteItemDao> {
    private final DbI18N dbI18N;

    @Autowired
    public DbEducatorSessionNoteItem(
            DSLContext dslContext,
            DbI18N dbI18N
    ) {
        super(dslContext, Public.PUBLIC.EDUCATOR_SESSION_NOTE_ITEM, new EducatorSessionNoteItemDao(dslContext.configuration()));
        this.dbI18N = dbI18N;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorSessionNoteItemTable alias) {
        var title = dbI18N.getTable().as(combineAlias(alias, dbI18N.getTable().as("a")));
        var titleQ = dbI18N.getQuery(title);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                titleQ.where(alias.TITLE_I18N_ID.eq(title.ID))
                        ).as(Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(EducatorSessionNoteItemTable table) {
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
    public static final class Result extends EducatorSessionNoteItemPojo {
        private DbI18N.Result titleI18n;
        private String comment;
    }
}
