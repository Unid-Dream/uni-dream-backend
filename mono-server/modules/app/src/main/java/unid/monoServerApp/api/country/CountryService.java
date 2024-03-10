package unid.monoServerApp.api.country;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.CountryPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.CountryMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.CountryRequest;
import unid.monoServerMeta.api.TagResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CountryService {
    private final DbCountry dbCountry;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final CountryMapper countryMapper;

    DbCountry.Result get(UUID id) {
        var table = dbCountry.getTable();
        return dbCountry.getQuery(table)
                .where(table.ID.eq(id).and(dbCountry.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Country Not Found"))
                .into(DbCountry.Result.class);
    }

    CountryPojo create(CountryRequest payload) {
        sessionService.initDatabaseSession();
        var name = i18nMapper.toPojo(payload.getNameI18n());
        dbI18N.getDao().insert(name);
        var pojo = new CountryPojo()
                .setNameI18nId(name.getId())
                .setTagId(payload.getTagId());
        dbCountry.getDao().insert(pojo);
        return pojo;
    }

    CountryPojo update(UUID id, CountryRequest payload) {
        sessionService.initDatabaseSession();
        var country = get(id);
        var nameI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(country.getNameI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Name Not Found"));
        i18nMapper.merge(nameI18n, payload.getNameI18n());
        dbI18N.getDao().update(nameI18n);
        countryMapper.merge(country, payload);
        dbCountry.getDao().update(country);
        return country;
    }

}
