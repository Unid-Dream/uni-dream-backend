package unid.monoServerApp.database.service;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.schoolIdentity.DbSchoolIdentity;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserCacheService {
    private final DbStudentProfile dbStudentProfile;
    private final DbEducatorProfile dbEducatorProfile;
    private final DSLContext dslContext;
    private final DbUser dbUser;
    private final DbCountry dbCountry;
    private final DbSchool dbSchool;
    private final DbSchoolIdentity dbSchoolIdentity;
    private final DbLanguage dbLanguage;
    private final DbEducationLevel dbEducationLevel;


//    @Cacheable(
//            value = CacheTags.STUDENT_PROFILE,
//            key = "#userId"
//    )
    public Optional<DbStudentProfile.Result> getStudentProfileByUserId(UUID userId) {
        var table = dbStudentProfile.getTable();
        return dbStudentProfile.getQuery(table)
                .where(table.USER_ID.eq(userId))
                .fetchOptionalInto(DbStudentProfile.Result.class);
    }

    public DbStudentProfile.Result getStudentProfile(UserPojo userPojo){

        DbStudentProfile.Result result = dbStudentProfile.getDsl()
                        .select(STUDENT_PROFILE.fields()).from(STUDENT_PROFILE,USER).where(STUDENT_PROFILE.USER_ID.eq(USER.ID))
                        .and(USER.ID.eq(userPojo.getId()))
                        .fetchOptionalInto(DbStudentProfile.Result.class)
                        .orElse(new DbStudentProfile.Result());
        DbUser.Result user = new DbUser.Result();
        DbI18N.Result firstNameI18n = dbStudentProfile.getDsl().select().from(I18N).where(I18N.ID.eq(userPojo.getFistNameI18nId()))
                .fetchOptionalInto(DbI18N.Result.class)
                .orElse(new DbI18N.Result());
        DbI18N.Result lastNameI18n =  dbStudentProfile.getDsl().select().from(I18N).where(I18N.ID.eq(userPojo.getLastNameI18nId()))
                .fetchOptionalInto(DbI18N.Result.class)
                .orElse(new DbI18N.Result());
        user.setFirstNameI18n(firstNameI18n);
        user.setLastNameI18n(lastNameI18n);
        result.setUser(user);
        return result;


    }






//    @Cacheable(
//            value = CacheTags.EDUCATOR_PROFILE,
//            key = "#userId"
//    )
    @Deprecated
    public Optional<DbEducatorProfile.Result> getEducatorProfileByUserIdXX(UUID userId) {
        log.info("LaLaLaLaLa...");
        var table = dbEducatorProfile.getTable();
        return dbEducatorProfile.getQuery(table)
                .where(table.USER_ID.eq(userId))
                .fetchOptionalInto(DbEducatorProfile.Result.class);
    }


    @Cacheable(
            value = CacheTags.EDUCATOR_PROFILE,
            key = "#userId"
    )
    public Optional<DbEducatorProfile.Result> getEducatorProfileByUserId(UUID userId) {
        log.info("LaLaLaLaLa...");
        // 查询educator信息

        Optional<DbEducatorProfile.Result> exist = dbEducatorProfile.getDsl().select().from(EDUCATOR_PROFILE)
                .where(EDUCATOR_PROFILE.USER_ID.eq(userId))
                .fetchOptionalInto(DbEducatorProfile.Result.class);
        if(exist.isEmpty()){
            return exist;
        }
       return ((Function<UUID, DbEducatorProfile.Result>) uuid -> {
            //query educator by userId
            return dbEducatorProfile.getDsl().select().from(EDUCATOR_PROFILE)
                    .where(EDUCATOR_PROFILE.USER_ID.eq(uuid))
                    .fetchOneInto(DbEducatorProfile.Result.class);
        }).andThen((dbEducatorProfile) -> {
            //查询 user
            var table = dbUser.getTable();
            Optional<DbUser.Result> dbUserOpt = dbUser.getQuery(table)
                    .where(table.ID.eq(userId))
                    .fetchOptionalInto(DbUser.Result.class);
            dbUserOpt.ifPresent(dbEducatorProfile::setUser);
            return dbEducatorProfile;
        }).andThen(dbEducatorProfile -> {
            var table = dbCountry.getTable();
            Optional<DbCountry.Result> dbUserOpt = dbCountry.getQuery(table)
                    .where(table.ID.eq(dbEducatorProfile.getCountryId()))
                    .fetchOptionalInto(DbCountry.Result.class);
            dbUserOpt.ifPresent(dbEducatorProfile::setCountry);
            return dbEducatorProfile;
        }).andThen(dbEducatorProfile -> {
            var table = dbSchool.getTable();
            Optional<DbSchool.Result> dbSchoolOpt = dbSchool.getQuery(table)
                    .where(table.ID.eq(dbEducatorProfile.getUniversityId()))
                    .fetchOptionalInto(DbSchool.Result.class);
            dbSchoolOpt.ifPresent(dbEducatorProfile::setUniversity);
            return dbEducatorProfile;
        }).andThen(dbEducatorProfile -> {
            var table = dbSchoolIdentity.getTable();
            Optional<DbSchoolIdentity.Result> dbSchoolIdentityOpt = dbSchoolIdentity.getQuery(table)
                    .where(table.ID.eq(dbEducatorProfile.getUniversityIdentityId()))
                    .fetchOptionalInto(DbSchoolIdentity.Result.class);
            dbSchoolIdentityOpt.ifPresent(dbEducatorProfile::setUniversityIdentity);
            return dbEducatorProfile;
        }).andThen(dbEducatorProfile -> {
            var table = dbLanguage.getTable();
            List<DbLanguage.Result> langs = dbLanguage.getQuery(table)
                    .join(EDUCATOR_PROFILE_LANGUAGE_MAP)
                    .on(EDUCATOR_PROFILE_LANGUAGE_MAP.LANGUAGE_ID.eq(table.ID))
                    .and(EDUCATOR_PROFILE_LANGUAGE_MAP.EDUCATOR_PROFILE_ID.eq(dbEducatorProfile.getId()))
                    .fetchInto(DbLanguage.Result.class);
            dbEducatorProfile.setLanguages(langs);
            return dbEducatorProfile;
        }).andThen(dbEducatorProfile -> {
            var table = dbEducationLevel.getTable();
            Optional<DbEducationLevel.Result> dbEducationLevelOpt = dbEducationLevel.getQuery(table)
                    .where(table.ID.eq(dbEducatorProfile.getUniversityEducationLevelId()))
                    .fetchOptionalInto(DbEducationLevel.Result.class);
            dbEducationLevelOpt.ifPresent(dbEducatorProfile::setUniversityEducationLevel);
            StaticLog.info("查询educator profile : {}", JSONUtil.toJsonStr(dbEducatorProfile));
            return Optional.of(dbEducatorProfile);
        }).apply(userId);
    }
}
