package unid.monoServerMeta.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.BaseResponse;

import java.util.UUID;


@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class SkillTopicItem {
    private UUID id;
    private String value;
}
