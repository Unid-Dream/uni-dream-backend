package unid.monoServerApp.api.city;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.CityPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.CityMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.CityRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CityService {
    private final DbCity dbCity;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final CityMapper cityMapper;

    DbCity.Result get(UUID id) {
        var table = dbCity.getTable();
        return dbCity.getQuery(table)
                .where(table.ID.eq(id).and(dbCity.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("City Not Found"))
                .into(DbCity.Result.class);
    }

    CityPojo create(CityRequest payload) {
        sessionService.initDatabaseSession();
        var name = i18nMapper.toPojo(payload.getNameI18n());
        dbI18N.getDao().insert(name);
        var pojo = new CityPojo()
                .setNameI18nId(name.getId())
                .setCountryId(payload.getCountryId());
        dbCity.getDao().insert(pojo);
        return pojo;
    }

    CityPojo update(UUID id, CityRequest payload) {
        sessionService.initDatabaseSession();
        var city = get(id);
        var nameI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(city.getNameI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Name Not Found"));
        i18nMapper.merge(nameI18n, payload.getNameI18n());
        dbI18N.getDao().update(nameI18n);
        cityMapper.merge(city, payload);
        dbCity.getDao().update(city);
        return city;
    }
}
