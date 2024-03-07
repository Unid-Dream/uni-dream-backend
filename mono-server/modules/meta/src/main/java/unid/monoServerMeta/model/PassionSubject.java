package unid.monoServerMeta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Validated
public class PassionSubject {

    private UUID id;
    @NotNull
    @Valid
    private I18n titleI18n;
    @NotNull
    private I18n descriptionI18n;
    private List<PassionSubjectBook> books;
    private List<PassionSubjectPodcast> podcasts;
    private List<PassionSubjectVideo> videos;
    private List<I18n> answers;


    @JsonIgnore
    private UUID nameI18nId;
    @JsonIgnore
    private UUID descI18nId;
}
