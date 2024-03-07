package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@FieldNameConstants
@Valid
public class EcaCoursePageRequest {
    @Valid
    @NotNull
    private Integer pageNumber;
    @Valid
    @NotNull
    private Integer pageSize;
    @Nullable
    private List<String> opportunities;
    @Nullable
    private List<String> academics;
    @Nullable
    private List<Integer> grades;
}
