package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SchoolLevel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class CurriculumResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @NotNull
    private SchoolLevel schoolLevel;
    @NotNull
    @Valid
    private I18n nameI18n;
    @NotNull
    @Valid
    private TagResponse tag;
}
