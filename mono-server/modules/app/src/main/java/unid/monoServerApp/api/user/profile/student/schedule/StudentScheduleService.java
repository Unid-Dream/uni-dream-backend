package unid.monoServerApp.api.user.profile.student.schedule;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.learningHub.DbLearningHub;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.mapper.StudentPaymentTransactionMapper;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.UniErrorCode;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;
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


    public JSONObject page(UUID profileId,
                           LocalDate startDate,
                           LocalDate endDate,
                           Integer pageNumber,
                           Integer pageSize){

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
        ).from(EDUCATOR_PROFILE,USER,EDUCATOR_CALENDAR).where(EDUCATOR_PROFILE.USER_ID.eq(USER.ID).and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID)));

        var calendarQ = dslContext.select(
                EDUCATOR_CALENDAR.DATE,
                EDUCATOR_CALENDAR.HOUR_START,
                EDUCATOR_CALENDAR.HOUR_END,
                EDUCATOR_CALENDAR.BOOKING_STATUS,
                EDUCATOR_CALENDAR.MEETING_URL,
                EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID
        ).from(EDUCATOR_CALENDAR);

        Integer totalRecords =  dslContext
                .selectCount()
                .from(STUDENT_PAYMENT_TRANSACTION,EDUCATOR_CALENDAR)
                .where(EDUCATOR_CALENDAR.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
                .and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(profileId))
                .and(EDUCATOR_CALENDAR.DATE.ge(startDate).and(endDate == null? DSL.noCondition():EDUCATOR_CALENDAR.DATE.le(endDate)))
                .fetchOptionalInto(Integer.class).orElse(0);


       List<StudentScheduleResponse> list = dbStudentPaymentTransaction.getDsl()
               .select(
//                       STUDENT_PAYMENT_TRANSACTION.asterisk(),
                       //查询 session

//                       STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.as(StudentScheduleResponse.Fields.id)
               )

//                       STUDENT_PAYMENT_TRANSACTION.ID.as(StudentScheduleResponse.Fields.transactionId),
//
//                       STUDENT_PAYMENT_TRANSACTION.TRANSACTION_AMOUNT,
//                STUDENT_PAYMENT_TRANSACTION.TRANSACTION_CURRENCY,
//                STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM,
//                STUDENT_PAYMENT_TRANSACTION.PAYMENT_METHOD,
//                STUDENT_PAYMENT_TRANSACTION.PAYMENT_STATUS,
//                       STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(StudentScheduleResponse.Fields.requestSubmissionTime),
//
//                       STUDENT_PAYMENT_TRANSACTION.CREATED_ON.as(BaseResponse.BaseResponseFields.createdOnUtc),
//                STUDENT_PAYMENT_TRANSACTION.UPDATED_ON.as(BaseResponse.BaseResponseFields.updatedOnUtc),
//                multiset(
//                               dbEducatorProfile.getQueryEducatorProfile().and(EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID.eq(EDUCATOR_PROFILE.ID))
//                        ).as(StudentScheduleResponse.Fields.educator).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(EducatorResponse.class)),
//                multiset(
//                               calendarQ.where(EDUCATOR_CALENDAR.ID.eq(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID))
//                       ).as(StudentScheduleResponse.Fields.calendar).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(StudentScheduleResponse.Calendar.class)),
//                multiset(
//                               dbLearningHub.getQuery().and(EVENT.ID.eq(EDUCATOR_CALENDAR_EXTENSION.EVENT_ID))
//                       ).as(StudentScheduleResponse.Fields.learningHubResponse).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(LearningHubResponse.class)),
//                multiset(
//                               select(I18N.fields())
//                                       .from(EVENT,I18N)
//                                       .where(I18N.ID.eq(EVENT.TITLE_I18N_ID).and(EDUCATOR_CALENDAR_EXTENSION.EVENT_ID.eq(EVENT.ID)))
//                       ).as(StudentScheduleResponse.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))

               .from(STUDENT_PAYMENT_TRANSACTION)
               .where(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(profileId))
//               .and(STUDENT_PAYMENT_TRANSACTION.DATE.ge(startDate).and(endDate == null? DSL.noCondition():EDUCATOR_CALENDAR.DATE.le(endDate)))
//               .orderBy(STUDENT_PAYMENT_TRANSACTION.DATE.desc(),EDUCATOR_CALENDAR.HOUR_START.asc())
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

    //预定educator时间槽
    public StudentPaymentTransactionResponse create(UUID studentProfileId, StudentBookingEducatorCalendarRequest request) {
        //创建支付订单
        //查询educator收费
        EducatorProfilePojo educatorProfilePojo =  dbEducatorProfile.getDao().fetchOneById(request.getEducatorProfileId());
        Optional.ofNullable(educatorProfilePojo).orElseThrow(()-> Exceptions.business(UniErrorCode.Business.EDUCATOR_NOT_EXIST));
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
}
