package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.SchoolIdentityPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.schoolIdentity.DbSchoolIdentity;
import unid.monoServerMeta.api.SchoolIdentityRequest;
import unid.monoServerMeta.api.SchoolIdentityResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-28T23:20:22+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class SchoolIdentityMapperImpl implements SchoolIdentityMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public SchoolIdentityPojo toPojo(SchoolIdentityResponse data) {
        if ( data == null ) {
            return null;
        }

        SchoolIdentityPojo schoolIdentityPojo = new SchoolIdentityPojo();

        schoolIdentityPojo.setId( data.getId() );

        return schoolIdentityPojo;
    }

    @Override
    public void merge(SchoolIdentityPojo data, SchoolIdentityRequest source) {
        if ( source == null ) {
            return;
        }
    }

    @Override
    public void merge(DbSchoolIdentity.Result data, SchoolIdentityRequest source) {
        if ( source == null ) {
            return;
        }

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
    public SchoolIdentityResponse toResponse(DbSchoolIdentity.Result data) {
        if ( data == null ) {
            return null;
        }

        SchoolIdentityResponse schoolIdentityResponse = new SchoolIdentityResponse();

        schoolIdentityResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        schoolIdentityResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        schoolIdentityResponse.setId( data.getId() );
        schoolIdentityResponse.setNameI18n( i18nMapper.toModel( data.getNameI18n() ) );

        return schoolIdentityResponse;
    }

    @Override
    public List<SchoolIdentityResponse> toResponse(List<DbSchoolIdentity.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<SchoolIdentityResponse> list = new ArrayList<SchoolIdentityResponse>( data.size() );
        for ( DbSchoolIdentity.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
