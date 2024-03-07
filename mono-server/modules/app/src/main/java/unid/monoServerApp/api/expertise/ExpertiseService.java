package unid.monoServerApp.api.expertise;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.AcademicMajorPojo;
import unid.jooqMono.model.tables.pojos.ExpertiseAcademicMajorMapPojo;
import unid.jooqMono.model.tables.pojos.ExpertisePojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerApp.database.table.expertise.DbExpertiseMajorMap;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.ExpertiseMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.ExpertiseRequest;
import unid.monoServerMeta.model.I18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static unid.jooqMono.model.Tables.I18N;
import static unid.jooqMono.model.Tables.TAG;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ExpertiseService {
    private final DbExpertise dbExpertise;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final ExpertiseMapper expertiseMapper;
    private final DbExpertiseMajorMap dbExpertiseMajorMap;

    DbExpertise.Result get(UUID id) {
        var table = dbExpertise.getTable();
        return dbExpertise.getQuery(table)
                .where(table.ID.eq(id).and(dbExpertise.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Expertise Not Found"))
                .into(DbExpertise.Result.class);
    }

    ExpertisePojo create(ExpertiseRequest payload) {
        sessionService.initDatabaseSession();
        var desc = i18nMapper.toPojo(payload.getDescriptionI18n());
        dbI18N.getDao().insert(desc);
        var pojo = new ExpertisePojo();
        expertiseMapper.merge(pojo, payload);
        dbExpertise.getDao().insert(pojo.setDescriptionI18nId(desc.getId()));
        var majors = ListUtils.defaultIfNull(payload.getMajors(), new ArrayList<>());
        majors.forEach(major -> {
            dbExpertiseMajorMap.getDao().insert(
                    new ExpertiseAcademicMajorMapPojo()
                            .setExpertiseId(pojo.getId())
                            .setAcademicMajorId(major)
            );
        });
        return pojo;
    }

    ExpertisePojo update(UUID id, ExpertiseRequest payload) {
        sessionService.initDatabaseSession();
        var expertise = get(id);
        var desc = Optional.ofNullable(dbI18N.getDao().fetchOneById(expertise.getDescriptionI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Description Not Found"));
        i18nMapper.merge(desc, payload.getDescriptionI18n());
        dbI18N.getDao().update(desc);
        expertiseMapper.merge(expertise, payload);
        dbExpertise.getDao().update(expertise);
        Db.parentListCrud(
                expertise::getMajors,
                payload::getMajors,
                AcademicMajorPojo::getId,
                source -> source,
                null,
                crud -> crud
                        .onDelete((db) -> {
                            dbExpertiseMajorMap.getDao().deleteById(db.getId());
                        })
                        .onInsert((source) -> {
                            dbExpertiseMajorMap.getDao().insert(
                                    new ExpertiseAcademicMajorMapPojo()
                                            .setExpertiseId(expertise.getId())
                                            .setAcademicMajorId(source)
                            );
                        })
                        .execute()
        );
        return expertise;
    }

    public List<I18n> list() {
        return dbExpertise.getDsl().select(I18N.ENGLISH,I18N.CHINESE_SIMPLIFIED,I18N.CHINESE_TRADITIONAL)
                .from(TAG)
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(TAG.TAG_CATEGORY.eq(TagCategoryEnum.EXPERTISE))
                .groupBy(I18N.ENGLISH,I18N.CHINESE_SIMPLIFIED,I18N.CHINESE_TRADITIONAL)
                .fetchInto(I18n.class);
    }
}
