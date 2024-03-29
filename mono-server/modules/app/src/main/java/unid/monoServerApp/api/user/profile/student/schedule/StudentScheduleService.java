package unid.monoServerApp.api.user.profile.student.schedule;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.*;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.course.DbEvent;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.eventLog.DbSessionOpLog;
import unid.monoServerApp.database.table.learningHub.DbLearningHub;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.StudentPaymentTransactionMapper;
import unid.monoServerApp.model.SessionLogger;
import unid.monoServerApp.service.SessionLoggerService;
import unid.monoServerApp.util.TypeSerialNumberUtils;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import pwh.springWebStarter.response.UniErrorCode;
import unid.monoServerMeta.model.SessionOpType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StudentScheduleService {
    private final DSLContext dslContext;
    private final DbEducatorProfile dbEducatorProfile;
    private final DbLearningHub dbLearningHub;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final StudentPaymentTransactionMapper studentPaymentTransactionMapper;

    private final DbEvent dbEvent;
    private final DbStudentProfile dbStudentProfile;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final RedisTemplate<String, String> redisTemplateRefCache;
    private final SessionLoggerService sessionLoggerService;

    public UniPageResponse<StudentHistoryPayload> page(
            UUID studentProfileId,
            StudentHistoryPageRequest request
    ) {
        var interviewQ = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_UPLOADED_INTERVIEW.ID,
                        STUDENT_UPLOADED_INTERVIEW.CREATED_ON.as(StudentHistoryPayload.Fields.submissionTime),
                        STUDENT_UPLOADED_INTERVIEW.REVIEW_TYPE.cast(String.class).as(StudentHistoryPayload.Fields.status),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.as(StudentHistoryPayload.Fields.transactionItem)
                )
                .select(count().over().as(StudentHistoryPayload.Fields.total))
                .from(STUDENT_UPLOADED_INTERVIEW,STUDENT_PAYMENT_TRANSACTION)
                .where(STUDENT_PAYMENT_TRANSACTION.ID.eq(STUDENT_UPLOADED_INTERVIEW.PAYMENT_TRANSACTION_ID)
                        .and(STUDENT_UPLOADED_INTERVIEW.STUDENT_PROFILE_ID.eq(studentProfileId)));

        var writingQ = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_UPLOADED_WRITING.ID,
                        STUDENT_UPLOADED_WRITING.CREATED_ON.as(StudentHistoryPayload.Fields.submissionTime),
                        STUDENT_UPLOADED_WRITING.REVIEW_TYPE.cast(String.class).as(StudentHistoryPayload.Fields.status),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.as(StudentHistoryPayload.Fields.transactionItem)
                )
                .select(count().over().as(StudentHistoryPayload.Fields.total))
                .from(STUDENT_UPLOADED_WRITING,STUDENT_PAYMENT_TRANSACTION)
                .where(STUDENT_PAYMENT_TRANSACTION.ID.eq(STUDENT_UPLOADED_WRITING.PAYMENT_TRANSACTION_ID).and(STUDENT_UPLOADED_WRITING.STUDENT_PROFILE_ID.eq(studentProfileId)));

        var sessionQ = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID,
                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(StudentHistoryPayload.Fields.submissionTime),
                        STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.cast(String.class).as(StudentHistoryPayload.Fields.status),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.as(StudentHistoryPayload.Fields.transactionItem)
                )
                .select(count().over().as(StudentHistoryPayload.Fields.total))
                .from(STUDENT_PAYMENT_TRANSACTION)
                .where(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(studentProfileId));


        List<StudentHistoryPayload> list = writingQ
                .union(sessionQ)
                .union(interviewQ)
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(StudentHistoryPayload.class);


        int totalSize = list.stream()
                .findFirst()
                .map(StudentHistoryPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                list
        );
    }


    public JSONObject page(UUID studentProfileId,
                           OffsetDateTime startDateTimeUtc,
                           OffsetDateTime endDateTimeUtc,
                           Integer pageNumber,
                           Integer pageSize,
                           UUID transactionId) {
        //查询这个时间段session
        List<UUID> sessionTimeCondition = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID
                )
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR)
                .where(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                                .and(EDUCATOR_CALENDAR.START_TIME_UTC.ge(startDateTimeUtc)
                                        .and(endDateTimeUtc == null ? DSL.noCondition() : EDUCATOR_CALENDAR.START_TIME_UTC.le(endDateTimeUtc))
                                )
                )
                .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(studentProfileId))
                .and(transactionId == null ? DSL.noCondition() : STUDENT_PAYMENT_TRANSACTION.ID.eq(transactionId))
                .fetchInto(UUID.class);
        //查询这个时间段的 course event
        List<UUID> courseTimeCondition = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID
                )
                .from(STUDENT_PAYMENT_TRANSACTION)
                .leftJoin(EVENT).on(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EVENT.ID))
                .leftJoin(EVENT_SCHEDULE_TIME).on(EVENT_SCHEDULE_TIME.EVENT_ID.eq(EVENT.ID))
                .where(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(studentProfileId)
                        .and(EVENT_SCHEDULE_TIME.START_TIME.ge(startDateTimeUtc.toLocalDateTime())
                                .and(endDateTimeUtc == null ? DSL.noCondition() : EVENT_SCHEDULE_TIME.START_TIME.le(endDateTimeUtc.toLocalDateTime()))
                        ))
                .fetchInto(UUID.class);
        sessionTimeCondition.addAll(courseTimeCondition);

        //查询 sessionQ
        var sessionQ = DSL.multiset(
                dbEducatorCalendar.getSimpleQuery(dbEducatorCalendar.getTable())
                        .where(dbEducatorCalendar.getTable().ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
        ).as(DbStudentPaymentTransaction.Result.Fields.session).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbEducatorCalendar.SimpleResult.class));

        //查询 courseQ
        var courseQ = DSL.multiset(
                dbEvent.getSimpleQuery(dbEvent.getTable()).where(EVENT.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
        ).as(DbStudentPaymentTransaction.Result.Fields.event).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbEvent.SimpleResult.class));

        //查询 student
        var studentQ = DSL.multiset(
                dbStudentProfile
                        .getSimpleQuery(dbStudentProfile.getTable())
                        .where(STUDENT_PROFILE.ID.eq(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID))
        ).as(DbStudentPaymentTransaction.Result.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentProfile.SimpleResult.class));


        Integer total = dbStudentPaymentTransaction.getDsl()
                .selectCount()
                .from(STUDENT_PAYMENT_TRANSACTION)
                .where(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(studentProfileId))
                .and(STUDENT_PAYMENT_TRANSACTION.ID.in(sessionTimeCondition))
                .fetchOptionalInto(Integer.class).orElse(0);


        List<DbStudentPaymentTransaction.Result> list = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.asterisk(),
                        courseQ,
                        studentQ,
                        sessionQ
                )
                .from(STUDENT_PAYMENT_TRANSACTION)
                .where(STUDENT_PAYMENT_TRANSACTION.ID.in(sessionTimeCondition))
                .limit(pageSize)
                .offset((pageNumber - 1) * pageSize)
                .fetchInto(DbStudentPaymentTransaction.Result.class);
        List<StudentPaymentTransactionResponse> result = studentPaymentTransactionMapper.toResponse(list);
        int totalPages = (total + pageSize - 1) / pageSize;

        return JSONUtil.createObj().set("totalRecords", total)
                .set("pageNumber", pageNumber)
                .set("totalPages", totalPages)
                .set("pageSize", pageSize)
                .set("result", result);
    }


    @Deprecated
    public JSONObject pageXXXX(UUID profileId,
                               OffsetDateTime startDateTimeUtc,
                               OffsetDateTime endDateTimeUtc,
                               Integer pageNumber,
                               Integer pageSize) {

        var educatorQ = dslContext.select(
                multiset(
                        select(I18N.fields())
                                .from(I18N)
                                .where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                ).as(UserResponse.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                multiset(
                        select(I18N.fields())
                                .from(I18N)
                                .where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                ).as(UserResponse.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                EDUCATOR_PROFILE.CREATED_ON.as(BaseResponse.BaseResponseFields.createdOnUtc),
                EDUCATOR_PROFILE.UPDATED_ON.as(BaseResponse.BaseResponseFields.updatedOnUtc),
                EDUCATOR_PROFILE.ID
        ).from(EDUCATOR_PROFILE, USER, EDUCATOR_CALENDAR).where(EDUCATOR_PROFILE.USER_ID.eq(USER.ID).and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID)));

        var calendarQ = dslContext.select(
                EDUCATOR_CALENDAR.DATE,
                EDUCATOR_CALENDAR.HOUR_START,
                EDUCATOR_CALENDAR.HOUR_END,
                EDUCATOR_CALENDAR.BOOKING_STATUS,
                EDUCATOR_CALENDAR.MEETING_URL,
                EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID
        ).from(EDUCATOR_CALENDAR);

        Integer totalRecords = dslContext
                .selectCount()
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
                .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(profileId))
                .and(EDUCATOR_CALENDAR.START_TIME_UTC.ge(startDateTimeUtc).and(endDateTimeUtc == null ? DSL.noCondition() : EDUCATOR_CALENDAR.START_TIME_UTC.le(endDateTimeUtc)))
                .fetchOptionalInto(Integer.class).orElse(0);


        List<StudentScheduleResponse> list = dslContext.select(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.as(StudentScheduleResponse.Fields.id),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_AMOUNT,
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_CURRENCY,
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM,
                        STUDENT_PAYMENT_TRANSACTION.PAYMENT_METHOD,
                        STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS,
                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(StudentScheduleResponse.Fields.requestSubmissionTime),

                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(BaseResponse.BaseResponseFields.createdOnUtc),
                        STUDENT_PAYMENT_TRANSACTION.UPDATED_ON.as(BaseResponse.BaseResponseFields.updatedOnUtc),
                        multiset(
                                dbEducatorProfile.getSimpleQuery().and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID))
                        ).as(StudentScheduleResponse.Fields.educator).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(EducatorProfileSimpleResponse.class)),
                        multiset(
                                calendarQ.where(EDUCATOR_CALENDAR.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
                        ).as(StudentScheduleResponse.Fields.calendar).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentScheduleResponse.Calendar.class)),
                        multiset(
                                dbLearningHub.getQuery().and(EVENT.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
                        ).as(StudentScheduleResponse.Fields.learningHubResponse).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(LearningHubResponse.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(EVENT, I18N)
                                        .where(I18N.ID.eq(EVENT.TITLE_I18N_ID).and(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EVENT.ID)))
                        ).as(StudentScheduleResponse.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))

                ).from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR)
                .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID))
                .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(profileId))
                .and(EDUCATOR_CALENDAR.START_TIME_UTC.ge(startDateTimeUtc).and(endDateTimeUtc == null ? DSL.noCondition() : EDUCATOR_CALENDAR.START_TIME_UTC.le(endDateTimeUtc)))
                .orderBy(EDUCATOR_CALENDAR.START_TIME_UTC.desc())
                .limit(pageSize)
                .offset((pageNumber - 1) * pageSize)
                .fetchInto(StudentScheduleResponse.class);

        int totalPages = (totalRecords + pageSize - 1) / pageSize;

        return JSONUtil.createObj().set("totalRecords", totalRecords)
                .set("pageNumber", pageNumber)
                .set("totalPages", totalPages)
                .set("pageSize", pageSize)
                .set("result", list);
    }


    public ScheduleTransactionResponse create(UUID studentProfileId, StudentBookingEducatorCalendarRequest request) {
        //创建支付订单
        //查询educator收费
        EducatorProfilePojo educatorProfilePojo = dbEducatorProfile.getDao().fetchOneById(request.getEducatorProfileId());
        Optional.ofNullable(educatorProfilePojo).orElseThrow(() -> Exceptions.business(UniErrorCode.EDUCATOR_NOT_EXIST));
        Optional.ofNullable(educatorProfilePojo.getHourlyRate()).orElseThrow(() -> Exceptions.business(UniErrorCode.EDUCATOR_HOURLY_RATE_IS_NULL));
        //当前学生是否已经提交过该时间段订单
        dbEducatorCalendar.getDsl()
                .select()
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR)
                .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID))
                .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(studentProfileId))
                .and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(request.getEducatorProfileId()))
                .and(EDUCATOR_CALENDAR.ID.eq(request.getEducatorCalendarId()))
                .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING))
                .fetchOptional()
                .ifPresentOrElse(
                        value -> {
                            StaticLog.info(" 当前订单尚未处理, 无法重复提交 studentProfileId = {}, educatorCalendarId = ", studentProfileId, request.getEducatorCalendarId());
                            throw Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_IS_ALREADY_EXIST);
                        },
                        () -> {
                        }
                );

        StudentPaymentTransactionPojo studentPaymentTransactionPojo = new StudentPaymentTransactionPojo()
                .setTransactionItemRefId(request.getEducatorCalendarId())
                .setStudentProfileId(studentProfileId)
                .setTransactionAmount(new BigDecimal(educatorProfilePojo.getHourlyRate()))
                .setTransactionItem(StudentTransactionItemEnum.EDUCATOR_SCHEDULE)
                .setTransactionCurrency(CurrencyEnum.HKD)
                .setPaymentStatus(PaymentStatusEnum.PENDING)
                .setProcessStatus(BookingStatusEnum.PENDING)
                .setTransactionSubmitTime(LocalDateTime.now());
        studentPaymentTransactionPojo.setTransactionSerialNumber(TypeSerialNumberUtils.generateOrderNumber("ER", redisTemplateRefCache));

        //查询当前educator calendar
        DbEducatorCalendar.Result calendar = dbEducatorCalendar.getDsl()
                .select().from(EDUCATOR_CALENDAR).where(EDUCATOR_CALENDAR.ID.eq(studentPaymentTransactionPojo.getTransactionItemRefId()))
                .fetchOptionalInto(DbEducatorCalendar.Result.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.EDUCATOR_CALENDAR_NOT_EXIST));

        dbStudentPaymentTransaction.getDao().insert(studentPaymentTransactionPojo);

        sessionLoggerService.async(SessionLogger.OpEvent.builder()
                .userId(RequestHolder.get().getUser().getUserId())
                .status(BookingStatus.PENDING)
                .transactionId(studentPaymentTransactionPojo.getTransactionItemRefId())
                .timeUtc(OffsetDateTime.now())
                .opType(SessionOpType.REQUEST).build());

        return studentPaymentTransactionMapper.toResponse(studentPaymentTransactionPojo);
    }

}
