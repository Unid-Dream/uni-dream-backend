package unid.monoServerApp.api.user.profile.educator.calendar;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.*;
import unid.jooqMono.model.tables.pojos.*;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.course.DbEvent;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorCalendar.DbSessionReschedule;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.eventLog.DbSessionOpLog;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.EducatorCalendarMapper;
import unid.monoServerApp.mapper.StudentPaymentTransactionMapper;
import unid.monoServerApp.model.SessionLogger;
import unid.monoServerApp.service.SessionLoggerService;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import pwh.springWebStarter.response.UniErrorCode;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SessionOpType;

import java.time.OffsetDateTime;
import java.util.*;

import static org.jooq.impl.DSL.case_;
import static org.jooq.impl.DSL.count;
import static unid.jooqMono.model.Tables.*;
import static pwh.springWebStarter.response.UniErrorCode.EDUCATOR_CAN_NOT_ACCEPT;

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
    private final DbUser dbUser;
    private final DbSessionReschedule dbSessionReschedule;
    private final DbSessionOpLog dbSessionOpLog;
    private final SessionLoggerService sessionLoggerService;
    private final DbEvent dbEvent;

    public DbEducatorCalendar.Result get(UUID id) {
        var table = dbEducatorCalendar.getTable();
        return dbEducatorCalendar.getQuery(table)
                .where(table.ID.eq(id).and(dbEducatorCalendar.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("EducatorCalendar Not Found"))
                .into(DbEducatorCalendar.Result.class);
    }


    public EducatorCalendarSimpleResponse getAvailableTimeSlots(UUID educatorProfileId,
                                                                EducatorCalendarRequest.TimeSlotPayload payload) {
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
                                                ).as(DbStudentProfile.Result.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                                                DSL.multiset(
                                                        DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(DbStudentProfile.Result.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                                                STUDENT_PROFILE.ID.as(StudentProfilePojo.Columns.id),
                                                STUDENT_PROFILE.PROFILE_PICTURE.as(StudentProfilePojo.Columns.profilePicture),
                                                DSL.multiset(
                                                        DSL.select(
                                                                        TAG.ID,
                                                                        DSL.multiset(
                                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                                        ).as(DbCountry.Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(TAG)
                                                                .where(TAG.ID.eq(STUDENT_PROFILE.COUNTRY_ID))
                                                ).as(DbStudentProfile.Result.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbCountry.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(
                                                                        SCHOOL.ID,
                                                                        DSL.multiset(
                                                                                DSL.select().from(I18N).where(I18N.ID.eq(SCHOOL.NAME_I18N_ID))
                                                                        ).as(DbSchool.Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(SCHOOL)
                                                                .where(SCHOOL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchool).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbSchool.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(EDUCATION_LEVEL.asterisk())
                                                                .from(EDUCATION_LEVEL)
                                                                .where(EDUCATION_LEVEL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_EDUCATION_LEVEL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchoolEducationLevel).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbEducationLevel.Result.class))
                                        )
                                        .from(STUDENT_PAYMENT_TRANSACTION, STUDENT_PROFILE, USER)
                                        .where(
                                                STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                                                        .and(STUDENT_PROFILE.ID.eq(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID))
                                                        .and(STUDENT_PROFILE.USER_ID.eq(USER.ID))
                                                        .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.ne(BookingStatusEnum.REJECTED))
                                                        .and(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE))
                                        )
                        ).as(DbEducatorCalendar.Result.Fields.studentProfiles).convertFrom(r -> r.isEmpty() ? null : r.into(DbStudentProfile.Result.class))
                )
                .from(EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(EDUCATOR_CALENDAR.START_TIME_UTC.between(startDateTimeUtc, endDateTimeUtc))
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
                .and(Objects.isNull(startTimeUtc) ? DSL.noCondition() : table.START_TIME_UTC.ge(startTimeUtc))
                .and(Objects.isNull(endTimeUtc) ? DSL.noCondition() : table.END_TIME_UTC.le(endTimeUtc))
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

        dbEducatorCalendar.validateMarking(profileId, startDateTimeUtc, endDateTimeUtc);

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
        dbEducatorCalendar.validateUnMarking(profileId, educatorProfileId);
        // 删除当前 available time slot;
        dbEducatorCalendar.getDao().deleteById(educatorProfileId);
    }


    public void acceptOrDenyBooking(
            UUID educatorProfileId,
            EducatorCalendarAcceptOrRejectRequest request,
            boolean accept
    ) {
        sessionService.initDatabaseSession();
        DbEducatorCalendar.Result calendar = dbEducatorCalendar.getDsl()
                .select().from(EDUCATOR_CALENDAR).where(EDUCATOR_CALENDAR.ID.eq(request.getEducatorCalendarId()))
                .fetchOptionalInto(DbEducatorCalendar.Result.class)
                .orElseThrow(()->Exceptions.business(UniErrorCode.EDUCATOR_CALENDAR_NOT_EXIST));
        BookingStatusEnum fromStatus = calendar.getBookingStatus();
        if (!accept) {
            //reject student apply session
            //检查这些apply session 是否合法。也就是判断这些是否存在
            //查询当前educator calendar

            request.getSessions().forEach((session) -> {
                StudentPaymentTransactionPojo pojo = dbStudentPaymentTransaction.getDsl().select()
                        .from(STUDENT_PAYMENT_TRANSACTION)
                        .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(request.getEducatorCalendarId()))
                        .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(session.getStudentProfileId()))
                        .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                        .orElseThrow(() -> Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));
                pojo.setRejectReason(session.getReason());
                pojo.setProcessStatus(BookingStatusEnum.REJECTED);
                dbStudentPaymentTransaction.getDao().update(pojo);

                sessionLoggerService.async(SessionLogger.OpEvent.builder()
                        .status(BookingStatus.REJECTED)
                        .userId(RequestHolder.get().getUser().getUserId())
                        .transactionId(pojo.getTransactionItemRefId())
                        .timeUtc(OffsetDateTime.now())
                        .opType(SessionOpType.REJECT).build());
            });

            return;
        }
        if (request.getSessions().size() != 1) {
            StaticLog.error("Educator只允许接受一个学生请求: {}", request.getSessions().size());
            throw Exceptions.business(EDUCATOR_CAN_NOT_ACCEPT);
        }
        request.getSessions().stream().findAny().ifPresent(session -> {
            StudentPaymentTransactionPojo studentPaymentTransactionPojo = dbStudentPaymentTransaction.getDsl().select()
                    .from(STUDENT_PAYMENT_TRANSACTION)
                    .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(request.getEducatorCalendarId()))
                    .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(session.getStudentProfileId()))
                    .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING))
                    .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                    .orElseThrow(() -> Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));
            studentPaymentTransactionPojo.setProcessStatus(BookingStatusEnum.ACCEPTED);
            dbStudentPaymentTransaction.getDao().update(studentPaymentTransactionPojo);
            //将EducatorCalendar的状态改为Accept
            sessionLoggerService.async(SessionLogger.OpEvent.builder()
                    .userId(RequestHolder.get().getUser().getUserId())
                    .status(BookingStatus.ACCEPTED)
                    .transactionId(studentPaymentTransactionPojo.getTransactionItemRefId())
                    .timeUtc(OffsetDateTime.now())
                    .opType(SessionOpType.ACCEPT).build());
        });

    }


    private void sendAcceptStudentSessionEmail() {
//        emailService.sendEmail(
//                "Verify Your E-mail",
//                payload.getMessage().getContent(),
//                payload.getMessage().getRecipients().toArray(new String[]{})
//        );
//        messageProducer.sendEmailRequest(EmailRequestPayload.builder()
//                .category(EmailRequestPayload.Category.VERIFY_EMAIL)
//                .subject("Verify Your E-mail")
//                .content(html)
//                .recipients(Arrays.asList(payload.getEmail()))
//                .build());
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
                                                ).as(DbStudentProfile.Result.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                                                DSL.multiset(
                                                        DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(DbStudentProfile.Result.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                                                STUDENT_PROFILE.ID.as(StudentProfilePojo.Columns.id),
                                                DSL.multiset(
                                                        DSL.select(
                                                                        TAG.ID,
                                                                        DSL.multiset(
                                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                                        ).as(DbCountry.Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(TAG)
                                                                .where(TAG.ID.eq(STUDENT_PROFILE.COUNTRY_ID))
                                                ).as(DbStudentProfile.Result.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbCountry.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(
                                                                        SCHOOL.ID,
                                                                        DSL.multiset(
                                                                                DSL.select().from(I18N).where(I18N.ID.eq(SCHOOL.NAME_I18N_ID))
                                                                        ).as(DbSchool.Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                                                )
                                                                .from(SCHOOL)
                                                                .where(SCHOOL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchool).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbSchool.Result.class)),
                                                DSL.multiset(
                                                        DSL.select(EDUCATION_LEVEL.asterisk())
                                                                .from(EDUCATION_LEVEL)
                                                                .where(EDUCATION_LEVEL.ID.eq(STUDENT_PROFILE.SECONDARY_SCHOOL_EDUCATION_LEVEL_ID))
                                                ).as(DbStudentProfile.Result.Fields.secondarySchoolEducationLevel).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbEducationLevel.Result.class))
                                        )
                                        .from(STUDENT_PAYMENT_TRANSACTION, STUDENT_PROFILE, USER)
                                        .where(
                                                STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                                                        .and(STUDENT_PROFILE.ID.eq(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID))
                                                        .and(STUDENT_PROFILE.USER_ID.eq(USER.ID))
                                                        .and(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.ne(PaymentStatusEnum.PAID))
                                                        .and(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE))
                                        )
                        ).as(DbEducatorCalendar.Result.Fields.studentProfiles).convertFrom(r -> r.isEmpty() ? null : r.into(DbStudentProfile.Result.class))
                )
                .from(EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(educatorProfileId))
                .and(EDUCATOR_CALENDAR.START_TIME_UTC.between(startDateTimeUtc, endDateTimeUtc))
                .and(EDUCATOR_CALENDAR.BOOKING_STATUS.eq(BookingStatusEnum.ACCEPTED))
                .fetchInto(DbEducatorCalendar.Result.class);
        var list = educatorCalendarMapper.toSimpleResponse(results);
        return new EducatorCalendarSimpleResponse().setSlots(list);
    }

    public ConsultationSessionResponse getConsultationSession(UUID profileId, UUID calendarId) {
        DbEducatorCalendar.Result data = dbEducatorCalendar.getDsl()
                .select(
                        EDUCATOR_CALENDAR.BOOKING_STATUS,
                        DSL.multiset(
                                DSL.select(
                                                STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID,
                                                STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS,
                                                STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS
                                        )
                                        .from(STUDENT_PAYMENT_TRANSACTION)
                                        .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID))
                        ).as(DbEducatorCalendar.Result.Fields.paymentTransaction).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentPaymentTransaction.Result.class))
                )
                .from(EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.ID.eq(calendarId))
                .fetchOptionalInto(DbEducatorCalendar.Result.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.EDUCATOR_CALENDAR_NOT_EXIST));
        return educatorCalendarMapper.toSessionResponse(data);

    }

    public void reschedule(UUID profileId, SessionRescheduleRequest request) {
        //查询这个profileId,是老师还是学生
        DbUser.Result user = dbUser.getDsl()
                .select(USER.asterisk())
                .from(STUDENT_PROFILE, USER)
                .where(STUDENT_PROFILE.USER_ID.eq(USER.ID).and(STUDENT_PROFILE.ID.eq(profileId)))
                .fetchOptionalInto(DbUser.Result.class)
                .orElseGet(
                        () -> dbUser.getDsl()
                                .select(USER.asterisk())
                                .from(EDUCATOR_PROFILE, USER)
                                .where(EDUCATOR_PROFILE.USER_ID.eq(USER.ID).and(EDUCATOR_PROFILE.ID.eq(profileId)))
                                .fetchOptionalInto(DbUser.Result.class)
                                .orElseThrow(() -> Exceptions.business(UniErrorCode.USER_PROFILE_ID_NOT_EXIST))
                );
        SessionReschedulePojo pojo = new SessionReschedulePojo()
                .setEducatorCalendarId(request.getEducatorCalendarId())
                .setEndTimeUtc(OffsetDateTime.parse(request.getEndTimeUtc()))
                .setStartTimeUtc(OffsetDateTime.parse(request.getStartTimeUtc()))
                .setUserId(user.getId())
                .setUserRole(user.getUserRole());
        dbSessionReschedule.getDao().insert(pojo);


    }

    //确定Student参加了Session, 只有educator可以确认学生是否参与了本次session
    public void commitStudentAttend(UUID educatorProfileId, UUID calendarId) {
        StudentPaymentTransactionPojo pojo = dbStudentPaymentTransaction.getDsl()
                .select(STUDENT_PAYMENT_TRANSACTION.asterisk())
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR)
                .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                        .and(EDUCATOR_CALENDAR.ID.eq(calendarId)
                                .and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(educatorProfileId)))
                ).fetchOptionalInto(StudentPaymentTransactionPojo.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));
        pojo.setProcessStatus(BookingStatusEnum.ATTEND);
    }




    public SessionEventLogResponse getSessionEventLogs(UUID transactionId) {
        List<SessionEventLogResponse.SessionEventLogPayload> payload = dbSessionOpLog.getDsl()
                .select()
                .from(dbSessionOpLog.getTable())
                .where(dbSessionOpLog.getTable().TRANSACTION_ID.eq(transactionId))
                .fetchInto(SessionEventLogResponse.SessionEventLogPayload.class);
        SessionEventLogResponse response = new SessionEventLogResponse();
        response.setPayload(payload);
        return response;
    }


    public UniPageResponse<EducatorHistoryPayload> page(
            UUID profileId,
            EducatorHistoryPageRequest request
    ) {
        var sessionQ = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID,
                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(EducatorHistoryPayload.Fields.submissionTime),
                        STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.cast(String.class).as(EducatorHistoryPayload.Fields.status),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.as(EducatorHistoryPayload.Fields.transactionItem)
                )
                .select(count().over().as(EducatorHistoryPayload.Fields.total))
                .from(STUDENT_PAYMENT_TRANSACTION,EDUCATOR_CALENDAR)
                .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                        .and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(profileId)));


        List<EducatorHistoryPayload> list = sessionQ
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(EducatorHistoryPayload.class);


        int totalSize = list.stream()
                .findFirst()
                .map(EducatorHistoryPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                list
        );
    }
}
