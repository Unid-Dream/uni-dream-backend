package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.CountryPojo;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.CountryRequest;
import unid.monoServerMeta.api.CountryResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T21:35:09+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public CountryPojo toPojo(CountryResponse data) {
        if ( data == null ) {
            return null;
        }

        CountryPojo countryPojo = new CountryPojo();

        countryPojo.setId( data.getId() );

        return countryPojo;
    }

    @Override
    public void merge(DbCountry.Result data, CountryRequest source) {
        if ( source == null ) {
            return;
        }

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
    public CountryResponse toResponse(DbCountry.Result data) {
        if ( data == null ) {
            return null;
        }

        CountryResponse countryResponse = new CountryResponse();

        countryResponse.setId( data.getId() );
        countryResponse.setNameI18n( i18nMapper.toModel( data.getNameI18n() ) );
        countryResponse.setTag( tagMapper.toResponse( data.getTag() ) );

        return countryResponse;
    }

    @Override
    public List<CountryResponse> toResponse(List<DbCountry.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<CountryResponse> list = new ArrayList<CountryResponse>( data.size() );
        for ( DbCountry.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
