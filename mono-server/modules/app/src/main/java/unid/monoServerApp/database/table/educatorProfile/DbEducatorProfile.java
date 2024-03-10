package unid.monoServerApp.database.table.educatorProfile;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.jooqMono.model.tables.EducatorProfileLanguageMapTable;
import unid.jooqMono.model.tables.EducatorProfileTable;
import unid.jooqMono.model.tables.daos.EducatorProfileDao;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.schoolIdentity.DbSchoolIdentity;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerMeta.api.EducatorResponse;
import unid.monoServerMeta.api.EventEducatorProfileResponse;
import unid.monoServerMeta.api.LearningHubResponse;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.tables.EducatorProfileTable.EDUCATOR_PROFILE;

@Component
public class DbEducatorProfile extends Db<EducatorProfileTable, EducatorProfileDao> {
    private final DbUser dbUserQuery;
    private final DbCountry dbCountry;
    private final DbSchool dbSchool;
    private final DbEducationLevel dbEducationLevel;
    private final DbSchoolIdentity dbSchoolIdentity;
    private final DbLanguage dbLanguage;
    private final DbExpertise dbExpertise;

    @Autowired
    public DbEducatorProfile(
            DSLContext dslContext,
            DbUser dbUserQuery,
            DbCountry dbCountry,
            DbSchool dbSchool,
            DbEducationLevel dbEducationLevel,
            DbSchoolIdentity dbSchoolIdentity,
            DbLanguage dbLanguage,
            DbExpertise dbExpertise
    ) {
        super(dslContext, Public.PUBLIC.EDUCATOR_PROFILE, new EducatorProfileDao(dslContext.configuration()));
        this.dbUserQuery = dbUserQuery;
        this.dbCountry = dbCountry;
        this.dbSchool = dbSchool;
        this.dbEducationLevel = dbEducationLevel;
        this.dbSchoolIdentity = dbSchoolIdentity;
        this.dbLanguage = dbLanguage;
        this.dbExpertise = dbExpertise;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorProfileTable alias) {
        var user = dbUserQuery.getTable().as(combineAlias(alias, dbUserQuery.getTable()));
        var userQ = dbUserQuery.getQuery(user);
        var country = dbCountry.getTable().as(combineAlias(alias, dbCountry.getTable()));
        var countryQ = dbCountry.getQuery(country);
        var school = dbSchool.getTable().as(combineAlias(alias, dbSchool.getTable()));
        var schoolQ = dbSchool.getQuery(school);
        var eduLevel = dbEducationLevel.getTable().as(combineAlias(alias, dbEducationLevel.getTable()));
        var eduLevelQ = dbEducationLevel.getQuery(eduLevel);
        var schoolIden = dbSchoolIdentity.getTable().as(combineAlias(alias, dbSchoolIdentity.getTable()));
        var schoolIdenQ = dbSchoolIdentity.getQuery(schoolIden);
        var langMap = EducatorProfileLanguageMapTable.EDUCATOR_PROFILE_LANGUAGE_MAP.as(
                combineAlias(alias, EducatorProfileLanguageMapTable.EDUCATOR_PROFILE_LANGUAGE_MAP)
        );
        var lang = dbLanguage.getTable().as(combineAlias(alias, dbLanguage.getTable()));
        var langQ = dbLanguage.getQuery(lang);
//        var expertiseMap = EducatorProfileExpertiseMapTable.EDUCATOR_PROFILE_EXPERTISE_MAP
//                .as(combineAlias(alias, EducatorProfileExpertiseMapTable.EDUCATOR_PROFILE_EXPERTISE_MAP));
//        var expertise = dbExpertise.getTable().as(combineAlias(alias, dbExpertise.getTable()));
//        var expertiseQ = dbExpertise.getQuery(expertise);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                userQ.where(alias.USER_ID.eq(user.ID))
                        ).as(Result.Fields.user).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                countryQ.where(alias.COUNTRY_ID.eq(country.ID))
                        ).as(Result.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                schoolQ.where(alias.UNIVERSITY_ID.eq(school.ID))
                        ).as(Result.Fields.university).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                eduLevelQ.where(alias.UNIVERSITY_EDUCATION_LEVEL_ID.eq(eduLevel.ID))
                        ).as(Result.Fields.universityEducationLevel).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                schoolIdenQ.where(alias.UNIVERSITY_IDENTITY_ID.eq(schoolIden.ID))
                        ).as(Result.Fields.universityIdentity).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                langQ.join(langMap)
                                        .on(langMap.LANGUAGE_ID.eq(lang.ID))
                                        .and(langMap.EDUCATOR_PROFILE_ID.eq(alias.ID))
                        ).as(Result.Fields.languages)
//                        DSL.multiset(
//                                expertiseQ.join(expertiseMap)
//                                        .on(expertiseMap.EXPERTISE_ID.eq(expertise.ID))
//                                        .and(expertiseMap.EDUCATOR_PROFILE_ID.eq(alias.ID))
//                        ).as(Result.Fields.expertises)
                );
        return q.from(alias);
    }

    public @NotNull SelectConditionStep<?> getQueryEducatorProfileCnt(){
        return dsl.selectCount()
                .from(USER,EDUCATOR_PROFILE)
                .where(USER.ID.eq(EDUCATOR_PROFILE.USER_ID).and(EDUCATOR_PROFILE.APPLICATION_APPROVAL.eq(ApplicationApprovalEnum.APPROVED)));
    }

    public @NotNull SelectConditionStep<?> getQueryEducatorProfile(){
        return dsl.select(
                        EDUCATOR_PROFILE.ID,
                        USER.EMAIL,
                        EDUCATOR_PROFILE.HOURLY_RATE.as(EducatorResponse.Fields.hourly_rate),
                        EDUCATOR_PROFILE.PROFILE_PICTURE.as(EducatorResponse.Fields.photo),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                        ).as(EducatorResponse.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                        ).as(EducatorResponse.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N,TAG)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID).and(TAG.ID.eq(EDUCATOR_PROFILE.COUNTRY_ID)))
                        ).as(EducatorResponse.Fields.countryI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N,TAG)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID).and(TAG.ID.eq(any(EDUCATOR_PROFILE.CITY_ID))))
                        ).as(EducatorResponse.Fields.cityI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N,TAG)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID).and(TAG.ID.eq(any(EDUCATOR_PROFILE.EXPERTISE_ID))))
                        ).as(EducatorResponse.Fields.expertises).convertFrom(r -> r.isEmpty() ? null : r.into(I18n.class)),

                        multiset(
                                select(I18N.fields())
                                        .from(I18N,TAG)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID).and(TAG.ID.eq(any(EDUCATOR_PROFILE.LANGUAGE_ID))))
                        ).as(EducatorResponse.Fields.languages).convertFrom(r -> r.isEmpty() ? null : r.into(I18n.class)),

                        multiset(
                                select(I18N.fields())
                                        .from(I18N,TAG)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID).and(TAG.ID.eq(any(EDUCATOR_PROFILE.ACADEMIC_MAJOR_ID))))
                        ).as(EducatorResponse.Fields.subjects).convertFrom(r -> r.isEmpty() ? null : r.into(I18n.class)),

                        multiset(
                                select(I18N.fields())
                                        .from(I18N,EXPERTISE)
                                        .where(I18N.ID.eq(EXPERTISE.DESCRIPTION_I18N_ID).and(EXPERTISE.ID.eq(any(EDUCATOR_PROFILE.EXPERTISE_DESCRIPTION_ID))))
                        ).as(EducatorResponse.Fields.expertiseDescriptions).convertFrom(r -> r.isEmpty() ? null : r.into(I18n.class)),


                        EDUCATOR_PROFILE.DESCRIPTION,
                        multiset(
                                select(
                                        I18N.as("school_i18n").ENGLISH.as(EducatorResponse.Education.Fields.university),
                                        I18N.as("degree_i18n").ENGLISH.as(EducatorResponse.Education.Fields.degree)
                                )
                                        .from(EDUCATOR_SCHOOL)
                                        .leftJoin(TAG.as("school_tag")).on(EDUCATOR_SCHOOL.UNIVERSITY_ID.eq(TAG.as("school_tag").ID))
                                        .leftJoin(I18N.as("school_i18n")).on(TAG.as("school_tag").DESCRIPTION_I18N_ID.eq(I18N.as("school_i18n").ID))
                                        .leftJoin(TAG.as("degree_tag")).on(EDUCATOR_SCHOOL.DEGREE_ID.eq(TAG.as("degree_tag").ID))
                                        .leftJoin(I18N.as("degree_i18n")).on(TAG.as("degree_tag").DESCRIPTION_I18N_ID.eq(I18N.as("degree_i18n").ID))
                                        .where(EDUCATOR_SCHOOL.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID))
                        ).as(EducatorResponse.Fields.educations).convertFrom(r -> r.isEmpty() ? null : r.into(EducatorResponse.Education.class))
                 )
                .from(USER,EDUCATOR_PROFILE)
                .where(USER.ID.eq(EDUCATOR_PROFILE.USER_ID).and(EDUCATOR_PROFILE.APPLICATION_APPROVAL.eq(ApplicationApprovalEnum.APPROVED)));
    }


    public SelectJoinStep<Record> getQueryUserOnly(EducatorProfileTable alias) {
        var user = dbUserQuery.getTable().as(combineAlias(alias, dbUserQuery.getTable()));
        var userQ = dbUserQuery.getQuery(user);
        var country = dbCountry.getTable().as(combineAlias(alias, dbCountry.getTable()));
        var countryQ = dbCountry.getQuery(country);
        var school = dbSchool.getTable().as(combineAlias(alias, dbSchool.getTable()));
        var schoolQ = dbSchool.getQuery(school);
        var eduLevel = dbEducationLevel.getTable().as(combineAlias(alias, dbEducationLevel.getTable()));
        var eduLevelQ = dbEducationLevel.getQuery(eduLevel);
        var schoolIden = dbSchoolIdentity.getTable().as(combineAlias(alias, dbSchoolIdentity.getTable()));
        var schoolIdenQ = dbSchoolIdentity.getQuery(schoolIden);
        var langMap = EducatorProfileLanguageMapTable.EDUCATOR_PROFILE_LANGUAGE_MAP.as(
                combineAlias(alias, EducatorProfileLanguageMapTable.EDUCATOR_PROFILE_LANGUAGE_MAP)
        );
        var lang = dbLanguage.getTable().as(combineAlias(alias, dbLanguage.getTable()));
        var langQ = dbLanguage.getQuery(lang);
//        var expertiseMap = EducatorProfileExpertiseMapTable.EDUCATOR_PROFILE_EXPERTISE_MAP
//                .as(combineAlias(alias, EducatorProfileExpertiseMapTable.EDUCATOR_PROFILE_EXPERTISE_MAP));
//        var expertise = dbExpertise.getTable().as(combineAlias(alias, dbExpertise.getTable()));
//        var expertiseQ = dbExpertise.getQuery(expertise);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                userQ.where(alias.USER_ID.eq(user.ID))
                        ).as(Result.Fields.user).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                countryQ.where(alias.COUNTRY_ID.eq(country.ID))
                        ).as(Result.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                schoolQ.where(alias.UNIVERSITY_ID.eq(school.ID))
                        ).as(Result.Fields.university).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                eduLevelQ.where(alias.UNIVERSITY_EDUCATION_LEVEL_ID.eq(eduLevel.ID))
                        ).as(Result.Fields.universityEducationLevel).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                schoolIdenQ.where(alias.UNIVERSITY_IDENTITY_ID.eq(schoolIden.ID))
                        ).as(Result.Fields.universityIdentity).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                langQ.join(langMap)
                                        .on(langMap.LANGUAGE_ID.eq(lang.ID))
                                        .and(langMap.EDUCATOR_PROFILE_ID.eq(alias.ID))
                        ).as(Result.Fields.languages)
//                        DSL.multiset(
//                                expertiseQ.join(expertiseMap)
//                                        .on(expertiseMap.EXPERTISE_ID.eq(expertise.ID))
//                                        .and(expertiseMap.EDUCATOR_PROFILE_ID.eq(alias.ID))
//                        ).as(Result.Fields.expertises)
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(EducatorProfileTable table) {
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
    public static final class Result extends EducatorProfilePojo {
        private DbUser.Result user;
        private DbCountry.Result country;
        private DbSchool.Result university;
        private DbEducationLevel.Result universityEducationLevel;
        private DbSchoolIdentity.Result universityIdentity;
        private List<DbLanguage.Result> languages;
        private List<DbExpertise.Result> expertises;
    }
}
