package unid.monoServerApp.api.transaction;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerMeta.api.TransactionResponse;
import unid.monoServerMeta.model.I18n;

import java.util.UUID;

import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.Tables.STUDENT_PAYMENT_TRANSACTION;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TransactionService {
    private final DSLContext dslContext;

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
}
