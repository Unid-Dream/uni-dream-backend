package unid.monoServerMeta.api.version2.request;


import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class WritingSkillAssignRequest {
    @NotNull
    private List<UUID> writingIds;
    @NotNull
    private UUID educatorProfileId;
}
