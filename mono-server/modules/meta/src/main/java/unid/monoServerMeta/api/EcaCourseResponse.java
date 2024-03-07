package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class EcaCourseResponse {
    private UUID id;
    private I18n titleI18n;
    private I18n descriptionI18n;
    private I18n eligibilityI18n;
    private String refUrl;
    private String coverImage;
    private List<I18n> academicI18ns;
    private List<I18n> opportunityI18ns;
    private Integer[] grade;
}
