package unid.monoServerMeta.api.version2;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.api.EducatorProfileSimpleResponse;
import unid.monoServerMeta.api.InterviewSkillPayload;

import java.util.List;

@Data
@FieldNameConstants
public class InterviewSkillAssignPayload {
    private EducatorProfileSimpleResponse educator;
    private List<InterviewSkillPayload> interviews;
}
