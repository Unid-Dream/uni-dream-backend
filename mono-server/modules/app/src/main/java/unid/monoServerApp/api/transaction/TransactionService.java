package unid.monoServerApp.api.transaction;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerMeta.api.TransactionResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.UniErrorCode;

import java.util.UUID;

import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.Tables.STUDENT_PAYMENT_TRANSACTION;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TransactionService {
    private final DSLContext dslContext;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorSessionNoteItem dbEducatorSessionNoteItem;

    public TransactionResponse get(UUID transactionId) {
        //0.查询 Writing Skill Transaction
        TransactionResponse response = dslContext.select(
                        USER.LAST_NAME_I18N_ID,
                        USER.FIST_NAME_I18N_ID,
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM.as(TransactionResponse.Fields.serviceType),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_SUBMIT_TIME.as(TransactionResponse.Fields.requestSubmissionTime),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_SUBMIT_TIME.as(TransactionResponse.Fields.requestTime),
                        STUDENT_PAYMENT_TRANSACTION.PAYMENT_METHOD.as(TransactionResponse.Fields.paymentMethod),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_AMOUNT.as(TransactionResponse.Fields.amount),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_CURRENCY.as(TransactionResponse.Fields.unit))
                .from(STUDENT_PAYMENT_TRANSACTION)
                .leftJoin(STUDENT_PROFILE).on(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(STUDENT_PROFILE.ID))
                .leftJoin(USER).on(USER.ID.eq(STUDENT_PROFILE.USER_ID))
                .where(STUDENT_PAYMENT_TRANSACTION.ID.eq(transactionId))
                .fetchOptionalInto(TransactionResponse.class)
                .orElseThrow(()-> Exceptions.notFound(" Transaction Record Not Found "));

        I18n firstNameI18n = dslContext.select().from(I18N).where(I18N.ID.eq(response.getFistNameI18nId())).fetchOneInto(I18n.class);
        I18n lastNameI18n = dslContext.select().from(I18N).where(I18N.ID.eq(response.getLastNameI18nId())).fetchOneInto(I18n.class);

        response.setFirstNameI18n(firstNameI18n);
        response.setLastNameI18n(lastNameI18n);
        return response;
    }

    public void list(UUID profileId) {
        //查询 educator session comment list item
//        var commentQ = dbStudentPaymentTransaction.getDsl()
//                        .select()
//                        .from()







//        dbStudentPaymentTransaction.getDsl()
//                .select(
//                        STUDENT_PAYMENT_TRANSACTION.asterisk(),
//                        //查询educator session comment
//                )
//                .from(STUDENT_PAYMENT_TRANSACTION)
//                .where(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(profileId).and(STUDENT_PAYMENT_TRANSACTION.PROCESS_STATUS.eq(BookingStatusEnum.FINISHED)))




    }

    public void payment(UUID profileId, UUID transactionId) {
        //查询当前学生是否有这个订单
        DbStudentPaymentTransaction.Result transaction = dbStudentPaymentTransaction.getDsl().select()
                .from(STUDENT_PAYMENT_TRANSACTION)
                .where(STUDENT_PAYMENT_TRANSACTION.ID.eq(transactionId).and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(profileId)))
                .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                .orElseThrow(()->Exceptions.business(UniErrorCode.Business.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));
        //组装 AsiaPay Frontend需要的参数,以及创建


    }
}
