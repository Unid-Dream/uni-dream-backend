package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.AcademicMajorPojo;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.AcademicMajorPayload;
import unid.monoServerMeta.api.AcademicMajorRequest;
import unid.monoServerMeta.api.AcademicMajorResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-24T01:03:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class AcademicMajorMapperImpl implements AcademicMajorMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private AcademicSubjectMapper academicSubjectMapper;

    @Override
    public AcademicMajorPojo toPojo(AcademicMajorResponse data) {
        if ( data == null ) {
            return null;
        }

        AcademicMajorPojo academicMajorPojo = new AcademicMajorPojo();

        academicMajorPojo.setId( data.getId() );
        academicMajorPojo.setIconPath( data.getIconPath() );

        return academicMajorPojo;
    }

    @Override
    public void merge(DbAcademicMajor.Result data, AcademicMajorRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagId( source.getTagId() );
        if ( source.getTitleI18n() != null ) {
            if ( data.getTitleI18n() == null ) {
                data.setTitleI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getTitleI18n(), source.getTitleI18n() );
        }
        else {
            data.setTitleI18n( null );
        }
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
    public void merge(AcademicMajorPojo data, AcademicMajorRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagId( source.getTagId() );
    }

    @Override
    public void merge(AcademicMajorPojo data, AcademicMajorPayload source) {
        if ( source == null ) {
            return;
        }

        data.setId( source.getId() );
        data.setIconPath( source.getIconPath() );
    }

    @Override
    public AcademicMajorPayload toResponse(AcademicMajorPojo data) {
        if ( data == null ) {
            return null;
        }

        AcademicMajorPayload academicMajorPayload = new AcademicMajorPayload();

        academicMajorPayload.setId( data.getId() );
        academicMajorPayload.setIconPath( data.getIconPath() );

        return academicMajorPayload;
    }

    @Override
    public AcademicMajorResponse toResponse(DbAcademicMajor.Result data) {
        if ( data == null ) {
            return null;
        }

        AcademicMajorResponse academicMajorResponse = new AcademicMajorResponse();

        academicMajorResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        academicMajorResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        academicMajorResponse.setId( data.getId() );
        academicMajorResponse.setTitleI18n( i18nMapper.toModel( data.getTitleI18n() ) );
        academicMajorResponse.setDescriptionI18n( i18nMapper.toModel( data.getDescriptionI18n() ) );
        academicMajorResponse.setIconPath( data.getIconPath() );
        academicMajorResponse.setSubjects( academicSubjectMapper.toResponse( data.getSubjects() ) );

        return academicMajorResponse;
    }

    @Override
    public List<AcademicMajorResponse> toResponse(List<DbAcademicMajor.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<AcademicMajorResponse> list = new ArrayList<AcademicMajorResponse>( data.size() );
        for ( DbAcademicMajor.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
