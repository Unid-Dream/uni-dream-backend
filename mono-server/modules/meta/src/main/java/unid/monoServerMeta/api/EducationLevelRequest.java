package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SchoolLevel;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducationLevelRequest {
    @NotBlank
    private String grade;
    @NotNull
    private SchoolLevel schoolLevel;
    @Nullable
    @Valid
    private I18n descriptionI18n;
}
