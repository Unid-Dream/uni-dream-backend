package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Data
@FieldNameConstants
public class UploadedSupervisorReviewResponse {

    private Integer        supervisorScore;
    private String         supervisorCommentedStrength;
    private String         supervisorCommentedWeakness;
    private String         supervisorCommentedImprovement;
    private String         supervisorCommentedWrapUp;
}
