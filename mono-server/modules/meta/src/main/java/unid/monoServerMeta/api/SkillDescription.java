package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;

import java.util.List;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SkillDescription{
    private String title;
    private String note;
}
