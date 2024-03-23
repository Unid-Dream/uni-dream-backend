package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.StudentUploadedSupervisorReviewPojo;
import unid.monoServerMeta.api.InterviewSkillPayload;
import unid.monoServerMeta.api.WritingSkillPayload;

@Mapper(
        componentModel = "spring"
)
public interface StudentUploadedSupervisorReviewMapper {

    @Mappings({
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.id, source=WritingSkillPayload.StudentUploadedSupervisorReview.Fields.id),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorScore, source=WritingSkillPayload.StudentUploadedSupervisorReview.Fields.score),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedStrength, source=WritingSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedWeakness, source=WritingSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedImprovement, source=WritingSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedWrapUp, source=WritingSkillPayload.StudentUploadedSupervisorReview.Fields.warpUp)

    })
    StudentUploadedSupervisorReviewPojo toPojo(WritingSkillPayload.StudentUploadedSupervisorReview data);


    @Mappings({
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.id, source=InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.id),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorScore, source=InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.score),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedStrength, source=InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedWeakness, source=InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedImprovement, source=InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
            @Mapping(target = StudentUploadedSupervisorReviewPojo.Columns.supervisorCommentedWrapUp, source=InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.warpUp)

    })
    StudentUploadedSupervisorReviewPojo toPojo(InterviewSkillPayload.StudentUploadedSupervisorReview data);
}
