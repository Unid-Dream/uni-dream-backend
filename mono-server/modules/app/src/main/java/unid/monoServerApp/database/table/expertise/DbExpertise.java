package unid.monoServerApp.database.table.expertise;

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
import unid.jooqMono.model.tables.ExpertiseAcademicMajorMapTable;
import unid.jooqMono.model.tables.ExpertiseTable;
import unid.jooqMono.model.tables.daos.ExpertiseDao;
import unid.jooqMono.model.tables.pojos.ExpertisePojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;
import java.util.List;

@Component
public class DbExpertise extends Db<ExpertiseTable, ExpertiseDao> {
    private final DbI18N dbI18NQuery;
    private final DbTag dbTag;
    private final DbAcademicMajor dbAcademicMajor;

    @Autowired
    public DbExpertise(
            DSLContext dslContext,
            DbI18N dbI18NQuery,
            DbTag dbTag,
            DbAcademicMajor dbAcademicMajor
    ) {
        super(dslContext, Public.PUBLIC.EXPERTISE, new ExpertiseDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
        this.dbTag = dbTag;
        this.dbAcademicMajor = dbAcademicMajor;
    }

    @Override
    public SelectJoinStep<Record> getQuery(ExpertiseTable alias) {
        var i18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable()));
        var i18nQ = dbI18NQuery.getQuery(i18n);
        var tag = dbTag.getTable().as(combineAlias(alias, dbTag.getTable()));
        var tagQ = dbTag.getQuery(tag);
        var major = dbAcademicMajor.getTable().as(combineAlias(alias, dbAcademicMajor.getTable()));
        var majorQ = dbAcademicMajor.getQuery(major);
        var majorMapTable = ExpertiseAcademicMajorMapTable.EXPERTISE_ACADEMIC_MAJOR_MAP;
        var majorMap = majorMapTable.as(combineAlias(alias, majorMapTable));
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                i18nQ.where(alias.DESCRIPTION_I18N_ID.eq(i18n.ID))
                        ).as(Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                tagQ.where(alias.TAG_ID.eq(tag.ID))
                        ).as(Result.Fields.tag).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                majorQ.join(majorMap)
                                        .on(
                                                majorMap.ACADEMIC_MAJOR_ID.eq(major.ID)
                                                        .and(majorMap.EXPERTISE_ID.eq(alias.ID))
                                        )
                        ).as(Result.Fields.majors)
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(ExpertiseTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends ExpertisePojo implements Serializable {
        private DbI18N.Result descriptionI18n;
        private DbTag.Result tag;
        private List<DbAcademicMajor.Result> majors;
    }
}
