package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.PaymentMethodEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.monoServerMeta.api.CodWallet;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.PaymentMethod;
import unid.monoServerMeta.model.PaymentStatus;
import unid.monoServerMeta.model.TransactionItem;

@Mapper(
        componentModel = "spring"
)
public interface EnumMapper {
    Currency toCurrencyResponse(CurrencyEnum data);

    @ValueMappings({
            @ValueMapping(source = "ALIPAYCN", target = "COD_ALIPAY_CN"),
            @ValueMapping(source = "ALIPAYHK", target = "COD_ALIPAY_HK")
    })
    PaymentMethodEnum toPaymentMethod(CodWallet data);

    PaymentMethod toPaymentMethodResponse(PaymentMethodEnum data);

    PaymentStatus toPaymentStatusResponse(PaymentStatusEnum data);

    TransactionItem toTransactionItemResponse(StudentTransactionItemEnum data);
}
