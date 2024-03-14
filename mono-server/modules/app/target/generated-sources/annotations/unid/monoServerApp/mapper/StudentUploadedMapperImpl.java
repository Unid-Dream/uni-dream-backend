package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.skill.DbStudentUploadedSupervisorReview;
import unid.monoServerMeta.api.UploadedSupervisorReviewResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-14T20:47:45+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class StudentUploadedMapperImpl implements StudentUploadedMapper {

    @Override
    public UploadedSupervisorReviewResponse toResponse(DbStudentUploadedSupervisorReview.Result result) {
        if ( result == null ) {
            return null;
        }

        UploadedSupervisorReviewResponse uploadedSupervisorReviewResponse = new UploadedSupervisorReviewResponse();

        uploadedSupervisorReviewResponse.setSupervisorScore( result.getSupervisorScore() );
        uploadedSupervisorReviewResponse.setSupervisorCommentedStrength( result.getSupervisorCommentedStrength() );
        uploadedSupervisorReviewResponse.setSupervisorCommentedWeakness( result.getSupervisorCommentedWeakness() );
        uploadedSupervisorReviewResponse.setSupervisorCommentedImprovement( result.getSupervisorCommentedImprovement() );
        uploadedSupervisorReviewResponse.setSupervisorCommentedWrapUp( result.getSupervisorCommentedWrapUp() );

        return uploadedSupervisorReviewResponse;
    }
}
