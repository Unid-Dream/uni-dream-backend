package unid.monoServerApp.cron;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteMapPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNotePojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteMap;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNote;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.queue.MessageProducer;
import unid.monoServerApp.queue.model.ExpiredMeetingRequestPayload;
import unid.monoServerApp.service.EmailService;
import unid.monoServerApp.service.TeamsMeetingService;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ExpiredMeeting {
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final DbEducatorSessionNote dbEducatorSessionNote;
    private final MessageProducer messageProducer;
    private final EmailService emailService;
    private final DbEducatorSessionNoteMap dbEducatorSessionNoteMap;

    @Scheduled(cron = "0/20 * * * * *") // 0 15 * * * *
    @SchedulerLock(name = "ExpiredMeeting")
    public void cron() {
        var table = dbStudentPaymentTransaction.getTable();
        var eduTable = dbEducatorCalendar.getTable();
        var sql = dbStudentPaymentTransaction.getDsl().select()
                .from(table)
                .join(eduTable)
                .on(eduTable.BOOKING_STATUS.eq(BookingStatusEnum.ACCEPTED)
                        .and(eduTable.PAYMENT_TRANSACTION_ID.eq(table.ID))
                        .and(dbEducatorCalendar.scheduleEnded(eduTable))
                )
                .where(table.PAYMENT_STATUS.eq(PaymentStatusEnum.PAID))
                .and(table.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE));
        var records = sql.fetchGroups(
                r -> r.into(table).into(StudentPaymentTransactionPojo.class),
                r -> r.into(eduTable).into(EducatorCalendarPojo.class)
        );
        for (var record : records.entrySet()) {
            var transaction = record.getKey();
            var calendar = record.getValue().get(0);
            messageProducer.sendExpiredMeeting(ExpiredMeetingRequestPayload.builder()
                    .transactionId(transaction.getId())
                    .calendarId(calendar.getId())
                    .build());
        }
    }

    @Transactional
    public void handle(ExpiredMeetingRequestPayload payload) {
        var transaction = Optional.ofNullable(
                        dbStudentPaymentTransaction.getDao().fetchOneById(payload.getTransactionId())
                ).orElse(null);
        if(transaction == null) {
            return;
        }
        var calendar = Optional.ofNullable(
                dbEducatorCalendar.getDao().fetchOneById(payload.getCalendarId())
                ).orElse(null);
        if(calendar == null) {
            return;
        }
        if(calendar.getMeetingId() == null) {
            unfinished(calendar);
            return;
        }
        // TODO uncomment
        // roughly determine attendees participation
//        var attendance = teamsMeetingService.getMeetingAttendance(calendar.getMeetingId());
//        if(ObjectUtils.isEmpty(attendance)) {
//            unfinished(calendar);
//            return;
//        }
//        var att = attendance.get(0);
//        if(att.meetingStartDateTime != null) {
            calendar.setBookingStatus(BookingStatusEnum.FINISHED);
            dbEducatorCalendar.getDao().update(calendar);
            injectSessionNote(calendar);
            emailService.requestEducatorSessionNote(calendar, transaction);
            emailService.requestStudentSessionNote(calendar, transaction);
//        }
    }

    private void unfinished(EducatorCalendarPojo calendar) {
        calendar.setBookingStatus(BookingStatusEnum.UNFINISHED);
        dbEducatorCalendar.getDao().update(calendar);
    }

    private void injectSessionNote(EducatorCalendarPojo calendar) {
        var noteTable = dbEducatorSessionNote.getTable();
        var notes = dbEducatorSessionNote.getQuery(noteTable)
                .where(noteTable.OBSOLETED.eq(false))
                .fetchInto(DbEducatorSessionNote.Result.class);
        for(var note : notes) {
            // educator session note map
            for(var item : note.getItems()) {
                dbEducatorSessionNoteMap.getDao().insert(
                        new EducatorSessionNoteMapPojo()
                                .setEducatorCalendarId(calendar.getId())
                                .setEducatorSessionNoteItemId(item.getId())
                );
            }
        }
    }
}
