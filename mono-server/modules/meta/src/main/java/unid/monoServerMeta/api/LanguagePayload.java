package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class LanguagePayload {
    private UUID id;
    private I18n i18n;


    @JsonIgnore
    private Integer total;

}
