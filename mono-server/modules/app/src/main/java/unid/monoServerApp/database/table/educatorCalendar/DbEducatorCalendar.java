package unid.monoServerApp.database.table.educatorCalendar;

import cn.hutool.log.StaticLog;
import io.lettuce.core.BitFieldArgs;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.tables.EducatorCalendarTable;
import unid.jooqMono.model.tables.daos.EducatorCalendarDao;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.Constant;
import unid.monoServerApp.ErrorCode;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.Properties;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerMeta.api.EducatorCalendarRejectRequest;
import unid.monoServerMeta.model.UniErrorCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static unid.jooqMono.model.Tables.EDUCATOR_CALENDAR;
import static unid.jooqMono.model.Tables.STUDENT_PAYMENT_TRANSACTION;

@Component
@Slf4j
public class DbEducatorCalendar extends Db<EducatorCalendarTable, EducatorCalendarDao> {
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final Properties properties;

    @Autowired
    public DbEducatorCalendar(
            DSLContext dslContext,
            DbStudentPaymentTransaction dbStudentPaymentTransaction,
            Properties properties
    ) {
        super(dslContext, Public.PUBLIC.EDUCATOR_CALENDAR, new EducatorCalendarDao(dslContext.configuration()));
        this.dbStudentPaymentTransaction = dbStudentPaymentTransaction;
        this.properties = properties;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorCalendarTable alias) {
        var payTran = dbStudentPaymentTransaction.getTable().as(combineAlias(alias, dbStudentPaymentTransaction.getTable()));
        var payTranQ = dbStudentPaymentTransaction.getQuery(payTran);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                payTranQ.where(alias.PAYMENT_TRANSACTION_ID.eq(payTran.ID))
                        ).as(Result.Fields.paymentTransaction).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    public SelectJoinStep<Record> getQuerySimple(EducatorCalendarTable alias) {
        @Cleanup var q = dsl
                .select(
                        alias.asterisk()
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(EducatorCalendarTable table) {
        return DSL.noCondition();
    }

    public Condition timeSlotIsValidToBook(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC).plusMinutes(properties.getMeetingAllowedToBookBeforeStartMinutes());
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_START).cast(LocalDateTime.class);
        return timeslot.gt(now);
    }

    public Condition timeSlotIsValidToAccept(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC).plusMinutes(properties.getMeetingAllowedToAcceptBeforeStartMinutes());
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_START).cast(LocalDateTime.class);
        return timeslot.gt(now);
    }

    public Condition timeSlotIsValidToPay(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC).plusMinutes(properties.getMeetingAllowedToPayBeforeStartMinutes());
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_START).cast(LocalDateTime.class);
        return timeslot.gt(now);
    }

    public Condition scheduleEnded(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC);
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_END).cast(LocalDateTime.class);
        return timeslot.lt(now);
    }

    @Deprecated
    public void validateMarking(
            @NotNull LocalDate date,
            @NotNull LocalTime start,
            @NotNull LocalTime end
    ) {
        var now = LocalDate.now(ZoneOffset.UTC);
        var span = start.getHour() - end.getHour();
        if (
                date.isBefore(now)
                        || date.isAfter(date.plusDays(Constant.PAGINATION_MAX_SIZE_EDUCATOR_CALENDAR))
                        || !start.isBefore(end)
                        || span != -1
        ) {
            throw Exceptions.invalidTimeslot();
        }
    }

    @Deprecated
    public void validateMarking(
            @NotNull OffsetDateTime date,
            @NotNull LocalTime start,
            @NotNull LocalTime end
    ) {
//        var now = LocalDate.now(ZoneOffset.UTC);
//        var span = start.getHour() - end.getHour();
//        if (
//                date.isBefore(now)
//                        || date.isAfter(date.plusDays(Constant.PAGINATION_MAX_SIZE_EDUCATOR_CALENDAR))
//                        || !start.isBefore(end)
//                        || span != -1
//        ) {
//            throw Exceptions.invalidTimeslot();
//        }
    }

    public void validateUnMarking(
            @NotNull UUID profileId,
            @NotNull UUID educatorCalendarId
    ){
        //检查当前时间点是否为空闲状态,如果不是空闲，也就是查询结果为空,则认为是不空闲状态,抛出异常
        getDsl().select()
                .from(EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(profileId))
                .and(EDUCATOR_CALENDAR.ID.eq(educatorCalendarId))
                .and(EDUCATOR_CALENDAR.BOOKING_STATUS.eq(BookingStatusEnum.AVAILABLE))
                .fetchOptionalInto(DbEducatorCalendar.Result.class)
                .orElseThrow(()->{
                    // 如果查询结果为空,则说明当前时间槽不是空闲状态,抛出异常
                    throw Exceptions.business(UniErrorCode.Business.SLOT_CAN_NOT_CHANGE_TO_UNAVAILABLE);
                });
    }

    public void validateRejectStudentSession(
            @NotNull UUID educatorProfileId,
            @NotNull EducatorCalendarRejectRequest request
    ){
        getDsl().selectCount()
                .from(STUDENT_PAYMENT_TRANSACTION)
                .leftJoin(STUDENT_PAYMENT_TRANSACTION).on(EDUCATOR_CALENDAR.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
                .where(EDUCATOR_CALENDAR.ID.eq(request.getEducatorCalendarId()).and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(educatorProfileId)))
                .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.AVAILABLE))
                .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.in(
                        request.getSessions().stream().map(
                                EducatorCalendarRejectRequest.RejectSession::getStudentProfileId
                        ).collect(Collectors.toList())
                )).fetchOptionalInto(Integer.class)
                .ifPresent(value->{
                    if(value != request.getSessions().size()){
                        //如果不相等,则当前提交拒绝异常
                        throw Exceptions.client(
                                UniErrorCode.Client.EDUCATOR_CAN_NOT_REJECT
                        );

                    }
                });
    }




    public void validateMarking(
            @NotNull UUID profileId,
            @NotNull OffsetDateTime startDateTimeUtc,
            @NotNull OffsetDateTime endDateTimeUtc
    ) {
        //检查当前时间段是否存在,如果存在,则不允许设置 available
        //时间段是否都为整点, 如果不为整点,则认为异常
        if( startDateTimeUtc.getMinute()!=0 || startDateTimeUtc.getSecond()!=0){
            StaticLog.error(" {}, 当前时间段开始时间非整点",startDateTimeUtc);
            throw Exceptions.business(UniErrorCode.Business.EDUCATOR_CALENDAR_TIME_SLOT_CAN_NOT_CHANGE);
        }
        if( endDateTimeUtc.getMinute()!=0 || endDateTimeUtc.getSecond()!=0){
            StaticLog.error("{} , 当前时间段结束时间非整点",endDateTimeUtc);
            throw Exceptions.business(UniErrorCode.Business.EDUCATOR_CALENDAR_TIME_SLOT_CAN_NOT_CHANGE);
        }
        //如果时间间隔小于1个小时,则认为异常
//        var span = endDateTimeUtc.toLocalTime().getHour() - startDateTimeUtc.toLocalTime().getHour();
//        if(span != 1){
//            StaticLog.error("{} - {} , 当前时间段开始时间和结束时间小于一个小时",startDateTimeUtc,endDateTimeUtc);
//            throw Exceptions.business(
//                    UniErrorCode.Client.EDUCATOR_CALENDAR_TIME_SLOT_INVALID.code(),
//                    UniErrorCode.Client.EDUCATOR_CALENDAR_TIME_SLOT_INVALID.message());
//        }
        //如果开始时间小于当前时间,则认为异常
//        var now = LocalDateTime.now(ZoneOffset.UTC);
//        if(startDateTimeUtc.toLocalDateTime().isBefore(now)){
//            StaticLog.error("{}, 预定时间段不允许早于当前时间",startDateTimeUtc);
//            throw Exceptions.business(
//                    UniErrorCode.Client.EDUCATOR_CALENDAR_TIME_SLOT_INVALID.code(),
//                    UniErrorCode.Client.EDUCATOR_CALENDAR_TIME_SLOT_INVALID.message());
//        }
        //当前时间段是否已经有状态
        getDsl().select()
                .from(EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(profileId))
                .and(EDUCATOR_CALENDAR.START_TIME_UTC.between(startDateTimeUtc,endDateTimeUtc))
                .and(EDUCATOR_CALENDAR.END_TIME_UTC.between(startDateTimeUtc,endDateTimeUtc))
                .fetchOptionalInto(DbEducatorCalendar.Result.class)
                .ifPresentOrElse(
                        value -> {
                            // 如果Optional对象存在值，执行此处代码
                            throw Exceptions.business(UniErrorCode.Business.EDUCATOR_CALENDAR_TIME_SLOT_CAN_NOT_CHANGE);
                        },
                        () -> {}
                );

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EducatorCalendarPojo implements Serializable {
        private DbStudentPaymentTransaction.Result paymentTransaction;
        private List<DbStudentProfile.Result> studentProfiles;
        private List<DbEducatorSessionNoteItem.Result> comments;
        private DbEducatorProfile.Result educatorProfile;
    }
}
