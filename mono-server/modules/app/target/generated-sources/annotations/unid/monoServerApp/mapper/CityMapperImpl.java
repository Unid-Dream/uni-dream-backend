package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.CityPojo;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.CityRequest;
import unid.monoServerMeta.api.CityResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T20:54:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private CountryMapper countryMapper;

    @Override
    public CityPojo toPojo(CityResponse data) {
        if ( data == null ) {
            return null;
        }

        CityPojo cityPojo = new CityPojo();

        cityPojo.setId( data.getId() );

        return cityPojo;
    }

    @Override
    public void merge(DbCity.Result data, CityRequest source) {
        if ( source == null ) {
            return;
        }

        data.setCountryId( source.getCountryId() );
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
    public CityResponse toResponse(DbCity.Result data) {
        if ( data == null ) {
            return null;
        }

        CityResponse cityResponse = new CityResponse();

        cityResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        cityResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        cityResponse.setId( data.getId() );
        cityResponse.setNameI18n( i18nMapper.toModel( data.getNameI18n() ) );
        cityResponse.setCountry( countryMapper.toResponse( data.getCountry() ) );

        return cityResponse;
    }

    @Override
    public List<CityResponse> toResponse(List<DbCity.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<CityResponse> list = new ArrayList<CityResponse>( data.size() );
        for ( DbCity.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
