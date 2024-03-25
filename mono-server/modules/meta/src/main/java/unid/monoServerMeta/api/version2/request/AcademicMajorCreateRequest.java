package unid.monoServerMeta.api.version2.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@FieldNameConstants
public class AcademicMajorCreateRequest {
    @NotBlank
    private I18n titleI18n;
    @NotBlank
    private I18n descriptionI18n;
    @NotNull
    private String iconPath;
}
