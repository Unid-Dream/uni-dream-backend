package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum PaymentStatus implements NamedEnum {
    PENDING("PENDING"),
    PAID("PAID"),
    EXPIRED("EXPIRED");
    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
