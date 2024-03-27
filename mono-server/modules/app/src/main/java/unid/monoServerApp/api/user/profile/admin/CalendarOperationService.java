package unid.monoServerApp.api.user.profile.admin;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.EventStatusEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.jooqMono.model.tables.pojos.EventPojo;
import unid.jooqMono.model.tables.pojos.EventScheduleTimePojo;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.user.profile.educator.calendar.comment.EducatorSessionCommentService;
import unid.monoServerApp.database.table.course.DbEvent;
import unid.monoServerApp.database.table.course.DbEventScheduleTime;
import unid.monoServerApp.database.table.educatorCalendar.DbSessionReschedule;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.eventLog.DbSessionOpLog;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.CourseEventMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.model.SessionLogger;
import unid.monoServerApp.service.EmailService;
import unid.monoServerApp.service.SessionLoggerService;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.api.version2.request.PendingSessionRemindEducatorEmailPayload;
import unid.monoServerMeta.api.version2.request.PendingSessionRemindEducatorPayload;
import unid.monoServerMeta.api.version2.request.RescheduleSessionPageRequest;
import unid.monoServerMeta.api.version2.request.RescheduleSessionPayload;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SessionOpType;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.any;
import static org.jooq.impl.DSL.count;
import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.Tables.STUDENT_PAYMENT_TRANSACTION;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CalendarOperationService {
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final SessionLoggerService sessionLoggerService;
    private final DbSessionOpLog dbSessionOpLog;
    private final EducatorSessionCommentService educatorSessionCommentService;
    private final DbEvent dbEvent;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final CourseEventMapper courseEventMapper;
    private final DbEventScheduleTime dbEventScheduleTime;
    private final SessionService sessionService;
    private final DbUser dbUser;
    private final DbEducatorProfile dbEducatorProfile;
    private final EmailService emailService;
    private final DbSessionReschedule dbSessionReschedule;

    //查询session 分页列表
    //查询条件(transactionId, educatorName, studentName, status )
    //从页面的角度来看：status (null: 查询全部, pending: )
    public UniPageResponse<StudentSessionTransactionPayload> page(CalendarSessionPageRequest request, BookingStatusEnum status) {
        var statusCondition = DSL.noCondition();

        if (status == BookingStatusEnum.PENDING) {
            statusCondition = DSL.and(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING)
                    .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING)));
        }

        List<StudentSessionTransactionPayload> list = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID.as(StudentSessionTransactionPayload.Fields.transactionId),
                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(StudentSessionTransactionPayload.Fields.submissionTime),
                        EDUCATOR_CALENDAR.asterisk(),
                        DSL.case_()
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.isNull()), BookingStatusEnum.PENDING_APPROVAL)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)), BookingStatusEnum.PENDING_PAYMENT)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PAID).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)), BookingStatusEnum.ACCEPTED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.REJECTED), BookingStatusEnum.REJECTED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.CANCELLED), BookingStatusEnum.CANCELLED)
                                .as(StudentSessionTransactionPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                        STUDENT_PROFILE.ID.as(StudentSessionTransactionPayload.StudentProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                ).from(USER, STUDENT_PROFILE).where(USER.ID.eq(STUDENT_PROFILE.USER_ID).and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(STUDENT_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentSessionTransactionPayload.StudentProfile.class)),
                        DSL.multiset(
                                DSL.select(
                                        EDUCATOR_PROFILE.ID.as(StudentSessionTransactionPayload.EducatorProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                ).from(USER, EDUCATOR_PROFILE).where(USER.ID.eq(EDUCATOR_PROFILE.USER_ID).and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.educatorProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentSessionTransactionPayload.EducatorProfile.class))
                )
                .select(count().over().as(StudentSessionTransactionPayload.Fields.total))
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR)
                .where(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID).and(statusCondition)
                )
                .orderBy(STUDENT_PAYMENT_TRANSACTION.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(StudentSessionTransactionPayload.class);

        int totalSize = list.stream()
                .findFirst()
                .map(StudentSessionTransactionPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                list
        );
    }

    public void cancel(UUID sessionId) {
        var table = dbStudentPaymentTransaction.getTable();
        dbStudentPaymentTransaction.getDsl()
                .selectFrom(table)
                .where(table.ID.eq(sessionId))
                .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                .ifPresentOrElse(
                        (pojo) -> {
                            //统计记录操作日志
                            pojo.setProcessStatus(BookingStatusEnum.CANCELLED);
                            dbStudentPaymentTransaction.getDao().update(pojo);
                            sessionLoggerService.async(SessionLogger.OpEvent.builder()
                                    .userId(RequestHolder.get().getUser().getUserId())
                                    .status(BookingStatus.CANCELLED)
                                    .transactionId(pojo.getTransactionItemRefId())
                                    .timeUtc(OffsetDateTime.now())
                                    .opType(SessionOpType.CANCEL).build());
                        },
                        () -> Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST)
                );
    }

    public StudentSessionTransactionPayload getSessionDetail(UUID id) {
        return dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID.as(StudentSessionTransactionPayload.Fields.transactionId),
                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(StudentSessionTransactionPayload.Fields.submissionTime),
                        EDUCATOR_CALENDAR.asterisk(),
                        //记录日志
                        //查询educator
                        DSL.case_()
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING)), BookingStatusEnum.PENDING_APPROVAL)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)), BookingStatusEnum.PENDING_PAYMENT)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PAID).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)), BookingStatusEnum.ACCEPTED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.REJECTED), BookingStatusEnum.REJECTED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.CANCELLED), BookingStatusEnum.CANCELLED)
                                .as(StudentSessionTransactionPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                        STUDENT_PROFILE.asterisk(),
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(STUDENT_PROFILE.COUNTRY_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //TODO:secondary school

                                        STUDENT_PROFILE.ID.as(StudentSessionTransactionPayload.StudentProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                ).from(USER, STUDENT_PROFILE).where(USER.ID.eq(STUDENT_PROFILE.USER_ID).and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(STUDENT_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentSessionTransactionPayload.StudentProfile.class)),
                        DSL.multiset(
                                DSL.select(
                                        //查询country
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(EDUCATOR_PROFILE.COUNTRY_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //查询expertise
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(any(EDUCATOR_PROFILE.EXPERTISE_ID)))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.expertises).convertFrom(r -> r.isEmpty() ? null : r.into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //查询languages
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(any(EDUCATOR_PROFILE.LANGUAGE_ID)))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.languages).convertFrom(r -> r.isEmpty() ? null : r.into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //TODO:university

                                        EDUCATOR_PROFILE.asterisk(),
                                        EDUCATOR_PROFILE.ID.as(StudentSessionTransactionPayload.EducatorProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                ).from(USER, EDUCATOR_PROFILE).where(USER.ID.eq(EDUCATOR_PROFILE.USER_ID).and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.educatorProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentSessionTransactionPayload.EducatorProfile.class))
                )
                .select(count().over().as(StudentSessionTransactionPayload.Fields.total))
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR)
                .where(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                ).and(STUDENT_PAYMENT_TRANSACTION.ID.eq(id))
                .fetchOptionalInto(StudentSessionTransactionPayload.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));

    }

    public SessionEventLogResponse getSessionEventLogs(UUID transactionId) {
        var table = dbSessionOpLog.getTable();
        List<SessionEventLogResponse.SessionEventLogPayload> payload = dbSessionOpLog.getDsl().select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.select(
                                                USER.asterisk(),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(SessionEventLogResponse.SessionEventLogPayload.UserPayload.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(SessionEventLogResponse.SessionEventLogPayload.UserPayload.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(USER)
                                        .where(USER.ID.eq(table.USER_ID))
                        ).as(SessionEventLogResponse.SessionEventLogPayload.Fields.user).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(SessionEventLogResponse.SessionEventLogPayload.UserPayload.class))
                )
                .from(table,STUDENT_PAYMENT_TRANSACTION)
                .where(table.TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID)
                        .and(STUDENT_PAYMENT_TRANSACTION.ID.eq(transactionId)))
                .fetchInto(SessionEventLogResponse.SessionEventLogPayload.class);
        SessionEventLogResponse response = new SessionEventLogResponse();
        response.setPayload(payload);
        return response;
    }


    public List<EducatorSessionNoteCommentResponse> getSessionComments(UUID transactionId) {
        var table = dbStudentPaymentTransaction.getTable();
        return dbStudentPaymentTransaction.getDsl()
                .selectFrom(table)
                .where(table.ID.eq(transactionId))
                .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                .map(transaction -> educatorSessionCommentService.getComments(transaction.getTransactionItemRefId()))
                .orElseGet(ListUtil::empty);

    }

    public UniPageResponse<CourseEventPayload> page(CourseEventPageRequest request) {
        var table = dbEvent.getTable();
        List<CourseEventPayload> payload = dbEvent.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(table.TITLE_I18N_ID))
                        ).as(CourseEventPayload.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.select(
                                        EVENT_SCHEDULE_TIME.START_TIME.as(CourseEventPayload.Duration.Fields.startTimeUtc),
                                        EVENT_SCHEDULE_TIME.END_TIME.as(CourseEventPayload.Duration.Fields.endTimeUtc)
                                ).from(EVENT_SCHEDULE_TIME).where(EVENT_SCHEDULE_TIME.EVENT_ID.eq(table.ID))
                        ).as(CourseEventPayload.Fields.duration).convertFrom(r -> r.isEmpty() ? null : r.into(CourseEventPayload.Duration.class))
                )
                .select(count().over().as(CourseEventPayload.Fields.total))
                .from(table)
                .orderBy(table.ID.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(CourseEventPayload.class);
        int totalSize = payload.stream()
                .findFirst()
                .map(CourseEventPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public EventPojo create(CourseEventPayload payload) {
        sessionService.initDatabaseSession();

        DbEvent.Result event = new DbEvent.Result();
        courseEventMapper.merge(event, payload);
        //默认状态
        event.setEventStatus(EventStatusEnum.OPEN);

        var i18nTable = dbI18N.getTable();
        dbI18N.getDsl().insertInto(i18nTable)
                .set(dbI18N.getDsl().newRecord(i18nTable, i18nMapper.toPojo(payload.getTitleI18n())))
                .returning(i18nTable.ID)
                .fetchOptionalInto(DbI18N.Result.class)
                .ifPresent(i18n -> event.setTitleI18nId(i18n.getId()));

        dbI18N.getDsl().insertInto(i18nTable)
                .set(dbI18N.getDsl().newRecord(i18nTable, i18nMapper.toPojo(payload.getDescriptionI18n())))
                .returning(i18nTable.ID)
                .fetchOptionalInto(DbI18N.Result.class)
                .ifPresent(i18n -> event.setDescriptionI18nId(i18n.getId()));

        dbI18N.getDsl().insertInto(i18nTable)
                .set(dbI18N.getDsl().newRecord(i18nTable, i18nMapper.toPojo(payload.getAgendaI18n())))
                .returning(i18nTable.ID)
                .fetchOptionalInto(DbI18N.Result.class)
                .ifPresent(i18n -> event.setAgendaI18nId(i18n.getId()));

        dbEvent.getDao().insert(event);

        payload.getDuration().forEach(timeUtc -> dbEventScheduleTime.getDao().insert(
                new DbEventScheduleTime.Result()
                        .setStartTime(timeUtc.getStartTimeUtc().toLocalDateTime())
                        .setEndTime(timeUtc.getEndTimeUtc().toLocalDateTime())
                        .setEventId(event.getId())
        ));

        return event;
    }


    public CourseEventPayload get(UUID eventId) {
        var table = dbEvent.getTable();
        return dbEvent.getDsl()
                .select(
                        table.asterisk(),
                        table.CREATED_ON.as(CourseEventPayload.Fields.eventCreateTime),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.TITLE_I18N_ID))
                        ).as(CourseEventPayload.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.DESCRIPTION_I18N_ID))
                        ).as(CourseEventPayload.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(table.AGENDA_I18N_ID))
                        ).as(CourseEventPayload.Fields.agendaI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.selectFrom(EVENT_SCHEDULE_TIME).where(EVENT_SCHEDULE_TIME.ID.eq(table.ID))
                        ).as(CourseEventPayload.Fields.duration).convertFrom(r -> r.isEmpty() ? null : r.into(CourseEventPayload.Duration.class))
                )
                .from(table)
                .where(table.ID.eq(eventId))
                .fetchOptionalInto(CourseEventPayload.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.COURSE_EVENT_NOT_EXIST));
    }

    @Transactional(rollbackFor = Exception.class)
    public EventPojo update(CourseEventPayload payload) {
        sessionService.initDatabaseSession();
        var table = dbEvent.getTable();

        DbEvent.Result event = dbEvent.getDsl()
                .selectFrom(table)
                .where(table.ID.eq(payload.getId()))
                .fetchOptionalInto(DbEvent.Result.class)
                .orElseThrow(() -> Exceptions.business(UniErrorCode.COURSE_EVENT_NOT_EXIST));
        courseEventMapper.merge(event, payload);

        Optional.ofNullable(
                event.getTitleI18nId()
        ).ifPresent(i18nId -> dbI18N.getDao().update(
                i18nMapper.toPojo(payload.getTitleI18n()).setId(i18nId)
        ));

        Optional.ofNullable(
                event.getDescriptionI18nId()
        ).ifPresent(i18nId -> dbI18N.getDao().update(
                i18nMapper.toPojo(payload.getDescriptionI18n()).setId(i18nId)
        ));

        Optional.ofNullable(
                event.getAgendaI18nId()
        ).ifPresent(i18nId -> dbI18N.getDao().update(
                i18nMapper.toPojo(payload.getAgendaI18n()).setId(i18nId)
        ));

        dbEvent.getDao().update(event);

        return event;
    }

    public void remindEducator(PendingSessionRemindEducatorPayload request) {
        //查询对应eudcator的邮箱
        PendingSessionRemindEducatorEmailPayload payload =  dbEducatorProfile.getDsl()
                        .select(
                                USER.EMAIL,
                                EDUCATOR_CALENDAR.START_TIME_UTC.as(PendingSessionRemindEducatorEmailPayload.Fields.startTimeUtc),
                                EDUCATOR_CALENDAR.END_TIME_UTC.as(PendingSessionRemindEducatorEmailPayload.Fields.endTimeUtc),
                                DSL.multiset(
                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                ).as(PendingSessionRemindEducatorEmailPayload.Fields.educatorFirstName).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                DSL.multiset(
                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                ).as(PendingSessionRemindEducatorEmailPayload.Fields.educatorLastName).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                        )
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR,USER, EDUCATOR_PROFILE)
                .where(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                )
                .and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID))
                .and(EDUCATOR_PROFILE.USER_ID.eq(USER.ID))
                .and(STUDENT_PAYMENT_TRANSACTION.ID.eq(request.getTransactionId()))
                .fetchOptionalInto(PendingSessionRemindEducatorEmailPayload.class)
                .orElseThrow(()->Exceptions.business(UniErrorCode.EDUCATOR_NOT_EXIST));
        emailService.requestRemindEducatorPendingSession(payload);
    }



    public void remindAllEducator() {
        //查询对应eudcator的邮箱
        List<PendingSessionRemindEducatorEmailPayload> list =  dbEducatorProfile.getDsl()
                .select(
                        USER.EMAIL,
                        EDUCATOR_CALENDAR.START_TIME_UTC.as(PendingSessionRemindEducatorEmailPayload.Fields.startTimeUtc),
                        EDUCATOR_CALENDAR.END_TIME_UTC.as(PendingSessionRemindEducatorEmailPayload.Fields.endTimeUtc),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                        ).as(PendingSessionRemindEducatorEmailPayload.Fields.educatorFirstName).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                        ).as(PendingSessionRemindEducatorEmailPayload.Fields.educatorLastName).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .from(STUDENT_PAYMENT_TRANSACTION, EDUCATOR_CALENDAR,USER, EDUCATOR_PROFILE)
                .where(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                )
                .and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID))
                .and(EDUCATOR_PROFILE.USER_ID.eq(USER.ID))
                //学生提交了生成,但是没有accept or reject
                .and(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING))
                .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING))
                .fetchInto(PendingSessionRemindEducatorEmailPayload.class);
        list.forEach(emailService::requestRemindEducatorPendingSession);
    }

    public void deleteEvent(UUID id) {
        dbEvent.getDao().deleteById(id);
    }

    public UniPageResponse<RescheduleSessionPayload> getRescheduleSessionPage(RescheduleSessionPageRequest request) {
        List<RescheduleSessionPayload> list = dbStudentPaymentTransaction.getDsl()
                .select(

                        DSL.multiset(
                                DSL.select(
                                        DSL.multiset(
                                                DSL.select(I18N.asterisk()).from(I18N,USER,EDUCATOR_PROFILE).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID).and(USER.ID.eq(EDUCATOR_PROFILE.USER_ID)).and(EDUCATOR_PROFILE.ID.eq(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID)))
                                        ).as(RescheduleSessionPayload.EducatorProfile.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select(I18N.asterisk()).from(I18N,USER,EDUCATOR_PROFILE).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID).and(USER.ID.eq(EDUCATOR_PROFILE.USER_ID)).and(EDUCATOR_PROFILE.ID.eq(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID)))
                                        ).as(RescheduleSessionPayload.EducatorProfile.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                )
                        ).as(RescheduleSessionPayload.Fields.educator).convertFrom(r->r.isEmpty()?null:r.get(0).into(RescheduleSessionPayload.EducatorProfile.class)),
                        DSL.multiset(
                                DSL.select(
                                        DSL.multiset(
                                                DSL.select(I18N.asterisk()).from(I18N,USER,STUDENT_PROFILE).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID).and(USER.ID.eq(STUDENT_PROFILE.USER_ID)).and(STUDENT_PROFILE.ID.eq(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID)))
                                        ).as(RescheduleSessionPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select(I18N.asterisk()).from(I18N,USER,STUDENT_PROFILE).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID).and(USER.ID.eq(STUDENT_PROFILE.USER_ID)).and(STUDENT_PROFILE.ID.eq(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID)))
                                        ).as(RescheduleSessionPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                )
                        ).as(RescheduleSessionPayload.Fields.student).convertFrom(r->r.isEmpty()?null:r.get(0).into(RescheduleSessionPayload.StudentProfile.class))

                 )
                .select(count().over().as(RescheduleSessionPayload.Fields.total))
                .from(SESSION_RESCHEDULE,EDUCATOR_CALENDAR,STUDENT_PAYMENT_TRANSACTION)
                .where(SESSION_RESCHEDULE.EDUCATOR_CALENDAR_ID.eq(EDUCATOR_CALENDAR.ID))
                .and(STUDENT_PAYMENT_TRANSACTION.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
                .orderBy(SESSION_RESCHEDULE.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(RescheduleSessionPayload.class);
        int totalSize = list.stream()
                .findFirst()
                .map(RescheduleSessionPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                list
        );
    }

    public void acceptOrRejectReschedule(UUID id, boolean accept) {
        Optional.ofNullable(
                dbSessionReschedule.getDao().fetchOneById(id)
        ).ifPresent((pojo)->{
            //更新
            dbSessionReschedule.getDao().update(pojo.setAccpet(accept));
            //记录操作日志
            sessionLoggerService.async(SessionLogger.OpEvent.builder()
                    .userId(RequestHolder.get().getUser().getUserId())
                    .status(accept? BookingStatus.RESCHEDULE_ACCEPT : BookingStatus.RESCHEDULE_REJECT)
                    .transactionId(pojo.getEducatorCalendarId())
                    .timeUtc(OffsetDateTime.now())
                    .opType(accept? SessionOpType.RESCHEDULE_ACCEPTED : SessionOpType.RESCHEDULE_REJECTED).build());
        });
    }
}
