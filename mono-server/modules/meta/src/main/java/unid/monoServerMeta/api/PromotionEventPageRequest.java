package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@Data
@FieldNameConstants
@Validated
public class PromotionEventPageRequest {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
    @Nullable
    private String searchKey;
}
