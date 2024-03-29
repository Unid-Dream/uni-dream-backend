package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.PassionSubject;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class AcademicMajorResponse extends BaseResponse {
    private UUID id;
    @NotBlank
    private I18n titleI18n;
    @Nullable
    private I18n descriptionI18n;
    private String iconPath;

    private List<AcademicSubjectResponse> subjects;


//    @NotNull
//    private UUID id;
//    @NotNull
//    @Valid
//    private I18n titleI18n;
//    @Nullable
//    @Valid
//    private I18n descriptionI18n;
//    @NotNull
//    private TagResponse tag;
//    @Nullable
//    private List<AcademicSubjectResponse> subjects;
}
