package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class EducatorResponse {
    private UUID id;
    private I18n lastNameI18n;
    private I18n firstNameI18n;
    private String email;
    private I18n countryI18n;
    private I18n cityI18n;
    private I18n descriptionI18n;
    private List<Education> educations;
    private Integer hourly_rate;
    private String photo;
    private List<I18n> expertises;
    private List<I18n> subjects;
    private List<I18n> languages;
    private List<I18n> expertiseDescriptions;

    @JsonIgnore
    private String description;

    @Data
    @FieldNameConstants
    public static class Education{
        private String university;
        private String degree;
    }

}
