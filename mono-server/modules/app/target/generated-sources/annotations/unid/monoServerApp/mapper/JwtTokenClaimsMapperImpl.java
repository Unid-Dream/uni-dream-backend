package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerMeta.model.JwtTokenClaims;
import unid.monoServerMeta.model.UserRole;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T01:03:57+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class JwtTokenClaimsMapperImpl implements JwtTokenClaimsMapper {

    @Override
    public JwtTokenClaims toClaims(UserPojo data) {
        if ( data == null ) {
            return null;
        }

        JwtTokenClaims.JwtTokenClaimsBuilder<?, ?> jwtTokenClaims = JwtTokenClaims.builder();

        jwtTokenClaims.userId( data.getId() );
        jwtTokenClaims.email( data.getEmail() );
        jwtTokenClaims.userRole( userRoleEnumToUserRole( data.getUserRole() ) );

        return jwtTokenClaims.build();
    }

    protected UserRole userRoleEnumToUserRole(UserRoleEnum userRoleEnum) {
        if ( userRoleEnum == null ) {
            return null;
        }

        UserRole userRole;

        switch ( userRoleEnum ) {
            case ROOT: userRole = UserRole.ROOT;
            break;
            case ADMIN: userRole = UserRole.ADMIN;
            break;
            case EDUCATOR: userRole = UserRole.EDUCATOR;
            break;
            case STUDENT: userRole = UserRole.STUDENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + userRoleEnum );
        }

        return userRole;
    }
}
