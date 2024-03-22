package unid.monoServerApp.api.transaction;


import cn.hutool.log.StaticLog;
//import com.asiapay.secure.PaydollarSecureUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.SessionOpTypeEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.eventLog.DbSessionOpLog;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.model.SessionLogger;
import unid.monoServerApp.queue.model.EducatorMeetingRequestPayload;
import unid.monoServerApp.service.SessionLoggerService;
import unid.monoServerApp.service.TeamsMeetingService;
import unid.monoServerApp.util.asiapay.PaydollarSecureUtil;
import unid.monoServerMeta.api.PaymentTransactionRequest;
import unid.monoServerMeta.api.PaymentTransactionResponse;
import unid.monoServerMeta.api.TransactionResponse;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import pwh.springWebStarter.response.UniErrorCode;
import unid.monoServerMeta.model.SessionOpType;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import static unid.jooqMono.model.Tables.*;
import static pwh.springWebStarter.response.UniErrorCode.STUDENT_PAYMENT_TRANSACTION_FAIL_TO_PAY_ON_ALIPAY;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TransactionService {
    private final DSLContext dslContext;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorSessionNoteItem dbEducatorSessionNoteItem;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final TeamsMeetingService teamsMeetingService;
    private final DbSessionOpLog dbSessionOpLog;
    private final SessionLoggerService sessionLoggerService;

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

    public PaymentTransactionResponse payment(UUID profileId, PaymentTransactionRequest request) {
        PaymentTransactionResponse response = new PaymentTransactionResponse();
        //查询当前学生是否有这个订单
        //TODO:还需要判断当前订单是否有效
        DbStudentPaymentTransaction.Result transaction = dbStudentPaymentTransaction.getDsl().select()
                .from(STUDENT_PAYMENT_TRANSACTION)
                .where(STUDENT_PAYMENT_TRANSACTION.ID.eq(request.getTransactionId()).and(STUDENT_PAYMENT_TRANSACTION.STUDENT_PROFILE_ID.eq(profileId)))
                .fetchOptionalInto(DbStudentPaymentTransaction.Result.class)
                .orElseThrow(()->Exceptions.business(UniErrorCode.STUDENT_PAYMENT_TRANSACTION_NOT_EXIST));
        //组装 AsiaPay Frontend需要的参数,以及创建
        PaymentTransactionResponse.AsiaPayPayload payload = new PaymentTransactionResponse.AsiaPayPayload();
        payload.setAmount(transaction.getTransactionAmount().toString());
        payload.setOrderRef(transaction.getTransactionSerialNumber());
        payload.setPaymentType("N");
        payload.setCurrCode(request.getAsiaPayPayload().getCurrCode());
        payload.setMerchantId("88163035");
        payload.setPayMethod(request.getAsiaPayPayload().getPayMethod());
        try{
            payload.setSecureHash(PaydollarSecureUtil.generatePaymentSecureHash(payload.getMerchantId(),payload.getOrderRef(),payload.getCurrCode(),payload.getAmount(),"N"));
        }catch (Exception e){
            StaticLog.error(" 创建AsiaPay SecureHash 异常",e);
            throw Exceptions.business(STUDENT_PAYMENT_TRANSACTION_FAIL_TO_PAY_ON_ALIPAY);
        }
        response.setAsiaPayPayload(payload);
        return response;


    }

    public void acceptAsiaPayNotify(HttpServletRequest request) {
        StaticLog.info("*********************************************************************************************************************");
        StaticLog.info("Requeset PathInfo=>" + request.getPathInfo());
        StaticLog.info("Requeset PathTranslated=>"
                + request.getPathTranslated());
        StaticLog.info("Requeset RequestURI=>"
                + request.getRequestURI());
        StaticLog.info("Requeset QueryString=>"
                + request.getQueryString());
        Enumeration enumeration = request.getParameterNames();
        String[] paramValues = null;
        String paramName = null;
        StringBuffer buffer = new StringBuffer();
        int paramValuesSize = 0;

        while (enumeration != null && enumeration.hasMoreElements()) {
            buffer.delete(0, buffer.length());
            paramName = (String) enumeration.nextElement();
            paramValues = request.getParameterValues(paramName);
            paramValuesSize = 0;
            if (paramValues != null && paramValues.length > 0)
                paramValuesSize = paramValues.length;

            for (int i = 0; i < paramValuesSize; i++) {
                buffer.append(paramValues[i] + ",");
            }

            buffer.deleteCharAt(buffer.lastIndexOf(","));

            StaticLog.info("Requeset Parameter NAME=>" + paramName
                    + " VALUES =>" + buffer.toString());

        }

        StaticLog.info("Request Charater Encoding is ==>"
                + request.getCharacterEncoding());

        String src = request.getParameter("src"); 													//Return bank host status code (secondary).
        String prc = request.getParameter("prc");													//Return bank host status code (primary).
        String successcode = request.getParameter("successcode");					//0- succeeded, 1- failure, Others - error
        String ref = request.getParameter("Ref");													//Merchant‘s Order Reference Number
        String payRef = request.getParameter("PayRef");										//PayDollar Payment Reference Number
        String amt = request.getParameter("Amt");												//Transaction Amount
        String cur = request.getParameter("Cur");													//Transaction Currency
        String payerAuth = request.getParameter("payerAuth");							//Payer Authentication Status

        String ord = request.getParameter("Ord");													//Bank Reference – Order id
        String holder = request.getParameter("Holder");										//The Holder Name of the Payment Account
        String remark = request.getParameter("remark");										//A remark field for you to store additional data that will not show on the transaction web page
        String authId = request.getParameter("AuthId");										//Approval Code
        String eci = request.getParameter("eci");														//ECI value (for 3D enabled Merchants)
        String sourceIp = request.getParameter("sourceIp");									//IP address of payer
        String ipCountry = request.getParameter("ipCountry");							//Country of payer ( e.g. HK) - if country is on high risk country list, an asterisk will be shown (e.g. MY*)

        String mpsAmt = request.getParameter("mpsAmt");								//MPS Transaction Amount
        String mpsCur = request.getParameter("mpsCur");									//MPS Transaction Currency
        String mpsForeignAmt = request.getParameter("mpsForeignAmt");		//MPS Transaction Foreign Amount
        String mpsForeignCur = request.getParameter("mpsForeignCur");			//MPS Transaction Foreign Currency
        String mpsRate = request.getParameter("mpsRate");								//MPS Exchange Rate: (Foreign / Base) e.g. USD / HKD = 7.77
        String cardlssuingCountry = request
                .getParameter("cardlssuingCountry");													//Card Issuing Country Code ( e.g. HK)
        String payMethod = request.getParameter("payMethod");						//Payment method (e.g. VISA, Master, Diners, JCB, AMEX)


        StaticLog.info("OK");

        boolean isSecureHashSetting=true;

        //if Secure Hash is used
        if (isSecureHashSetting) {
            String[] secureHash = request.getParameterValues("secureHash");
            List tempList = new ArrayList();
            if (secureHash != null) {
                for (int i = 0; i < secureHash.length; i++) {
                    System.out.println(secureHash[i]);

                    if (secureHash[i].indexOf(",") > 0) {
                        String[] data = secureHash[i].split(",");
                        for (int j = 0; data != null & j < data.length; j++) {
                            tempList.add(data[j]);
                        }

                    } else {
                        tempList.add(secureHash[i]);
                    }
                }
            }

            int size = tempList.size();
            if (size > 0) {
                secureHash = new String[size];
                for (int i = 0; i < size; i++) {
                    secureHash[i] = (String) tempList.get(i);
                }

            }

            boolean verifyResult = PaydollarSecureUtil
                    .verifyPaymentDatafeed(src, prc, successcode, ref,
                            payRef, cur, amt, payerAuth, secureHash);

            StaticLog.info("verifyResult =" + verifyResult);
            if (!verifyResult) {
                StaticLog.info("verifyResult fail");
            }
        }

        if (successcode.equals("0")) {
            // Transaction Accepted
            // *** Add the Security Control here, to check the currency, amount with the
            // *** merchant’s order reference from your database, if the order exist then
            // *** accepted otherwise rejected the transaction.

            //  Update your database for Transaction Accepted and send email or notify your
            //   customer.
            StaticLog.info("AsiaPay success !!! ref = {}", ref);

            //根据回调的 orderRef,更新支付状态
            //查询当前支付的订单
            List<StudentPaymentTransactionPojo> transactionPojoList = dbStudentPaymentTransaction.getDao().fetchByTransactionSerialNumber(ref);
            if(transactionPojoList.isEmpty()){
                StaticLog.info("AsiaPay success !!! 订单不存在 = {}", ref);
            }
            //更新当前订单的支付状态
            for(StudentPaymentTransactionPojo transactionPojo : transactionPojoList){
                transactionPojo.setPaymentStatus(PaymentStatusEnum.PAID);
                dbStudentPaymentTransaction.getDao().update(transactionPojo);
                if(transactionPojo.getTransactionItem().equals(StudentTransactionItemEnum.EDUCATOR_SCHEDULE)){
                    //设置educator calendar状态为accept
                    EducatorCalendarPojo calendarPojo =  dbEducatorCalendar.getDao().fetchOneById(transactionPojo.getTransactionItemRefId());
                    if(calendarPojo == null){
                        continue;
                    }
                    BookingStatusEnum fromStatus = calendarPojo.getBookingStatus();
                    calendarPojo.setBookingStatus(BookingStatusEnum.ACCEPTED);
                    dbEducatorCalendar.getDao().update(calendarPojo);
                    //创建meeting url
                    EducatorMeetingRequestPayload payload = new EducatorMeetingRequestPayload();
                    payload.setCalendarId(calendarPojo.getId());
                    payload.setEducatorProfileId(calendarPojo.getEducatorProfileId());

                    teamsMeetingService.createMeetingWithStudent(payload);

                    sessionLoggerService.async(SessionLogger.OpEvent.builder()
                            .userId(transactionPojo.getStudentProfileId())
                            .status(BookingStatus.PAID)
                            .transactionId(transactionPojo.getTransactionItemRefId())
                            .timeUtc(OffsetDateTime.now())
                            .opType(SessionOpType.PAY).build());
                }
            }



            // In case if your database or your system got problem, you can send a void transaction request. See API guide for more details
        } else {
            // Transaction Rejected
            // Update your database for Transaction Rejected
        }
        StaticLog.info("*********************************************************************************************************************");
    }

}
