package unid.monoServerApp.feign.cod.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodCurrency implements NamedEnum {
    HKD("HKD");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
