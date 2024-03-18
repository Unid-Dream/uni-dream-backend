package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.tables.pojos.SchoolPojo;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerMeta.api.SchoolRequest;
import unid.monoServerMeta.api.SchoolResponse;
import unid.monoServerMeta.model.SchoolLevel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T21:35:10+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class SchoolMapperImpl implements SchoolMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public SchoolPojo toPojo(SchoolResponse data) {
        if ( data == null ) {
            return null;
        }

        SchoolPojo schoolPojo = new SchoolPojo();

        schoolPojo.setId( data.getId() );
        schoolPojo.setSchoolLevel( schoolLevelToSchoolLevelEnum( data.getSchoolLevel() ) );
        schoolPojo.setLongitude( data.getLongitude() );
        schoolPojo.setLatitude( data.getLatitude() );

        return schoolPojo;
    }

    @Override
    public void merge(SchoolPojo data, SchoolRequest source) {
        if ( source == null ) {
            return;
        }

        data.setSchoolLevel( schoolLevelToSchoolLevelEnum( source.getSchoolLevel() ) );
        data.setCityId( source.getCityId() );
        data.setLongitude( source.getLongitude() );
        data.setLatitude( source.getLatitude() );
        data.setTagId( source.getTagId() );
    }

    @Override
    public SchoolResponse toResponse(DbSchool.Result data) {
        if ( data == null ) {
            return null;
        }

        SchoolResponse schoolResponse = new SchoolResponse();

        schoolResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        schoolResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        schoolResponse.setId( data.getId() );
        schoolResponse.setSchoolLevel( schoolLevelEnumToSchoolLevel( data.getSchoolLevel() ) );
        schoolResponse.setNameI18n( i18nMapper.toModel( data.getNameI18n() ) );
        schoolResponse.setDetailedAddressI18n( i18nMapper.toModel( data.getDetailedAddressI18n() ) );
        schoolResponse.setTag( tagMapper.toResponse( data.getTag() ) );
        schoolResponse.setCity( cityMapper.toResponse( data.getCity() ) );
        schoolResponse.setLongitude( data.getLongitude() );
        schoolResponse.setLatitude( data.getLatitude() );

        return schoolResponse;
    }

    @Override
    public List<SchoolResponse> toResponse(List<DbSchool.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<SchoolResponse> list = new ArrayList<SchoolResponse>( data.size() );
        for ( DbSchool.Result result : data ) {
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
