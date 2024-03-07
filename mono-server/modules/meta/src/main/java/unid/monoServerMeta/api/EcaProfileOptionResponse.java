package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.UUID;

@Data
@FieldNameConstants
public class EcaProfileOptionResponse  {
    private UUID id;
    private I18n optionI18n;
}
