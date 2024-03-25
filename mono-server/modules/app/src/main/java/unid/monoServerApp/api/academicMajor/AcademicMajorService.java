package unid.monoServerApp.api.academicMajor;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.DAO;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pwh.springWebStarter.response.UniErrorCode;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.AcademicSubjectResourceTypeEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.I18nTable;
import unid.jooqMono.model.tables.pojos.*;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajorSubjectMap;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileSection;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.mapper.AcademicMajorMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerApp.util.TypeSerialNumberUtils;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.api.version2.request.AcademicMajorCreateRequest;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SerialNumberType;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AcademicMajorService {
    private final DbAcademicMajor dbAcademicMajor;
    private final DbAcademicSubject dbAcademicSubject;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final AcademicMajorMapper academicMajorMapper;
    private final DbAcademicMajorSubjectMap dbAcademicMajorSubjectMap;
    private final DbAcademicSubjectResource dbAcademicSubjectResource;
    private final DbTag dbTag;
    private final RedisTemplate<String, String>  redisTemplateRefCache;

    AcademicMajorPayload get(UUID id) {
        var table = dbAcademicMajor.getTable();
        return dbAcademicMajor.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.TITLE_I18N_ID))
                        ).as(AcademicMajorPayload.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.DESCRIPTION_I18N_ID))
                        ).as(AcademicMajorPayload.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                )
                .from(table)
                .where(table.ID.eq(id))
                .fetchOptionalInto(AcademicMajorPayload.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.ACADEMIC_MAJOR_IS_NOT_EXIST));
    }


    public AcademicMajorPojo create(AcademicMajorCreateRequest payload) {
        sessionService.initDatabaseSession();
        var title = i18nMapper.toPojo(payload.getTitleI18n());
        dbI18N.getDao().insert(title);
        var desc = i18nMapper.toPojo(payload.getDescriptionI18n());
        dbI18N.getDao().insert(desc);
        var pojo = new AcademicMajorPojo();
        academicMajorMapper.merge(pojo, payload);
        pojo
                .setTitleI18nId(title.getId())
                .setDescriptionI18nId(desc.getId())
                .setSerialNumber(TypeSerialNumberUtils.generate(SerialNumberType.AcademicMajor,redisTemplateRefCache));
        dbAcademicMajor.getDao().insert(pojo);

        dbTag.getDao().insert(
                new TagPojo()
                        .setDescriptionI18nId(title.getId())
                        .setTagCategory(TagCategoryEnum.ACADEMIC_MAJOR)
        );

        return pojo;
    }


    public List<AcademicMajorResponse> list() {
        List<DbAcademicMajor.Result> results = dbAcademicMajor.getDsl()
                .select(
                        ACADEMIC_MAJOR.asterisk(),
                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_MAJOR.TITLE_I18N_ID))
                        ).as(DbAcademicMajor.Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),

                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_MAJOR.DESCRIPTION_I18N_ID))
                        ).as(DbAcademicMajor.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),

                        multiset(
                                DSL.select(
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
                                                                .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.READINGS)))
                                                ).as(DbAcademicSubject.Result.Fields.books).convertFrom(r -> r.isEmpty() ? null : r.into(DbAcademicSubjectResource.Result.class)),


                                                multiset(
                                                        DSL.select(
                                                                        ACADEMIC_SUBJECT_RESOURCE.ID,
                                                                        ACADEMIC_SUBJECT_RESOURCE.THUMBNAIL,
                                                                        ACADEMIC_SUBJECT_RESOURCE.URL,
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
                                                                .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.VIDEO)))
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
                                                                .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.PODCAST)))
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


                                        ).from(ACADEMIC_MAJOR_SUBJECT_MAP, ACADEMIC_SUBJECT)
                                        .where(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID))
                                        .and(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_MAJOR_ID.eq(ACADEMIC_MAJOR.ID))
                        ).as(DbAcademicMajor.Result.Fields.subjects).convertFrom(r -> r.isEmpty() ? null : r.into(DbAcademicSubject.Result.class))


                )
                .from(ACADEMIC_MAJOR)
                .fetchInto(DbAcademicMajor.Result.class);

        return academicMajorMapper.toResponse(results);
    }

    public AcademicMajorI18nResponse getOneBy(UUID id) {
        return dbAcademicMajor.getDsl().select(
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(ACADEMIC_MAJOR.TITLE_I18N_ID))
                        ).as(AcademicMajorI18nResponse.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),

                        multiset(
                                select(I18N.fields())
                                        .from(I18N, ACADEMIC_SUBJECT, ACADEMIC_MAJOR_SUBJECT_MAP)
                                        .where(I18N.ID.eq(ACADEMIC_SUBJECT.TITLE_I18N_ID).and(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID).and(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_MAJOR_ID.eq(ACADEMIC_MAJOR.ID))))
                        ).as(AcademicMajorI18nResponse.Fields.subjects).convertFrom(r -> r.isEmpty() ? null : r.into(I18n.class))
                )
                .from(ACADEMIC_MAJOR)
                .where(ACADEMIC_MAJOR.ID.eq(id))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Major Not Found"))
                .into(AcademicMajorI18nResponse.class);
    }


    public AcademicMajorResponse getOne(UUID academicMajorId, UUID academicSubjectId) {
        DbAcademicMajor.Result result = dbAcademicMajor.getDsl()
                .select(
                        ACADEMIC_MAJOR.asterisk(),
                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_MAJOR.TITLE_I18N_ID))
                        ).as(DbAcademicMajor.Result.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),

                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(ACADEMIC_MAJOR.DESCRIPTION_I18N_ID))
                        ).as(DbAcademicMajor.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),

                        multiset(
                                DSL.select(
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
                                                                .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.READINGS)))
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
                                                                .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.VIDEO)))
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
                                                                .where(ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID).and(ACADEMIC_SUBJECT_RESOURCE.TYPE.eq(AcademicSubjectResourceTypeEnum.PODCAST)))
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


                                        ).from(ACADEMIC_MAJOR_SUBJECT_MAP, ACADEMIC_SUBJECT)
                                        .where(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID.eq(ACADEMIC_SUBJECT.ID).and(academicSubjectId == null ? DSL.noCondition() : ACADEMIC_SUBJECT.ID.eq(academicSubjectId)))
                                        .and(ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_MAJOR_ID.eq(ACADEMIC_MAJOR.ID))
                        ).as(DbAcademicMajor.Result.Fields.subjects).convertFrom(r -> r.isEmpty() ? null : r.into(DbAcademicSubject.Result.class))
                )
                .from(ACADEMIC_MAJOR).where(ACADEMIC_MAJOR.ID.eq(academicMajorId))
                .fetchOneInto(DbAcademicMajor.Result.class);
        return academicMajorMapper.toResponse(result);
    }


    public UniPageResponse<AcademicMajorPayload> page(AcademicMajorPageRequest request) {
        var table = dbAcademicMajor.getTable();
        List<AcademicMajorPayload> payload = dbAcademicMajor.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.TITLE_I18N_ID))
                        ).as(AcademicMajorPayload.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.DESCRIPTION_I18N_ID))
                        ).as(AcademicMajorPayload.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                )
                .select(count().over().as(WritingSkillPayload.Fields.total))
                .from(table, I18N)
                .where(table.TITLE_I18N_ID.eq(I18N.ID))
                .and(StrUtil.isEmpty(request.getSearchKey()) ? DSL.noCondition() :
                        I18N.ENGLISH.contains(request.getSearchKey())
                                .or(I18N.CHINESE_SIMPLIFIED.contains(request.getSearchKey()))
                                .or(I18N.CHINESE_TRADITIONAL.contains(request.getSearchKey())))
                .orderBy(table.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(AcademicMajorPayload.class);

        int totalSize = payload.stream()
                .findFirst()
                .map(AcademicMajorPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }

    AcademicMajorPojo update(AcademicMajorPayload payload) {
        sessionService.initDatabaseSession();
        var table = dbAcademicMajor.getTable();
        AcademicMajorPojo pojo = dbAcademicMajor.getDsl()
                .selectFrom(table)
                .where(table.ID.eq(payload.getId()))
                .fetchOptionalInto(AcademicMajorPojo.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.ACADEMIC_MAJOR_IS_NOT_EXIST));
        Optional.ofNullable(pojo.getTitleI18nId())
                .ifPresent(id -> dbI18N.getDao().update(i18nMapper.toPojo(payload.getTitleI18n()).setId(id)));
        Optional.ofNullable(pojo.getDescriptionI18nId())
                .ifPresent(id -> dbI18N.getDao().update(i18nMapper.toPojo(payload.getDescriptionI18n()).setId(id)));

        academicMajorMapper.merge(pojo, payload);
        dbAcademicMajor.getDao().update(pojo);

        return pojo;

    }

    public void delete(UUID id) {
        dbAcademicMajor.getDao().deleteById(id);
    }
}

