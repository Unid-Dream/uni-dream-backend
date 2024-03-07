package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum BookingStatus implements NamedEnum {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED"),
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    CANCELLED("CANCELLED"),
    VOID("VOID"),
    FINISHED("FINISHED"),
    UNFINISHED("UNFINISHED");
    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
