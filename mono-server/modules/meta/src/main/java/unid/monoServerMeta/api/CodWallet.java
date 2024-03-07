package unid.monoServerMeta.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodWallet implements NamedEnum {
    ALIPAYHK("ALIPAYHK"),
    ALIPAYCN("ALIPAYCN");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
