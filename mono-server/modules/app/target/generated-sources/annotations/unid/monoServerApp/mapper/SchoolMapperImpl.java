package unid.monoServerApp.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.tables.pojos.SchoolPojo;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerMeta.api.SchoolPayload;
import unid.monoServerMeta.api.SchoolRequest;
import unid.monoServerMeta.api.SchoolResponse;
import unid.monoServerMeta.model.SchoolLevel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-27T22:36:01+0800",
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
        schoolPojo.setRate( data.getRate() );
        schoolPojo.setPopulation( data.getPopulation() );
        schoolPojo.setTuition( data.getTuition() );
        schoolPojo.setFactor( data.getFactor() );

        return schoolPojo;
    }

    @Override
    public SchoolPojo toPojo(SchoolPayload data) {
        if ( data == null ) {
            return null;
        }

        SchoolPojo schoolPojo = new SchoolPojo();

        schoolPojo.setId( data.getId() );
        schoolPojo.setSchoolLevel( schoolLevelToSchoolLevelEnum( data.getSchoolLevel() ) );
        schoolPojo.setLongitude( data.getLongitude() );
        schoolPojo.setLatitude( data.getLatitude() );
        schoolPojo.setRate( data.getRate() );
        schoolPojo.setPopulation( data.getPopulation() );
        if ( data.getTuition() != null ) {
            schoolPojo.setTuition( new BigDecimal( data.getTuition() ) );
        }
        schoolPojo.setFactor( data.getFactor() );
        schoolPojo.setDescription( data.getDescription() );

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
    public void merge(SchoolPojo data, SchoolPayload source) {
        if ( source == null ) {
            return;
        }

        data.setId( source.getId() );
        data.setSchoolLevel( schoolLevelToSchoolLevelEnum( source.getSchoolLevel() ) );
        data.setLongitude( source.getLongitude() );
        data.setLatitude( source.getLatitude() );
        data.setRate( source.getRate() );
        data.setPopulation( source.getPopulation() );
        if ( source.getTuition() != null ) {
            data.setTuition( new BigDecimal( source.getTuition() ) );
        }
        else {
            data.setTuition( null );
        }
        data.setFactor( source.getFactor() );
        data.setDescription( source.getDescription() );
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
        schoolResponse.setRate( data.getRate() );
        schoolResponse.setPopulation( data.getPopulation() );
        schoolResponse.setFactor( data.getFactor() );
        schoolResponse.setTuition( data.getTuition() );

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
