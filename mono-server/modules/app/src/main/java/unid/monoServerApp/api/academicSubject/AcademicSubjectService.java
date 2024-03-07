package unid.monoServerApp.api.academicSubject;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.AcademicSubjectPojo;
import unid.jooqMono.model.tables.pojos.AcademicSubjectResourcePojo;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.AcademicSubjectMapper;
import unid.monoServerApp.mapper.AcademicSubjectResourceMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.S3Service;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.AcademicSubjectRequest;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AcademicSubjectService {
    private final DbAcademicSubject dbAcademicSubject;
    private final DbAcademicSubjectResource dbAcademicSubjectResource;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final AcademicSubjectMapper academicSubjectMapper;
    private final AcademicSubjectResourceMapper academicSubjectResourceMapper;
    private final S3Service s3Service;
    private final ObjectMapper objectMapper;

    DbAcademicSubject.Result get(UUID id) {
        var table = dbAcademicSubject.getTable();
        return dbAcademicSubject.getQuery(table)
                .where(table.ID.eq(id).and(dbAcademicSubject.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Subject Not Found"))
                .into(DbAcademicSubject.Result.class);
    }

    AcademicSubjectPojo create(AcademicSubjectRequest payload) {
        sessionService.initDatabaseSession();
        var title = i18nMapper.toPojo(payload.getTitleI18n());
        dbI18N.getDao().insert(title);
        var desc = Optional.ofNullable(
                i18nMapper.toPojo(payload.getDescriptionI18n())
        );
        desc.ifPresent(d -> dbI18N.getDao().insert(d));
        var md = Optional.ofNullable(
                i18nMapper.toPojo(payload.getDescriptionMasterDegreeI18n())
        );
        md.ifPresent(d -> dbI18N.getDao().insert(d));
        var phd = Optional.ofNullable(
                i18nMapper.toPojo(payload.getDescriptionPhdI18n())
        );
        phd.ifPresent(d -> dbI18N.getDao().insert(d));
        var pojo = new AcademicSubjectPojo()
                .setTitleI18nId(title.getId())
                .setDescriptionI18nId(desc.map(I18nPojo::getId).orElse(null))
                .setDescriptionMasterDegreeI18nId(md.map(I18nPojo::getId).orElse(null))
                .setDescriptionPhdI18nId(phd.map(I18nPojo::getId).orElse(null));
        academicSubjectMapper.merge(pojo, payload);
        dbAcademicSubject.getDao().insert(pojo);
        insertOrUpdateResources(pojo.getId(), payload.getResources());
        return pojo;
    }

    AcademicSubjectPojo update(UUID id, AcademicSubjectRequest payload) {
        sessionService.initDatabaseSession();
        var subject = get(id);
        Db.parentCrud(subject::getDescriptionI18n, payload::getDescriptionI18n)
                .onUpdate((db, obj) -> {
                    i18nMapper.merge(db, obj);
                    dbI18N.getDao().update(db);
                })
                .onDelete((db) -> {
                    subject.setDescriptionI18nId(null);
                    dbAcademicSubject.getDao().update(subject);
                    dbI18N.getDao().deleteById(db.getId());
                })
                .onInsert((obj) -> {
                    var pojo = i18nMapper.toPojo(obj);
                    dbI18N.getDao().insert(pojo);
                    subject.setDescriptionI18nId(pojo.getId());
                    dbAcademicSubject.getDao().update(subject);
                })
                .execute();
        Db.parentCrud(subject::getDescriptionMasterDegreeI18n, payload::getDescriptionMasterDegreeI18n)
                .onUpdate((db, obj) -> {
                    i18nMapper.merge(db, obj);
                    dbI18N.getDao().update(db);
                })
                .onDelete((db) -> {
                    subject.setDescriptionMasterDegreeI18nId(null);
                    dbAcademicSubject.getDao().update(subject);
                    dbI18N.getDao().deleteById(db.getId());
                })
                .onInsert((obj) -> {
                    var pojo = i18nMapper.toPojo(obj);
                    dbI18N.getDao().insert(pojo);
                    subject.setDescriptionMasterDegreeI18nId(pojo.getId());
                    dbAcademicSubject.getDao().update(subject);
                })
                .execute();
        Db.parentCrud(subject::getDescriptionPhdI18n, payload::getDescriptionPhdI18n)
                .onUpdate((db, obj) -> {
                    i18nMapper.merge(db, obj);
                    dbI18N.getDao().update(db);
                })
                .onDelete((db) -> {
                    subject.setDescriptionPhdI18nId(null);
                    dbAcademicSubject.getDao().update(subject);
                    dbI18N.getDao().deleteById(db.getId());
                })
                .onInsert((obj) -> {
                    var pojo = i18nMapper.toPojo(obj);
                    dbI18N.getDao().insert(pojo);
                    subject.setDescriptionPhdI18nId(pojo.getId());
                    dbAcademicSubject.getDao().update(subject);
                })
                .execute();
        var title = Optional.ofNullable(
                dbI18N.getDao().fetchOneById(subject.getTitleI18nId())
        ).orElseThrow(() -> Exceptions.notFound("Title Not Found"));
        i18nMapper.merge(title, payload.getTitleI18n());
        dbI18N.getDao().update(title);
        academicSubjectMapper.merge(subject, payload);
        dbAcademicSubject.getDao().update(subject);
        insertOrUpdateResources(id, payload.getResources());
        return subject;
    }

    private void insertOrUpdateResources(
            @NotNull UUID subjectId,
            @Nullable List<AcademicSubjectResourcePayload> resources
    ) {
        var table = dbAcademicSubjectResource.getTable();
        var existingRecords = dbAcademicSubjectResource.getQuery(table)
                .where(table.ACADEMIC_SUBJECT_ID.eq(subjectId))
                .fetchInto(DbAcademicSubjectResource.Result.class);
        Db.parentListCrud(
                () -> existingRecords,
                () -> resources,
                AcademicSubjectResourcePojo::getId,
                AcademicSubjectResourcePayload::getId,
                primaryKey -> Exceptions.notFound("No Such Resource", primaryKey.toString()),
                crud -> crud
                        .onUpdate((db, source) -> {
                            var title = dbI18N.getDao().fetchOneById(db.getTitleI18nId());
                            i18nMapper.merge(title, source.getTitleI18n());
                            dbI18N.getDao().update(title);
                            var pojo = academicSubjectResourceMapper.toPojo(source)
                                    .setAcademicSubjectId(subjectId)
                                    .setTitleI18nId(db.getTitleI18nId());
                            dbAcademicSubjectResource.getDao().update(pojo.setId(db.getId()));

                            s3Service.oldToNew(db.getThumbnail(), source.getThumbnail());
                        })
                        .onDelete((db) -> {
                            dbAcademicSubjectResource.getDao().deleteById(db.getId());
                            dbI18N.getDao().deleteById(db.getTitleI18nId());
//                            s3Service.deleteObject(db.getThumbnail());
                        })
                        .onInsert((source) -> {
                            var title = i18nMapper.toPojo(source.getTitleI18n());
                            dbI18N.getDao().insert(title);
                            var pojo = academicSubjectResourceMapper.toPojo(source)
                                    .setAcademicSubjectId(subjectId)
                                    .setTitleI18nId(title.getId());
                            dbAcademicSubjectResource.getDao().insert(pojo);
                            s3Service.tempToPermanent(source.getThumbnail());
                        })
                        .execute()
        );
    }
}
