package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerMeta.model.JwtTokenClaims;

@Mapper(
        componentModel = "spring"
)
public interface UserPayloadMapper {
    RequestHolder.UserSessionPayload toPayload(JwtTokenClaims data);
}
