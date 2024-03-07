package unid.monoServerApp.feign.cod.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodPaymentStatus implements NamedEnum {
    NEW("new"),
    WAIT("wait"),
    PAID("paid"),
    EXPIRED("expired"),
    LIQUIDATED("liquidated");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
