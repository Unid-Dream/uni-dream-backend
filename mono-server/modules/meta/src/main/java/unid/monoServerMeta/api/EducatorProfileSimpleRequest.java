package unid.monoServerMeta.api;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.ApplicationApprovalEnum;
import unid.monoServerMeta.model.Gender;
import unid.monoServerMeta.model.I18n;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorProfileSimpleRequest {

    private I18n firstNameI18n;
    private I18n lastNameI18n;
    private UUID countryId;
    private String timezone;
    private String description;
    private List<String> expertiseDescription;
    private List<EducationLevel> educationLevel;
    private List<UUID> expertiseId;
    private List<UUID> languageId;
    private Integer hourlyRate;
    private String profilePicture;

    private String phoneCountryCode;
    private String phone;
    private Gender gender;

    private String microsoftEmail;

    @Data
    @FieldNameConstants
    public static class EducationLevel{
        private UUID universityId;
        private UUID degreeId;

    }
}
