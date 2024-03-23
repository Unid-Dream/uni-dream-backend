package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.Gender;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class StudentProfilePayload {
    @NotNull
    private UUID id;
    private I18n firstNameI18n;
    private I18n lastNameI18n;
    private String phone;
    private String email;
    private Gender gender;
    private Integer total;
}
