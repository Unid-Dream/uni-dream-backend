package pwh.coreRsqlJooq.jooq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import pwh.coreRsqlJooq.rsql.ConditionsVisitor;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.coreRsqlJooq.rsql.PaginationParser;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
public class PaginatedQueryConditions {
    @Getter
    private final PaginatedQuerySelect previousStep;
    @Getter
    private final String conditionString;
    private final ConditionsVisitor rsqlVisitor;
    @Getter
    private Condition conditions = DSL.noCondition();

    private void resolveConditions() {
        if (StringUtils.isNotBlank(conditionString)) {
            var filter = PaginationParser.parseQuery(conditionString);
            conditions = conditions.and(filter.accept(rsqlVisitor));
        }
    }

    public PaginatedQueryConditions extraConditions(Condition conditions) {
        this.conditions = this.conditions.and(conditions);
        return this;
    }

    public PaginatedQuerySorting sortBy(
            OrderingVisitor orderingVisitor,
            Consumer<LinkedHashSet<PaginatedQuerySorting.ExtraOrUniqueSort>> extraSortBeforeUserDef,
            Consumer<LinkedHashSet<PaginatedQuerySorting.ExtraOrUniqueSort>> defaultWhenUserDefEmpty,
            Consumer<LinkedHashSet<PaginatedQuerySorting.ExtraOrUniqueSort>> extraSortAfterUserDef,
            Consumer<LinkedHashSet<PaginatedQuerySorting.ExtraOrUniqueSort>> uniqueSort
    ) {
        log.debug("Pagination - Condition String: {}", conditionString);
        resolveConditions();
        orderingVisitor.init();
        var sort = new PaginatedQuerySorting(
                this,
                getCursor().isPresent() ? getCursor().get().getSort() : previousStep.getPreviousStep().getRequest().getSort(),
                orderingVisitor);
        sort.extraSort(extraSortBeforeUserDef);
        sort.defaultWhenEmptySortString(defaultWhenUserDefEmpty);
        sort.extraSort(extraSortAfterUserDef);
        sort.uniqueSort(uniqueSort);
        return sort;
    }

    private Optional<PaginationCursor> getCursor() {
        return Optional.ofNullable(previousStep.getPreviousStep().getCursor());
    }
}
