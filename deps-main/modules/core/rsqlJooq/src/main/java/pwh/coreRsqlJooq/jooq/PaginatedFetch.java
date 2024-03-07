package pwh.coreRsqlJooq.jooq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SortOrder;
import pwh.coreRsqlJooq.model.PaginationResult;

import java.util.*;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public class PaginatedFetch {
    @Getter
    private final PaginatedQuerySorting previousStep; // previous step

    @SneakyThrows
    public PaginationResult fetch() {
        var config = previousStep.getPreviousStep().getPreviousStep().getPreviousStep();
        var select = previousStep.getPreviousStep().getPreviousStep();
        var where = previousStep.getPreviousStep();
        var sort = previousStep;
        var keySet = previousStep.getPreviousStep().getPreviousStep().getSelect().getSQL();
        log.debug("Pagination - Resolved Conditions: {}", where.getConditions());
        log.debug("Pagination - Resolved Sorting: {}", sort.getSortFields());
        var query = select.getSelect()
                .where(where.getConditions())
                .orderBy(sort.getSortFields());
        var totalCount = getCursor().isEmpty()
                ? (long) config.getDsl().fetchCount(query)
                : getCursor().get().getTotalCount();
        Result<?> result;
        var fetchLimit = config.getRequest().getLimit() + 1;

        Supplier<Object[]> getSeeks = () -> {
            var seeks = getCursor().get().getSeeks().toArray(new String[]{});
            var seekResult = new Object[seeks.length];
            var sets = sort.getSortFieldsRef();
            var i = 0;
            for (var set : sets) {
                if (set.isSortByValues()) {
                    var seek = set.getSeeking().seeking(seeks[i]);
                    log.debug("Pagination = Sorting By Values: {} | {} | {} | {}",
                            getCursor().get().getDirection(), seek, set.getSortOrder(), set.getSeekingRef());
                    var index = set.getSeekingRef().indexOf(seek);
                    var isPrevious = PaginationCursor.Direction.PREVIOUS.equals(getCursor().get().getDirection());
                    if (
                            (!isPrevious && set.getSortOrder().equals(SortOrder.DESC))
                                    || isPrevious && set.getSortOrder().equals(SortOrder.ASC)
                    ) {
                        index *= -1;
                    }
                    seekResult[i] = String.valueOf(index);
                } else {
                    seekResult[i] = set.getSeeking().seeking(seeks[i]);
                }
                i++;
            }
            log.debug("Pagination - Resolved Seeking: {}", Arrays.asList(seekResult));
            return seekResult;
        };
        log.debug("Pagination - Input Cursor: {}", getCursor());
        var seekStep = query
                .seek(getCursor().isPresent() ? getSeeks.get() : null);
        result = seekStep.limit(fetchLimit)
                .fetch();

        boolean hasNextPage, hasPreviousPage = false;
        hasNextPage = result.size() > config.getRequest().getLimit();

        if (result.size() > config.getRequest().getLimit()) {
            result.remove(result.size() - 1);
        }

        if (getCursor().isPresent()) {
            hasNextPage = hasNextPage || PaginationCursor.Direction.PREVIOUS.equals(getCursor().get().getDirection());
            hasPreviousPage = PaginationCursor.Direction.NEXT.equals(getCursor().get().getDirection())
                    || CollectionUtils.isNotEmpty(getCursor().get().getSeeks());
            if (PaginationCursor.Direction.PREVIOUS.equals(getCursor().get().getDirection())) {
                Collections.reverse(result);
            }
        }

        log.debug("Pagination - Next? {} Previous? {}", hasNextPage, hasPreviousPage);

        PaginationCursor cursorNext = null;
        PaginationCursor cursorPrevious = null;

        var seekNext = new ArrayList<String>();
        var topOfSeekNext = new ArrayList<String>();

        if (hasNextPage) {
            cursorNext = generateCursor(
                    totalCount,
                    result.get(result.size() - 1),
                    sort,
                    seekNext,
                    keySet,
                    config,
                    where,
                    PaginationCursor.Direction.NEXT
            );
            if (getCursor().isPresent() && PaginationCursor.Direction.PREVIOUS.equals(getCursor().get().getDirection())) {
                hasPreviousPage = !new HashSet<>(cursorNext.getSeeks()).containsAll(getCursor().get().getFirstSeeks())
                        && !new HashSet<>(getCursor().get().getFirstSeeks()).containsAll(cursorNext.getSeeks());
            }
        }
        log.debug("Pagination - Seek Next: {}", seekNext);
        if (hasPreviousPage) {
            cursorPrevious = generateCursor(
                    totalCount,
                    result.get(0),
                    sort,
                    topOfSeekNext,
                    keySet,
                    config,
                    where,
                    PaginationCursor.Direction.PREVIOUS
            );
        }

        log.debug("Pagination - Top of Seek Next: {}", topOfSeekNext);

        return new PaginationResult(
                result,
                totalCount,
                cursorNext != null ? cursorNext.getEncodedString(keySet, config.getObjectMapper()) : null,
                cursorPrevious != null ? cursorPrevious.getEncodedString(keySet, config.getObjectMapper()) : null
        );
    }

    private PaginationCursor generateCursor(
            Long totalCount,
            Record result,
            PaginatedQuerySorting sort,
            List<String> seeking,
            String keySet,
            PaginatedQuery config,
            PaginatedQueryConditions where,
            PaginationCursor.Direction direction
    ) {
        for (var sortRef : sort.getSortFieldsRef()) {
            var seek = result.get(sortRef.getField().getQualifiedName());
            seeking.add(seek.toString());
        }
        var cursor = new PaginationCursor(
                seeking,
                getCursor().isEmpty() ? seeking : getCursor().get().getFirstSeeks(),
                config.getRequest().getLimit(),
                where.getConditionString(),
                sort.getSortString(),
                direction,
                config.getOptionalOwnerIdentifier(),
                totalCount);
        log.debug("Pagination - Beacon ({}) {}",
                direction,
                PaginationCursor.fromEncodedString(
                        cursor.getEncodedString(keySet, config.getObjectMapper()),
                        keySet,
                        config.getObjectMapper()
                )
        );
        return cursor;
    }

    private Optional<PaginationCursor> getCursor() {
        return Optional.ofNullable(previousStep.getPreviousStep().getPreviousStep().getPreviousStep().getCursor());
    }
}
