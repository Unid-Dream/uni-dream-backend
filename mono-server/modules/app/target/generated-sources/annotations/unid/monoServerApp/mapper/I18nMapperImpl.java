package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.monoServerMeta.model.I18n;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T20:54:48+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class I18nMapperImpl implements I18nMapper {

    @Override
    public I18nPojo toPojo(I18n data) {
        if ( data == null ) {
            return null;
        }

        I18nPojo i18nPojo = new I18nPojo();

        i18nPojo.setEnglish( data.getEnglish() );
        i18nPojo.setChineseTraditional( data.getChineseTraditional() );
        i18nPojo.setChineseSimplified( data.getChineseSimplified() );

        return i18nPojo;
    }

    @Override
    public I18n toModel(I18nPojo data) {
        if ( data == null ) {
            return null;
        }

        I18n.I18nBuilder<?, ?> i18n = I18n.builder();

        i18n.english( data.getEnglish() );
        i18n.chineseTraditional( data.getChineseTraditional() );
        i18n.chineseSimplified( data.getChineseSimplified() );

        return i18n.build();
    }

    @Override
    public void merge(I18nPojo data, I18n source) {
        if ( source == null ) {
            return;
        }

        data.setEnglish( source.getEnglish() );
        data.setChineseTraditional( source.getChineseTraditional() );
        data.setChineseSimplified( source.getChineseSimplified() );
    }
}
