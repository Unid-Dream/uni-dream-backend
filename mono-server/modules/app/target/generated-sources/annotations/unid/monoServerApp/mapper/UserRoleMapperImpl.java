package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerMeta.model.UserRole;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T20:54:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class UserRoleMapperImpl implements UserRoleMapper {

    @Override
    public UserRoleEnum toEnum(UserRole data) {
        if ( data == null ) {
            return null;
        }

        UserRoleEnum userRoleEnum;

        switch ( data ) {
            case ROOT: userRoleEnum = UserRoleEnum.ROOT;
            break;
            case ADMIN: userRoleEnum = UserRoleEnum.ADMIN;
            break;
            case EDUCATOR: userRoleEnum = UserRoleEnum.EDUCATOR;
            break;
            case STUDENT: userRoleEnum = UserRoleEnum.STUDENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return userRoleEnum;
    }

    @Override
    public UserRole toPojo(UserRoleEnum data) {
        if ( data == null ) {
            return null;
        }

        UserRole userRole;

        switch ( data ) {
            case ROOT: userRole = UserRole.ROOT;
            break;
            case ADMIN: userRole = UserRole.ADMIN;
            break;
            case EDUCATOR: userRole = UserRole.EDUCATOR;
            break;
            case STUDENT: userRole = UserRole.STUDENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return userRole;
    }
}
