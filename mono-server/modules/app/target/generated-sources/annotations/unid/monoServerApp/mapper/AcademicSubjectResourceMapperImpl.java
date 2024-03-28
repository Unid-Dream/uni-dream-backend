package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.AcademicSubjectResourceTypeEnum;
import unid.jooqMono.model.tables.pojos.AcademicSubjectResourcePojo;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerMeta.api.AcademicSubjectPayload;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;
import unid.monoServerMeta.model.AcademicSubjectResourceType;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-28T23:20:21+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class AcademicSubjectResourceMapperImpl implements AcademicSubjectResourceMapper {

    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public AcademicSubjectResourcePojo toPojo(AcademicSubjectResourcePayload data) {
        if ( data == null ) {
            return null;
        }

        AcademicSubjectResourcePojo academicSubjectResourcePojo = new AcademicSubjectResourcePojo();

        academicSubjectResourcePojo.setId( data.getId() );
        if ( data.getType() != null ) {
            academicSubjectResourcePojo.setType( Enum.valueOf( AcademicSubjectResourceTypeEnum.class, data.getType() ) );
        }
        academicSubjectResourcePojo.setUrl( data.getUrl() );

        return academicSubjectResourcePojo;
    }

    @Override
    public AcademicSubjectResourcePojo toPojo(AcademicSubjectPayload.AcademicSubjectResourcePayload data) {
        if ( data == null ) {
            return null;
        }

        AcademicSubjectResourcePojo academicSubjectResourcePojo = new AcademicSubjectResourcePojo();

        academicSubjectResourcePojo.setId( data.getId() );
        academicSubjectResourcePojo.setType( academicSubjectResourceTypeToAcademicSubjectResourceTypeEnum( data.getType() ) );
        academicSubjectResourcePojo.setAuthor( data.getAuthor() );
        academicSubjectResourcePojo.setUrl( data.getUrl() );
        academicSubjectResourcePojo.setThumbnail( data.getThumbnail() );

        return academicSubjectResourcePojo;
    }

    @Override
    public AcademicSubjectResourcePayload toResponse(DbAcademicSubjectResource.Result data) {
        if ( data == null ) {
            return null;
        }

        AcademicSubjectResourcePayload academicSubjectResourcePayload = new AcademicSubjectResourcePayload();

        academicSubjectResourcePayload.setAuthorI18n( i18nMapper.toModel( data.getAuthorI18n() ) );
        academicSubjectResourcePayload.setImage( data.getThumbnail() );
        academicSubjectResourcePayload.setUrl( data.getUrl() );
        if ( data.getType() != null ) {
            academicSubjectResourcePayload.setType( data.getType().name() );
        }
        academicSubjectResourcePayload.setId( data.getId() );
        academicSubjectResourcePayload.setTitleI18n( i18nMapper.toModel( data.getTitleI18n() ) );

        return academicSubjectResourcePayload;
    }

    @Override
    public List<AcademicSubjectResourcePayload> toResponse(List<DbAcademicSubjectResource.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<AcademicSubjectResourcePayload> list = new ArrayList<AcademicSubjectResourcePayload>( data.size() );
        for ( DbAcademicSubjectResource.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    protected AcademicSubjectResourceTypeEnum academicSubjectResourceTypeToAcademicSubjectResourceTypeEnum(AcademicSubjectResourceType academicSubjectResourceType) {
        if ( academicSubjectResourceType == null ) {
            return null;
        }

        AcademicSubjectResourceTypeEnum academicSubjectResourceTypeEnum;

        switch ( academicSubjectResourceType ) {
            case READINGS: academicSubjectResourceTypeEnum = AcademicSubjectResourceTypeEnum.READINGS;
            break;
            case VIDEO: academicSubjectResourceTypeEnum = AcademicSubjectResourceTypeEnum.VIDEO;
            break;
            case PODCAST: academicSubjectResourceTypeEnum = AcademicSubjectResourceTypeEnum.PODCAST;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + academicSubjectResourceType );
        }

        return academicSubjectResourceTypeEnum;
    }
}
