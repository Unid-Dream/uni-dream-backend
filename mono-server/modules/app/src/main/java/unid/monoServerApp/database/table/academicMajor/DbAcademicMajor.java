package unid.monoServerApp.database.table.academicMajor;

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
import unid.jooqMono.model.tables.AcademicMajorSubjectMapTable;
import unid.jooqMono.model.tables.AcademicMajorTable;
import unid.jooqMono.model.tables.daos.AcademicMajorDao;
import unid.jooqMono.model.tables.pojos.AcademicMajorPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;
import java.util.List;

@Component
public class DbAcademicMajor extends Db<AcademicMajorTable, AcademicMajorDao> {
    private final DbI18N dbI18NQuery;
    private final DbTag dbTag;
    private final DbAcademicSubject dbAcademicSubject;

    @Autowired
    public DbAcademicMajor(
            DSLContext dslContext,
            DbI18N dbI18NQuery,
            DbTag dbTag,
            DbAcademicSubject dbAcademicSubject
    ) {
        super(dslContext, Public.PUBLIC.ACADEMIC_MAJOR, new AcademicMajorDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
        this.dbTag = dbTag;
        this.dbAcademicSubject = dbAcademicSubject;
    }

    @Override
    public SelectJoinStep<Record> getQuery(AcademicMajorTable alias) {
        var titleI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("titleI18n")));
        var titleI18nQ = dbI18NQuery.getQuery(titleI18n);
        var descI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("descI18n")));
        var descI18nQ = dbI18NQuery.getQuery(descI18n);
        var tag = dbTag.getTable().as(combineAlias(alias, dbTag.getTable()));
        var tagQ = dbTag.getQuery(tag);
        var subjectMapTable = AcademicMajorSubjectMapTable.ACADEMIC_MAJOR_SUBJECT_MAP;
        var subjectMap = subjectMapTable.as(combineAlias(alias, subjectMapTable));
        var subject = dbAcademicSubject.getTable().as(combineAlias(alias, dbAcademicSubject.getTable()));
        var subjectQ = dbAcademicSubject.getQuery(subject);
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
                                tagQ.where(alias.TAG_ID.eq(tag.ID))
                        ).as(Result.Fields.tag).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                subjectQ.join(subjectMap)
                                        .on(
                                                subjectMap.ACADEMIC_SUBJECT_ID.eq(subject.ID)
                                                        .and(subjectMap.ACADEMIC_MAJOR_ID.eq(alias.ID))
                                        )
                        ).as(Result.Fields.subjects)
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(AcademicMajorTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends AcademicMajorPojo implements Serializable {
        private DbI18N.Result titleI18n;
        private DbI18N.Result descriptionI18n;
        private DbTag.Result tag;
        private List<DbAcademicSubject.Result> subjects;
    }
}
