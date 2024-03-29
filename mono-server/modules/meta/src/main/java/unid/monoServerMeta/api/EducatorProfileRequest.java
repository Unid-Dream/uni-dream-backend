package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorProfileRequest {

    private I18n lastNameI18n;
    private I18n firstNameI18n;
    private UUID countryId;
//    private UUID cityId;
    private I18n descriptionI18n;
//    private List<EducationLevel> educations;
    private Integer hourly_rate;
    private String photo;
    private List<UUID> expertises;
    private List<UUID> subjects;
    private List<UUID> languages;
    private List<I18n> expertiseDescriptions;



    @Data
    @FieldNameConstants
    public static class EducationLevel{
        private String universityId;
        private String degreeId;
    }
}
