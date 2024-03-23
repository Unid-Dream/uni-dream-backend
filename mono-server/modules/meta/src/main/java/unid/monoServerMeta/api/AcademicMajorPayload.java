package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class AcademicMajorPayload {
    private UUID id;
    @NotBlank
    private I18n titleI18n;
    @NotBlank
    private I18n descriptionI18n;
    @NotNull
    private String iconPath;
    @JsonIgnore
    private Integer total;
}
