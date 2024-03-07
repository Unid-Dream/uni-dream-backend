package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerMeta.model.JwtTokenClaims;

@Mapper(
        componentModel = "spring"
)
public interface JwtTokenClaimsMapper {

    @Mappings({
            @Mapping(source = UserPojo.Columns.id, target = JwtTokenClaims.Fields.userId),
            @Mapping(source = UserPojo.Columns.email, target = JwtTokenClaims.Fields.email)
    })
    JwtTokenClaims toClaims(UserPojo data);
}
