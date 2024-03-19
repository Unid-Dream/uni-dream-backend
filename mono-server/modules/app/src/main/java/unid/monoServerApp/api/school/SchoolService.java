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

import static org.jooq.impl.DSL.multiset;
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


    DbSchool.Result get(UUID id) {
        var table = dbSchool.getTable();
        return dbSchool.getQuery(table)
                .where(table.ID.eq(id).and(dbSchool.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("School Not Found"))
                .into(DbSchool.Result.class);
    }

    SchoolPojo create(SchoolRequest payload) {
        sessionService.initDatabaseSession();
        var nameI18n = i18nMapper.toPojo(payload.getNameI18n());
        dbI18N.getDao().insert(nameI18n);
        var addressI18n = i18nMapper.toPojo(payload.getDetailedAddressI18n());
        dbI18N.getDao().insert(addressI18n);
        var cityTable = dbCity.getTable();
        var city = dbCity.getQuery(cityTable)
                .where(
                        cityTable.ID.eq(payload.getCityId())
                                .and(dbCity.validateCondition(cityTable))
                ).fetchOptional()
                .orElseThrow(() -> Exceptions.notFound("School City Not Found"))
                .into(DbCity.Result.class);
        var pojo = new SchoolPojo()
                .setCityId(city.getId())
                .setCountryId(city.getCountryId())
                .setNameI18nId(nameI18n.getId())
                .setDetailedAddressI18nId(addressI18n.getId())
                .setTagId(payload.getTagId());
        schoolMapper.merge(pojo, payload);
        dbSchool.getDao().insert(pojo);
        return pojo;
    }

    SchoolPojo update(UUID id, SchoolRequest payload) {
        sessionService.initDatabaseSession();
        var school = get(id);
        var nameI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(school.getNameI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Name Not Found"));
        i18nMapper.merge(nameI18n, payload.getNameI18n());
        dbI18N.getDao().update(nameI18n);
        var addressI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(school.getDetailedAddressI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Address Not Found"));
        i18nMapper.merge(addressI18n, payload.getDetailedAddressI18n());
        dbI18N.getDao().update(addressI18n);
        var cityTable = dbCity.getTable();
        var city = dbCity.getQuery(cityTable)
                .where(
                        cityTable.ID.eq(payload.getCityId())
                                .and(dbCity.validateCondition(cityTable))
                ).fetchOptional()
                .orElseThrow(() -> Exceptions.notFound("School City Not Found"))
                .into(DbCity.Result.class);
        schoolMapper.merge(school, payload);
        dbSchool.getDao().update(school.setCountryId(city.getCountryId()));
        return school;
    }


    /** insert University data hub **/
//    @PostConstruct
    public void readDataHub(){
//        List<SchoolPojo> list = dbSchool.getDsl().select().from(SchoolTable.SCHOOL).fetchInto(SchoolPojo.class);

        /*for(SchoolPojo obj : list){
            //查询city
            CityPojo city = dbCity.getDao().fetchOneById(obj.getCityId());
            //查询city i18n
            I18nPojo cityI18n = dbI18N.getDao().fetchOneById(city.getNameI18nId());
            //删除 city, city i18n
            dbCity.getDao().deleteById(city.getId());
            dbI18N.getDao().deleteById(cityI18n.getId());

            //查询country
            CountryPojo country = dbCountry.getDao().fetchOneById(obj.getCountryId());
            //查询country i18n
            I18nPojo countryI18n = dbI18N.getDao().fetchOneById(country.getNameI18nId());
            //删除 countryI18n,country
            dbCountry.getDao().deleteById(obj.getCountryId());
            dbI18N.getDao().deleteById(countryI18n.getId());


            //查询tag
            TagPojo tag = dbTag.getDao().fetchOneById(obj.getTagId());
            //查询tag i18n
            I18nPojo tagI18n = dbI18N.getDao().fetchOneById(tag.getDescriptionI18nId());
            //删除 tag, tag i18n
            dbTag.getDao().deleteById(tag.getId());
            dbI18N.getDao().deleteById(tagI18n.getId());

            //删除 school

            //删除 school_extension

         }*/

        ExcelReader reader = ExcelUtil.getReader("/Users/apple/Documents/school.xlsx");
        List<Map<String, Object>> readAll = reader.readAll();
        List<SchoolBatchRQ> list = Lists.newArrayList();
        for(Map<String,Object> row : readAll){
            SchoolBatchRQ batchRQ = new SchoolBatchRQ();
            JSONObject jsonObject = new JSONObject(row);

            I18n countryI18n = new I18n();
            countryI18n.setEnglish(jsonObject.getStr("Country"));
            batchRQ.setCountryI18n(countryI18n);
            I18n cityI18n = new I18n();
            cityI18n.setEnglish(jsonObject.getStr("City"));
            batchRQ.setCityI18n(cityI18n);

            I18n nameI18n = new I18n();
            nameI18n.setEnglish(jsonObject.getStr("University Name"));
            batchRQ.setNameI18n(nameI18n);

            I18n tagI18n = new I18n();
            tagI18n.setEnglish(jsonObject.getStr("Uni Tag (Short Form)"));
            batchRQ.setTagI18n(tagI18n);

            batchRQ.setAcceptanceRate(jsonObject.getStr("Acceptance Rate"));
            batchRQ.setUFactor(jsonObject.getStr("The U-Factor"));
            batchRQ.setUndergradPopulation(jsonObject.getStr("Undergrad population"));
            batchRQ.setLat(jsonObject.getStr("Latitude"));
            batchRQ.setLng(jsonObject.getStr("Longitude"));
            batchRQ.setTuition(jsonObject.getStr("Tuition (excluding room & board) in USD"));

            list.add(batchRQ);
        }

        for(SchoolBatchRQ batchRQ : list){
            //根据大学名称查询,对于的数据
            SchoolPojo school = dbSchool.getDsl().select(SCHOOL.fields())
                    .from(SCHOOL)
                    .leftJoin(I18N).on(SCHOOL.NAME_I18N_ID.eq(I18N.ID))
                    .where(I18N.ENGLISH.eq(batchRQ.getNameI18n().getEnglish()))
                    .fetchOptional().orElseThrow(()->Exceptions.notFound(" School Not Found"))
                    .into(SchoolPojo.class);
            school.setLatitude(batchRQ.getLat());
            school.setLongitude(batchRQ.getLng());
            dbSchool.getDao().update(school);
        }

        System.out.println(readAll);
    }


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



}
