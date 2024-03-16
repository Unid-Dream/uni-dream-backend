package unid.monoServerApp.api.user.profile.educator.calendar.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteMapPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNote;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteMap;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.mapper.EducatorCalendarMapper;
import unid.monoServerApp.mapper.EducatorSessionNoteItemMapper;
import unid.monoServerApp.mapper.EducatorSessionNoteMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.EducatorSessionCommentRequest;
import unid.monoServerMeta.api.EducatorSessionNoteCommentResponse;
import unid.monoServerMeta.model.UniErrorCode;

import java.util.List;
import java.util.UUID;

import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EducatorSessionCommentService {
    private final DbEducatorCalendar dbEducatorCalendar;
    private final SessionService sessionService;
    private final EducatorCalendarMapper educatorCalendarMapper;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorSessionNoteMap dbEducatorSessionNoteMap;
    private final DbEducatorSessionNote dbEducatorSessionNote;
    private final DbEducatorSessionNoteItem dbEducatorSessionNoteItem;
    private final EducatorSessionNoteItemMapper educatorSessionNoteItemMapper;

    public DbEducatorSessionNoteMap.Result get(UUID calendarId, UUID commentId) {
        var table = dbEducatorSessionNoteMap.getTable();
        return dbEducatorSessionNoteMap.getQuery(table)
                .where(table.EDUCATOR_CALENDAR_ID.eq(calendarId))
                .and(table.ID.eq(commentId))
                .and(dbEducatorSessionNoteMap.validateCondition(table))
                .fetchOptionalInto(DbEducatorSessionNoteMap.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Comment Not Found"));
    }

    public List<EducatorSessionNoteCommentResponse> getComments(UUID calendarId) {
        List<DbEducatorSessionNoteItem.Result> list = dbEducatorSessionNoteItem
                .getDsl()
                .select(
                        EDUCATOR_SESSION_NOTE_ITEM.asterisk(),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(EDUCATOR_SESSION_NOTE_ITEM.TITLE_I18N_ID))
                        ).as(DbEducatorSessionNoteItem.Result.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                        DSL.multiset(
                                DSL.select(
                                                EDUCATOR_SESSION_NOTE_MAP.NOTE_ITEM_INPUT
                                        )
                                        .from(EDUCATOR_SESSION_NOTE_MAP)
                                        .where(EDUCATOR_SESSION_NOTE_MAP.EDUCATOR_SESSION_NOTE_ITEM_ID.eq(EDUCATOR_SESSION_NOTE_ITEM.ID).and(EDUCATOR_SESSION_NOTE_MAP.EDUCATOR_CALENDAR_ID.eq(calendarId)))
                        ).as(DbEducatorSessionNoteItem.Result.Fields.comment).convertFrom(r->r.isEmpty()?null:r.get(0).into(String.class))
                )
                .from(EDUCATOR_SESSION_NOTE_ITEM)
                .orderBy(EDUCATOR_SESSION_NOTE_ITEM.ORDER)
                .fetchInto(DbEducatorSessionNoteItem.Result.class);
        return educatorSessionNoteItemMapper.toResponse(list);
    }

    public void makeComments(UUID calendarId, UUID profileId, EducatorSessionCommentRequest request) {
        dbEducatorCalendar.getDsl().select()
                        .from(EDUCATOR_CALENDAR)
                        .where(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(profileId)
                        .and(EDUCATOR_CALENDAR.ID.eq(calendarId)))
                                .fetchOptional().orElseThrow(()->Exceptions.business(UniErrorCode.Business.EDUCATOR_CALENDAR_NOT_EXIST));

        request.getPayload().forEach((note)-> dbEducatorCalendar.getDsl().select()
                .from(EDUCATOR_SESSION_NOTE_MAP)
                .where(EDUCATOR_SESSION_NOTE_MAP.EDUCATOR_CALENDAR_ID.eq(calendarId)
                        .and(EDUCATOR_SESSION_NOTE_MAP.EDUCATOR_SESSION_NOTE_ITEM_ID.eq(note.getId())))
                .fetchOptionalInto(EducatorSessionNoteMapPojo.class)
                .ifPresentOrElse(
                        (value)->{
                            value.setNoteItemInput(note.getComment());
                            dbEducatorSessionNoteMap.getDao().update(value);
                        },
                        ()->{
                            dbEducatorSessionNoteMap.getDao().insert(new EducatorSessionNoteMapPojo()
                                    .setEducatorCalendarId(calendarId)
                                    .setEducatorSessionNoteItemId(note.getId())
                                    .setNoteItemInput(note.getComment())
                            );
                        }
                ));




    }
}
