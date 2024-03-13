package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorLevelResponse implements Serializable {
    private UUID universityId;
    private UUID degreeId;
}
