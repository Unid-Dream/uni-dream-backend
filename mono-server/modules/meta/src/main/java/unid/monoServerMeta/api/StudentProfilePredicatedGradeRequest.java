package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class StudentProfilePredicatedGradeRequest {
    @Nullable
    private UUID academicSubjectId;
    @Nullable
    @Size(min = 1, max = 10)
    private String grade;
}
