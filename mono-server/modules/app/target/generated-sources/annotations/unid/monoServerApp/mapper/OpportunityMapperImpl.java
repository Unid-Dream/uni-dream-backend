package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.opportunity.DbOpportunity;
import unid.monoServerMeta.api.OpportunityResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-27T22:36:02+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class OpportunityMapperImpl implements OpportunityMapper {

    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public OpportunityResponse toResponse(DbOpportunity.Result data) {
        if ( data == null ) {
            return null;
        }

        OpportunityResponse opportunityResponse = new OpportunityResponse();

        opportunityResponse.setTitleI18n( i18nMapper.toModel( data.getTitleI18n() ) );
        opportunityResponse.setId( data.getId() );

        return opportunityResponse;
    }

    @Override
    public List<OpportunityResponse> toResponse(List<DbOpportunity.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<OpportunityResponse> list = new ArrayList<OpportunityResponse>( data.size() );
        for ( DbOpportunity.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
