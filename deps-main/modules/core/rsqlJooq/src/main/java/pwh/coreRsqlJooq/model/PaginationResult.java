package pwh.coreRsqlJooq.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jooq.Result;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@ToString
public class PaginationResult {
    @Getter
    private final Result<?> result;
    @Getter
    @NotNull
    private final Long totalCount;
    @Getter
    private final String cursorNext;
    @Getter
    private final String cursorPrevious;
}
