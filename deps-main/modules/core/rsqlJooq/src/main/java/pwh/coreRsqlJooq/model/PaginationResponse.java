package pwh.coreRsqlJooq.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@ToString
public class PaginationResponse<T> {

    @Schema(
            description = "previous page cursor string" +
                    " | null = no previous page (current is first page)" +
                    " | pass this value to API to query previous page"
    )
    @Nullable
    private String previousPage;

    @Schema(
            description = "next page cursor string" +
                    " | null = no next page (current is last page)" +
                    " | pass this value to API to query next page"
    )
    @Nullable
    private String nextPage;

    @Schema(
            description = "current page items count"
    )
    @NotNull
    private Long pageSize;

    @Schema(
            description = "total items count of all pages"
    )
    @NotNull
    private Long totalSize;

    @Schema(
            description = "them items"
    )
    @Valid
    private List<T> result;

    public static <T> PaginationResponse<T> fromResult(PaginationResult result, Class<T> responseType) {
        return new PaginationResponse<>(
                result.getCursorPrevious(),
                result.getCursorNext(),
                (long) result.getResult().size(),
                result.getTotalCount(),
                result.getResult().into(responseType)
        );
    }

    public static <T> PaginationResponse<T> asResult(PaginationResult result, List<T> responseList) {
        return new PaginationResponse<>(
                result.getCursorPrevious(),
                result.getCursorNext(),
                (long) result.getResult().size(),
                result.getTotalCount(),
                responseList
        );
    }
}
