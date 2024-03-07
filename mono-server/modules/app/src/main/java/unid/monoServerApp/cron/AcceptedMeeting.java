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
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.queue.MessageProducer;
import unid.monoServerApp.queue.model.PendingConfirmedMeetingRequestPayload;
import unid.monoServerMeta.api.EducatorCalendarRequest;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AcceptedMeeting {
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final MessageProducer messageProducer;
    private final EducatorCalendarService educatorCalendarService;

    @Scheduled(cron = "0/45 * * * * *")
    @SchedulerLock(name = "AcceptedMeeting")
    public void cron() {
        var table = dbStudentPaymentTransaction.getTable();
        var eduTable = dbEducatorCalendar.getTable();
        var transactions = dbStudentPaymentTransaction.getQuery(table)
                .join(eduTable)
                .on(eduTable.BOOKING_STATUS.eq(BookingStatusEnum.ACCEPTED)
                        .and(eduTable.PAYMENT_TRANSACTION_ID.eq(table.ID)))
                .where(table.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING))
                .and(table.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE))
                .and(dbEducatorCalendar.timeSlotIsValidToPay(eduTable).not())
                .and(table.COD_PAYMENT_URL.isNull())
                .fetchInto(StudentPaymentTransactionPojo.class);
        for (var transaction : transactions) {
            // sending to a queue instead of passing to handler; trying to ease the server
            messageProducer.sendPendingConfirmedMeeting(PendingConfirmedMeetingRequestPayload.builder()
                    .transactionId(transaction.getId())
                    .build());
        }
    }

    @Transactional
    public void handle(PendingConfirmedMeetingRequestPayload payload) {
        var tranTable = dbStudentPaymentTransaction.getTable();
        var transaction = dbStudentPaymentTransaction.getQuery(tranTable)
                .where(tranTable.ID.eq(payload.getTransactionId()))
                .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                .orElse(null);
        if (transaction == null) {
            return;
        }
        log.info("Handling {} Transaction ID: {}", this.getClass().getSimpleName(), transaction.getId());
        transaction.setPaymentStatus(PaymentStatusEnum.EXPIRED);
        dbStudentPaymentTransaction.getDao().update(transaction);

        var eduTable = dbEducatorCalendar.getTable();
        var educatorCalendarQ = dbEducatorCalendar.getQuery(eduTable);
        var educatorCalendar = educatorCalendarQ
                .where(eduTable.PAYMENT_TRANSACTION_ID.eq(transaction.getId()))
                .fetchOptionalInto(EducatorCalendarPojo.class)
                .orElse(null);
        if (educatorCalendar == null) {
            return;
        }
        educatorCalendar.setBookingStatus(BookingStatusEnum.VOID);
        dbEducatorCalendar.getDao().update(educatorCalendar);

        try {
            educatorCalendarService.markAvailable(
                    educatorCalendar.getEducatorProfileId(),
                    new EducatorCalendarRequest()
                            .setDate(educatorCalendar.getDate())
                            .setHourStart(educatorCalendar.getHourStart())
                            .setHourEnd(educatorCalendar.getHourEnd())
            );
        } catch (Exception ignored) {
        }
    }
}
