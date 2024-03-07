package unid.monoServerMeta.api;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class PassionMajorResponse extends BaseResponse {
    @NotNull
    private UUID id;
    @NotNull
    @Valid
    private I18n majorI18n;
    @NotNull
    @Valid
    private I18n descriptionI18n;
}
