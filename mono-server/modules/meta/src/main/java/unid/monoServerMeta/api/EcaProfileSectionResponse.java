package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcaProfileSectionResponse {
    private UUID id;
    private I18n section;
    private List<EcaProfileOptionResponse> options;
}
