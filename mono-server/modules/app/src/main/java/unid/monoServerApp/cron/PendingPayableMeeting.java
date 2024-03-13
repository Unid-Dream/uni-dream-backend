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
import unid.monoServerApp.feign.cod.CodApiService;
import unid.monoServerApp.feign.cod.model.CodOrderDetailRequest;
import unid.monoServerApp.feign.cod.model.CodPaymentStatus;
import unid.monoServerApp.queue.MessageProducer;
import unid.monoServerApp.queue.model.EducatorMeetingRequestPayload;
import unid.monoServerApp.queue.model.PendingTransactionRequestPayload;
import unid.monoServerMeta.api.EducatorCalendarRequest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PendingPayableMeeting {
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final MessageProducer messageProducer;
    private final CodApiService codApiService;
    private final EducatorCalendarService educatorCalendarService;

    @Scheduled(cron = "0/30 * * * * *")
    @SchedulerLock(name = "PendingPayableMeeting")
    public void cron() {
        var table = dbStudentPaymentTransaction.getTable();
        var eduTable = dbEducatorCalendar.getTable();
        var transactions = dbStudentPaymentTransaction.getQuery(table)
                .join(eduTable)
                .on(eduTable.BOOKING_STATUS.eq(BookingStatusEnum.ACCEPTED)
                        .and(eduTable.PAYMENT_TRANSACTION_ID.eq(table.ID))
                )
                .where(table.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING))
                .and(table.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE))
                .and(table.COD_PAYMENT_URL.isNotNull())
                .fetchInto(StudentPaymentTransactionPojo.class);
        for (var transaction : transactions) {
            // sending to a queue instead of passing to handler; trying to ease the server
            messageProducer.sendPendingTransaction(PendingTransactionRequestPayload.builder()
                    .transactionId(transaction.getId())
                    .build());
        }
    }

    // alipay webhook does not notify on order status other than 'paid', hence using a cron job instead
    @Transactional
    public void handle(PendingTransactionRequestPayload payload) {
        var transaction = Optional.ofNullable(
                dbStudentPaymentTransaction.getDao().fetchOneById(payload.getTransactionId())
        ).orElse(null);
        if (transaction == null) {
            return;
        }
        log.info("Handling {} Transaction ID: {}", this.getClass().getSimpleName(), transaction.getId());
        var isValidToPay = dbStudentPaymentTransaction.isValidToPay(transaction);
        var codOrder = codApiService.getOrder(CodOrderDetailRequest.builder().orderRef(transaction.getCodOrderRef()))
                .getResultObj();

        if (codOrder == null) {
            log.error("COD Order Is Empty");
            return;
        }

        if (!CodPaymentStatus.PAID.equals(codOrder.getStatus()) && isValidToPay) {
            log.info("Transaction {} is within valid payment period", transaction.getId());
            return;
        }
        var status = codOrder.getStatus();
        // alipay returns 'NEW' even the transaction is expired
        // see if alipay production env still having this issue, if no the above 'temp solution for alipay' can be removed
        log.info("COD Status: {}", status);
        if (status.equals(CodPaymentStatus.NEW)) {
            status = CodPaymentStatus.EXPIRED;
        }
        var transactionId = codOrder.getTransactionId();
        log.info("Transaction final determined status: {}", status);
        var eduTable = dbEducatorCalendar.getTable();
        var educatorCalendarQ = dbEducatorCalendar.getQuery(eduTable);
        var educatorCalendar = educatorCalendarQ
                .where(eduTable.PAYMENT_TRANSACTION_ID.eq(transaction.getId()))
                .fetchOptionalInto(EducatorCalendarPojo.class)
                .orElse(null);
        switch (status) {
            case PAID:
            case LIQUIDATED:
                transaction.setCodTransactionId(transactionId)
                        .setPaymentStatus(PaymentStatusEnum.PAID);
                dbStudentPaymentTransaction.getDao().update(transaction);
                if (educatorCalendar == null) {
                    return;
                }
                messageProducer.sendEducatorMeeting(EducatorMeetingRequestPayload.builder()
                        .calendarId(educatorCalendar.getId())
                        .educatorProfileId(educatorCalendar.getEducatorProfileId())
                        .build());
                break;
            case EXPIRED:
                transaction.setCodTransactionId(transactionId)
                        .setPaymentStatus(PaymentStatusEnum.EXPIRED);
                dbStudentPaymentTransaction.getDao().update(transaction);
                if (educatorCalendar == null) {
                    return;
                }
                educatorCalendar.setBookingStatus(BookingStatusEnum.VOID);
                dbEducatorCalendar.getDao().update(educatorCalendar);
                try {
//                    OffsetDateTime startDateTime = educatorCalendar.getStartDatetime();
//                    OffsetDateTime endDateTime = educatorCalendar.getEndDatetime();
//
//                    educatorCalendarService.markAvailable(
//                            educatorCalendar.getEducatorProfileId(),
//                            new EducatorCalendarRequest()
//                                    .setStartDatetime(startDateTime)
//                                    .setEndDatetime(endDateTime)
//                    );
                } catch (Exception ignored) {
                }
                break;
            default:
                log.info("Ignored transaction: {} | {}", transaction, codOrder);
        }
    }
}
