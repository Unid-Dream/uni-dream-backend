package unid.monoServerMeta.api.version2;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.api.EducatorProfileSimpleResponse;
import unid.monoServerMeta.api.InterviewSkillPayload;
import unid.monoServerMeta.api.WritingSkillPayload;

import java.util.List;

@Data
@FieldNameConstants
public class WritingSkillAssignPayload {
    private EducatorProfileSimpleResponse educator;
    private List<WritingSkillPayload> writings;
}
