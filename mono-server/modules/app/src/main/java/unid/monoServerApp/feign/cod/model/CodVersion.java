package unid.monoServerApp.feign.cod.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodVersion implements NamedEnum {
    IPAY("ipay"),
    ABOSS("aboss");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
