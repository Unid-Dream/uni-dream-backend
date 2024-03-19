package unid.monoServerApp.database.table.studentProfile;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.Tables;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;
import unid.jooqMono.model.tables.StudentProfileTable;
import unid.jooqMono.model.tables.daos.StudentProfileDao;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.curriculum.DbCurriculum;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbEducatorSchool;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.user.DbUser;

import java.util.List;

import static com.google.common.base.CharMatcher.any;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;
import static unid.jooqMono.model.Tables.I18N;
import static unid.jooqMono.model.Tables.USER;

@Component
public class DbStudentProfile extends Db<StudentProfileTable, StudentProfileDao> {
    private final DbUser dbUserQuery;
    private final DbCountry dbCountry;
    private final DbSchool dbSchool;
    private final DbEducationLevel dbEducationLevel;
    private final DbCurriculum dbCurriculum;
    private final DbStudentProfileSchoolReport dbStudentProfileSchoolReport;

    @Autowired
    public DbStudentProfile(
            DSLContext dslContext,
            DbUser dbUserQuery,
            DbCountry dbCountry,
            DbSchool dbSchool,
            DbEducationLevel dbEducationLevel,
            DbCurriculum dbCurriculum,
            DbStudentProfileSchoolReport dbStudentProfileSchoolReport
    ) {
        super(dslContext, Public.PUBLIC.STUDENT_PROFILE, new StudentProfileDao(dslContext.configuration()));
        this.dbUserQuery = dbUserQuery;
        this.dbCountry = dbCountry;
        this.dbSchool = dbSchool;
        this.dbEducationLevel = dbEducationLevel;
        this.dbCurriculum = dbCurriculum;
        this.dbStudentProfileSchoolReport = dbStudentProfileSchoolReport;
    }

    @Override
    public SelectJoinStep<Record> getQuery(StudentProfileTable alias) {
        var user = dbUserQuery.getTable().as(combineAlias(alias, dbUserQuery.getTable()));
        var userQ = dbUserQuery.getQuery(user);
        var country = dbCountry.getTable().as(combineAlias(alias, dbCountry.getTable()));
        var countryQ = dbCountry.getQuery(country);
        var secondarySchool = dbSchool.getTable().as(combineAlias(alias, dbSchool.getTable()));
        var secondarySchoolQ = dbSchool.getQuery(secondarySchool);
        var secondarySchoolEduLevel = dbEducationLevel.getTable().as(combineAlias(alias, dbEducationLevel.getTable()));
        var secondarySchoolEduLevelQ = dbEducationLevel.getQuery(secondarySchoolEduLevel);
        var secondarySchoolCurriculum = dbCurriculum.getTable().as(combineAlias(alias, dbCurriculum.getTable()));
        var secondarySchoolCurriculumQ = dbCurriculum.getQuery(secondarySchoolCurriculum);
        var schoolReport = dbStudentProfileSchoolReport.getTable().as(combineAlias(alias, dbStudentProfileSchoolReport.getTable()));
        var schoolReportQ = dbStudentProfileSchoolReport.getQuery(schoolReport);
        var uni1 = dbSchool.getTable().as(combineAlias(alias, dbSchool.getTable().as("uni1")));
        var uni1Q = dbSchool.getQuery(uni1);
        var uni2 = dbSchool.getTable().as(combineAlias(alias, dbSchool.getTable().as("uni2")));
        var uni2Q = dbSchool.getQuery(uni2);
        var uni3 = dbSchool.getTable().as(combineAlias(alias, dbSchool.getTable().as("uni3")));
        var uni3Q = dbSchool.getQuery(uni3);
        var otherUni = dbSchool.getTable().as(combineAlias(alias, dbSchool.getTable().as("other_uni")));
        var otherUniQ = dbSchool.getQuery(otherUni);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                userQ.where(alias.USER_ID.eq(user.ID))
                        ).as(Result.Fields.user).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                userQ.where(alias.USER_ID.eq(user.ID))
                        ).as(Result.Fields.user).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                countryQ.where(alias.COUNTRY_ID.eq(country.ID))
                        ).as(Result.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                secondarySchoolQ.where(alias.SECONDARY_SCHOOL_ID.eq(secondarySchool.ID))
                        ).as(Result.Fields.secondarySchool).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                secondarySchoolEduLevelQ.where(alias.SECONDARY_SCHOOL_EDUCATION_LEVEL_ID.eq(secondarySchoolEduLevel.ID))
                        ).as(Result.Fields.secondarySchoolEducationLevel).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                secondarySchoolCurriculumQ.where(alias.SECONDARY_SCHOOL_CURRICULUM_ID.eq(secondarySchoolCurriculum.ID))
                        ).as(Result.Fields.secondarySchoolCurriculum).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                schoolReportQ.where(alias.ID.eq(schoolReport.STUDENT_PROFILE_ID))
                        ).as(Result.Fields.studentProfileSchoolReports),
                        DSL.multiset(
                                uni1Q.where(alias.PREFERRED_UNIVERSITY_1_ID.eq(uni1.ID))
                        ).as(Result.Fields.preferredUniversity_1).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                uni2Q.where(alias.PREFERRED_UNIVERSITY_2_ID.eq(uni2.ID))
                        ).as(Result.Fields.preferredUniversity_2).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                uni3Q.where(alias.PREFERRED_UNIVERSITY_3_ID.eq(uni3.ID))
                        ).as(Result.Fields.preferredUniversity_3).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    public SelectJoinStep<Record> getSimpleQuery(StudentProfileTable alias){
        return DSL.select(
                        alias.asterisk(),
                        multiset(
                                select()
                                        .from(I18N,USER)
                                        .where(I18N.ID.eq(USER.LAST_NAME_I18N_ID).and(USER.ID.eq(Tables.STUDENT_PROFILE.USER_ID)))
                        ).as(Result.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        multiset(
                                select()
                                        .from(I18N,USER)
                                        .where(I18N.ID.eq(USER.FIST_NAME_I18N_ID).and(USER.ID.eq(Tables.STUDENT_PROFILE.USER_ID)))
                        ).as(Result.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                )
                .from(alias);
    }




    @Override
    public Condition validateCondition(StudentProfileTable table) {
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
    public static final class Result extends StudentProfilePojo {
        private DbI18N.Result firstNameI18n;
        private DbI18N.Result lastNameI18n;
        private DbUser.Result user;
        private DbCountry.Result country;
        private DbSchool.Result secondarySchool;
        private DbEducationLevel.Result secondarySchoolEducationLevel;
        private DbCurriculum.Result secondarySchoolCurriculum;
        private DbSchool.Result preferredUniversity_1;
        private DbSchool.Result preferredUniversity_2;
        private DbSchool.Result preferredUniversity_3;
        private List<DbStudentProfileSchoolReport.Result> studentProfileSchoolReports;
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class SimpleResult extends StudentProfilePojo {
        private DbI18N.Result firstNameI18n;
        private DbI18N.Result lastNameI18n;
        private List<DbEducatorSchool.Result> educationLevel;
    }


}
