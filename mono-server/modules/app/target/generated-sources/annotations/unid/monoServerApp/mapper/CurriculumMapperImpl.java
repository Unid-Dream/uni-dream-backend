package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.tables.pojos.CurriculumPojo;
import unid.monoServerApp.database.table.curriculum.DbCurriculum;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.CurriculumRequest;
import unid.monoServerMeta.api.CurriculumResponse;
import unid.monoServerMeta.model.SchoolLevel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-27T22:36:01+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class CurriculumMapperImpl implements CurriculumMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public CurriculumPojo toPojo(CurriculumResponse data) {
        if ( data == null ) {
            return null;
        }

        CurriculumPojo curriculumPojo = new CurriculumPojo();

        curriculumPojo.setId( data.getId() );
        curriculumPojo.setSchoolLevel( schoolLevelToSchoolLevelEnum( data.getSchoolLevel() ) );

        return curriculumPojo;
    }

    @Override
    public void merge(CurriculumPojo data, CurriculumRequest source) {
        if ( source == null ) {
            return;
        }

        data.setSchoolLevel( schoolLevelToSchoolLevelEnum( source.getSchoolLevel() ) );
        data.setTagId( source.getTagId() );
    }

    @Override
    public void merge(DbCurriculum.Result data, CurriculumRequest source) {
        if ( source == null ) {
            return;
        }

        data.setSchoolLevel( schoolLevelToSchoolLevelEnum( source.getSchoolLevel() ) );
        data.setTagId( source.getTagId() );
        if ( source.getNameI18n() != null ) {
            if ( data.getNameI18n() == null ) {
                data.setNameI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getNameI18n(), source.getNameI18n() );
        }
        else {
            data.setNameI18n( null );
        }
    }

    @Override
    public CurriculumResponse toResponse(DbCurriculum.Result data) {
        if ( data == null ) {
            return null;
        }

        CurriculumResponse curriculumResponse = new CurriculumResponse();

        curriculumResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        curriculumResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        curriculumResponse.setId( data.getId() );
        curriculumResponse.setSchoolLevel( schoolLevelEnumToSchoolLevel( data.getSchoolLevel() ) );
        curriculumResponse.setNameI18n( i18nMapper.toModel( data.getNameI18n() ) );
        curriculumResponse.setTag( tagMapper.toResponse( data.getTag() ) );

        return curriculumResponse;
    }

    @Override
    public List<CurriculumResponse> toResponse(List<DbCurriculum.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<CurriculumResponse> list = new ArrayList<CurriculumResponse>( data.size() );
        for ( DbCurriculum.Result result : data ) {
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
