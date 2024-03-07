package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.jooqMono.model.tables.records.UserRecord;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerMeta.api.AuthRegisterRequest;
import unid.monoServerMeta.api.UserRequest;
import unid.monoServerMeta.api.UserResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class
        }
)
public interface UserMapper {
    UserPojo toPojo(AuthRegisterRequest data);

    UserPojo toPojo(UserRecord data);

    void merge(@MappingTarget UserPojo target, UserRequest source);

    @Mappings({
            @Mapping(source = DbUser.Result.Columns.createdOn, target = UserResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbUser.Result.Columns.updatedOn, target = UserResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    UserResponse toResponse(DbUser.Result data);

    @Mappings({
            @Mapping(source = DbUser.Result.Columns.createdOn, target = UserResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbUser.Result.Columns.updatedOn, target = UserResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<UserResponse> toResponse(List<DbUser.Result> data);
}
