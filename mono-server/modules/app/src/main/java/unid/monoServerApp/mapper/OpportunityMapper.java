package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import unid.monoServerApp.database.table.opportunity.DbOpportunity;
import unid.monoServerMeta.api.OpportunityResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
        }
)
public interface OpportunityMapper {

        OpportunityResponse toResponse(DbOpportunity.Result data);

        List<OpportunityResponse> toResponse(List<DbOpportunity.Result> data);

}
