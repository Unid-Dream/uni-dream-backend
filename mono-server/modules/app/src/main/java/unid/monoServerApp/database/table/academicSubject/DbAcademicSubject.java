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
import unid.jooqMono.model.tables.AcademicSubjectTable;
import unid.jooqMono.model.tables.daos.AcademicSubjectDao;
import unid.jooqMono.model.tables.pojos.AcademicSubjectPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;
import java.util.List;

@Component
public class DbAcademicSubject extends Db<AcademicSubjectTable, AcademicSubjectDao> {
    private final DbI18N dbI18NQuery;
    private final DbTag dbTag;
    private final DbAcademicSubjectResource dbAcademicSubjectResource;

    @Autowired
    public DbAcademicSubject(
            DSLContext dslContext,
            DbI18N dbI18NQuery,
            DbTag dbTag,
            DbAcademicSubjectResource dbAcademicSubjectResource
    ) {
        super(dslContext, Public.PUBLIC.ACADEMIC_SUBJECT, new AcademicSubjectDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
        this.dbTag = dbTag;
        this.dbAcademicSubjectResource = dbAcademicSubjectResource;
    }

    @Override
    public SelectJoinStep<Record> getQuery(AcademicSubjectTable alias) {
        var titleI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("titleI18n")));
        var titleI18nQ = dbI18NQuery.getQuery(titleI18n);
        var descI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("descI18n")));
        var descI18nQ = dbI18NQuery.getQuery(descI18n);
        var mdI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("mdI18n")));
        var mdI18nQ = dbI18NQuery.getQuery(mdI18n);
        var phdI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("phdI18n")));
        var phdI18nQ = dbI18NQuery.getQuery(phdI18n);
        var tag = dbTag.getTable().as(combineAlias(alias, dbTag.getTable()));
        var tagQ = dbTag.getQuery(tag);
        var resource = dbAcademicSubjectResource.getTable().as(combineAlias(alias, dbAcademicSubjectResource.getTable()));
        var resourceQ = dbAcademicSubjectResource.getQuery(resource);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                titleI18nQ.where(alias.TITLE_I18N_ID.eq(titleI18n.ID))
                        ).as(Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                descI18nQ.where(alias.DESCRIPTION_I18N_ID.eq(descI18n.ID))
                        ).as(Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                mdI18nQ.where(alias.DESCRIPTION_MASTER_DEGREE_I18N_ID.eq(mdI18n.ID))
                        ).as(Result.Fields.descriptionMasterDegreeI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                phdI18nQ.where(alias.DESCRIPTION_PHD_I18N_ID.eq(phdI18n.ID))
                        ).as(Result.Fields.descriptionPhdI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                tagQ.where(alias.TAG_ID.eq(tag.ID))
                        ).as(Result.Fields.tag).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                resourceQ.where(alias.ID.eq(resource.ACADEMIC_SUBJECT_ID))
                        ).as(Result.Fields.resources)
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(AcademicSubjectTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends AcademicSubjectPojo implements Serializable {
        private DbI18N.Result titleI18n;
        private DbI18N.Result descriptionI18n;
        private DbI18N.Result descriptionMasterDegreeI18n;
        private DbI18N.Result descriptionPhdI18n;
        private DbTag.Result tag;
        private List<DbAcademicSubjectResource.Result> resources;


        //new property
        private List<DbAcademicSubjectResource.Result> books;
        private List<DbAcademicSubjectResource.Result> videos;
        private List<DbAcademicSubjectResource.Result> podcasts;

        private List<DbI18N.Result> answers;

    }
}
