package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerMeta.model.JwtTokenClaims;
import unid.monoServerMeta.model.UserRole;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-14T23:21:02+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class UserPayloadMapperImpl implements UserPayloadMapper {

    @Override
    public RequestHolder.UserSessionPayload toPayload(JwtTokenClaims data) {
        if ( data == null ) {
            return null;
        }

        RequestHolder.UserSessionPayload.UserSessionPayloadBuilder<?, ?> userSessionPayload = RequestHolder.UserSessionPayload.builder();

        userSessionPayload.userId( data.getUserId() );
        userSessionPayload.userRole( userRoleToUserRoleEnum( data.getUserRole() ) );
        userSessionPayload.userProfileId( data.getUserProfileId() );

        return userSessionPayload.build();
    }

    protected UserRoleEnum userRoleToUserRoleEnum(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        UserRoleEnum userRoleEnum;

        switch ( userRole ) {
            case ROOT: userRoleEnum = UserRoleEnum.ROOT;
            break;
            case ADMIN: userRoleEnum = UserRoleEnum.ADMIN;
            break;
            case EDUCATOR: userRoleEnum = UserRoleEnum.EDUCATOR;
            break;
            case STUDENT: userRoleEnum = UserRoleEnum.STUDENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + userRole );
        }

        return userRoleEnum;
    }
}
