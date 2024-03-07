package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerMeta.model.UserRole;

@Mapper(
        componentModel = "spring"
)
public interface UserRoleMapper {
    UserRoleEnum toEnum(UserRole data);

    UserRole toPojo(UserRoleEnum data);
}
