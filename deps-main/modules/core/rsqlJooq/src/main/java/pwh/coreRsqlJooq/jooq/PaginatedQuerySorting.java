package pwh.coreRsqlJooq.jooq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Field;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import pwh.coreRsqlJooq.Constant;
import pwh.coreRsqlJooq.model.PaginationResult;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.coreRsqlJooq.rsql.PaginationParser;
import pwh.coreStarter.exception.UnifiedException;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
public class PaginatedQuerySorting {
    @Getter
    private final PaginatedQueryConditions previousStep;
    @Getter
    private final String sortString;
    private final OrderingVisitor rsqlVisitor;
    @Getter
    private final LinkedHashSet<OrderingVisitor.FieldRef> sortFieldsRef = new LinkedHashSet<>();

    private void resolveSortString() {
        if (StringUtils.isNotBlank(sortString)) {
            var filter = PaginationParser.parseOrder(sortString);
            sortFieldsRef.addAll(filter.accept(rsqlVisitor));
        }
    }

    LinkedHashSet<SortField<?>> getSortFields() { // used by external
        var sortFields = new LinkedHashSet<SortField<?>>();
        sortFieldsRef.forEach(ref -> {
            sortFields.add(getSortField(ref));
        });
        return sortFields;
    }

    private SortField<?> getSortField(OrderingVisitor.FieldRef ref) {
        var reverse = getCursor().isPresent() && PaginationCursor.Direction.PREVIOUS.equals(getCursor().get().getDirection());
        var sortValues = ref.getSeekingRef();
        SortField<?> sortField;
        var field = DSL.field(ref.getField().getQualifiedName());
        if (ref.isSortByValues()) {
            // if enum, add all of its values for correct ordering
            if (sortValues.get(0).getClass().isEnum()) {
                for (var eVal : sortValues.get(0).getClass().getEnumConstants()) {
                    if (!sortValues.contains(eVal)) {
                        sortValues.add(eVal);
                    }
                }
            }
            // order
            if (SortOrder.ASC.equals(ref.getSortOrder())) {
                sortField = !reverse ? field.sortAsc(sortValues) : field.sortDesc(sortValues);
            } else {
                sortField = !reverse ? field.sortDesc(sortValues) : field.sortAsc(sortValues);
            }
        } else {
            sortField = field.sort(!reverse ? ref.getSortOrder() : SortOrder.DESC.equals(ref.getSortOrder()) ? SortOrder.ASC : SortOrder.DESC);
        }
        return sortField;
    }

    private void resolveUniqueSortFields(
            Consumer<LinkedHashSet<ExtraOrUniqueSort>> uniqueSortFieldFunc
    ) {
        var uniqueSortFields = new LinkedHashSet<ExtraOrUniqueSort>();
        uniqueSortFieldFunc.accept(uniqueSortFields);
        if (uniqueSortFields.isEmpty()) {
            throw new UnifiedException(
                    Constant.ERROR_MISSING_UNIQUE_SORT_FIELD,
                    null,
                    400
            );
        }
        for (var eu : uniqueSortFields) {
            // suppose seeking = no input and output direct values
            var ref = toFieldRef(eu);
            sortFieldsRef.add(ref);
        }
    }

    void defaultWhenEmptySortString(Consumer<LinkedHashSet<ExtraOrUniqueSort>> defaultSortFieldFunc) {
        if (StringUtils.isBlank(sortString) && defaultSortFieldFunc != null) {
            resolveUniqueSortFields(defaultSortFieldFunc);
            log.debug("Pagination - Applied Default Sort: {}", sortFieldsRef);
        } else {
            resolveSortString();
            log.debug("Pagination - Applied User Defined Sort: {}", sortFieldsRef);
        }
    }

    void extraSort(Consumer<LinkedHashSet<ExtraOrUniqueSort>> uniqueSortFieldFunc) {
        if (uniqueSortFieldFunc != null) {
            resolveUniqueSortFields(uniqueSortFieldFunc);
            log.debug("Pagination - Applied Extra Sort: {}", sortFieldsRef);
        }
    }

    void uniqueSort(Consumer<LinkedHashSet<ExtraOrUniqueSort>> uniqueSortFunc) {
        if (uniqueSortFunc == null) {
            throw new UnifiedException(
                    Constant.ERROR_MISSING_UNIQUE_SORT_FIELD,
                    null,
                    400
            );
        }
        resolveUniqueSortFields(uniqueSortFunc);
        log.debug("Pagination - Applied Unique Sort: {}", sortFieldsRef);
    }

    private OrderingVisitor.FieldRef toFieldRef(ExtraOrUniqueSort extraOrUniqueSort) {
        return new OrderingVisitor.FieldRef(
                extraOrUniqueSort.getField(), extraOrUniqueSort.getSortOrder(),
                extraOrUniqueSort.getSeeking() != null ? extraOrUniqueSort.getSeeking().sortByValues(null) : null,
                extraOrUniqueSort.getSeeking() != null ? extraOrUniqueSort.getSeeking() : OrderingVisitor.Seeking.builder().build()
        );
    }

    public PaginationResult fetch() {
        return new PaginatedFetch(this).fetch();
    }

    private Optional<PaginationCursor> getCursor() {
        return Optional.ofNullable(previousStep.getPreviousStep().getPreviousStep().getCursor());
    }

    @RequiredArgsConstructor
    public static class ExtraOrUniqueSort {
        @Getter
        @NotNull
        private final Field<?> field;
        @Getter
        @NotNull
        private final SortOrder sortOrder;
        @Getter
        @NotNull
        private final OrderingVisitor.Seeking seeking;
    }
}