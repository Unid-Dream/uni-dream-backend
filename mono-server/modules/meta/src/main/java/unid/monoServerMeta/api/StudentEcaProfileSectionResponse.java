package unid.monoServerMeta.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

@Data
@FieldNameConstants
public class StudentEcaProfileSectionResponse {
    private I18n section;
    private Integer score;
}
