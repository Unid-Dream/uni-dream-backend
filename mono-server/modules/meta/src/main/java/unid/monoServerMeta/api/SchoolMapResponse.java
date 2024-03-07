package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SchoolLevel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class SchoolMapResponse {
    @NotNull
    private UUID id;
    @NotNull
    private SchoolLevel schoolLevel;
    @NotNull
    @Valid
    private I18n nameI18n;
    @NotNull
    @Valid
    private I18n tagI18n;
    @NotNull
    @Valid
    private I18n countryI18n;
    @NotNull
    @Valid
    private I18n cityI18n;
    @NotBlank
    private String longitude;
    @NotBlank
    private String latitude;

    private BigDecimal rate;
    private BigDecimal population;
    private BigDecimal tuition;
    private String factor;
}
