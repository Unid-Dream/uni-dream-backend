package unid.monoServerMeta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Validated
public class PassionSubjectBook  implements Serializable {

    private UUID id;
    @NotBlank
    private I18n titleI18n;
    @Nullable
    private I18n authorI18n;
    private String image;
}