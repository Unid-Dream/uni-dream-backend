package unid.monoServerApp.api.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.TagPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.TagCategoryMapper;
import unid.monoServerApp.mapper.TagMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.api.version2.request.TagPagePayload;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.count;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TagService {
    private final TagCategoryMapper tagCategoryMapper;
    private final DbTag dbTag;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final TagMapper tagMapper;

    public List<TagResponse> page(TagCategoryEnum tagCategoryEnum) {
        List<DbTag.Result> list = dbTag.getDsl().select(
                        TAG.asterisk(),
                        DSL.multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                        ).as(DbTag.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                )
                .from(TAG,I18N)
                .where(TAG.TAG_CATEGORY.eq(tagCategoryEnum).and(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID)).and(I18N.ENGLISH.isNotNull()))
                .fetchInto(DbTag.Result.class);
        return tagMapper.toResponse(list);
    }


    public List<TagResponse> list(TagCategoryEnum tagCategoryEnum) {
        List<DbTag.Result> list = dbTag.getDsl().select(
                        TAG.asterisk(),
                        DSL.multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                        ).as(DbTag.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                )
                .from(TAG,I18N)
                .where(TAG.TAG_CATEGORY.eq(tagCategoryEnum).and(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID)).and(I18N.ENGLISH.isNotNull()))
                .fetchInto(DbTag.Result.class);
        return tagMapper.toResponse(list);
    }


    public List<TagResponse> listAcademicMajorTags() {
        List<DbTag.Result> list = dbTag.getDsl().select(
                        ACADEMIC_MAJOR.asterisk(),
                        DSL.multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(ACADEMIC_MAJOR.TITLE_I18N_ID))
                        ).as(DbTag.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                )
                .from(ACADEMIC_MAJOR)
                .fetchInto(DbTag.Result.class);
        return tagMapper.toResponse(list);
    }


    public UniPageResponse<TagResponse> page(TagPagePayload payload, TagCategoryEnum tagCategoryEnum) {
        List<DbTag.Result> list = dbTag.getDsl().select(
                        TAG.asterisk(),
                        DSL.multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                        ).as(DbTag.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                )
                .select(count().over().as(DbTag.Result.Fields.total))
                .from(TAG,I18N)
                .where(TAG.TAG_CATEGORY.eq(tagCategoryEnum).and(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID)).and(I18N.ENGLISH.isNotNull()))
                .orderBy(TAG.CREATED_ON.desc())
                .limit(payload.getPageSize())
                .offset((payload.getPageNumber() - 1) * payload.getPageSize())
                .fetchInto(DbTag.Result.class);

        int totalSize = list.stream()
                .findFirst()
                .map(DbTag.Result::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                payload.getPageNumber(),
                payload.getPageSize(),
                null,
                tagMapper.toResponse(list)
        );
    }


    public DbTag.Result get(UUID id) {
        var table = dbTag.getTable();
        return dbTag.getQuery(table)
                .where(table.ID.eq(id).and(dbTag.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Tag Not Found"))
                .into(DbTag.Result.class);
    }



    public TagPojo create(TagRequest payload) {
        sessionService.initDatabaseSession();
        var category = tagCategoryMapper.toRecord(payload.getTagCategory());
        var descriptionI18n = i18nMapper.toPojo(payload.getDescriptionI18n());
        dbI18N.getDao().insert(descriptionI18n);
        var pojo = new TagPojo()
                .setTagCategory(category)
                .setDescriptionI18nId(descriptionI18n.getId());
        dbTag.getDao().insert(pojo);
        return pojo;
    }


    public TagPojo update(UUID id, TagRequest payload) {
        sessionService.initDatabaseSession();
        var tag = get(id);
        var descriptionI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(tag.getDescriptionI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Description Not Found"));
        i18nMapper.merge(descriptionI18n, payload.getDescriptionI18n());
        dbI18N.getDao().update(descriptionI18n);
        tagMapper.merge(tag, payload);
        dbTag.getDao().update(tag);
        return tag;
    }

    public void delete(UUID id) {
        dbTag.getDao().deleteById(id);
    }
}
