package unid.monoServerApp.feign.cod.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodNotificationType implements NamedEnum {
    PAYMENT("payment"),
    REFUND("refund");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
