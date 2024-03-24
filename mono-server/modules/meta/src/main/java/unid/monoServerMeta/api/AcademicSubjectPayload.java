package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.AcademicSubjectResourceType;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AcademicSubjectPayload {

    private UUID id;

    private AcademicMajorPayload major;
    private I18n titleI18n;
    private I18n descriptionI18n;

    private List<AcademicSubjectResourcePayload> resources;


    @JsonIgnore
    private Integer total;





    @Data
    @FieldNameConstants
    @Schema
    public static class AcademicMajorPayload{
        private UUID id;
        private I18n titleI18n;
    }




    @Data
    @FieldNameConstants
    @Schema
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class AcademicSubjectResourcePayload{
        private UUID id;
        private AcademicSubjectResourceType type;
        private I18n titleI18n;
        private String author;
        private String thumbnail;
        private String url;
    }
}
