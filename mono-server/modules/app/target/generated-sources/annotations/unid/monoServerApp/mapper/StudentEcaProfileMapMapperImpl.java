package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.ecaProfile.DbStudentEcaProfileMap;
import unid.monoServerMeta.api.StudentEcaProfileSectionResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-17T21:01:51+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class StudentEcaProfileMapMapperImpl implements StudentEcaProfileMapMapper {

    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public StudentEcaProfileSectionResponse toResponse(DbStudentEcaProfileMap.Result data) {
        if ( data == null ) {
            return null;
        }

        StudentEcaProfileSectionResponse studentEcaProfileSectionResponse = new StudentEcaProfileSectionResponse();

        studentEcaProfileSectionResponse.setSection( i18nMapper.toModel( data.getSection() ) );
        studentEcaProfileSectionResponse.setScore( data.getScore() );

        return studentEcaProfileSectionResponse;
    }

    @Override
    public List<StudentEcaProfileSectionResponse> toResponse(List<DbStudentEcaProfileMap.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentEcaProfileSectionResponse> list1 = new ArrayList<StudentEcaProfileSectionResponse>( list.size() );
        for ( DbStudentEcaProfileMap.Result result : list ) {
            list1.add( toResponse( result ) );
        }

        return list1;
    }
}
