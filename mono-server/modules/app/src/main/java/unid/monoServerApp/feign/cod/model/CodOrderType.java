package unid.monoServerApp.feign.cod.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodOrderType implements NamedEnum {
    PAYMENT("payment");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
