package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class SkillPrice {

    private Double amount;
    private String unit;
}


