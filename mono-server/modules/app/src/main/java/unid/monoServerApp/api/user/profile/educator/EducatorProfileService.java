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
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.CountryMapper;
import unid.monoServerApp.mapper.EducatorProfileMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.S3Service;
import unid.monoServerApp.service.SessionService;
import unid.monoServerApp.util.PageUtils;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;

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
    private final DSLContext dslContext;

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
        updateUser(userId, payload);
        insertOrUpdateLanguage(
                profilePojo.getId(),
                Optional.ofNullable(payload.getLanguages())
                        .orElse(new ArrayList<>())
        );
        s3Service.tempToPermanent(profilePojo.getProfilePicture());
        return profilePojo;
    }

    @CacheEvict(
            value = CacheTags.EDUCATOR_PROFILE,
            key = "#userId"
    )
    public EducatorProfilePojo update(UUID userId, EducatorProfileRequest payload) {
        sessionService.initDatabaseSession();
        var existingRecord = Optional.ofNullable(
                dbEducatorProfile.getDao().fetchOneByUserId(userId)
        ).orElseThrow(() -> Exceptions.notFound("Profile Not Found"));
        educatorProfileMapper.merge(existingRecord, payload);
        dbEducatorProfile.getDao().update(existingRecord);
        insertOrUpdateLanguage(
                existingRecord.getId(),
                Optional.ofNullable(payload.getLanguages())
                        .orElse(new ArrayList<>())
        );
        s3Service.oldToNew(existingRecord.getProfilePicture(), payload.getProfilePicture());
        return existingRecord;
    }

    private void updateUser(UUID userId, EducatorProfileRequest payload) {
        var user = Optional.ofNullable(dbUser.getDao().fetchOneById(userId))
                .orElseThrow(() -> Exceptions.notFound("User Not Found"));
        var firstName = i18nMapper.toPojo(payload.getFirstName());
        Optional.ofNullable(user.getFistNameI18nId())
                .ifPresentOrElse(id -> {
                    firstName.setId(id);
                    dbI18N.getDao().update(firstName);
                }, () -> {
                    dbI18N.getDao().insert(firstName);
                });
        user.setFistNameI18nId(firstName.getId());
        var lastName = i18nMapper.toPojo(payload.getLastName());
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


    public UniPageResponse<EducatorResponse> page(Integer pageNumber,
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

        List<UUID> langCondition = dslContext.select(EDUCATOR_PROFILE_EXTENSION.EDUCATOR_PROFILE_ID)
                .from(EDUCATOR_PROFILE_EXTENSION)
                .leftJoin(TAG).on(TAG.ID.eq(any(EDUCATOR_PROFILE_EXTENSION.LANGUAGE_ID)))
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(I18N.ENGLISH.in(languageQuery).or(I18N.CHINESE_SIMPLIFIED.in(languageQuery)).or(I18N.CHINESE_TRADITIONAL.in(languageQuery))).fetchInto(UUID.class);

        Integer totalRecords = dbEducatorProfile
                .getQueryEducatorProfileCnt()
                .and(schoolQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(schoolCondition))
                .and(subjectQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(majorCondition))
                .and(expertiseQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(expertiseCondition))
                .and(languageQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(langCondition))
                .fetchOptionalInto(Integer.class).orElse(0);

        // 计算总页数
        int totalPages = (totalRecords + pageSize - 1) / pageSize;
        List<EducatorResponse> list = dbEducatorProfile
                .getQueryEducatorProfile()
                .and(schoolQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(schoolCondition))
                .and(subjectQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(majorCondition))
                .and(expertiseQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(expertiseCondition))
                .and(languageQuery.isEmpty()?DSL.noCondition():EDUCATOR_PROFILE.ID.in(langCondition))
                .offset((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .fetchInto(EducatorResponse.class);
        StaticLog.info(" query educator page size : {}",list.size());
        for(EducatorResponse obj : list){
            I18n descriptionI18n = new I18n();
            descriptionI18n.setEnglish(obj.getDescription());
            obj.setDescriptionI18n(descriptionI18n);
        }
        return new UniPageResponse<>(totalRecords, pageNumber, pageSize, totalPages, list);
    }


    public EducatorResponse getCourseEducator(UUID educatorProfileId) {
        EducatorResponse response = dbEducatorProfile.getQueryEducatorProfile().and(EDUCATOR_PROFILE.ID.eq(educatorProfileId))
                .fetchOptional().orElseThrow(()->Exceptions.notFound(" Course Educator Not Found"))
                .into(EducatorResponse.class);
        I18n descriptionI18n = new I18n();
        descriptionI18n.setEnglish(response.getDescription());
        response.setDescriptionI18n(descriptionI18n);
        return response;
    }

}
