package unid.monoServerMeta.api.version2.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.api.InterviewSkillPayload;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class InterviewSkillEducatorRequest {
    @NotNull
    private List<UUID> interviewIds;
    @NotNull
    private UUID educatorProfileId;
}
