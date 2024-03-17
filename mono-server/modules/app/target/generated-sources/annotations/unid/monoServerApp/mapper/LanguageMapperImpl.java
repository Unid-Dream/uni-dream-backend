package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.LanguagePojo;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerMeta.api.LanguageRequest;
import unid.monoServerMeta.api.LanguageResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-17T21:01:51+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class LanguageMapperImpl implements LanguageMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public LanguagePojo toPojo(LanguageResponse data) {
        if ( data == null ) {
            return null;
        }

        LanguagePojo languagePojo = new LanguagePojo();

        languagePojo.setId( data.getId() );

        return languagePojo;
    }

    @Override
    public void merge(LanguagePojo data, LanguageRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagId( source.getTagId() );
    }

    @Override
    public void merge(DbLanguage.Result data, LanguageRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagId( source.getTagId() );
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
    public LanguageResponse toResponse(DbLanguage.Result data) {
        if ( data == null ) {
            return null;
        }

        LanguageResponse languageResponse = new LanguageResponse();

        languageResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        languageResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        languageResponse.setId( data.getId() );
        languageResponse.setDescriptionI18n( i18nMapper.toModel( data.getDescriptionI18n() ) );
        languageResponse.setTag( tagMapper.toResponse( data.getTag() ) );

        return languageResponse;
    }

    @Override
    public List<LanguageResponse> toResponse(List<DbLanguage.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<LanguageResponse> list = new ArrayList<LanguageResponse>( data.size() );
        for ( DbLanguage.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
