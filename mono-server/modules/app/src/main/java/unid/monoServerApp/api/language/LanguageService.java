package unid.monoServerApp.api.language;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.LanguagePojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.LanguageMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.LanguageRequest;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static unid.jooqMono.model.Tables.I18N;
import static unid.jooqMono.model.Tables.TAG;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LanguageService {
    private final DbLanguage dbLanguage;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final LanguageMapper languageMapper;

    DbLanguage.Result get(UUID id) {
        var table = dbLanguage.getTable();
        return dbLanguage.getQuery(table)
                .where(table.ID.eq(id).and(dbLanguage.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Language Not Found"))
                .into(DbLanguage.Result.class);
    }

    LanguagePojo create(LanguageRequest payload) {
        sessionService.initDatabaseSession();
        var desc = i18nMapper.toPojo(payload.getDescriptionI18n());
        dbI18N.getDao().insert(desc);
        var pojo = new LanguagePojo();
        languageMapper.merge(pojo, payload);
        dbLanguage.getDao().insert(pojo.setDescriptionI18nId(desc.getId()));
        return pojo;
    }

    LanguagePojo update(UUID id, LanguageRequest payload) {
        sessionService.initDatabaseSession();
        var language = get(id);
        var desc = Optional.ofNullable(dbI18N.getDao().fetchOneById(language.getDescriptionI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Description Not Found"));
        i18nMapper.merge(desc, payload.getDescriptionI18n());
        dbI18N.getDao().update(desc);
        languageMapper.merge(language, payload);
        dbLanguage.getDao().update(language);
        return language;
    }

    public List<I18n> list() {
        return dbLanguage.getDsl().select(I18N.ENGLISH,I18N.CHINESE_SIMPLIFIED,I18N.CHINESE_TRADITIONAL)
                .from(TAG)
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(TAG.TAG_CATEGORY.eq(TagCategoryEnum.LANGUAGE))
                .groupBy(I18N.ENGLISH,I18N.CHINESE_SIMPLIFIED,I18N.CHINESE_TRADITIONAL)
                .fetchInto(I18n.class);
    }
}
