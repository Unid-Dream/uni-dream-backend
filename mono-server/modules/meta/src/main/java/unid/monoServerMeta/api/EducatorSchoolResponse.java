package unid.monoServerMeta.api;

import lombok.Data;
import unid.monoServerMeta.model.I18n;

@Data
public class EducatorSchoolResponse {
    private I18n universityI18n;
    private I18n degreeI18n;
}
