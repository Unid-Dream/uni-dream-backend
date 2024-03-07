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
import unid.jooqMono.model.tables.daos.AcademicMajorSubjectMapDao;
import unid.jooqMono.model.tables.pojos.AcademicMajorSubjectMapPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;

@Component
public class DbAcademicMajorSubjectMap extends Db<AcademicMajorSubjectMapTable, AcademicMajorSubjectMapDao> {
    private final DbI18N dbI18NQuery;
    private final DbTag dbTag;
    private final DbAcademicSubject dbAcademicSubject;
    private final DbAcademicMajor dbAcademicMajor;

    @Autowired
    public DbAcademicMajorSubjectMap(
            DSLContext dslContext,
            DbI18N dbI18NQuery,
            DbTag dbTag,
            DbAcademicSubject dbAcademicSubject,
            DbAcademicMajor dbAcademicMajor
    ) {
        super(dslContext, Public.PUBLIC.ACADEMIC_MAJOR_SUBJECT_MAP, new AcademicMajorSubjectMapDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
        this.dbTag = dbTag;
        this.dbAcademicSubject = dbAcademicSubject;
        this.dbAcademicMajor = dbAcademicMajor;
    }

    @Override
    public SelectJoinStep<Record> getQuery(AcademicMajorSubjectMapTable alias) {
        var subject = dbAcademicSubject.getTable().as(combineAlias(alias, dbAcademicSubject.getTable()));
        var subjectQ = dbAcademicSubject.getQuery(subject);
        var major = dbAcademicMajor.getTable().as(combineAlias(alias, dbAcademicMajor.getTable()));
        var majorQ = dbAcademicMajor.getQuery(major);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                subjectQ.where(alias.ACADEMIC_SUBJECT_ID.eq(subject.ID))
                        ).as(Result.Fields.academicSubject),
                        DSL.multiset(
                                majorQ.where(alias.ACADEMIC_MAJOR_ID.eq(major.ID))
                        ).as(Result.Fields.academicMajor)
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(AcademicMajorSubjectMapTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends AcademicMajorSubjectMapPojo implements Serializable {
        private DbAcademicMajor.Result academicMajor;
        private DbAcademicSubject.Result academicSubject;
    }
}
