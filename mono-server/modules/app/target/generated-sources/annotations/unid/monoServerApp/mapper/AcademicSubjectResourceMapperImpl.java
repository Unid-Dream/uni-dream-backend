package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.AcademicSubjectResourceTypeEnum;
import unid.jooqMono.model.tables.pojos.AcademicSubjectResourcePojo;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;
import unid.monoServerMeta.model.AcademicSubjectResourceType;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-05T21:42:25+0800",
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

        academicSubjectResourcePayload.setId( data.getId() );
        academicSubjectResourcePayload.setType( academicSubjectResourceTypeEnumToAcademicSubjectResourceType( data.getType() ) );
        academicSubjectResourcePayload.setTitleI18n( i18nMapper.toModel( data.getTitleI18n() ) );
        academicSubjectResourcePayload.setAuthor( data.getAuthor() );
        academicSubjectResourcePayload.setUrl( data.getUrl() );
        academicSubjectResourcePayload.setThumbnail( data.getThumbnail() );

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

    protected AcademicSubjectResourceType academicSubjectResourceTypeEnumToAcademicSubjectResourceType(AcademicSubjectResourceTypeEnum academicSubjectResourceTypeEnum) {
        if ( academicSubjectResourceTypeEnum == null ) {
            return null;
        }

        AcademicSubjectResourceType academicSubjectResourceType;

        switch ( academicSubjectResourceTypeEnum ) {
            case READINGS: academicSubjectResourceType = AcademicSubjectResourceType.READINGS;
            break;
            case VIDEO: academicSubjectResourceType = AcademicSubjectResourceType.VIDEO;
            break;
            case PODCAST: academicSubjectResourceType = AcademicSubjectResourceType.PODCAST;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + academicSubjectResourceTypeEnum );
        }

        return academicSubjectResourceType;
    }
}
