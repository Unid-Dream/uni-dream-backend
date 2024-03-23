package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.AcademicSubjectResourceType;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class AcademicSubjectPageRequest {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;

    private String searchKey;
}
