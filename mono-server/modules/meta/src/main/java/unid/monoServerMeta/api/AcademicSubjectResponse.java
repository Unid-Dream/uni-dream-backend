package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.*;

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
public class AcademicSubjectResponse extends BaseResponse {
    private UUID id;
    @NotNull
    @Valid
    private I18n titleI18n;
    @NotNull
    private I18n descriptionI18n;

    private List<AcademicSubjectResourcePayload> books;
    private List<AcademicSubjectResourcePayload> podcasts;
    private List<AcademicSubjectResourcePayload> videos;

    private List<I18n> answers;
}
