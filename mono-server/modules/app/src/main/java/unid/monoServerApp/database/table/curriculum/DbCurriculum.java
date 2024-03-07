package unid.monoServerApp.database.table.curriculum;

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
import unid.jooqMono.model.tables.CurriculumTable;
import unid.jooqMono.model.tables.daos.CurriculumDao;
import unid.jooqMono.model.tables.pojos.CurriculumPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;

@Component
public class DbCurriculum extends Db<CurriculumTable, CurriculumDao> {
    private final DbI18N dbI18NQuery;
    private final DbTag dbTag;

    @Autowired
    public DbCurriculum(
            DSLContext dslContext,
            DbI18N dbI18NQuery,
            DbTag dbTag
    ) {
        super(dslContext, Public.PUBLIC.CURRICULUM, new CurriculumDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
        this.dbTag = dbTag;
    }

    @Override
    public SelectJoinStep<Record> getQuery(CurriculumTable alias) {
        var i18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable()));
        var i18nQ = dbI18NQuery.getQuery(i18n);
        var tag = dbTag.getTable().as(combineAlias(alias, dbTag.getTable()));
        var tagQ = dbTag.getQuery(tag);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                i18nQ.where(alias.NAME_I18N_ID.eq(i18n.ID))
                        ).as(Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                tagQ.where(alias.TAG_ID.eq(tag.ID))
                        ).as(Result.Fields.tag).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(CurriculumTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends CurriculumPojo implements Serializable {
        private DbI18N.Result nameI18n;
        private DbTag.Result tag;
    }
}
