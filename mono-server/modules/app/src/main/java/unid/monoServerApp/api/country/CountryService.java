package unid.monoServerApp.api.country;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.CountryPojo;
import unid.jooqMono.model.tables.pojos.TagPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.mapper.CountryMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.count;
import static unid.jooqMono.model.Tables.I18N;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CountryService {
    private final DbCountry dbCountry;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final CountryMapper countryMapper;
    private final DbTag dbTag;

//    DbCountry.Result get(UUID id) {
//        var table = dbCountry.getTable();
//        return dbCountry.getQuery(table)
//                .where(table.ID.eq(id).and(dbCountry.validateCondition(table)))
//                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Country Not Found"))
//                .into(DbCountry.Result.class);
//    }


    CountryPayload get(UUID id){
        var table = dbCountry.getTable();
        return dbCountry.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.NAME_I18N_ID))
                        ).as(CountryPayload.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .from(table)
                .where(table.ID.eq(id))
                .fetchOneInto(CountryPayload.class);
    }



    @Transactional(rollbackFor = Exception.class)
    CountryPojo create(CountryPayload.Create payload) {
        sessionService.initDatabaseSession();
        var name = i18nMapper.toPojo(payload.getI18n());
        dbI18N.getDao().insert(name);
        var pojo = new CountryPojo()
                .setNameI18nId(name.getId());
        dbCountry.getDao().insert(pojo);
        dbTag.getDao().insert(
                new TagPojo()
                        .setTagCategory(TagCategoryEnum.COUNTRY)
                        .setDescriptionI18nId(name.getId())
        );
        return pojo;
    }

    CountryPojo update(CountryPayload payload) {
        sessionService.initDatabaseSession();
        //查询
        CountryPojo pojo = dbCountry.getDao().fetchOneById(payload.getId());
        Optional.ofNullable(pojo).orElseThrow(()->Exceptions.business(UniErrorCode.COUNTRY_IS_NOT_EXIST));
        Optional.ofNullable(dbI18N.getDao().fetchOneById(pojo.getNameI18nId()))
                .ifPresent(
                        i18n-> {
                            i18nMapper.merge(i18n,payload.getI18n(),i18n.getId());
                            dbI18N.getDao().update(i18n);
                        }
                );
        return pojo;
    }

    public UniPageResponse<CountryPayload> page(CountryPageRequest request) {
        var table = dbCountry.getTable();
        List<CountryPayload> payload = dbCountry.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.NAME_I18N_ID))
                        ).as(CountryPayload.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .select(count().over().as(CountryPayload.Fields.total))
                .from(table)
                .orderBy(table.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(CountryPayload.class);

        int totalSize = payload.stream()
                .findFirst()
                .map(CountryPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }

    void delete(UUID id){
        dbCountry.getDao().deleteById(id);
    }
}
