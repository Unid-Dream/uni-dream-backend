package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.tables.pojos.EducationLevelPojo;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.EducationLevelRequest;
import unid.monoServerMeta.api.EducationLevelResponse;
import unid.monoServerMeta.model.SchoolLevel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-17T09:34:59+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EducationLevelMapperImpl implements EducationLevelMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public EducationLevelPojo toPojo(EducationLevelResponse data) {
        if ( data == null ) {
            return null;
        }

        EducationLevelPojo educationLevelPojo = new EducationLevelPojo();

        educationLevelPojo.setId( data.getId() );
        educationLevelPojo.setGrade( data.getGrade() );
        educationLevelPojo.setSchoolLevel( schoolLevelToSchoolLevelEnum( data.getSchoolLevel() ) );

        return educationLevelPojo;
    }

    @Override
    public void merge(EducationLevelPojo data, EducationLevelRequest source) {
        if ( source == null ) {
            return;
        }

        data.setGrade( source.getGrade() );
        data.setSchoolLevel( schoolLevelToSchoolLevelEnum( source.getSchoolLevel() ) );
    }

    @Override
    public void merge(DbEducationLevel.Result data, EducationLevelRequest source) {
        if ( source == null ) {
            return;
        }

        data.setGrade( source.getGrade() );
        data.setSchoolLevel( schoolLevelToSchoolLevelEnum( source.getSchoolLevel() ) );
        if ( source.getDescriptionI18n() != null ) {
            if ( data.getDescriptionI18n() == null ) {
                data.setDescriptionI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getDescriptionI18n(), source.getDescriptionI18n() );
        }
        else {
            data.setDescriptionI18n( null );
        }
    }

    @Override
    public EducationLevelResponse toResponse(DbEducationLevel.Result data) {
        if ( data == null ) {
            return null;
        }

        EducationLevelResponse educationLevelResponse = new EducationLevelResponse();

        educationLevelResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        educationLevelResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        educationLevelResponse.setId( data.getId() );
        educationLevelResponse.setGrade( data.getGrade() );
        educationLevelResponse.setSchoolLevel( schoolLevelEnumToSchoolLevel( data.getSchoolLevel() ) );
        educationLevelResponse.setDescriptionI18n( i18nMapper.toModel( data.getDescriptionI18n() ) );

        return educationLevelResponse;
    }

    @Override
    public List<EducationLevelResponse> toResponse(List<DbEducationLevel.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<EducationLevelResponse> list = new ArrayList<EducationLevelResponse>( data.size() );
        for ( DbEducationLevel.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    protected SchoolLevelEnum schoolLevelToSchoolLevelEnum(SchoolLevel schoolLevel) {
        if ( schoolLevel == null ) {
            return null;
        }

        SchoolLevelEnum schoolLevelEnum;

        switch ( schoolLevel ) {
            case SECONDARY_SCHOOL: schoolLevelEnum = SchoolLevelEnum.SECONDARY_SCHOOL;
            break;
            case UNIVERSITY: schoolLevelEnum = SchoolLevelEnum.UNIVERSITY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + schoolLevel );
        }

        return schoolLevelEnum;
    }

    protected SchoolLevel schoolLevelEnumToSchoolLevel(SchoolLevelEnum schoolLevelEnum) {
        if ( schoolLevelEnum == null ) {
            return null;
        }

        SchoolLevel schoolLevel;

        switch ( schoolLevelEnum ) {
            case SECONDARY_SCHOOL: schoolLevel = SchoolLevel.SECONDARY_SCHOOL;
            break;
            case UNIVERSITY: schoolLevel = SchoolLevel.UNIVERSITY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + schoolLevelEnum );
        }

        return schoolLevel;
    }
}
