package unid.monoServerApp.api.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.TagPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.TagCategoryMapper;
import unid.monoServerApp.mapper.TagMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.TagRequest;

import java.util.Optional;
import java.util.UUID;

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

    DbTag.Result get(UUID id) {
        var table = dbTag.getTable();
        return dbTag.getQuery(table)
                .where(table.ID.eq(id).and(dbTag.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Tag Not Found"))
                .into(DbTag.Result.class);
    }

    TagPojo create(TagRequest payload) {
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

    TagPojo update(UUID id, TagRequest payload) {
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
}
