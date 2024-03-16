package unid.monoServerApp.api.user.profile.educator.calendar;

import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.*;
import unid.jooqMono.model.tables.daos.StudentPaymentTransactionDao;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.mapper.EducatorCalendarMapper;
import unid.monoServerApp.mapper.StudentPaymentTransactionMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.UniErrorCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static unid.jooqMono.model.Tables.*;
import static unid.monoServerMeta.model.UniErrorCode.Client.EDUCATOR_CAN_NOT_ACCEPT;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EducatorCalendarService {
    private final DbEducatorCalendar dbEducatorCalendar;
    private final SessionService sessionService;
    private final EducatorCalendarMapper educatorCalendarMapper;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorProfile dbEducatorProfile;
    private final StudentPaymentTransactionMapper studentPaymentTransactionMapper;

    public DbEducatorCalendar.Result get(UUID id) {
        var table = dbEducatorCalendar.getTable();
        return dbEducatorCalendar.getQuery(table)
                .where(table.ID.eq(id).and(dbEducatorCalendar.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("EducatorCalendar Not Found"))
                .into(DbEducatorCalendar.Result.class);
    }




    public EducatorCalendarSimpleResponse getAvailableTimeSlots(UUID educatorProfileId,EducatorCalendarRequest.TimeSlotPayload payload){
        //根据时间范围查询, 状态为"预定"的时间槽
        OffsetDateTime startDateTimeUtc = OffsetDateTime.parse(payload.getStartDateTimeUtc());
        OffsetDateTime endDateTimeUtc = OffsetDateTime.parse(payload.getEndDateTimeUtc());


        List<DbEducatorCalendar.Result> results = dbEducatorCalendar.getDsl()
                .select(
                        EDUCATOR_CALENDAR.asterisk(),
                        DSL.multiset(
                                DSL.select(
                                                DSL.multiset(
                                                        DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(DbStudentProfile.Result.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                                                DSL.multiset(
                                                        DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(DbStudentProfile.Result.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                                                STUDENT_PROFILE.ID.as(StudentProfilePojo.Columns.id),
                                                DSL.multiset(
                                                        DSL.select(
                                                                    TAG.ID,
                                                                    DSL.multiset(
                                                                            DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                                    ).as(DbCountry.Result.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(TAG)
                                                                .where(TAG.ID.eq(STUDENT_PROFILE.COUNTRY_ID))
                                                ).as(DbStudentProfile.Result.Fields.country).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbCountry.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(
                                                                        SCHOOL.ID,
                                                                        DSL.multiset(
                                                                                DSL.select().from(I18N).where(I18N.ID.eq(SCHOOL.NAME_I18N_ID))
                                                                        ).as(DbSchool.Result.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(SCHOOL)
                                                                .where(SCHOOL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchool).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbSchool.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(EDUCATION_LEVEL.asterisk())
                                                                .from(EDUCATION_LEVEL)
                                                                .where(EDUCATION_LEVEL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_EDUCATION_LEVEL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchoolEducationLevel).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbEducationLevel.Result.class))
                                        )
                                        .from(STUDENT_PAYMENT_TRANSACTION,STUDENT_PROFILE,USER)
                                        .where(
                                                STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                                                .and(STUDENT_PROFILE.ID.eq(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID))
                                                .and(STUDENT_PROFILE.USER_ID.eq(USER.ID))
                                                .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.ne(BookingStatusEnum.REJECTED))
                                                .and(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE))
                                        )
                        ).as(DbEducatorCalendar.Result.Fields.studentProfiles).convertFrom(r->r.isEmpty()?null:r.into(DbStudentProfile.Result.class))
                )
                .from(EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(EDUCATOR_CALENDAR.START_TIME_UTC.between(startDateTimeUtc,endDateTimeUtc))
                .and(EDUCATOR_CALENDAR.BOOKING_STATUS.eq(BookingStatusEnum.AVAILABLE))
                .fetchInto(DbEducatorCalendar.Result.class);
        var list = educatorCalendarMapper.toSimpleResponse(results);
        return new EducatorCalendarSimpleResponse().setSlots(list);
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

    public EducatorCalendarTimeSlotResponse markAvailable(UUID profileId, EducatorCalendarRequest.TimeSlotPayload payload) {
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
        return educatorCalendarMapper.toAvailableTimeSlotResponse(pojo);
    }

    public void unmarkAvailable(UUID profileId, UUID educatorProfileId) {
        sessionService.initDatabaseSession();
        // 移除的 available time slot
        // 查询当前时间槽的id,是否为available状态
        dbEducatorCalendar.validateUnMarking(profileId,educatorProfileId);
        // 删除当前 available time slot;
        dbEducatorCalendar.getDao().deleteById(educatorProfileId);
    }


    void acceptOrDenyBooking(
            UUID educatorProfileId,
            EducatorCalendarRejectRequest request,
            boolean accept
    ) {
        sessionService.initDatabaseSession();
//        dbEducatorCalendar.validateRejectStudentSession(educatorProfileId,request);
        if(!accept){
            //reject student apply session
            //检查这些apply session 是否合法。也就是判断这些是否存在
            //检查的条件包括：是否为当前老师；
            request.getSessions().forEach((session)->{
                StudentPaymentTransactionPojo pojo = dbStudentPaymentTransaction.getDsl().select()
                        .from(STUDENT_PAYMENT_TRANSACTION)
                        .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(request.getEducatorCalendarId()))
                        .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(session.getStudentProfileId()))
                        .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                        .orElseThrow(()-> Exceptions.client(UniErrorCode.Client.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));
                pojo.setRejectReason(session.getReason());
                pojo.setProcessStatus(BookingStatusEnum.REJECTED);
                dbStudentPaymentTransaction.getDao().update(pojo);
            });
            return;
        }
        if(request.getSessions().size() != 1){
            StaticLog.error("Educator只允许接受一个学生请求: {}",request.getSessions().size());
            throw Exceptions.client(EDUCATOR_CAN_NOT_ACCEPT);
        }
        request.getSessions().stream().findAny().ifPresent(session->{
            StudentPaymentTransactionPojo studentPaymentTransactionPojo = dbStudentPaymentTransaction.getDsl().select()
                    .from(STUDENT_PAYMENT_TRANSACTION)
                    .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(request.getEducatorCalendarId()))
                    .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(session.getStudentProfileId()))
                    .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                    .orElseThrow(()-> Exceptions.client(UniErrorCode.Client.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));
            studentPaymentTransactionPojo.setProcessStatus(BookingStatusEnum.ACCEPTED);
            dbStudentPaymentTransaction.getDao().update(studentPaymentTransactionPojo);
            //将EducatorCalendar的状态改为Accept
            //给student 发送educator accept 的消息并且提供支付链接


        });


    }

    //预定educator时间槽
    public StudentPaymentTransactionResponse bookEducatorCalendar(UUID studentProfileId, StudentBookingEducatorCalendarRequest request) {
        //创建支付订单
        //查询educator收费
        EducatorProfilePojo educatorProfilePojo =  dbEducatorProfile.getDao().fetchOneById(request.getEducatorProfileId());
        Optional.ofNullable(educatorProfilePojo).orElseThrow(()->Exceptions.business(UniErrorCode.Business.EDUCATOR_NOT_EXIST));
        Optional.ofNullable(educatorProfilePojo.getHourlyRate()).orElseThrow(()->Exceptions.business(UniErrorCode.Business.EDUCATOR_HOURLY_RATE_IS_NULL));

        StudentPaymentTransactionPojo studentPaymentTransactionPojo = new StudentPaymentTransactionPojo()
                .setTransactionItemRefId(request.getEducatorCalendarId())
                .setStudentProfileId(studentProfileId)
                .setTransactionAmount(new BigDecimal(educatorProfilePojo.getHourlyRate()))
                .setTransactionItem(StudentTransactionItemEnum.EDUCATOR_SCHEDULE)
                .setTransactionCurrency(CurrencyEnum.HKD)
                .setPaymentStatus(PaymentStatusEnum.PENDING)
                .setProcessStatus(BookingStatusEnum.PENDING)
                .setTransactionSubmitTime(LocalDateTime.now());
        dbStudentPaymentTransaction.getDao().insert(studentPaymentTransactionPojo);
        return studentPaymentTransactionMapper.toResponse(studentPaymentTransactionPojo);
    }

    public EducatorCalendarSimpleResponse getAcceptTimeSlot(UUID educatorProfileId, EducatorCalendarRequest.TimeSlotPayload payload) {
        //根据时间范围查询, 状态为"Accept"的时间槽
        OffsetDateTime startDateTimeUtc = OffsetDateTime.parse(payload.getStartDateTimeUtc());
        OffsetDateTime endDateTimeUtc = OffsetDateTime.parse(payload.getEndDateTimeUtc());
        List<DbEducatorCalendar.Result> results = dbEducatorCalendar.getDsl()
                .select(
                        EDUCATOR_CALENDAR.asterisk(),
                        DSL.multiset(
                                DSL.select(
                                                DSL.multiset(
                                                        DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(DbStudentProfile.Result.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                                                DSL.multiset(
                                                        DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(DbStudentProfile.Result.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class)),
                                                STUDENT_PROFILE.ID.as(StudentProfilePojo.Columns.id),
                                                DSL.multiset(
                                                        DSL.select(
                                                                        TAG.ID,
                                                                        DSL.multiset(
                                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                                        ).as(DbCountry.Result.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(TAG)
                                                                .where(TAG.ID.eq(STUDENT_PROFILE.COUNTRY_ID))
                                                ).as(DbStudentProfile.Result.Fields.country).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbCountry.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(
                                                                        SCHOOL.ID,
                                                                        DSL.multiset(
                                                                                DSL.select().from(I18N).where(I18N.ID.eq(SCHOOL.NAME_I18N_ID))
                                                                        ).as(DbSchool.Result.Fields.nameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(SCHOOL)
                                                                .where(SCHOOL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchool).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbSchool.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(EDUCATION_LEVEL.asterisk())
                                                                .from(EDUCATION_LEVEL)
                                                                .where(EDUCATION_LEVEL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_EDUCATION_LEVEL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchoolEducationLevel).convertFrom(r->r.isEmpty()?null:r.get(0).into(DbEducationLevel.Result.class))
                                        )
                                        .from(STUDENT_PAYMENT_TRANSACTION,STUDENT_PROFILE,USER)
                                        .where(
                                                STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                                                        .and(STUDENT_PROFILE.ID.eq(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID))
                                                        .and(STUDENT_PROFILE.USER_ID.eq(USER.ID))
                                                        .and(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.ne(PaymentStatusEnum.PAID))
                                                        .and(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE))
                                        )
                        ).as(DbEducatorCalendar.Result.Fields.studentProfiles).convertFrom(r->r.isEmpty()?null:r.into(DbStudentProfile.Result.class))
                )
                .from(EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(EDUCATOR_CALENDAR.START_TIME_UTC.between(startDateTimeUtc,endDateTimeUtc))
                .and(EDUCATOR_CALENDAR.BOOKING_STATUS.eq(BookingStatusEnum.ACCEPTED))
                .fetchInto(DbEducatorCalendar.Result.class);
        var list = educatorCalendarMapper.toSimpleResponse(results);
        return new EducatorCalendarSimpleResponse().setSlots(list);
    }

    public void writeStudentSessionComment(UUID educatorProfileId, UUID educatorCalendarId) {
    }

}
