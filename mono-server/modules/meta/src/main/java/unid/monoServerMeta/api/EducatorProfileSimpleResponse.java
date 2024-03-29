package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.ApplicationApprovalEnum;
import unid.monoServerMeta.model.Gender;
import unid.monoServerMeta.model.I18n;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EducatorProfileSimpleResponse implements Serializable {
    private UUID id;
    private I18n firstNameI18n;
    private I18n lastNameI18n;
    private UUID countryId;
    private String timezone;
    private String description;
    private List<String> expertiseDescription;
    private List<EducatorLevelResponse> educationLevel;
    private List<UUID> expertiseId;
    private List<UUID> languageId;
    private String profilePicture;
    private Integer hourlyRate;
    private ApplicationApprovalEnum applicationApproval;

    private String phoneCountryCode;
    private String phone;
    private Gender gender;
    private String email;


}
