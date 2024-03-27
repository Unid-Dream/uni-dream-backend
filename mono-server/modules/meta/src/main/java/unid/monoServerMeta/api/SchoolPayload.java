package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SchoolLevel;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SchoolPayload {
    private UUID id;
    private I18n nameI18n;
    private SchoolLevel schoolLevel;
    private String latitude;
    private String longitude;
    private String tuition;
    private String factor;
    private Country country;
    private BigDecimal rate;
    private BigDecimal population;
    private String description;



    @JsonIgnore
    public Integer total;

    @Data
    @Schema
    @FieldNameConstants
    public static class Country{
        private UUID id;
        private I18n nameI18n;
    }
}
