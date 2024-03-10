package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.AcademicSubjectResourceType;
import unid.monoServerMeta.model.I18n;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class AcademicSubjectResourcePayload {
    //book
    private UUID id;
    @NotBlank
    private I18n titleI18n;
    @Nullable
    private I18n authorI18n;
    private String image;

    //video
//    private UUID id;
//    @NotBlank
//    private I18n titleI18n;
//    @Nullable
//    private I18n authorI18n;
    private String url;
    private String type = "youtube";

    //podcast
//    private UUID id;
//    @NotBlank
//    private I18n titleI18n;
//    @Nullable
//    private I18n authorI18n;
//    private String url;









//    @Nullable
//    private UUID id;
//    @NotNull
//    private AcademicSubjectResourceType type;
//    @NotNull
//    @Valid
//    private I18n titleI18n;
//    @Nullable
//    private String author;
//    @Nullable
//    private String url;
//    @Nullable
//    private String thumbnail;
}
