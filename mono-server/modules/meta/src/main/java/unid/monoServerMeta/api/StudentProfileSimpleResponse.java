package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

@Data
@FieldNameConstants
public class StudentProfileSimpleResponse {
    private I18n firstNameI18n;
    private I18n lastNameI18n;
}
