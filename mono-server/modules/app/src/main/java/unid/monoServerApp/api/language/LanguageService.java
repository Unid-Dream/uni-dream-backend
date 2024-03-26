package unid.monoServerApp.api.language;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.LanguagePojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.LanguageMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.count;
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

//    DbLanguage.Result get(UUID id) {
//        var table = dbLanguage.getTable();
//        return dbLanguage.getQuery(table)
//                .where(table.ID.eq(id).and(dbLanguage.validateCondition(table)))
//                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Language Not Found"))
//                .into(DbLanguage.Result.class);
//    }


    LanguagePayload get(UUID id){
        var table = dbLanguage.getTable();
        return dbLanguage.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.DESCRIPTION_I18N_ID))
                        ).as(LanguagePayload.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .from(table)
                .where(table.ID.eq(id))
                .fetchOptional().orElseThrow(()->Exceptions.business(UniErrorCode.LANGUAGE_IS_NOT_EXIST))
                .into(LanguagePayload.class);
    }







    LanguagePojo create(LanguagePayload payload) {
        sessionService.initDatabaseSession();
        var desc = i18nMapper.toPojo(payload.getI18n());
        dbI18N.getDao().insert(desc);
        var pojo = new LanguagePojo();
        languageMapper.merge(pojo, payload);
        dbLanguage.getDao().insert(pojo.setDescriptionI18nId(desc.getId()));
        return pojo;
    }

    @Transactional(rollbackFor = Exception.class)
    LanguagePojo update(LanguagePayload payload) {
        sessionService.initDatabaseSession();
        var pojo = dbLanguage.getDao().fetchOneById(payload.getId());
        var desc = Optional.ofNullable(dbI18N.getDao().fetchOneById(pojo.getDescriptionI18nId()))
                .orElseThrow(() -> Exceptions.business(UniErrorCode.LANGUAGE_IS_NOT_EXIST));

        i18nMapper.merge(desc, payload.getI18n());
        dbI18N.getDao().update(desc);

        languageMapper.merge(pojo, payload);
        dbLanguage.getDao().update(pojo);
        return pojo;
    }

    public List<I18n> list() {
        return dbLanguage.getDsl().select(I18N.ENGLISH,I18N.CHINESE_SIMPLIFIED,I18N.CHINESE_TRADITIONAL)
                .from(TAG)
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(TAG.TAG_CATEGORY.eq(TagCategoryEnum.LANGUAGE))
                .groupBy(I18N.ENGLISH,I18N.CHINESE_SIMPLIFIED,I18N.CHINESE_TRADITIONAL)
                .fetchInto(I18n.class);
    }

    public UniPageResponse<LanguagePayload> page(LanguagePageRequest request) {
        var table = dbLanguage.getTable();
        List<LanguagePayload> payload = dbLanguage.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.DESCRIPTION_I18N_ID))
                        ).as(LanguagePayload.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .select(count().over().as(LanguagePayload.Fields.total))
                .from(table)
                .orderBy(table.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(LanguagePayload.class);
        int totalSize = payload.stream()
                .findFirst()
                .map(LanguagePayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }

    public void delete(UUID id) {
        dbLanguage.getDao().deleteById(id);
    }
}
