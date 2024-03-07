package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SchoolLevel;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@Validated
public class SchoolResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @Nullable
    private SchoolLevel schoolLevel;
    @NotNull
    @Valid
    private I18n nameI18n;
//    @NotNull
//    @Valid
    private I18n detailedAddressI18n;
//    @NotNull
//    @Valid
    private TagResponse tag;
    @Nullable
    @Valid
    private CityResponse city;
    @Nullable
//    @Pattern(regexp = unid.monoServerMeta.Pattern.LONGITUDE)
    private String longitude;
    @Nullable
//    @Pattern(regexp = unid.monoServerMeta.Pattern.LATITUDE)
    private String latitude;

    private BigDecimal rate;
    private BigDecimal population;
    private String factor;
    private BigDecimal tuition;

}
