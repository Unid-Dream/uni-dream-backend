package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@FieldNameConstants
@Schema
public class CountryPayload {
    private UUID id;
    private I18n i18n;

    @JsonIgnore
    private Integer total;

    @Data
    @Schema
    public static class Create{
        private I18n i18n;
    }
}
