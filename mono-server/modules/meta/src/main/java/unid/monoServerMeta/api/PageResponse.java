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
@AllArgsConstructor
@Data
public class PageResponse<T> {
     private final Integer totalRecords;
     private final Integer pageNumber;
     private final Integer pageSize;
     private final Integer totalPages;
     private final List<T> result;


}
