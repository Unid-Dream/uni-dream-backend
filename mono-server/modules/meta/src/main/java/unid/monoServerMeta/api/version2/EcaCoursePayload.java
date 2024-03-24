package unid.monoServerMeta.api.version2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class EcaCoursePayload {
    private UUID id;
    private I18n nameI18n;
    private I18n descriptionI18n;
    private List<AcademicMajor> majors;
    private Integer[] grades;
    private List<Opportunity> opportunities;


    @Schema
    @FieldNameConstants
    @Data
    public static class AcademicMajor{
        private UUID id;
        private I18n i18n;
    }


    @Schema
    @FieldNameConstants
    @Data
    public static class Opportunity{
        private UUID id;
        private I18n i18n;
    }



}
