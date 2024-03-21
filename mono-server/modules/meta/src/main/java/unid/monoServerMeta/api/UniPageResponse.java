package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
public class UniPageResponse<T> {
    @Schema(
            description = "previous page cursor string | null = no previous page (current is first page) | pass this value to API to query previous page"
    )
    @Nullable
    private Integer totalSize;
    @Schema(
            description = "next page cursor string | null = no next page (current is last page) | pass this value to API to query next page"
    )
    @Nullable
    private Integer currentPage;
    @Schema(
            description = "current page items count"
    )
    private @NotNull Integer itemsPerPage;
    @Schema(
            description = "total items count of all pages"
    )
    private @NotNull Integer totalPages;
    @Schema(
            description = "them items"
    )
    private @Valid List<T> result;


}
