package unid.monoServerApp.api.curriculum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.CurriculumPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.curriculum.DbCurriculum;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.CurriculumMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.CurriculumRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CurriculumService {
    private final DbCurriculum dbCurriculum;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final CurriculumMapper curriculumMapper;

    DbCurriculum.Result get(UUID id) {
        var table = dbCurriculum.getTable();
        return dbCurriculum.getQuery(table)
                .where(table.ID.eq(id).and(dbCurriculum.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Curriculum Not Found"))
                .into(DbCurriculum.Result.class);
    }

    CurriculumPojo create(CurriculumRequest payload) {
        sessionService.initDatabaseSession();
        var name = i18nMapper.toPojo(payload.getNameI18n());
        dbI18N.getDao().insert(name);
        var pojo = new CurriculumPojo();
        curriculumMapper.merge(
                pojo,
                payload
        );
        dbCurriculum.getDao().insert(pojo.setNameI18nId(name.getId()));
        return pojo;
    }

    CurriculumPojo update(UUID id, CurriculumRequest payload) {
        sessionService.initDatabaseSession();
        var curriculum = get(id);
        var name = Optional.ofNullable(dbI18N.getDao().fetchOneById(curriculum.getNameI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Name Not Found"));
        i18nMapper.merge(name, payload.getNameI18n());
        dbI18N.getDao().update(name);
        curriculumMapper.merge(curriculum, payload);
        dbCurriculum.getDao().update(curriculum);
        return curriculum;
    }
}
