package pwh.coreRsqlJooq.jooq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jooq.DSLContext;
import org.jooq.SelectJoinStep;
import pwh.coreRsqlJooq.Constant;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreStarter.exception.UnifiedException;

import java.util.function.Function;

@RequiredArgsConstructor
public class PaginatedQuery {
    @Getter
    private final ObjectMapper objectMapper;
    @Getter
    private final DSLContext dsl;
    @Getter
    private PaginationRequest request;
    @Getter
    private PaginationCursor cursor;
    @Getter
    private String optionalOwnerIdentifier;

    public static PaginatedQuery init(
            DSLContext dsl,
            ObjectMapper objectMapper,
            PaginationRequest request,
            String optionalOwnerIdentifier,
            long minLimit,
            long maxLimit
    ) {
        var query = new PaginatedQuery(objectMapper, dsl);
        // sanitize
        request.setLimit(NumberUtils.min(NumberUtils.max(ObjectUtils.defaultIfNull(request.getLimit(), minLimit), minLimit), maxLimit));
        query.request = request;
        query.optionalOwnerIdentifier = optionalOwnerIdentifier;
        return query;
    }

    public PaginatedQuerySelect select(Function<DSLContext, SelectJoinStep<?>> selectFunc) {
        var select = selectFunc.apply(this.dsl);
        if (StringUtils.isNotBlank(request.getPage())) {
            this.cursor = PaginationCursor.fromEncodedString(request.getPage(), select.getSQL(), objectMapper);
            request.setLimit(this.cursor.getLimit());
            if (StringUtils.isNotBlank(this.cursor.getOptionalOwnerIdentifier())) {
                if (!this.optionalOwnerIdentifier.equals(this.cursor.getOptionalOwnerIdentifier())) {
                    throw new UnifiedException(
                            Constant.ERROR_INVALID_CURSOR_OWNER,
                            "Unexpected Owner",
                            403
                    );
                }
            }
        }
        return new PaginatedQuerySelect(this, select);
    }
}
