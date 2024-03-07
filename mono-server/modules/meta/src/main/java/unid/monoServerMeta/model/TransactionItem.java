package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum TransactionItem implements NamedEnum {
    EDUCATOR_SCHEDULE("EDUCATOR_SCHEDULE"),
    WRITING("WRITING"),
    INTERVIEW("INTERVIEW"),

    WEBINAR("WEBINAR"),

    COURSE("COURSE")
    ;
    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
