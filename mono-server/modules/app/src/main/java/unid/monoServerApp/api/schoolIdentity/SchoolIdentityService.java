package unid.monoServerApp.api.schoolIdentity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.SchoolIdentityPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.schoolIdentity.DbSchoolIdentity;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.SchoolIdentityMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.SchoolIdentityRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SchoolIdentityService {
    private final DbSchoolIdentity dbSchoolIdentity;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final SchoolIdentityMapper schoolIdentityMapper;

    DbSchoolIdentity.Result get(UUID id) {
        var table = dbSchoolIdentity.getTable();
        return dbSchoolIdentity.getQuery(table)
                .where(table.ID.eq(id).and(dbSchoolIdentity.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Identity Not Found"))
                .into(DbSchoolIdentity.Result.class);
    }

    SchoolIdentityPojo create(SchoolIdentityRequest payload) {
        sessionService.initDatabaseSession();
        var name = i18nMapper.toPojo(payload.getNameI18n());
        dbI18N.getDao().insert(name);
        var pojo = new SchoolIdentityPojo();
        schoolIdentityMapper.merge(pojo, payload);
        dbSchoolIdentity.getDao().insert(pojo.setNameI18nId(name.getId()));
        return pojo;
    }

    SchoolIdentityPojo update(UUID id, SchoolIdentityRequest payload) {
        sessionService.initDatabaseSession();
        var identity = get(id);
        var name = Optional.ofNullable(dbI18N.getDao().fetchOneById(identity.getNameI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Name Not Found"));
        i18nMapper.merge(name, payload.getNameI18n());
        dbI18N.getDao().update(name);
        schoolIdentityMapper.merge(identity, payload);
        dbSchoolIdentity.getDao().update(identity);
        return identity;
    }
}
