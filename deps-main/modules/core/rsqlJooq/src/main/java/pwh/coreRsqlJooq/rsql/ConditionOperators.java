package pwh.coreRsqlJooq.rsql;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum ConditionOperators implements NamedEnum {
    EQUALS("=eq="),
    NOT_EQUAL("=ne="),
    LESS_THAN("=lt="),
    LESS_THAN_EQUALS("=le="),
    GREATER_THAN("=gt="),
    GREATER_THAN_EQUALS("=ge="),
    IN("=in="),
    NOT_IN("=out="),
    BETWEEN("=bt="),
    LIKE("=lk=");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
