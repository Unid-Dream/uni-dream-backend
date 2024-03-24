package unid.monoServerApp.api.school;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.enums.DeletedTypeEnum;
import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.*;
import unid.jooqMono.model.tables.pojos.*;
import unid.jooqMono.model.tables.records.CountryRecord;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileSection;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.mapper.*;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.AcademicSubjectResourceType;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.multiset;
import static pwh.springWebStarter.response.UniErrorCode.COUNTRY_IS_NOT_EXIST;
import static pwh.springWebStarter.response.UniErrorCode.SCHOOL_LEVEL_IS_NOT_EXIST;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SchoolService {
    private final DbSchool dbSchool;
    private final DbI18N dbI18N;
    private final DbTag dbTag;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final SchoolMapper schoolMapper;
    private final CityMapper cityMapper;
    private final CountryMapper countryMapper;
    private final TagMapper tagMapper;
    private final DbCity dbCity;
    private final DbCountry dbCountry;
    private final DSLContext dslContext;


//    DbSchool.Result get(UUID id) {
//        var table = dbSchool.getTable();
//        return dbSchool.getQuery(table)
//                .where(table.ID.eq(id).and(dbSchool.validateCondition(table)))
//                .fetchOptional().orElseThrow(() -> Exceptions.notFound("School Not Found"))
//                .into(DbSchool.Result.class);
//    }

    SchoolPayload get(UUID id){
        var table = dbSchool.getTable();
        return dbSchool.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.select(
                                        COUNTRY.asterisk(),
                                        DSL.multiset(
                                                DSL.selectFrom(I18N).where(I18N.ID.eq(COUNTRY.NAME_I18N_ID))
                                        ).as(SchoolPayload.Country.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                ).from(COUNTRY).where(COUNTRY.ID.eq(table.COUNTRY_ID))
                        ).as(SchoolPayload.Fields.country).convertFrom(r->r.isEmpty()?null:r.get(0).into(SchoolPayload.Country.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.NAME_I18N_ID))
                        ).as(SchoolPayload.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .select(count().over().as(SchoolPayload.Fields.total))
                .from(table)
                .where(table.ID.eq(id))
                .fetchOptional()
                .orElseThrow(() -> Exceptions.business(COUNTRY_IS_NOT_EXIST))
                .into(SchoolPayload.class);
    }

    @Transactional(rollbackFor = Exception.class)
    SchoolPojo create(SchoolPayload payload) {
        sessionService.initDatabaseSession();
        var name = i18nMapper.toPojo(payload.getNameI18n());
        dbI18N.getDao().insert(name);
        SchoolPojo pojo = schoolMapper.toPojo(payload);
        pojo.setNameI18nId(name.getId());
        pojo.setCountryId(payload.getCountry().getId());
        dbSchool.getDao().insert(pojo);
        //查询TagCategory
        Optional.ofNullable(pojo.getSchoolLevel())
                .ifPresent(
                        schoolLevel -> {
                            TagPojo tag = new TagPojo()
                                    .setDescriptionI18nId(name.getId());
                            if(schoolLevel.equals(SchoolLevelEnum.UNIVERSITY)){
                                tag.setTagCategory(TagCategoryEnum.UNIVERSITY);
                            }else{
                                tag.setTagCategory(TagCategoryEnum.SECONDARY_SCHOOL);
                            }
                            dbTag.getDao().insert(tag);
                        }
                );
        return pojo;
    }

    @Transactional(rollbackFor = Exception.class)
    SchoolPojo update(SchoolPayload payload){
        SchoolPojo pojo = dbSchool.getDao().fetchOneById(payload.getId());
        if(pojo == null){
            throw Exceptions.business(COUNTRY_IS_NOT_EXIST);
        }
        pojo.setCountryId(payload.getCountry().getId());
        schoolMapper.merge(pojo,payload);
        var name = dbI18N.getDao().fetchOneById(
                pojo.getNameI18nId()
        );
        i18nMapper.merge(name,payload.getNameI18n());
        dbI18N.getDao().update(name);
        return pojo;
    }


//    SchoolPojo update(UUID id, SchoolRequest payload) {
//        sessionService.initDatabaseSession();
//        var school = get(id);
//        var nameI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(school.getNameI18nId()))
//                .orElseThrow(() -> Exceptions.notFound("Name Not Found"));
//        i18nMapper.merge(nameI18n, payload.getNameI18n());
//        dbI18N.getDao().update(nameI18n);
//        var addressI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(school.getDetailedAddressI18nId()))
//                .orElseThrow(() -> Exceptions.notFound("Address Not Found"));
//        i18nMapper.merge(addressI18n, payload.getDetailedAddressI18n());
//        dbI18N.getDao().update(addressI18n);
//        var cityTable = dbCity.getTable();
//        var city = dbCity.getQuery(cityTable)
//                .where(
//                        cityTable.ID.eq(payload.getCityId())
//                                .and(dbCity.validateCondition(cityTable))
//                ).fetchOptional()
//                .orElseThrow(() -> Exceptions.notFound("School City Not Found"))
//                .into(DbCity.Result.class);
//        schoolMapper.merge(school, payload);
//        dbSchool.getDao().update(school.setCountryId(city.getCountryId()));
//        return school;
//    }


//    @Cacheable(value = CacheTags.SECONDARY_SCHOOL, key = "#schoolLevelEnum", condition = "#schoolLevelEnum == T(unid.jooqMono.model.enums.SchoolLevelEnum).SECONDARY_SCHOOL")
    public List<SchoolResponse> list(String searchKey,SchoolLevelEnum schoolLevelEnum){
        //如果是高中,则只查询
        if(schoolLevelEnum.equals(SchoolLevelEnum.SECONDARY_SCHOOL)){
            return dslContext.select(
                             SCHOOL.ID,
                             SCHOOL.CREATED_ON.as(BaseResponse.BaseResponseFields.createdOnUtc),
                             SCHOOL.UPDATED_ON.as(BaseResponse.BaseResponseFields.updatedOnUtc),
                             multiset(
                                     DSL.select(I18N.fields())
                                             .from(I18N)
                                             .where(I18N.ID.eq(SCHOOL.NAME_I18N_ID))
                             ).as(SchoolResponse.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                     )
                     .from(SCHOOL)
                     .where(SCHOOL.SCHOOL_LEVEL.eq(schoolLevelEnum))
                     .fetchInto(SchoolResponse.class);
        }




        List<SchoolResponse> list = Lists.newArrayList();
        Condition condition = DSL.trueCondition();
        if(StrUtil.isNotEmpty(searchKey)){
            condition = I18N.ENGLISH.eq(searchKey).or(I18N.CHINESE_TRADITIONAL.eq(searchKey)).or(I18N.CHINESE_SIMPLIFIED.eq(searchKey));
        }
        List<DbSchool.Result> results = dslContext.select(SchoolTable.SCHOOL.fields())
                .from(SchoolTable.SCHOOL)
                .leftJoin(I18N).on(I18N.ID.eq(SchoolTable.SCHOOL.NAME_I18N_ID))
                .where(SchoolTable.SCHOOL.SCHOOL_LEVEL.eq(schoolLevelEnum))
                .and(condition)
                .fetchInto(DbSchool.Result.class);
        for(DbSchool.Result result : results){
            SchoolResponse response = schoolMapper.toResponse(result);
            //查询nameI18n
            I18nPojo nameI18n = dslContext.select()
                    .from(I18N)
                    .where(I18N.ID.eq(result.getNameI18nId()))
                    .fetchOneInto(I18nPojo.class);
            response.setNameI18n(i18nMapper.toModel(nameI18n));

            //查询city
            DbCity.Result city = dslContext.select().from(CITY)
                    .where(CITY.ID.eq(result.getCityId()))
                    .fetchOptionalInto(DbCity.Result.class)
                    .orElseThrow(()->Exceptions.notFound("City Not Found"));
            CityResponse cityResponse = cityMapper.toResponse(city);
            //查询city i18n
            I18nPojo cityI18n = dbI18N.getQuery()
                    .where(I18N.ID.eq(city.getNameI18nId()))
                    .fetchOptionalInto(I18nPojo.class)
                    .orElseThrow(()->Exceptions.notFound("City I18n Not Found"));
            cityResponse.setNameI18n(i18nMapper.toModel(cityI18n));

            Optional<DbCountry.Result> country = dslContext.select().from(COUNTRY)
                    .where(COUNTRY.ID.eq(city.getCountryId()))
                    .fetchOptionalInto(DbCountry.Result.class);
            if(country.isEmpty()){
                continue;
            }
            CountryResponse countryResponse = countryMapper.toResponse(country.get());
            I18nPojo countryI18n = dbI18N.getQuery()
                    .where(I18N.ID.eq(country.get().getNameI18nId()))
                    .fetchOptionalInto(I18nPojo.class)
                    .orElseThrow(()->Exceptions.notFound("Country I18n Not Found"));
            countryResponse.setNameI18n(i18nMapper.toModel(countryI18n));
            TagPojo tag = dbCountry.getDsl().select(TAG.fields())
                            .from(COUNTRY)
                            .leftJoin(TAG).on(COUNTRY.TAG_ID.eq(TAG.ID))
                            .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                            .where(COUNTRY.ID.eq(countryResponse.getId()))
                            .fetchOptional()
                            .orElseThrow(()->Exceptions.notFound("Country Tag Not Found"))
                            .into(TagPojo.class);
            TagResponse tagResponse = tagMapper.toResponse(tag);
            I18nPojo tagI18n = dbI18N.getQuery()
                    .where(I18N.ID.eq(tag.getDescriptionI18nId()))
                    .fetchOptionalInto(I18nPojo.class)
                    .orElseThrow(()->Exceptions.notFound("Tag I18n Not Found"));
            tagResponse.setDescriptionI18n(i18nMapper.toModel(tagI18n));
            countryResponse.setTag(tagResponse);

            cityResponse.setCountry(countryResponse);
            response.setCity(cityResponse);
            //查询extension
            Optional<SchoolExtensionPojo> extension = dslContext.select()
                    .from(SchoolExtensionTable.SCHOOL_EXTENSION)
                    .where(SCHOOL_EXTENSION.SCHOOL_ID.eq(result.getId()))
                    .and(SCHOOL_EXTENSION.DELETED.eq(DeletedTypeEnum.N))
                    .fetchOptionalInto(SchoolExtensionPojo.class);
            if(extension.isEmpty()){
                continue;
            }
            response.setFactor(extension.get().getFactor());
            response.setRate(extension.get().getRate());
            response.setPopulation(extension.get().getPopulation());
            response.setTuition(extension.get().getTuition());
            list.add(response);
        }
        return list;
    }

    public List<TagResponse> tags() {
        List<DbTag.Result> list = dbTag.getDsl().select(
                        TAG.asterisk(),
                        DSL.multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                        ).as(DbTag.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                )
                .from(TAG)
                .where(TAG.TAG_CATEGORY.eq(TagCategoryEnum.UNIVERSITY))
                .fetchInto(DbTag.Result.class);
        return tagMapper.toResponse(list);
    }


    public UniPageResponse<SchoolPayload> page(SchoolPageRequest request,SchoolLevelEnum schoolLevel) {
        var table = dbSchool.getTable();
        List<SchoolPayload> payload = dbSchool.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                               DSL.select(
                                       COUNTRY.asterisk(),
                                       DSL.multiset(
                                               DSL.selectFrom(I18N).where(I18N.ID.eq(COUNTRY.NAME_I18N_ID))
                                       ).as(SchoolPayload.Country.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                               ).from(COUNTRY).where(COUNTRY.ID.eq(table.COUNTRY_ID))
                        ).as(SchoolPayload.Fields.country).convertFrom(r->r.isEmpty()?null:r.get(0).into(SchoolPayload.Country.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.NAME_I18N_ID))
                        ).as(SchoolPayload.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .select(count().over().as(SchoolPayload.Fields.total))
                .from(table)
                .leftJoin(I18N).on(table.NAME_I18N_ID.eq(I18N.ID))
                .where(StrUtil.isEmpty(request.getSearchKey())?DSL.noCondition():I18N.ENGLISH.contains(request.getSearchKey()).or(I18N.CHINESE_TRADITIONAL.contains(request.getSearchKey())).or(I18N.CHINESE_SIMPLIFIED.contains(request.getSearchKey())))
                .and(table.SCHOOL_LEVEL.eq(schoolLevel))
                .orderBy(table.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(SchoolPayload.class);

        int totalSize = payload.stream()
                .findFirst()
                .map(SchoolPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }
}
