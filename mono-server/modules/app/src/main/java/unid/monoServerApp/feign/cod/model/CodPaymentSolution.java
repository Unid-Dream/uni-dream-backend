package unid.monoServerApp.feign.cod.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodPaymentSolution implements NamedEnum {
    MOBILE_WEB_OR_ALIPAYHK_INAPP("WAP"),
    APP_SDK("APP"),
    DESKTOP_WEB_QRCODE("PC2MOBILE"),
    IN_STORE_QRCODE("TRANSACTION_QR_CODE");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
