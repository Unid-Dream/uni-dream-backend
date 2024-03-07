package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SchoolLevel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class SchoolRequest {
    @NotNull
    private SchoolLevel schoolLevel;
    @NotNull
    @Valid
    private I18n nameI18n;
    @NotNull
    @Valid
    private I18n detailedAddressI18n;
    @NotNull
    private UUID tagId;
    @NotNull
    private UUID cityId;
    @NotBlank
    @Pattern(regexp = unid.monoServerMeta.Pattern.LONGITUDE)
    private String longitude;
    @NotBlank
    @Pattern(regexp = unid.monoServerMeta.Pattern.LATITUDE)
    private String latitude;
}
