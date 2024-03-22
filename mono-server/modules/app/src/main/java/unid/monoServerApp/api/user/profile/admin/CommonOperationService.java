package unid.monoServerApp.api.user.profile.admin;

import cn.hutool.core.collection.ListUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.user.profile.educator.calendar.comment.EducatorSessionCommentService;
import unid.monoServerApp.database.table.course.DbEvent;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.eventLog.DbSessionOpLog;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.model.SessionLogger;
import unid.monoServerApp.service.SessionLoggerService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SessionOpType;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.jooq.impl.DSL.any;
import static org.jooq.impl.DSL.count;
import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.Tables.STUDENT_PAYMENT_TRANSACTION;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CommonOperationService {
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final SessionLoggerService sessionLoggerService;
    private final DbSessionOpLog dbSessionOpLog;
    private final EducatorSessionCommentService educatorSessionCommentService;
    private final DbEvent dbEvent;

    //查询session 分页列表
    //查询条件(transactionId, educatorName, studentName, status )
    //从页面的角度来看：status (null: 查询全部, pending: )
    public UniPageResponse<StudentSessionTransactionPayload> getSessionPage(CalendarSessionPageRequest request, BookingStatusEnum status){
        var statusCondition = DSL.noCondition() ;

        if(status == BookingStatusEnum.PENDING){
            statusCondition = DSL.and(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING)
                    .and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING)));
        }

        List<StudentSessionTransactionPayload> list = dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID.as(StudentSessionTransactionPayload.Fields.transactionId),
                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(StudentSessionTransactionPayload.Fields.submissionTime),
                        EDUCATOR_CALENDAR.asterisk(),
                        DSL.case_()
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING)),BookingStatusEnum.PENDING)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)),BookingStatusEnum.ACCEPTED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PAID).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)),BookingStatusEnum.RESERVED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.REJECTED)),BookingStatusEnum.REJECTED)
                                .as(StudentSessionTransactionPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                        STUDENT_PROFILE.ID.as(StudentSessionTransactionPayload.StudentProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                ).from(USER,STUDENT_PROFILE).where(USER.ID.eq(STUDENT_PROFILE.USER_ID).and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(STUDENT_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.studentProfile).convertFrom(r->r.isEmpty()?null:r.get(0).into(StudentSessionTransactionPayload.StudentProfile.class)),
                        DSL.multiset(
                                DSL.select(
                                        EDUCATOR_PROFILE.ID.as(StudentSessionTransactionPayload.EducatorProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                ).from(USER,EDUCATOR_PROFILE).where(USER.ID.eq(EDUCATOR_PROFILE.USER_ID).and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.educatorProfile).convertFrom(r->r.isEmpty()?null:r.get(0).into(StudentSessionTransactionPayload.EducatorProfile.class))
                )
                .select(count().over().as(StudentSessionTransactionPayload.Fields.total))
                .from(STUDENT_PAYMENT_TRANSACTION,EDUCATOR_CALENDAR)
                .where(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                                .and(statusCondition)
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
                        (pojo)->{
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
                        ()-> Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST)
                );
    }

    public StudentSessionTransactionPayload getSessionDetail(UUID id) {
        return dbStudentPaymentTransaction.getDsl()
                .select(
                        STUDENT_PAYMENT_TRANSACTION.ID.as(StudentSessionTransactionPayload.Fields.transactionId),
                        STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(StudentSessionTransactionPayload.Fields.submissionTime),
                        EDUCATOR_CALENDAR.asterisk(),
                        //记录日志
//                        DSL.multiset(
//                                DSL.select(
//                                                SESSION_OP_LOG.asterisk()
////                                                DSL.multiset(
////                                                        DSL.select(
////                                                                        USER.asterisk(),
////                                                                        DSL.multiset(
////                                                                                DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
////                                                                        ).as(StudentSessionTransactionPayload.EventOpLog.User.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
////                                                                        DSL.multiset(
////                                                                                DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
////                                                                        ).as(StudentSessionTransactionPayload.EventOpLog.User.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
////                                                                )
////                                                                .from(USER)
////                                                                .where(USER.ID.eq(SESSION_OP_LOG.USER_ID))
////
////                                                ).as(StudentSessionTransactionPayload.EventOpLog.Fields.user).convertFrom(r->r.isEmpty()?null:r.get(0).into(StudentSessionTransactionPayload.EventOpLog.User.class))
//                                        )
//                                        .from(SESSION_OP_LOG)
//                                        .where(SESSION_OP_LOG.TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.ID))
//                        ).as(StudentSessionTransactionPayload.Fields.eventOpLogs).convertFrom(r->r.isEmpty()?null:r.into(StudentSessionTransactionPayload.EventOpLog.class)),
                        //查询educator
                        DSL.case_()
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.PENDING)),BookingStatusEnum.PENDING)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)),BookingStatusEnum.ACCEPTED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PAID).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.ACCEPTED)),BookingStatusEnum.RESERVED)
                                .when(STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS.eq(PaymentStatusEnum.PENDING).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.REJECTED)),BookingStatusEnum.REJECTED)
                                .as(StudentSessionTransactionPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                        STUDENT_PROFILE.asterisk(),
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(STUDENT_PROFILE.COUNTRY_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.country).convertFrom(r->r.isEmpty()?null:r.get(0).into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //TODO:secondary school

                                        STUDENT_PROFILE.ID.as(StudentSessionTransactionPayload.StudentProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                ).from(USER,STUDENT_PROFILE).where(USER.ID.eq(STUDENT_PROFILE.USER_ID).and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(STUDENT_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.studentProfile).convertFrom(r->r.isEmpty()?null:r.get(0).into(StudentSessionTransactionPayload.StudentProfile.class)),
                        DSL.multiset(
                                DSL.select(
                                        //查询country
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(EDUCATOR_PROFILE.COUNTRY_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.country).convertFrom(r->r.isEmpty()?null:r.get(0).into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //查询expertise
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(any(EDUCATOR_PROFILE.EXPERTISE_ID)))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.expertises).convertFrom(r->r.isEmpty()?null:r.into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //查询languages
                                        DSL.multiset(
                                                DSL.select(
                                                        TAG.ID,
                                                        DSL.multiset(
                                                                DSL.select().from(I18N).where(I18N.ID.eq(TAG.DESCRIPTION_I18N_ID))
                                                        ).as(StudentSessionTransactionPayload.TagResponse.Fields.i18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                                ).from(TAG).where(TAG.ID.eq(any(EDUCATOR_PROFILE.LANGUAGE_ID)))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.languages).convertFrom(r->r.isEmpty()?null:r.into(StudentSessionTransactionPayload.TagResponse.class)),
                                        //TODO:university

                                        EDUCATOR_PROFILE.asterisk(),
                                        EDUCATOR_PROFILE.ID.as(StudentSessionTransactionPayload.EducatorProfile.Fields.profileId),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.select().from(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(StudentSessionTransactionPayload.EducatorProfile.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                ).from(USER,EDUCATOR_PROFILE).where(USER.ID.eq(EDUCATOR_PROFILE.USER_ID).and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID)))
                        ).as(StudentSessionTransactionPayload.Fields.educatorProfile).convertFrom(r->r.isEmpty()?null:r.get(0).into(StudentSessionTransactionPayload.EducatorProfile.class))
                )
                .select(count().over().as(StudentSessionTransactionPayload.Fields.total))
                .from(STUDENT_PAYMENT_TRANSACTION,EDUCATOR_CALENDAR)
                .where(
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EDUCATOR_CALENDAR.ID)
                ).and(STUDENT_PAYMENT_TRANSACTION.ID.eq(id))
                .fetchOptionalInto(StudentSessionTransactionPayload.class)
                .orElseThrow(()->Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));

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
                                        ).as(SessionEventLogResponse.SessionEventLogPayload.UserPayload.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                                        DSL.multiset(
                                                DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                        ).as(SessionEventLogResponse.SessionEventLogPayload.UserPayload.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                                )
                                .from(USER)
                                .where(USER.ID.eq(table.USER_ID))
                    ).as(SessionEventLogResponse.SessionEventLogPayload.Fields.user).convertFrom(r->r.isEmpty()?null:r.get(0).into(SessionEventLogResponse.SessionEventLogPayload.UserPayload.class))
                )
                .from(table)
                .where(table.TRANSACTION_ID.eq(transactionId))
                .fetchInto(SessionEventLogResponse.SessionEventLogPayload.class);
        SessionEventLogResponse response = new SessionEventLogResponse();
        response.setPayload(payload);
        return response;
    }


    public List<EducatorSessionNoteCommentResponse> getSessionComments(UUID transactionId){
        var table = dbStudentPaymentTransaction.getTable();
        return dbStudentPaymentTransaction.getDsl()
                .selectFrom(table)
                .where(table.ID.eq(transactionId))
                .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                .map(transaction -> educatorSessionCommentService.getComments(transaction.getTransactionItemRefId()))
                .orElseGet(ListUtil::empty);

    }

    public UniPageResponse<PromotionEventPayload> getPromotionEventPage(PromotionEventPageRequest request) {
        var table = dbEvent.getTable();
        List<PromotionEventPayload> payload = dbEvent.getDsl()
                .select(
                        table.asterisk(),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(table.TITLE_I18N_ID))
                        ).as(PromotionEventPayload.Fields.titleI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.select().from(EVENT_SCHEDULE_TIME).where(EVENT_SCHEDULE_TIME.REF_EVENT_ID.eq(table.ID))
                        ).as(PromotionEventPayload.Fields.times).convertFrom(r->r.isEmpty()?null:r.into(PromotionEventPayload.ScheduleTime.class))
                )
                .select(count().over().as(PromotionEventPayload.Fields.total))
                .from(table)
                .orderBy(table.ID.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(PromotionEventPayload.class);
        int totalSize = payload.stream()
                .findFirst()
                .map(PromotionEventPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }
}
