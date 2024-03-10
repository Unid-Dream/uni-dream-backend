package unid.monoServerApp.database.table.academicSubject;

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
import unid.jooqMono.model.tables.AcademicSubjectResourceTable;
import unid.jooqMono.model.tables.daos.AcademicSubjectResourceDao;
import unid.jooqMono.model.tables.pojos.AcademicSubjectResourcePojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbAcademicSubjectResource extends Db<AcademicSubjectResourceTable, AcademicSubjectResourceDao> {
    private final DbI18N dbI18NQuery;

    @Autowired
    public DbAcademicSubjectResource(
            DSLContext dslContext,
            DbI18N dbI18NQuery
    ) {
        super(dslContext, Public.PUBLIC.ACADEMIC_SUBJECT_RESOURCE, new AcademicSubjectResourceDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
    }

    @Override
    public SelectJoinStep<Record> getQuery(AcademicSubjectResourceTable alias) {
        var titleI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable()));
        var titleI18nQ = dbI18NQuery.getQuery(titleI18n);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                titleI18nQ.where(alias.TITLE_I18N_ID.eq(titleI18n.ID))
                        ).as(Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(AcademicSubjectResourceTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends AcademicSubjectResourcePojo implements Serializable {
        private DbI18N.Result titleI18n;

        //new property
        private DbI18N.Result authorI18n;

    }
}
