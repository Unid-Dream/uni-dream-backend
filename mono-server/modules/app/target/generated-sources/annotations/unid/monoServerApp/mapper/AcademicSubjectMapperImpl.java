package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.AcademicSubjectResourceTypeEnum;
import unid.jooqMono.model.tables.pojos.AcademicSubjectPojo;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.AcademicSubjectRequest;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;
import unid.monoServerMeta.api.AcademicSubjectResponse;
import unid.monoServerMeta.model.I18n;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T00:13:51+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class AcademicSubjectMapperImpl implements AcademicSubjectMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private AcademicSubjectResourceMapper academicSubjectResourceMapper;

    @Override
    public AcademicSubjectPojo toPojo(AcademicSubjectResponse data) {
        if ( data == null ) {
            return null;
        }

        AcademicSubjectPojo academicSubjectPojo = new AcademicSubjectPojo();

        academicSubjectPojo.setId( data.getId() );

        return academicSubjectPojo;
    }

    @Override
    public void merge(DbAcademicSubject.Result data, AcademicSubjectRequest source) {
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
        if ( source.getDescriptionMasterDegreeI18n() != null ) {
            if ( data.getDescriptionMasterDegreeI18n() == null ) {
                data.setDescriptionMasterDegreeI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getDescriptionMasterDegreeI18n(), source.getDescriptionMasterDegreeI18n() );
        }
        else {
            data.setDescriptionMasterDegreeI18n( null );
        }
        if ( source.getDescriptionPhdI18n() != null ) {
            if ( data.getDescriptionPhdI18n() == null ) {
                data.setDescriptionPhdI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getDescriptionPhdI18n(), source.getDescriptionPhdI18n() );
        }
        else {
            data.setDescriptionPhdI18n( null );
        }
        if ( data.getResources() != null ) {
            List<DbAcademicSubjectResource.Result> list = academicSubjectResourcePayloadListToResultList( source.getResources() );
            if ( list != null ) {
                data.getResources().clear();
                data.getResources().addAll( list );
            }
            else {
                data.setResources( null );
            }
        }
        else {
            List<DbAcademicSubjectResource.Result> list = academicSubjectResourcePayloadListToResultList( source.getResources() );
            if ( list != null ) {
                data.setResources( list );
            }
        }
    }

    @Override
    public void merge(AcademicSubjectPojo data, AcademicSubjectRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagId( source.getTagId() );
    }

    @Override
    public AcademicSubjectResponse toResponse(DbAcademicSubject.Result data) {
        if ( data == null ) {
            return null;
        }

        AcademicSubjectResponse academicSubjectResponse = new AcademicSubjectResponse();

        academicSubjectResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        academicSubjectResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        academicSubjectResponse.setId( data.getId() );
        academicSubjectResponse.setTitleI18n( i18nMapper.toModel( data.getTitleI18n() ) );
        academicSubjectResponse.setDescriptionI18n( i18nMapper.toModel( data.getDescriptionI18n() ) );
        academicSubjectResponse.setBooks( academicSubjectResourceMapper.toResponse( data.getBooks() ) );
        academicSubjectResponse.setPodcasts( academicSubjectResourceMapper.toResponse( data.getPodcasts() ) );
        academicSubjectResponse.setVideos( academicSubjectResourceMapper.toResponse( data.getVideos() ) );
        academicSubjectResponse.setAnswers( resultListToI18nList( data.getAnswers() ) );

        return academicSubjectResponse;
    }

    @Override
    public List<AcademicSubjectResponse> toResponse(List<DbAcademicSubject.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<AcademicSubjectResponse> list = new ArrayList<AcademicSubjectResponse>( data.size() );
        for ( DbAcademicSubject.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    protected DbI18N.Result i18nToResult(I18n i18n) {
        if ( i18n == null ) {
            return null;
        }

        DbI18N.Result result = new DbI18N.Result();

        result.setEnglish( i18n.getEnglish() );
        result.setChineseTraditional( i18n.getChineseTraditional() );
        result.setChineseSimplified( i18n.getChineseSimplified() );

        return result;
    }

    protected DbAcademicSubjectResource.Result academicSubjectResourcePayloadToResult(AcademicSubjectResourcePayload academicSubjectResourcePayload) {
        if ( academicSubjectResourcePayload == null ) {
            return null;
        }

        DbAcademicSubjectResource.Result result = new DbAcademicSubjectResource.Result();

        result.setId( academicSubjectResourcePayload.getId() );
        if ( academicSubjectResourcePayload.getType() != null ) {
            result.setType( Enum.valueOf( AcademicSubjectResourceTypeEnum.class, academicSubjectResourcePayload.getType() ) );
        }
        result.setUrl( academicSubjectResourcePayload.getUrl() );
        result.setTitleI18n( i18nToResult( academicSubjectResourcePayload.getTitleI18n() ) );
        result.setAuthorI18n( i18nToResult( academicSubjectResourcePayload.getAuthorI18n() ) );

        return result;
    }

    protected List<DbAcademicSubjectResource.Result> academicSubjectResourcePayloadListToResultList(List<AcademicSubjectResourcePayload> list) {
        if ( list == null ) {
            return null;
        }

        List<DbAcademicSubjectResource.Result> list1 = new ArrayList<DbAcademicSubjectResource.Result>( list.size() );
        for ( AcademicSubjectResourcePayload academicSubjectResourcePayload : list ) {
            list1.add( academicSubjectResourcePayloadToResult( academicSubjectResourcePayload ) );
        }

        return list1;
    }

    protected List<I18n> resultListToI18nList(List<DbI18N.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<I18n> list1 = new ArrayList<I18n>( list.size() );
        for ( DbI18N.Result result : list ) {
            list1.add( i18nMapper.toModel( result ) );
        }

        return list1;
    }
}
