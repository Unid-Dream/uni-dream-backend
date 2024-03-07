package unid.monoServerApp.api.meeting.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.ErrorCodeGlobal;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteItemPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNotePojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.Properties;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNote;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.EducatorSessionNoteMapper;
import unid.monoServerApp.mapper.EnumMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.CodTransactionSubject;
import unid.monoServerMeta.api.MeetingReservationPaymentRequest;
import unid.monoServerMeta.api.MeetingReservationRequest;
import unid.monoServerMeta.api.EducatorSessionNoteRequest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EducatorSessionNoteService {
    private final DbI18N dbI18N;
    private final SessionService sessionService;
    private final DbEducatorSessionNote dbEducatorSessionNote;
    private final DbEducatorSessionNoteItem dbEducatorSessionNoteItem;
    private final I18nMapper i18nMapper;
    private final EducatorSessionNoteMapper educatorSessionNoteMapper;

    DbEducatorSessionNote.Result get(UUID id) {
        var table = dbEducatorSessionNote.getTable();
        return dbEducatorSessionNote.getQuery(table)
                .where(table.ID.eq(id).and(dbEducatorSessionNote.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("Session Note Not Found"))
                .into(DbEducatorSessionNote.Result.class);
    }

    EducatorSessionNotePojo create(EducatorSessionNoteRequest payload) {
        sessionService.initDatabaseSession();
        var titleI18n = i18nMapper.toPojo(payload.getTitleI18n());
        dbI18N.getDao().insert(titleI18n);
        var subtitleI18n = i18nMapper.toPojo(payload.getSubTitleI18n());
        dbI18N.getDao().insert(subtitleI18n);
        var descI18n = i18nMapper.toPojo(payload.getDescriptionI18n());
        dbI18N.getDao().insert(descI18n);
        var pojo = new EducatorSessionNotePojo()
                .setTitleI18nId(titleI18n.getId())
                .setSubTitleI18nId(subtitleI18n.getId())
                .setDescriptionI18nId(descI18n.getId());
        educatorSessionNoteMapper.merge(pojo, payload);
        dbEducatorSessionNote.getDao().insert(pojo.setObsoleted(false));
        var items = payload.getItems();
        for(var item : items) {
            var itemTitleI18n = i18nMapper.toPojo(item.getTitleI18n());
            dbI18N.getDao().insert(itemTitleI18n);
            var itemPojo = new EducatorSessionNoteItemPojo()
                    .setTitleI18nId(itemTitleI18n.getId())
                    .setEducatorSessionNoteId(pojo.getId());
            educatorSessionNoteMapper.merge(itemPojo, item);
            dbEducatorSessionNoteItem.getDao().insert(itemPojo);
        }
        return pojo;
    }

    EducatorSessionNotePojo update(UUID id, EducatorSessionNoteRequest payload) {
        sessionService.initDatabaseSession();
        obsolete(id);
        return create(payload);
    }

    EducatorSessionNotePojo obsolete(UUID id) {
        sessionService.initDatabaseSession();
        var sessionNote = get(id);
        dbEducatorSessionNote.getDao().update(sessionNote.setObsoleted(true));
        return sessionNote;
    }
}
