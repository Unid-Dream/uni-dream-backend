package unid.monoServerMeta.api.version2.request;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@FieldNameConstants
public class RescheduleSessionPageRequest {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
}
