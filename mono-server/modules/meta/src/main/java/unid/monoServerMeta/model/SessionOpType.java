package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum SessionOpType implements NamedEnum {
    AVAILABLE("AVAILABLE"),

    REQUEST("REQUEST"),

    ACCEPT("ACCEPT"),

    PAY("PAY"),

    ATTEND("ATTEND"),

    REJECT("REJECT"),

    CANCEL("CANCEL"),
    RESCHEDULE_ACCEPT("RESCHEDULE_ACCEPT"),
    RESCHEDULE_REJECT("RESCHEDULE_REJECT"),


    ;

    private final String value;

    @Override
    public String toNamedString() {
        return this.value;
    }
}
