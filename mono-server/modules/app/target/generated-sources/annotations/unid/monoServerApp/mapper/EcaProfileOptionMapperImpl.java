package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileOption;
import unid.monoServerMeta.api.EcaProfileOptionResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T19:46:58+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EcaProfileOptionMapperImpl implements EcaProfileOptionMapper {

    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public EcaProfileOptionResponse toResponse(DbEcaProfileOption.Result data) {
        if ( data == null ) {
            return null;
        }

        EcaProfileOptionResponse ecaProfileOptionResponse = new EcaProfileOptionResponse();

        ecaProfileOptionResponse.setId( data.getId() );
        ecaProfileOptionResponse.setOptionI18n( i18nMapper.toModel( data.getOptionI18n() ) );

        return ecaProfileOptionResponse;
    }

    @Override
    public List<EcaProfileOptionResponse> toResponse(List<DbEcaProfileOption.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<EcaProfileOptionResponse> list1 = new ArrayList<EcaProfileOptionResponse>( list.size() );
        for ( DbEcaProfileOption.Result result : list ) {
            list1.add( toResponse( result ) );
        }

        return list1;
    }
}
