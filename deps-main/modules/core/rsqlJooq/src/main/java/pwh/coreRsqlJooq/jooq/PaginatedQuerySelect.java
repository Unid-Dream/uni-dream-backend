package pwh.coreRsqlJooq.jooq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.SelectJoinStep;
import pwh.coreRsqlJooq.rsql.ConditionsVisitor;

@RequiredArgsConstructor
public class PaginatedQuerySelect {
    @Getter
    private final PaginatedQuery previousStep;
    @Getter
    private final SelectJoinStep<?> select;

    public PaginatedQueryConditions conditions(ConditionsVisitor rsqlVisitor) {
        rsqlVisitor.init();
        return new PaginatedQueryConditions(
                this,
                previousStep.getCursor() != null ? previousStep.getCursor().getQuery() : previousStep.getRequest().getQuery(),
                rsqlVisitor
        );
    }
}
