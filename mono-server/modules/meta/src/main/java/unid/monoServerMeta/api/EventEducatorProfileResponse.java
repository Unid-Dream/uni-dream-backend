package unid.monoServerMeta.api;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class EventEducatorProfileResponse {
    private UUID id;
    private I18n lastNameI18n;
    private I18n firstNameI18n;
    private String email;
    private I18n countryI18n;
    private I18n cityI18n;
    private String description;
    private List<I18n> schools;
    private String photo;
    private List<I18n> expertises;
    private List<I18n> majors;
    private List<I18n> languages;
}
