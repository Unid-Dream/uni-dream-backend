package pwh.coreRsqlJooq.rsql;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.SortOrder;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum OrderingOperators implements NamedEnum {
    ASC("=asc="),
    DESC("=desc=");

    private final String value;

    public static SortOrder getJooqSortOrder(OrderingOperators orderingOperators) {
        return OrderingOperators.ASC.equals(orderingOperators) ? SortOrder.ASC : SortOrder.DESC;
    }

    @Override
    public String toNamedString() {
        return value;
    }
}
