package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum PaymentMethod implements NamedEnum {
    COD_ALIPAY_HK("COD_ALIPAY_HK"),
    COD_ALIPAY_CN("COD_ALIPAY_CN"),
    AISA_PAY_ALIPAY("AISA_PAY_ALIPAY"),
    ;
    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
