package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class AcademicSubjectRequest {
    @NotNull
    @Valid
    private I18n titleI18n;
    @Nullable
    @Valid
    private I18n descriptionI18n;
    @Nullable
    @Valid
    private I18n descriptionMasterDegreeI18n;
    @Nullable
    @Valid
    private I18n descriptionPhdI18n;
    @NotNull
    private UUID tagId;
    @Nullable
    @Valid
    private List<AcademicSubjectResourcePayload> resources;
}
