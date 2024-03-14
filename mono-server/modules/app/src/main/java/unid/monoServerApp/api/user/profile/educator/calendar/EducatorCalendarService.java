package unid.monoServerApp.api.user.profile.educator.calendar;

import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
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
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.UniErrorCode;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import static unid.jooqMono.model.Tables.EDUCATOR_CALENDAR;

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
    public EducatorAvailableScheduleResponse getAllAvailableFromNow(UUID profileId, OffsetDateTime startTimeUtc, OffsetDateTime endTimeUtc) {
        var table = dbEducatorCalendar.getTable();
        List<DbEducatorCalendar.Result> list = dbEducatorCalendar.getDsl()
                .select()
                .from(table)
                .where(table.EDUCATOR_PROFILE_ID.eq(profileId))
                .and(table.BOOKING_STATUS.eq(BookingStatusEnum.AVAILABLE))
                .and(Objects.isNull(startTimeUtc)? DSL.noCondition():table.START_TIME_UTC.ge(startTimeUtc))
                .and(Objects.isNull(endTimeUtc)? DSL.noCondition():table.END_TIME_UTC.le(endTimeUtc))
                .fetchInto(DbEducatorCalendar.Result.class);
        List<EducatorCalendarTimeSlotResponse> slots = educatorCalendarMapper.toResponse(list);
        EducatorAvailableScheduleResponse response = new EducatorAvailableScheduleResponse();
        response.setSlots(slots);
        return response;
    }

    public EducatorCalendarPojo markAvailable(UUID profileId, EducatorCalendarTimeSlotPayload payload) {
        sessionService.initDatabaseSession();
        OffsetDateTime startDateTimeUtc = OffsetDateTime.parse(payload.getStartDateTimeUtc());
        OffsetDateTime endDateTimeUtc = OffsetDateTime.parse(payload.getEndDateTimeUtc());

        dbEducatorCalendar.validateMarking(profileId,startDateTimeUtc, endDateTimeUtc);

        var pojo = new EducatorCalendarPojo()
                .setEducatorProfileId(profileId)
                .setBookingStatus(BookingStatusEnum.AVAILABLE)
                .setEndTimeUtc(endDateTimeUtc)
                .setStartTimeUtc(startDateTimeUtc);
        dbEducatorCalendar.getDao().insert(pojo);
        return pojo;
    }

    public void unmarkAvailable(UUID profileId, UUID educatorProfileId) {
        sessionService.initDatabaseSession();
        // 移除的 available time slot
        // 查询当前时间槽的id,是否为available状态
        dbEducatorCalendar.validateUnMarking(profileId,educatorProfileId);
        // 删除当前 available time slot;
        dbEducatorCalendar.getDao().deleteById(educatorProfileId);
    }


    DbEducatorCalendar.Result acceptOrDenyBooking(
            UUID educatorProfileId,
            EducatorCalendarTimeSlotPayload payload,
            boolean accept
    ) {
        sessionService.initDatabaseSession();
        var calendarTable = dbEducatorCalendar.getTable();
//        var calendar = dbEducatorCalendar.getQuery(calendarTable)
//                .where(calendarTable.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
//                .and(calendarTable.START_AT.eq(payload.getStartDateTimeUtc()))
//                .and(dbEducatorCalendar.validateCondition(calendarTable))
//                .and(calendarTable.END_AT.eq(payload.getEndDateTimeUtc()))
//                .and(calendarTable.BOOKING_STATUS.eq(BookingStatusEnum.RESERVED))
//                .and(dbEducatorCalendar.timeSlotIsValidToAccept(calendarTable))
//                .fetchOptionalInto(EducatorCalendarPojo.class)
//                .orElseThrow(() -> Exceptions.notFound("Educator Schedule Not Found Or Invalid"));
//        dbEducatorCalendar.getDao().update(
//                calendar.setBookingStatus(accept ? BookingStatusEnum.ACCEPTED : BookingStatusEnum.REJECTED)
//        );
//        if (!accept) {
//            var tranTable = dbStudentPaymentTransaction.getTable();
//            var transaction = dbStudentPaymentTransaction.getQuery(tranTable)
//                    .where(tranTable.ID.eq(calendar.getPaymentTransactionId()))
//                    .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
//                    .orElseThrow(() -> Exceptions.notFound("Transaction Not Found"));
//            dbStudentPaymentTransaction.getDao().update(
//                    transaction.setPaymentStatus(PaymentStatusEnum.EXPIRED)
//            );
//            markAvailable(educatorProfileId, payload);
//        }
//        return get(calendar.getId());
        return null;
    }
}
