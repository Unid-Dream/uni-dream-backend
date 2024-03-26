package unid.monoServerApp.api.academicSubject;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.enums.AcademicSubjectResourceTypeEnum;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.*;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajorSubjectMap;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.mapper.AcademicSubjectMapper;
import unid.monoServerApp.mapper.AcademicSubjectResourceMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.S3Service;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.multiset;
import static pwh.springWebStarter.response.UniErrorCode.ACADEMIC_SUBJECT_MAP_IS_NOT_EXIST;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AcademicSubjectService {
    private final DbAcademicSubject dbAcademicSubject;
    private final DbAcademicMajorSubjectMap dbAcademicMajorSubjectMap;
    private final DbAcademicSubjectResource dbAcademicSubjectResource;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final AcademicSubjectMapper academicSubjectMapper;
    private final AcademicSubjectResourceMapper academicSubjectResourceMapper;
    private final S3Service s3Service;
    private final ObjectMapper objectMapper;
    private final DbTag dbTag;

//    DbAcademicSubject.Result get(UUID id) {
//        var table = dbAcademicSubject.getTable();
//        return dbAcademicSubject.getQuery(table)
//                .where(table.ID.eq(id).and(dbAcademicSubject.validateCondition(table)))
//                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Subject Not Found"))
//                .into(DbAcademicSubject.Result.class);
//    }


    AcademicSubjectPayload get(UUID id){
        return dbAcademicSubject.getDsl()
                .select(
                        ACADEMIC_SUBJECT.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(ACADEMIC_SUBJECT.TITLE_I18N_ID.eq(I18N.ID))
                        ).as(AcademicSubjectPayload.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),

                        DSL.multiset(
                                DSL.selectFrom(I18N).where(ACADEMIC_SUBJECT.DESCRIPTION_I18N_ID.eq(I18N.ID))
                        ).as(AcademicSubjectPayload.Fields.descriptionI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),

                        DSL.multiset(
                                DSL.select(
                                                ACADEMIC_MAJOR.asterisk(),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(ACADEMIC_MAJOR.TITLE_I18N_ID))
                                                ).as(AcademicSubjectPayload.AcademicMajorPayload.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                        )
                                        .from(ACADEMIC_MAJOR)
                                        .where(ACADEMIC_MAJOR.ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_MAJOR_ID))
                        ).as(AcademicSubjectPayload.Fields.major).convertFrom(r->r.isEmpty()?null:r.get(0).into(AcademicSubjectPayload.AcademicMajorPayload.class)),

                        DSL.multiset(
                                DSL.select(
                                                ACADEMIC_SUBJECT_RESOURCE.asterisk(),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(ACADEMIC_SUBJECT_RESOURCE.TITLE_I18N_ID))
                                                ).as(AcademicSubjectPayload.AcademicSubjectResourcePayload.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                        )
                                        .from(ACADEMIC_SUBJECT_RESOURCE)
                                        .where(ACADEMIC_SUBJECT.ID.eq(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID))
                        ).as(AcademicSubjectPayload.Fields.resources).convertFrom(r->r.isEmpty()?null:r.into(AcademicSubjectPayload.AcademicSubjectResourcePayload.class))
                )
                .from(ACADEMIC_MAJOR_SUBJECT_MAP,ACADEMIC_SUBJECT,I18N)
                .where(
                        ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID).and(ACADEMIC_SUBJECT.TITLE_I18N_ID.eq(I18N.ID))
                ).and(ACADEMIC_SUBJECT.ID.eq(id))
                .fetchOneInto(AcademicSubjectPayload.class);
    }







    @Transactional(rollbackFor = Exception.class)
    AcademicSubjectPojo create(AcademicSubjectPayload payload) {
        sessionService.initDatabaseSession();
        var title = i18nMapper.toPojo(payload.getTitleI18n());
        dbI18N.getDao().insert(title);
        var desc = Optional.ofNullable(
                i18nMapper.toPojo(payload.getDescriptionI18n())
        );
        desc.ifPresent(d -> dbI18N.getDao().insert(d));
        var pojo = new AcademicSubjectPojo()
                .setTitleI18nId(title.getId())
                .setDescriptionI18nId(desc.map(I18nPojo::getId).orElse(null));
        academicSubjectMapper.merge(pojo, payload);
        dbAcademicSubject.getDao().insert(pojo);

        dbAcademicMajorSubjectMap.getDao().insert(
                new AcademicMajorSubjectMapPojo()
                        .setAcademicMajorId(payload.getMajor().getId())
                        .setAcademicSubjectId(pojo.getId())
        );


        insertOrUpdateResources(pojo.getId(),payload.getResources());

        dbTag.getDao().insert(
                new TagPojo()
                        .setDescriptionI18nId(title.getId())
                        .setTagCategory(TagCategoryEnum.ACADEMIC_SUBJECT)
        );

        return pojo;
    }

    private void insertOrUpdateResources(
            @NotNull UUID subjectId,
            @Nullable List<AcademicSubjectPayload.AcademicSubjectResourcePayload> resources
    ) {
        if(resources == null){
            return;
        }
        var table = dbAcademicSubjectResource.getTable();

        dbAcademicSubjectResource.getDsl().deleteFrom(table)
                        .where(table.ACADEMIC_SUBJECT_ID.eq(subjectId))
                                .execute();
        resources.forEach(resource->{
            I18nPojo i18n = i18nMapper.toPojo(resource.getTitleI18n());
            dbI18N.getDao().insert(i18n);
            //查询 resource id 是否存在
            dbAcademicSubjectResource.getDao()
                    .insert(academicSubjectResourceMapper.toPojo(resource)
                            .setTitleI18nId(i18n.getId())
                            .setAcademicSubjectId(subjectId));
        });

    }

    public AcademicMajorResponse getOne(UUID subjectId) {
        DbAcademicSubject.Result result =  dbAcademicSubject.getDsl().select(
                        ACADEMIC_SUBJECT.asterisk(),
                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_SUBJECT.TITLE_I18N_ID))
                        ).as(DbAcademicSubject.Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_SUBJECT.DESCRIPTION_I18N_ID))
                        ).as(DbAcademicSubject.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_SUBJECT.DESCRIPTION_MASTER_DEGREE_I18N_ID))
                        ).as(DbAcademicSubject.Result.Fields.descriptionMasterDegreeI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_SUBJECT.DESCRIPTION_PHD_I18N_ID))
                        ).as(DbAcademicSubject.Result.Fields.descriptionPhdI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(ACADEMIC_SUBJECT.DESCRIPTION_PHD_I18N_ID))
                        ).as(DbAcademicSubject.Result.Fields.descriptionPhdI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),

                        multiset(
                                DSL.select(
                                                ACADEMIC_SUBJECT_RESOURCE.asterisk(),
                                                multiset(
                                                        DSL.select()
                                                                .from(I18N)
                                                                .where(I18N.ID.eq(ACADEMIC_SUBJECT_RESOURCE.TITLE_I18N_ID))
                                                ).as(DbAcademicSubjectResource.Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                                                multiset(
                                                        DSL.select()
                                                                .from(I18N)
                                                                .where(I18N.ENGLISH.eq(ACADEMIC_SUBJECT_RESOURCE.AUTHOR))
                                                ).as(DbAcademicSubjectResource.Result.Fields.authorI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                        )
                                        .from(ACADEMIC_SUBJECT_RESOURCE)
                                        .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.READINGS)))
                        ).as(DbAcademicSubject.Result.Fields.books).convertFrom(r -> r.isEmpty() ? null : r.into(DbAcademicSubjectResource.Result.class)),


                        multiset(
                                DSL.select(
                                                ACADEMIC_SUBJECT_RESOURCE.asterisk(),
                                                multiset(
                                                        DSL.select()
                                                                .from(I18N)
                                                                .where(I18N.ID.eq(ACADEMIC_SUBJECT_RESOURCE.TITLE_I18N_ID))
                                                ).as(DbAcademicSubjectResource.Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                                                multiset(
                                                        DSL.select()
                                                                .from(I18N)
                                                                .where(I18N.ENGLISH.eq(ACADEMIC_SUBJECT_RESOURCE.AUTHOR))
                                                ).as(DbAcademicSubjectResource.Result.Fields.authorI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                        )
                                        .from(ACADEMIC_SUBJECT_RESOURCE)
                                        .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.VIDEO)))
                        ).as(DbAcademicSubject.Result.Fields.videos).convertFrom(r -> r.isEmpty() ? null : r.into(DbAcademicSubjectResource.Result.class)),

                        multiset(
                                DSL.select(
                                                ACADEMIC_SUBJECT_RESOURCE.asterisk(),
                                                multiset(
                                                        DSL.select()
                                                                .from(I18N)
                                                                .where(I18N.ID.eq(ACADEMIC_SUBJECT_RESOURCE.TITLE_I18N_ID))
                                                ).as(DbAcademicSubjectResource.Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                                                multiset(
                                                        DSL.select()
                                                                .from(I18N)
                                                                .where(I18N.ENGLISH.eq(ACADEMIC_SUBJECT_RESOURCE.AUTHOR))
                                                ).as(DbAcademicSubjectResource.Result.Fields.authorI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                        )
                                        .from(ACADEMIC_SUBJECT_RESOURCE)
                                        .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.PODCAST)))
                        ).as(DbAcademicSubject.Result.Fields.podcasts).convertFrom(r -> r.isEmpty() ? null : r.into(DbAcademicSubjectResource.Result.class)),

                        multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(ACADEMIC_SUBJECT.DESCRIPTION_PHD_I18N_ID))
                                        .union(
                                                DSL.select()
                                                        .from(I18N)
                                                        .where(I18N.ID.eq(ACADEMIC_SUBJECT.DESCRIPTION_MASTER_DEGREE_I18N_ID))
                                        )
                        ).as(DbAcademicSubject.Result.Fields.answers).convertFrom(r -> r.isEmpty() ? null : r.into(DbI18N.Result.class))

                ).from(ACADEMIC_SUBJECT)
                .where(ACADEMIC_SUBJECT.ID.eq(subjectId))
                .fetchOneInto(DbAcademicSubject.Result.class);
        return null;
    }

    public UniPageResponse<AcademicSubjectPayload> page(AcademicSubjectPageRequest request) {
        List<AcademicSubjectPayload> payload = dbAcademicSubject.getDsl()
                .select(
                        ACADEMIC_SUBJECT.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(ACADEMIC_SUBJECT.TITLE_I18N_ID.eq(I18N.ID))
                        ).as(AcademicSubjectPayload.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),

                        DSL.multiset(
                                DSL.selectFrom(I18N).where(ACADEMIC_SUBJECT.DESCRIPTION_I18N_ID.eq(I18N.ID))
                        ).as(AcademicSubjectPayload.Fields.descriptionI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),

                        DSL.multiset(
                                DSL.select(
                                                ACADEMIC_MAJOR.asterisk(),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(ACADEMIC_MAJOR.TITLE_I18N_ID))
                                                ).as(AcademicSubjectPayload.AcademicMajorPayload.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                        )
                                        .from(ACADEMIC_MAJOR)
                                        .where(ACADEMIC_MAJOR.ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_MAJOR_ID))
                        ).as(AcademicSubjectPayload.Fields.major).convertFrom(r->r.isEmpty()?null:r.get(0).into(AcademicSubjectPayload.AcademicMajorPayload.class)),

                        DSL.multiset(
                                DSL.select(
                                                ACADEMIC_SUBJECT_RESOURCE.asterisk(),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(ACADEMIC_SUBJECT_RESOURCE.TITLE_I18N_ID))
                                                ).as(AcademicSubjectPayload.AcademicSubjectResourcePayload.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                        )
                                        .from(ACADEMIC_SUBJECT_RESOURCE)
                                        .where(ACADEMIC_SUBJECT.ID.eq(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID))
                        ).as(AcademicSubjectPayload.Fields.resources).convertFrom(r->r.isEmpty()?null:r.into(AcademicSubjectPayload.AcademicSubjectResourcePayload.class))
                )
                .select(count().over().as(AcademicSubjectPayload.Fields.total))
                .from(ACADEMIC_MAJOR_SUBJECT_MAP,ACADEMIC_SUBJECT,I18N)
                .where(
                        ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID).and(ACADEMIC_SUBJECT.TITLE_I18N_ID.eq(I18N.ID))
                ).and(StrUtil.isEmpty(request.getSearchKey()) ? DSL.noCondition() :
                        I18N.ENGLISH.contains(request.getSearchKey())
                                .or(I18N.CHINESE_SIMPLIFIED.contains(request.getSearchKey()))
                                .or(I18N.CHINESE_TRADITIONAL.contains(request.getSearchKey())))
                .orderBy(ACADEMIC_MAJOR_SUBJECT_MAP.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(AcademicSubjectPayload.class);

        int totalSize = payload.stream()
                .findFirst()
                .map(AcademicSubjectPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }

    @Transactional(rollbackFor = Exception.class)
    AcademicSubjectPojo update(AcademicSubjectPayload payload) {
        sessionService.initDatabaseSession();
        var subject = dbAcademicSubject.getDao().fetchOneById(payload.getId());
        Optional.ofNullable(subject).orElseThrow(()->Exceptions.business(UniErrorCode.ACADEMIC_SUBJECT_IS_NOT_EXIST));
        academicSubjectMapper.merge(subject, payload);
        dbAcademicSubject.getDao().update(subject);


        var academicMajorSubjectMap = dbAcademicMajorSubjectMap.getDsl()
                        .select().from(ACADEMIC_MAJOR_SUBJECT_MAP)
                        .where(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID.eq(subject.getId()))
                        .fetchOptional().orElseThrow(()->Exceptions.business(ACADEMIC_SUBJECT_MAP_IS_NOT_EXIST))
                        .into(AcademicMajorSubjectMapPojo.class);
        academicMajorSubjectMap.setAcademicMajorId(payload.getMajor().getId());
        dbAcademicMajorSubjectMap.getDao().update(academicMajorSubjectMap);


        Optional.ofNullable(subject.getTitleI18nId()).ifPresent((id)->{
            dbI18N.getDao().update(i18nMapper.toPojo(payload.getTitleI18n()).setId(id));
        });

        Optional.ofNullable(subject.getDescriptionI18nId()).ifPresent((id)->{
            dbI18N.getDao().update(i18nMapper.toPojo(payload.getDescriptionI18n()).setId(id));
        });

        insertOrUpdateResources(subject.getId(),payload.getResources());

        return subject;

    }

    public void delete(UUID id) {
        dbAcademicSubject.getDao().deleteById(id);
    }
}
