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
import unid.jooqMono.model.tables.daos.ExpertiseAcademicMajorMapDao;
import unid.jooqMono.model.tables.pojos.ExpertiseAcademicMajorMapPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;

import java.io.Serializable;

@Component
public class DbExpertiseMajorMap extends Db<ExpertiseAcademicMajorMapTable, ExpertiseAcademicMajorMapDao> {
    private final DbExpertise dbExpertise;
    private final DbAcademicMajor dbAcademicMajor;

    @Autowired
    public DbExpertiseMajorMap(
            DSLContext dslContext,
            DbExpertise dbExpertise,
            DbAcademicMajor dbAcademicMajor
    ) {
        super(dslContext, Public.PUBLIC.EXPERTISE_ACADEMIC_MAJOR_MAP, new ExpertiseAcademicMajorMapDao(dslContext.configuration()));
        this.dbExpertise = dbExpertise;
        this.dbAcademicMajor = dbAcademicMajor;
    }

    @Override
    public SelectJoinStep<Record> getQuery(ExpertiseAcademicMajorMapTable alias) {
        var expertise = dbExpertise.getTable().as(combineAlias(alias, dbExpertise.getTable()));
        var expertiseQ = dbExpertise.getQuery(expertise);
        var major = dbAcademicMajor.getTable().as(combineAlias(alias, dbAcademicMajor.getTable()));
        var majorQ = dbAcademicMajor.getQuery(major);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                expertiseQ.where(alias.EXPERTISE_ID.eq(expertise.ID))
                        ).as(Result.Fields.expertise),
                        DSL.multiset(
                                majorQ.where(alias.ACADEMIC_MAJOR_ID.eq(major.ID))
                        ).as(Result.Fields.academicMajor)
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(ExpertiseAcademicMajorMapTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends ExpertiseAcademicMajorMapPojo implements Serializable {
        private DbExpertise.Result expertise;
        private DbAcademicMajor.Result academicMajor;
    }
}
