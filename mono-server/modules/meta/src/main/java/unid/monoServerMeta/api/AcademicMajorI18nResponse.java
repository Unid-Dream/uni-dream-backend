package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;

@Data
@FieldNameConstants
public class AcademicMajorI18nResponse {

    private I18n titleI18n;
    private List<I18n> subjects;
}
