//package unid.monoServerApp.database.table.educatorProfile;
//
//import lombok.*;
//import lombok.experimental.FieldNameConstants;
//import org.jooq.Condition;
//import org.jooq.DSLContext;
//import org.jooq.Record;
//import org.jooq.SelectJoinStep;
//import org.jooq.impl.DSL;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import unid.jooqMono.model.Public;
//import unid.jooqMono.model.tables.EducatorProfileExpertiseAcademicMapTable;
//import unid.jooqMono.model.tables.daos.EducatorProfileExpertiseAcademicMapDao;
//import unid.jooqMono.model.tables.pojos.EducatorProfileExpertiseAcademicMapPojo;
//import unid.monoServerApp.database.Db;
//import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
//import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
//import unid.monoServerApp.database.table.expertise.DbExpertise;
//import unid.monoServerApp.database.table.language.DbLanguage;
//
//@Component
//public class DbEducatorProfileExpertiseAcademicMapMap extends Db<EducatorProfileExpertiseAcademicMapTable, EducatorProfileExpertiseAcademicMapDao> {
//    private final DbExpertise dbExpertise;
//    private final DbAcademicMajor dbAcademicMajor;
//    private final DbAcademicSubject dbAcademicSubject;
//
//    @Autowired
//    public DbEducatorProfileExpertiseAcademicMapMap(
//            DSLContext dslContext,
//            DbExpertise dbExpertise,
//            DbAcademicMajor dbAcademicMajor,
//            DbAcademicSubject dbAcademicSubject
//    ) {
//        super(dslContext, Public.PUBLIC.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP, new EducatorProfileExpertiseAcademicMapDao(dslContext.configuration()));
//        this.dbExpertise = dbExpertise;
//        this.dbAcademicMajor = dbAcademicMajor;
//        this.dbAcademicSubject = dbAcademicSubject;
//    }
//
//    @Override
//    public SelectJoinStep<Record> getQuery(EducatorProfileExpertiseAcademicMapTable alias) {
//        var profile = dbEducatorProfile.getTable().as(combineAlias(alias, dbEducatorProfile.getTable()));
//        var profileQ = dbEducatorProfile.getQuery(profile);
//        var lang = dbLanguage.getTable().as(combineAlias(alias, dbLanguage.getTable()));
//        var langQ = dbLanguage.getQuery(lang);
//        @Cleanup var q = dsl
//                .select(
//                        alias.asterisk(),
//                        DSL.multiset(
//                                profileQ.where(alias.EDUCATOR_PROFILE_ID.eq(profile.ID))
//                        ).as(Result.Fields.educatorProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
//                        alias.asterisk(),
//                        DSL.multiset(
//                                langQ.where(alias.LANGUAGE_ID.eq(lang.ID))
//                        ).as(Result.Fields.language).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
//                        alias.asterisk()
//                );
//        return q.from(alias);
//    }
//
//    @Override
//    public Condition validateCondition(EducatorProfileExpertiseAcademicMapTable table) {
//        return DSL.noCondition();
//    }
//
//    @EqualsAndHashCode(callSuper = true)
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @FieldNameConstants
//    @ToString(callSuper = true)
//    // (selectively) inherited from related jOOQ generated POJO
//    // expanding foreign keys
//    public static final class Result extends EducatorProfileExpertiseAcademicMapPojo {
//        private DbExpertise.Result expertise;
//        private DbAcademicMajor.Result academicMajor;
//        private DbAcademicSubject.Result academicSubject;
//    }
//}
