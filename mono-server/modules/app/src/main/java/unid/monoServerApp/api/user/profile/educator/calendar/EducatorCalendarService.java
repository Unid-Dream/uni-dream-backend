package unid.monoServerApp.api.user.profile.educator.calendar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.mapper.EducatorCalendarMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.EducatorCalendarRequest;
import unid.monoServerMeta.api.EducatorCalendarTimeSlot;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EducatorCalendarService {
    private final DbEducatorCalendar dbEducatorCalendar;
    private final SessionService sessionService;
    private final EducatorCalendarMapper educatorCalendarMapper;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;

    public DbEducatorCalendar.Result get(UUID id) {
        var table = dbEducatorCalendar.getTable();
        return dbEducatorCalendar.getQuery(table)
                .where(table.ID.eq(id).and(dbEducatorCalendar.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("EducatorCalendar Not Found"))
                .into(DbEducatorCalendar.Result.class);
    }

    //仅查询可用时间
    List<DbEducatorCalendar.Result> getAllAvailableFromNow(UUID profileId, OffsetDateTime startTimeUtc,OffsetDateTime endTimeUtc) {
        var table = dbEducatorCalendar.getTable();
        return dbEducatorCalendar.getQuery()
                .where(table.EDUCATOR_PROFILE_ID.eq(profileId)
                        .and(dbEducatorCalendar.validateCondition(table)))
                .and(table.BOOKING_STATUS.eq(BookingStatusEnum.AVAILABLE))
                .and(dbEducatorCalendar.timeSlotIsValidToBook(table))
                .and(table.START_DATETIME.ge(startTimeUtc))
                .and(table.END_DATETIME.le(endTimeUtc))
                .fetchInto(DbEducatorCalendar.Result.class);
    }

    public EducatorCalendarPojo markAvailable(UUID profileId, EducatorCalendarTimeSlot payload) {
        sessionService.initDatabaseSession();
        var table = dbEducatorCalendar.getTable();
        dbEducatorCalendar.getQuery(table)
                .where(table.EDUCATOR_PROFILE_ID.eq(profileId))
                .and(table.START_DATETIME.eq(payload.getStartDateTimeUtc()))
                .and(table.BOOKING_STATUS.notIn(BookingStatusEnum.REJECTED, BookingStatusEnum.VOID))
                .and(dbEducatorCalendar.validateCondition(table))
                .and(table.END_DATETIME.eq(payload.getEndDateTimeUtc()))
                .fetchOptional()
                .ifPresent(record -> {
                    throw Exceptions.invalidTimeslot();
                });
        var pojo = new EducatorCalendarPojo()
                .setEducatorProfileId(profileId)
                .setBookingStatus(BookingStatusEnum.AVAILABLE);
        educatorCalendarMapper.merge(pojo, payload);
        dbEducatorCalendar.getDao().insert(pojo);
        return pojo;
    }

    void unmarkAvailable(UUID profileId, EducatorCalendarTimeSlot payload) {
        sessionService.initDatabaseSession();
        var table = dbEducatorCalendar.getTable();
        var existingRecord = dbEducatorCalendar.getQuery(table)
                .where(table.EDUCATOR_PROFILE_ID.eq(profileId))
                .and(table.START_DATETIME.eq(payload.getStartDateTimeUtc()))
                .and(table.BOOKING_STATUS.eq(BookingStatusEnum.AVAILABLE))
                .and(dbEducatorCalendar.validateCondition(table))
                .and(table.END_DATETIME.eq(payload.getEndDateTimeUtc()))
                .fetchOptionalInto(EducatorCalendarPojo.class)
                .orElseThrow(() -> Exceptions.invalidTimeslot());
        dbEducatorCalendar.getDao().delete(existingRecord);
    }

    DbEducatorCalendar.Result acceptOrDenyBooking(
            UUID educatorProfileId,
            EducatorCalendarTimeSlot payload,
            boolean accept
    ) {
        sessionService.initDatabaseSession();
        var calendarTable = dbEducatorCalendar.getTable();
        var calendar = dbEducatorCalendar.getQuery(calendarTable)
                .where(calendarTable.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(calendarTable.START_DATETIME.eq(payload.getStartDateTimeUtc()))
                .and(dbEducatorCalendar.validateCondition(calendarTable))
                .and(calendarTable.END_DATETIME.eq(payload.getEndDateTimeUtc()))
                .and(calendarTable.BOOKING_STATUS.eq(BookingStatusEnum.RESERVED))
                .and(dbEducatorCalendar.timeSlotIsValidToAccept(calendarTable))
                .fetchOptionalInto(EducatorCalendarPojo.class)
                .orElseThrow(() -> Exceptions.notFound("Educator Schedule Not Found Or Invalid"));
        dbEducatorCalendar.getDao().update(
                calendar.setBookingStatus(accept ? BookingStatusEnum.ACCEPTED : BookingStatusEnum.REJECTED)
        );
        if (!accept) {
            var tranTable = dbStudentPaymentTransaction.getTable();
            var transaction = dbStudentPaymentTransaction.getQuery(tranTable)
                    .where(tranTable.ID.eq(calendar.getPaymentTransactionId()))
                    .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                    .orElseThrow(() -> Exceptions.notFound("Transaction Not Found"));
            dbStudentPaymentTransaction.getDao().update(
                    transaction.setPaymentStatus(PaymentStatusEnum.EXPIRED)
            );
            markAvailable(educatorProfileId, payload);
        }
        return get(calendar.getId());
    }
}
