package unid.monoServerMeta.api;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorProfileUpdateRequest {

    private I18n lastNameI18n;
    private I18n firstNameI18n;
    private UUID countryId;
//    private UUID cityId;
    private String description;
//    private List<EducationLevel> educations;
    private Integer hourly_rate;
    private String photo;
    private List<UUID> expertises;
    private List<UUID> subjects;
    private List<UUID> languages;
    private List<I18n> expertiseDescriptions = Lists.newArrayList();



    @Data
    @FieldNameConstants
    public static class EducationLevel{
        private String universityId;
        private String degreeId;
    }
}
