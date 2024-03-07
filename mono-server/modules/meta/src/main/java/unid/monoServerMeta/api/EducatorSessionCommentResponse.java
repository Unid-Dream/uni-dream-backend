package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorSessionCommentResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @Nullable
    private Boolean completed;
    @Nullable
    @Valid
    @JsonIgnoreProperties({
            EducatorSessionNoteResponse.Fields.id,
            EducatorSessionNoteResponse.Fields.obsoleted,
            EducatorSessionNoteResponse.Fields.items
    })
    @Schema
    private EducatorSessionNoteResponse subject;
    @Valid
    private List<EducatorSessionCommentItemPayload> items;
}
