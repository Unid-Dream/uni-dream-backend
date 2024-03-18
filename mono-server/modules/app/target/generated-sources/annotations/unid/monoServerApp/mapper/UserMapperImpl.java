package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.jooqMono.model.tables.records.UserRecord;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerMeta.api.AuthRegisterRequest;
import unid.monoServerMeta.api.UserRequest;
import unid.monoServerMeta.api.UserResponse;
import unid.monoServerMeta.model.UserRole;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T11:21:34+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public UserPojo toPojo(AuthRegisterRequest data) {
        if ( data == null ) {
            return null;
        }

        UserPojo userPojo = new UserPojo();

        userPojo.setUserRole( userRoleToUserRoleEnum( data.getUserRole() ) );
        userPojo.setLoginPassword( data.getLoginPassword() );
        userPojo.setEmail( data.getEmail() );

        return userPojo;
    }

    @Override
    public UserPojo toPojo(UserRecord data) {
        if ( data == null ) {
            return null;
        }

        UserPojo userPojo = new UserPojo();

        userPojo.setId( data.getId() );
        userPojo.setLastNameI18nId( data.getLastNameI18nId() );
        userPojo.setFistNameI18nId( data.getFistNameI18nId() );
        userPojo.setUserRole( data.getUserRole() );
        userPojo.setLoginId( data.getLoginId() );
        userPojo.setLoginPassword( data.getLoginPassword() );
        userPojo.setEmail( data.getEmail() );
        userPojo.setEmailVerified( data.getEmailVerified() );
        userPojo.setGoogleClientId( data.getGoogleClientId() );
        userPojo.setTencentWechatClientId( data.getTencentWechatClientId() );
        userPojo.setDeleted( data.getDeleted() );
        userPojo.setCreatedOn( data.getCreatedOn() );
        userPojo.setCreatedBy( data.getCreatedBy() );
        userPojo.setUpdatedOn( data.getUpdatedOn() );
        userPojo.setUpdatedBy( data.getUpdatedBy() );

        return userPojo;
    }

    @Override
    public void merge(UserPojo target, UserRequest source) {
        if ( source == null ) {
            return;
        }

        target.setUserRole( userRoleToUserRoleEnum( source.getUserRole() ) );
    }

    @Override
    public UserResponse toResponse(DbUser.Result data) {
        if ( data == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        userResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        userResponse.setId( data.getId() );
        userResponse.setLastNameI18n( i18nMapper.toModel( data.getLastNameI18n() ) );
        userResponse.setFirstNameI18n( i18nMapper.toModel( data.getFirstNameI18n() ) );
        userResponse.setUserRole( userRoleEnumToUserRole( data.getUserRole() ) );

        return userResponse;
    }

    @Override
    public List<UserResponse> toResponse(List<DbUser.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( data.size() );
        for ( DbUser.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
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
