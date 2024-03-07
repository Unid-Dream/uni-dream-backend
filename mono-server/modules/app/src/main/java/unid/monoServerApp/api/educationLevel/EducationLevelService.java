package unid.monoServerApp.api.educationLevel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.EducationLevelPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.EducationLevelMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.EducationLevelRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EducationLevelService {
    private final DbEducationLevel dbEducationLevel;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final EducationLevelMapper educationLevelMapper;

    DbEducationLevel.Result get(UUID id) {
        var table = dbEducationLevel.getTable();
        return dbEducationLevel.getQuery(table)
                .where(table.ID.eq(id).and(dbEducationLevel.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Education Level Not Found"))
                .into(DbEducationLevel.Result.class);
    }

    EducationLevelPojo create(EducationLevelRequest payload) {
        sessionService.initDatabaseSession();
        var description = i18nMapper.toPojo(payload.getDescriptionI18n());
        dbI18N.getDao().insert(description);
        var pojo = new EducationLevelPojo();
        educationLevelMapper.merge(pojo, payload);
        dbEducationLevel.getDao().insert(pojo.setDescriptionI18nId(description.getId()));
        return pojo;
    }

    EducationLevelPojo update(UUID id, EducationLevelRequest payload) {
        sessionService.initDatabaseSession();
        var educationLevel = get(id);
        var description = Optional.ofNullable(dbI18N.getDao().fetchOneById(educationLevel.getDescriptionI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Description Not Found"));
        i18nMapper.merge(description, payload.getDescriptionI18n());
        dbI18N.getDao().update(description);
        educationLevelMapper.merge(educationLevel, payload);
        dbEducationLevel.getDao().update(educationLevel);
        return educationLevel;
    }
}
