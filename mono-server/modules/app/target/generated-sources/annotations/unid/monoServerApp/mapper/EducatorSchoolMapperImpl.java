package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.school.DbEducatorSchool;
import unid.monoServerMeta.api.EducatorLevelResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T01:03:58+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EducatorSchoolMapperImpl implements EducatorSchoolMapper {

    @Override
    public EducatorLevelResponse toPojo(DbEducatorSchool.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorLevelResponse educatorLevelResponse = new EducatorLevelResponse();

        educatorLevelResponse.setUniversityId( data.getUniversityId() );
        educatorLevelResponse.setDegreeId( data.getDegreeId() );

        return educatorLevelResponse;
    }
}
