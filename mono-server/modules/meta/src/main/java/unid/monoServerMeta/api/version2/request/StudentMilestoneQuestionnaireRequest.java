package unid.monoServerMeta.api.version2.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.api.version2.StudentMilestoneQuestionnairePayload;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.MilestoneOptionType;

import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
@Schema
public class StudentMilestoneQuestionnaireRequest {

     public List<StudentMilestoneQuestionnairePayload> payload;
}
