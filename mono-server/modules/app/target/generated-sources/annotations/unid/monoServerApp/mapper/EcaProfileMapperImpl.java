package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileSection;
import unid.monoServerMeta.api.EcaProfileSectionResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-17T12:42:52+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EcaProfileMapperImpl implements EcaProfileMapper {

    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private EcaProfileOptionMapper ecaProfileOptionMapper;

    @Override
    public EcaProfileSectionResponse toResponse(DbEcaProfileSection.Result data) {
        if ( data == null ) {
            return null;
        }

        EcaProfileSectionResponse ecaProfileSectionResponse = new EcaProfileSectionResponse();

        ecaProfileSectionResponse.setId( data.getId() );
        ecaProfileSectionResponse.setSection( i18nMapper.toModel( data.getSection() ) );
        ecaProfileSectionResponse.setOptions( ecaProfileOptionMapper.toResponse( data.getOptions() ) );

        return ecaProfileSectionResponse;
    }

    @Override
    public List<EcaProfileSectionResponse> toResponse(List<DbEcaProfileSection.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<EcaProfileSectionResponse> list1 = new ArrayList<EcaProfileSectionResponse>( list.size() );
        for ( DbEcaProfileSection.Result result : list ) {
            list1.add( toResponse( result ) );
        }

        return list1;
    }
}
