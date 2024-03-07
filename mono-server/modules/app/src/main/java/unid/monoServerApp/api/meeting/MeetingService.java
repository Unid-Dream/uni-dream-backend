package unid.monoServerApp.api.meeting;

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
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.Properties;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.feign.cod.CodApiService;
import unid.monoServerApp.feign.cod.model.CodCreateOrderRequest;
import unid.monoServerApp.feign.cod.model.CodCurrency;
import unid.monoServerApp.feign.cod.model.CodPaymentSolution;
import unid.monoServerApp.mapper.EnumMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.CodTransactionSubject;
import unid.monoServerMeta.api.MeetingReservationPaymentRequest;
import unid.monoServerMeta.api.MeetingReservationRequest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MeetingService {
    private final DbEducatorCalendar dbEducatorCalendar;
    private final SessionService sessionService;
    private final DbStudentProfile dbStudentProfile;
    private final DbEducatorProfile dbEducatorProfile;
    private final CodApiService codApiService;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final EnumMapper enumMapper;
    private final Properties properties;
    private final EducatorCalendarService educatorCalendarService;

    DbEducatorCalendar.Result reserve(
            UUID studentProfileId,
            UUID educatorProfileId,
            MeetingReservationRequest payload
    ) {
        sessionService.initDatabaseSession();
        var studentProfile = Optional.ofNullable(
                dbStudentProfile.getDao().fetchOneById(studentProfileId)
        ).orElseThrow(() -> Exceptions.notFound("Student Profile Not Found"));
        var educatorProfile = Optional.ofNullable(
                dbEducatorProfile.getDao().fetchOneById(educatorProfileId)
        ).orElseThrow(() -> Exceptions.notFound("Educator Profile Not Found"));
        var calendarTable = dbEducatorCalendar.getTable();
        var calendar = dbEducatorCalendar.getQuery(calendarTable)
                .where(calendarTable.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(calendarTable.DATE.eq(payload.getDate()))
                .and(dbEducatorCalendar.validateCondition(calendarTable))
                .and(calendarTable.HOUR_START.eq(payload.getHourStart()))
                .and(calendarTable.BOOKING_STATUS.eq(BookingStatusEnum.AVAILABLE))
//                .and(dbEducatorCalendar.timeSlotIsValidToBook(calendarTable))
                .fetchOptionalInto(EducatorCalendarPojo.class)
                .orElseThrow(() -> Exceptions.notFound("Educator Schedule Not Found Or Unavailable"));
        dbEducatorCalendar.getDao().update(
                calendar.setBookingStatus(BookingStatusEnum.RESERVED)
        );
        var hourCharge = educatorProfile.getHourlyRate();
        var transaction = new StudentPaymentTransactionPojo()
                .setStudentProfileId(studentProfile.getId())
                .setTransactionAmount(BigDecimal.valueOf(0.01)) // TODO replace with 'hourCharge'
                .setTransactionCurrency(CurrencyEnum.HKD)
                .setTransactionItem(StudentTransactionItemEnum.EDUCATOR_SCHEDULE)
                .setPaymentStatus(PaymentStatusEnum.PENDING)
                .setTransactionItemRefId(calendar.getId())
                .setTransactionPersonnelRefId(educatorProfile.getUserId());
        dbStudentPaymentTransaction.getDao().insert(transaction);
        dbEducatorCalendar.getDao().update(
                calendar.setPaymentTransactionId(transaction.getId())
        );
        return educatorCalendarService.get(calendar.getId());
    }

    EducatorCalendarPojo cancelReserve(
            UUID studentProfileId,
            UUID educatorProfileId,
            MeetingReservationRequest payload
    ) {
        sessionService.initDatabaseSession();
        var calendarTable = dbEducatorCalendar.getTable();
        var calendar = dbEducatorCalendar.getQuery(calendarTable)
                .where(calendarTable.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(calendarTable.DATE.eq(payload.getDate()))
                .and(dbEducatorCalendar.validateCondition(calendarTable))
                .and(calendarTable.HOUR_START.eq(payload.getHourStart()))
                .and(calendarTable.BOOKING_STATUS.eq(BookingStatusEnum.RESERVED))
                .fetchOptionalInto(DbEducatorCalendar.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Educator Schedule Not Found"));
        var tranTable = dbStudentPaymentTransaction.getTable();
        var transaction = dbStudentPaymentTransaction.getQuery(tranTable)
                .where(tranTable.ID.eq(calendar.getPaymentTransactionId()))
                .and(tranTable.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING))
                .and(tranTable.STUDENT_PROFILE_ID.eq(studentProfileId))
                .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Transaction Not Found"));
        dbEducatorCalendar.getDao().update(
                calendar
                        .setPaymentTransactionId(null)
                        .setBookingStatus(BookingStatusEnum.AVAILABLE)
        );
        dbStudentPaymentTransaction.getDao().delete(transaction);
        return calendar;
    }

    String payReserved(
            UUID studentProfileId,
            UUID educatorProfileId,
            MeetingReservationPaymentRequest payload
    ) {
        sessionService.initDatabaseSession();
        var tranTable = dbStudentPaymentTransaction.getTable();
        var transaction = dbStudentPaymentTransaction.getQuery(tranTable)
                .where(tranTable.ID.eq(payload.getTransactionId()))
                .and(tranTable.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING))
                .and(tranTable.STUDENT_PROFILE_ID.eq(studentProfileId))
                .and(dbStudentPaymentTransaction.isValidToPay(tranTable))
                .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Transaction Not Found Or Expired"));
        var calendarTable = dbEducatorCalendar.getTable();
        dbEducatorCalendar.getQuery(calendarTable)
                .where(calendarTable.PAYMENT_TRANSACTION_ID.eq(transaction.getId()))
                .and(calendarTable.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(calendarTable.BOOKING_STATUS.eq(BookingStatusEnum.ACCEPTED))
                .and(dbEducatorCalendar.timeSlotIsValidToPay(calendarTable))
                .fetchOptionalInto(EducatorCalendarPojo.class)
                .orElseThrow(() -> Exceptions.notFound("Educator Schedule Not Found"));
        if (StringUtils.isNotBlank(transaction.getCodPaymentUrl())) {
            return transaction.getCodPaymentUrl();
        }
        // COD
        var codOrderRef = String.format("%s_%s", Instant.now().toEpochMilli(), UUID.randomUUID());
        var order = CodCreateOrderRequest.builder()
                .orderRef(codOrderRef)
//                        .amount(BigDecimal.valueOf(hourCharge))
                .amount(transaction.getTransactionAmount()) // TODO replace
                .currency(CodCurrency.HKD)
                .subject(CodTransactionSubject.STUDENT_BOOK_EDUCATOR)
                .wallet(payload.getCodWallet())
                .returnUrl(payload.getReturnUrl())
                .paymentSolution(CodPaymentSolution.MOBILE_WEB_OR_ALIPAYHK_INAPP)
                .build();
        var expiry = OffsetDateTime.now(ZoneOffset.UTC).plusMinutes(properties.getCodPaymentTimeoutMinutes());
        var codResponse = codApiService.createOrder(order.toBuilder());
        if (codResponse.hasError() || codResponse.getResultObj() == null) {
            log.error("COD Payment Failed: {}", codResponse);
            throw new UnifiedException(
                    ErrorCodeGlobal.ERROR_INTERNAL_ERROR,
                    "Payment Service Unavailable",
                    HttpStatus.SERVICE_UNAVAILABLE.value()
            );
        }
        transaction
                .setPaymentMethod(enumMapper.toPaymentMethod(order.getWallet()))
                .setPaymentStatus(PaymentStatusEnum.PENDING)
                .setCodOrderRef(codOrderRef)
                .setCodOutTradeNo(codResponse.getResultObj().getOutTradeNo())
                .setCodRefId(codResponse.getReferenceId())
                .setCodWallet(order.getWallet().toNamedString())
                .setCodExpiry(expiry)
                .setCodPaymentUrl(String.format(
                        "%s?%s",
                        codResponse.getResultObj().getUrl(),
                        codResponse.getResultObj().getAlipayOrderString()
                ));
        dbStudentPaymentTransaction.getDao().update(transaction);
        return transaction.getCodPaymentUrl();
    }
}
