package unid.monoServerApp.database.table.educationLevel;

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
import unid.jooqMono.model.tables.EducationLevelTable;
import unid.jooqMono.model.tables.daos.EducationLevelDao;
import unid.jooqMono.model.tables.pojos.EducationLevelPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbEducationLevel extends Db<EducationLevelTable, EducationLevelDao> {
    private final DbI18N dbI18NQuery;

    @Autowired
    public DbEducationLevel(
            DSLContext dslContext,
            DbI18N dbI18NQuery
    ) {
        super(dslContext, Public.PUBLIC.EDUCATION_LEVEL, new EducationLevelDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducationLevelTable alias) {
        var i18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable()));
        var i18nQ = dbI18NQuery.getQuery(i18n);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                i18nQ.where(alias.DESCRIPTION_I18N_ID.eq(i18n.ID))
                        ).as(Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(EducationLevelTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EducationLevelPojo implements Serializable {
        private DbI18N.Result descriptionI18n;
    }
}
