package unid.monoServerApp.api.user.profile.educator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.model.PaginationResult;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.CityTable;
import unid.jooqMono.model.tables.EducatorProfileTable;
import unid.jooqMono.model.tables.pojos.*;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.service.UserCacheService;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfileLanguageMap;
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbEducatorSchool;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.CountryMapper;
import unid.monoServerApp.mapper.EducatorProfileMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.UserMapper;
import unid.monoServerApp.service.S3Service;
import unid.monoServerApp.service.SessionService;
import unid.monoServerApp.util.PageUtils;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.UniErrorCode;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EducatorProfileService {
    private final DbEducatorProfile dbEducatorProfile;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final EducatorProfileMapper educatorProfileMapper;
    private final DbUser dbUser;
    private final S3Service s3Service;
    private final DbEducatorProfileLanguageMap dbEducatorProfileLanguageMap;
    private final UserCacheService userCacheService;
    private final CountryMapper countryMapper;
    private final DbExpertise dbExpertise;
    private final DSLContext dslContext;
    private final DbEducatorSchool dbEducatorSchool;

    public DbEducatorProfile.Result get(UUID userId) {
        // TODO ACL (educator: get self only, admin: get all)
        return userCacheService.getEducatorProfileByUserId(userId)
                .orElseThrow(() -> Exceptions.notFound("Profile Not Found"));
    }

    @CacheEvict(
            value = CacheTags.EDUCATOR_PROFILE,
            key = "#userId"
    )
    public EducatorProfilePojo create(UUID userId, EducatorProfileRequest payload) {
        sessionService.initDatabaseSession();
        var profilePojo = educatorProfileMapper.toPojo(payload)
                .setUserId(userId);
        dbEducatorProfile.getDao().insert(profilePojo);
//        updateUser(userId, payload);
        insertOrUpdateLanguage(
                profilePojo.getId(),
                Optional.ofNullable(payload.getLanguages())
                        .orElse(new ArrayList<>())
        );
        s3Service.tempToPermanent(profilePojo.getProfilePicture());
        return profilePojo;
    }

//    @CacheEvict(
//            value = CacheTags.EDUCATOR_SIMPLE_PROFILE,
//            key = "#profileId"
//    )
    public EducatorProfilePojo update(UUID profileId, EducatorProfileSimpleRequest payload) {
        sessionService.initDatabaseSession();
        EducatorProfilePojo pojo = dbEducatorProfile
                .getDsl()
                .select().from(EDUCATOR_PROFILE).where(EDUCATOR_PROFILE.ID.eq(profileId))
                .fetchOptionalInto(EducatorProfilePojo.class)
                .orElseThrow(Exceptions::unknownError);
        updateUser(pojo.getUserId(), payload);
        educatorProfileMapper.merge(pojo, payload);
        insertOrUpdateSchoolLevel(profileId,payload.getEducationLevel());
        dbEducatorProfile.getDao().update(pojo);
        return pojo;
    }


    private void insertOrUpdateSchoolLevel(UUID educatorProfileId,List<EducatorProfileSimpleRequest.EducationLevel> educationLevels){
        var table = dbEducatorSchool.getTable();
        dbEducatorSchool.getDsl().deleteFrom(table)
                .where(table.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .execute();
        educationLevels.forEach(educationLevel -> {
            Optional.ofNullable(educationLevel.getDegreeId()).orElseThrow(
                    ()->Exceptions.client(UniErrorCode.Client.EDUCATOR_UPDATE_PROFILE_SCHOOL_LEVEL_PARAMETERS_INVALID)
            );
            Optional.ofNullable(educationLevel.getUniversityId()).orElseThrow(
                    ()->Exceptions.client(UniErrorCode.Client.EDUCATOR_UPDATE_PROFILE_SCHOOL_LEVEL_PARAMETERS_INVALID)
            );
            EducatorSchoolPojo created = new EducatorSchoolPojo()
                    .setEducatorProfileId(educatorProfileId)
                    .setDegreeId(educationLevel.getDegreeId())
                    .setUniversityId(educationLevel.getUniversityId());
            dbEducatorSchool.getDao().insert(created);
            StaticLog.info("创建educator曾就读学校：{}",JSONUtil.toJsonStr(created));
        });

    }




    private void updateUser(UUID userId, EducatorProfileSimpleRequest payload) {
        var user = Optional.ofNullable(dbUser.getDao().fetchOneById(userId))
                .orElseThrow(() -> Exceptions.notFound("User Not Found"));
        var firstName = i18nMapper.toPojo(payload.getFirstNameI18n());
        Optional.ofNullable(user.getFistNameI18nId())
                .ifPresentOrElse(id -> {
                    firstName.setId(id);
                    dbI18N.getDao().update(firstName);
                }, () -> {
                    dbI18N.getDao().insert(firstName);
                });
        user.setFistNameI18nId(firstName.getId());
        var lastName = i18nMapper.toPojo(payload.getLastNameI18n());
        Optional.ofNullable(user.getLastNameI18nId())
                .ifPresentOrElse(id -> {
                    lastName.setId(id);
                    dbI18N.getDao().update(lastName);
                }, () -> {
                    dbI18N.getDao().insert(lastName);
                });
        user.setLastNameI18nId(lastName.getId());
        dbUser.getDao().update(user);
    }

    private void insertOrUpdateLanguage(
            UUID profileId,
            @NotNull
            List<UUID> languageIds
    ) {
        var table = dbEducatorProfileLanguageMap.getTable();
        var existingRecords = dbEducatorProfileLanguageMap.getQuery(table)
                .where(table.EDUCATOR_PROFILE_ID.eq(profileId))
                .fetchInto(DbEducatorProfileLanguageMap.Result.class);
        Db.parentListCrud(
                () -> existingRecords,
                () -> languageIds,
                EducatorProfileLanguageMapPojo::getId,
                source -> source,
                null,
                crud -> crud
                        .onDelete((db) -> {
                            dbEducatorProfileLanguageMap.getDao().deleteById(db.getId());
                        })
                        .onInsert((source) -> {
                            var pojo = new EducatorProfileLanguageMapPojo()
                                    .setLanguageId(source)
                                    .setEducatorProfileId(profileId);
                            dbEducatorProfileLanguageMap.getDao().insert(pojo);
                        })
                        .execute()
        );
    }


    public UniPageResponse<EducatorProfileSimpleResponse> page(Integer pageNumber,
                                                  Integer pageSize,
                                                  List<String> schoolQuery,
                                                  List<String> subjectQuery,
                                                  List<String> expertiseQuery,
                                                  List<String> languageQuery) {
        List<UUID> schoolCondition = dslContext.select(EDUCATOR_SCHOOL.EDUCATOR_PROFILE_ID)
                .from(EDUCATOR_SCHOOL)
                .leftJoin(TAG).on(EDUCATOR_SCHOOL.UNIVERSITY_ID.eq(TAG.ID))
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(I18N.ENGLISH.in(schoolQuery).or(I18N.CHINESE_SIMPLIFIED.in(schoolQuery)).or(I18N.CHINESE_TRADITIONAL.in(schoolQuery)))
                .fetchInto(UUID.class);

        List<UUID> majorCondition = dslContext.select(EDUCATOR_PROFILE_EXTENSION.EDUCATOR_PROFILE_ID)
                .from(EDUCATOR_PROFILE_EXTENSION)
                .leftJoin(TAG).on(TAG.ID.eq(any(EDUCATOR_PROFILE_EXTENSION.MAJOR_ID)))
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(I18N.ENGLISH.in(subjectQuery).or(I18N.CHINESE_SIMPLIFIED.in(subjectQuery)).or(I18N.CHINESE_TRADITIONAL.in(subjectQuery))).fetchInto(UUID.class);


        List<UUID> expertiseCondition = dslContext.select(EDUCATOR_PROFILE_EXTENSION.EDUCATOR_PROFILE_ID)
                .from(EDUCATOR_PROFILE_EXTENSION)
                .leftJoin(TAG).on(TAG.ID.eq(any(EDUCATOR_PROFILE_EXTENSION.EXPERTISE_ID)))
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(I18N.ENGLISH.in(expertiseQuery).or(I18N.CHINESE_SIMPLIFIED.in(expertiseQuery)).or(I18N.CHINESE_TRADITIONAL.in(expertiseQuery))).fetchInto(UUID.class);

        List<UUID> langCondition = dslContext.select(EDUCATOR_PROFILE.ID)
                .from(EDUCATOR_PROFILE)
                .leftJoin(TAG).on(TAG.ID.eq(any(EDUCATOR_PROFILE.LANGUAGE_ID)))
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(I18N.ENGLISH.in(languageQuery).or(I18N.CHINESE_SIMPLIFIED.in(languageQuery)).or(I18N.CHINESE_TRADITIONAL.in(languageQuery))).fetchInto(UUID.class);

        Integer totalRecords = dbEducatorProfile
                .getSimpleCnt()
                .and(schoolQuery == null || schoolQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(schoolCondition))
                .and(subjectQuery == null || subjectQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(majorCondition))
                .and(expertiseQuery == null || expertiseQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(expertiseCondition))
                .and(languageQuery == null || languageQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(langCondition))
                .fetchOptionalInto(Integer.class).orElse(0);

        // 计算总页数
        int totalPages = (totalRecords + pageSize - 1) / pageSize;
        List<DbEducatorProfile.Result> list = dbEducatorProfile
                .getSimpleQuery()
                .and(schoolQuery == null || schoolQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(schoolCondition))
                .and(subjectQuery == null || subjectQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(majorCondition))
                .and(expertiseQuery == null || expertiseQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(expertiseCondition))
                .and(languageQuery == null || languageQuery.isEmpty() ? DSL.noCondition() : EDUCATOR_PROFILE.ID.in(langCondition))
                .offset((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .fetchInto(DbEducatorProfile.Result.class);
        StaticLog.info(" query educator page size : {}", list.size());
//        for (EducatorResponse obj : list) {
//            I18n descriptionI18n = new I18n();
//            descriptionI18n.setEnglish(obj.getDescription());
//            obj.setDescriptionI18n(descriptionI18n);
//        }
        return new UniPageResponse<>(totalRecords, pageNumber, pageSize, totalPages, educatorProfileMapper.toSimpleResponse(list));
    }


    public EducatorResponse getCourseEducator(UUID educatorProfileId) {
        EducatorResponse response = dbEducatorProfile
                .getQueryEducatorProfile()
                .and(EDUCATOR_PROFILE.ID.eq(educatorProfileId))
                .fetchOptional()
                .orElseThrow(() -> Exceptions.notFound(" Course Educator Not Found"))
                .into(EducatorResponse.class);
        I18n descriptionI18n = new I18n();
        descriptionI18n.setEnglish(response.getDescription());
        response.setDescriptionI18n(descriptionI18n);
        return response;
    }

//    @Cacheable(value = CacheTags.EDUCATOR_SIMPLE_PROFILE, key = "#profileId")
    public EducatorProfileSimpleResponse getSimpleCache(UUID profileId) {
        DbEducatorProfile.Result result = dbEducatorProfile.getDsl().select(
                        EDUCATOR_PROFILE.asterisk(),
                        USER.asterisk(),
                        multiset(
                                select(
                                            EDUCATOR_SCHOOL.asterisk()
                                        )
                                        .from(EDUCATOR_SCHOOL)
                                        .where(EDUCATOR_SCHOOL.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID))
                        ).as(DbEducatorProfile.Result.Fields.educationLevel).convertFrom(r -> r.isEmpty() ? null : r.into(DbEducatorSchool.Result.class)),
                        multiset(
                                select()
                                        .from(I18N,USER)
                                        .where(I18N.ID.eq(USER.LAST_NAME_I18N_ID).and(USER.ID.eq(EDUCATOR_PROFILE.USER_ID)))
                        ).as(DbEducatorProfile.Result.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        multiset(
                                select()
                                        .from(I18N,USER)
                                        .where(I18N.ID.eq(USER.FIST_NAME_I18N_ID).and(USER.ID.eq(EDUCATOR_PROFILE.USER_ID)))
                        ).as(DbEducatorProfile.Result.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                ).from(EDUCATOR_PROFILE,USER)
                .where(EDUCATOR_PROFILE.ID.eq(profileId).and(EDUCATOR_PROFILE.USER_ID.eq(USER.ID)))
                .fetchOneInto(DbEducatorProfile.Result.class);
        return educatorProfileMapper.toSimpleResponse(result);
    }


}
