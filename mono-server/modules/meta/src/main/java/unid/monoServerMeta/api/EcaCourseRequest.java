package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class EcaCourseRequest {
    private UUID id;
    private I18n nameI18n;
    private I18n descriptionI18n;
    private List<AcademicMajor> majors;
    private Integer[] grades;
    private List<Opportunity> opportunities;
    private String refUrl;
    private String coverImage;


    @Schema
    @FieldNameConstants
    @Data
    public static class AcademicMajor{
        private UUID id;
    }


    @Schema
    @FieldNameConstants
    @Data
    public static class Opportunity{
        private UUID id;
    }



}
