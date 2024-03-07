package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import unid.monoServerApp.database.table.skill.DbStudentUploadedSupervisorReview;
import unid.monoServerMeta.api.UploadedSupervisorReviewResponse;

@Mapper(
        componentModel = "spring"
)
public interface StudentUploadedMapper {

    UploadedSupervisorReviewResponse toResponse(DbStudentUploadedSupervisorReview.Result result);
}
