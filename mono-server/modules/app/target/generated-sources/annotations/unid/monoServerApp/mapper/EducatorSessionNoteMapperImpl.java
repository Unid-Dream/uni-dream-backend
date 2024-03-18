package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteItemPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNotePojo;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNote;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteMap;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.EducatorSessionCommentItemPayload;
import unid.monoServerMeta.api.EducatorSessionCommentResponse;
import unid.monoServerMeta.api.EducatorSessionNoteItemPayload;
import unid.monoServerMeta.api.EducatorSessionNoteRequest;
import unid.monoServerMeta.api.EducatorSessionNoteResponse;
import unid.monoServerMeta.model.I18n;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T20:54:48+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EducatorSessionNoteMapperImpl implements EducatorSessionNoteMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public EducatorSessionNotePojo toPojo(EducatorSessionNoteResponse data) {
        if ( data == null ) {
            return null;
        }

        EducatorSessionNotePojo educatorSessionNotePojo = new EducatorSessionNotePojo();

        educatorSessionNotePojo.setId( data.getId() );
        educatorSessionNotePojo.setObsoleted( data.getObsoleted() );
        educatorSessionNotePojo.setMandatory( data.getMandatory() );

        return educatorSessionNotePojo;
    }

    @Override
    public void merge(EducatorSessionNotePojo data, EducatorSessionNoteRequest source) {
        if ( source == null ) {
            return;
        }

        data.setMandatory( source.getMandatory() );
    }

    @Override
    public void merge(DbEducatorSessionNote.Result data, EducatorSessionNoteRequest source) {
        if ( source == null ) {
            return;
        }

        data.setMandatory( source.getMandatory() );
        if ( source.getTitleI18n() != null ) {
            if ( data.getTitleI18n() == null ) {
                data.setTitleI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getTitleI18n(), source.getTitleI18n() );
        }
        else {
            data.setTitleI18n( null );
        }
        if ( source.getSubTitleI18n() != null ) {
            if ( data.getSubTitleI18n() == null ) {
                data.setSubTitleI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getSubTitleI18n(), source.getSubTitleI18n() );
        }
        else {
            data.setSubTitleI18n( null );
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
        if ( data.getItems() != null ) {
            List<DbEducatorSessionNoteItem.Result> list = educatorSessionNoteItemPayloadListToResultList( source.getItems() );
            if ( list != null ) {
                data.getItems().clear();
                data.getItems().addAll( list );
            }
            else {
                data.setItems( null );
            }
        }
        else {
            List<DbEducatorSessionNoteItem.Result> list = educatorSessionNoteItemPayloadListToResultList( source.getItems() );
            if ( list != null ) {
                data.setItems( list );
            }
        }
    }

    @Override
    public void merge(EducatorSessionNoteItemPojo data, EducatorSessionNoteItemPayload source) {
        if ( source == null ) {
            return;
        }

        data.setId( source.getId() );
        data.setOrder( source.getOrder() );
        data.setMandatory( source.getMandatory() );
    }

    @Override
    public EducatorSessionNoteResponse toResponse(DbEducatorSessionNote.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorSessionNoteResponse educatorSessionNoteResponse = new EducatorSessionNoteResponse();

        educatorSessionNoteResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        educatorSessionNoteResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        educatorSessionNoteResponse.setId( data.getId() );
        educatorSessionNoteResponse.setTitleI18n( i18nMapper.toModel( data.getTitleI18n() ) );
        educatorSessionNoteResponse.setSubTitleI18n( i18nMapper.toModel( data.getSubTitleI18n() ) );
        educatorSessionNoteResponse.setDescriptionI18n( i18nMapper.toModel( data.getDescriptionI18n() ) );
        educatorSessionNoteResponse.setMandatory( data.getMandatory() );
        educatorSessionNoteResponse.setObsoleted( data.getObsoleted() );
        educatorSessionNoteResponse.setItems( resultListToEducatorSessionNoteItemPayloadList( data.getItems() ) );

        return educatorSessionNoteResponse;
    }

    @Override
    public List<EducatorSessionNoteResponse> toResponse(List<DbEducatorSessionNote.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<EducatorSessionNoteResponse> list = new ArrayList<EducatorSessionNoteResponse>( data.size() );
        for ( DbEducatorSessionNote.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    @Override
    public EducatorSessionNoteResponse toResponse(DbEducatorSessionNoteMap.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorSessionNoteResponse educatorSessionNoteResponse = new EducatorSessionNoteResponse();

        educatorSessionNoteResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        educatorSessionNoteResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        educatorSessionNoteResponse.setId( data.getId() );

        return educatorSessionNoteResponse;
    }

    @Override
    public EducatorSessionCommentResponse toCommentResponse(DbEducatorSessionNoteMap.Map data) {
        if ( data == null ) {
            return null;
        }

        EducatorSessionCommentResponse educatorSessionCommentResponse = new EducatorSessionCommentResponse();

        educatorSessionCommentResponse.setSubject( toResponse( data.getEducatorSessionNote() ) );
        educatorSessionCommentResponse.setItems( resultListToEducatorSessionCommentItemPayloadList( data.getItems() ) );

        return educatorSessionCommentResponse;
    }

    @Override
    public List<EducatorSessionCommentResponse> toCommentResponse(List<DbEducatorSessionNoteMap.Map> data) {
        if ( data == null ) {
            return null;
        }

        List<EducatorSessionCommentResponse> list = new ArrayList<EducatorSessionCommentResponse>( data.size() );
        for ( DbEducatorSessionNoteMap.Map map : data ) {
            list.add( toCommentResponse( map ) );
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

    protected DbEducatorSessionNoteItem.Result educatorSessionNoteItemPayloadToResult(EducatorSessionNoteItemPayload educatorSessionNoteItemPayload) {
        if ( educatorSessionNoteItemPayload == null ) {
            return null;
        }

        DbEducatorSessionNoteItem.Result result = new DbEducatorSessionNoteItem.Result();

        result.setId( educatorSessionNoteItemPayload.getId() );
        result.setOrder( educatorSessionNoteItemPayload.getOrder() );
        result.setMandatory( educatorSessionNoteItemPayload.getMandatory() );
        result.setTitleI18n( i18nToResult( educatorSessionNoteItemPayload.getTitleI18n() ) );

        return result;
    }

    protected List<DbEducatorSessionNoteItem.Result> educatorSessionNoteItemPayloadListToResultList(List<EducatorSessionNoteItemPayload> list) {
        if ( list == null ) {
            return null;
        }

        List<DbEducatorSessionNoteItem.Result> list1 = new ArrayList<DbEducatorSessionNoteItem.Result>( list.size() );
        for ( EducatorSessionNoteItemPayload educatorSessionNoteItemPayload : list ) {
            list1.add( educatorSessionNoteItemPayloadToResult( educatorSessionNoteItemPayload ) );
        }

        return list1;
    }

    protected EducatorSessionNoteItemPayload resultToEducatorSessionNoteItemPayload(DbEducatorSessionNoteItem.Result result) {
        if ( result == null ) {
            return null;
        }

        EducatorSessionNoteItemPayload educatorSessionNoteItemPayload = new EducatorSessionNoteItemPayload();

        educatorSessionNoteItemPayload.setId( result.getId() );
        educatorSessionNoteItemPayload.setOrder( result.getOrder() );
        educatorSessionNoteItemPayload.setTitleI18n( i18nMapper.toModel( result.getTitleI18n() ) );
        educatorSessionNoteItemPayload.setMandatory( result.getMandatory() );

        return educatorSessionNoteItemPayload;
    }

    protected List<EducatorSessionNoteItemPayload> resultListToEducatorSessionNoteItemPayloadList(List<DbEducatorSessionNoteItem.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<EducatorSessionNoteItemPayload> list1 = new ArrayList<EducatorSessionNoteItemPayload>( list.size() );
        for ( DbEducatorSessionNoteItem.Result result : list ) {
            list1.add( resultToEducatorSessionNoteItemPayload( result ) );
        }

        return list1;
    }

    protected EducatorSessionCommentItemPayload resultToEducatorSessionCommentItemPayload(DbEducatorSessionNoteItem.Result result) {
        if ( result == null ) {
            return null;
        }

        EducatorSessionCommentItemPayload educatorSessionCommentItemPayload = new EducatorSessionCommentItemPayload();

        return educatorSessionCommentItemPayload;
    }

    protected List<EducatorSessionCommentItemPayload> resultListToEducatorSessionCommentItemPayloadList(List<DbEducatorSessionNoteItem.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<EducatorSessionCommentItemPayload> list1 = new ArrayList<EducatorSessionCommentItemPayload>( list.size() );
        for ( DbEducatorSessionNoteItem.Result result : list ) {
            list1.add( resultToEducatorSessionCommentItemPayload( result ) );
        }

        return list1;
    }
}
