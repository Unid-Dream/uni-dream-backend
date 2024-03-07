package pwh.coreRsqlJooq.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nullable;

@Data
@NoArgsConstructor
@ToString
public class PaginationRequest {

    @Schema(
            description = "next or previous page cursor" +
                    " | not applicable for page 1 query" +
                    " | when passing this attribute others will not be effective" +
                    " | cursor will contains your page 1 configs (query, sort & limit)",
            example = "somePageCursorString"
    )
    @Nullable
    private String page; // optional

    @Schema(
            description = "query string, in RSQL format" +
                    " | not applicable for page 2 and so on",
            pattern = "=eq= | =ne= | =lt= | =le= | =gt= | =ge= | =in= | =out= | =bt=",
            example = "(year=bt=('2014','2018') or capacity=ge=831) and (age=le=8)"
    )
    @Nullable
    private String query;// optional

    @Schema(
            description = "sort string (by order or by values), in RSQL format" +
                    " | not applicable for page 2 and so on",
            pattern = "=asc= | =desc=",
            example = "date=desc='' and country=asc='HK,UK'"
    )
    @Nullable
    private String sort;// optional

    @Schema(
            description = "page size" +
                    " | not applicable for page 2 and so on" +
                    " | default value may be applied",
            example = "20"
    )
    @Nullable
    private Long limit;// optional
}
