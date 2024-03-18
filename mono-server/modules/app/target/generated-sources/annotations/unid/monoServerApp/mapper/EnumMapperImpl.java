package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.PaymentMethodEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.monoServerMeta.api.CodWallet;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.PaymentMethod;
import unid.monoServerMeta.model.PaymentStatus;
import unid.monoServerMeta.model.TransactionItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T19:46:59+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EnumMapperImpl implements EnumMapper {

    @Override
    public Currency toCurrencyResponse(CurrencyEnum data) {
        if ( data == null ) {
            return null;
        }

        Currency currency;

        switch ( data ) {
            case USD: currency = Currency.USD;
            break;
            case HKD: currency = Currency.HKD;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return currency;
    }

    @Override
    public PaymentMethodEnum toPaymentMethod(CodWallet data) {
        if ( data == null ) {
            return null;
        }

        PaymentMethodEnum paymentMethodEnum;

        switch ( data ) {
            case ALIPAYCN: paymentMethodEnum = PaymentMethodEnum.COD_ALIPAY_CN;
            break;
            case ALIPAYHK: paymentMethodEnum = PaymentMethodEnum.COD_ALIPAY_HK;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return paymentMethodEnum;
    }

    @Override
    public PaymentMethod toPaymentMethodResponse(PaymentMethodEnum data) {
        if ( data == null ) {
            return null;
        }

        PaymentMethod paymentMethod;

        switch ( data ) {
            case COD_ALIPAY_HK: paymentMethod = PaymentMethod.COD_ALIPAY_HK;
            break;
            case COD_ALIPAY_CN: paymentMethod = PaymentMethod.COD_ALIPAY_CN;
            break;
            case AISA_PAY_ALIPAY: paymentMethod = PaymentMethod.AISA_PAY_ALIPAY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return paymentMethod;
    }

    @Override
    public PaymentStatus toPaymentStatusResponse(PaymentStatusEnum data) {
        if ( data == null ) {
            return null;
        }

        PaymentStatus paymentStatus;

        switch ( data ) {
            case PENDING: paymentStatus = PaymentStatus.PENDING;
            break;
            case PAID: paymentStatus = PaymentStatus.PAID;
            break;
            case EXPIRED: paymentStatus = PaymentStatus.EXPIRED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return paymentStatus;
    }

    @Override
    public TransactionItem toTransactionItemResponse(StudentTransactionItemEnum data) {
        if ( data == null ) {
            return null;
        }

        TransactionItem transactionItem;

        switch ( data ) {
            case EDUCATOR_SCHEDULE: transactionItem = TransactionItem.EDUCATOR_SCHEDULE;
            break;
            case WRITING: transactionItem = TransactionItem.WRITING;
            break;
            case INTERVIEW: transactionItem = TransactionItem.INTERVIEW;
            break;
            case WEBINAR: transactionItem = TransactionItem.WEBINAR;
            break;
            case COURSE: transactionItem = TransactionItem.COURSE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return transactionItem;
    }
}
