package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class PassionSubjectVideoRequest {

    @NotNull
    @Valid
    private I18n nameI18n;
    @NotNull
    private I18n descI18n;

    private I18n authorI18n;

    private String url;
    //TED, Youtube
    private String type;




}
