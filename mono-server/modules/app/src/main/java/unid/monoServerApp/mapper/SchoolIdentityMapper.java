package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.SchoolIdentityPojo;
import unid.monoServerApp.database.table.schoolIdentity.DbSchoolIdentity;
import unid.monoServerMeta.api.SchoolIdentityRequest;
import unid.monoServerMeta.api.SchoolIdentityResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class
        }
)
public interface SchoolIdentityMapper {

    SchoolIdentityPojo toPojo(SchoolIdentityResponse data);

    void merge(@MappingTarget SchoolIdentityPojo data, SchoolIdentityRequest source);

    void merge(@MappingTarget DbSchoolIdentity.Result data, SchoolIdentityRequest source);

    @Mappings({
            @Mapping(source = DbSchoolIdentity.Result.Columns.createdOn, target = SchoolIdentityResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbSchoolIdentity.Result.Columns.updatedOn, target = SchoolIdentityResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    SchoolIdentityResponse toResponse(DbSchoolIdentity.Result data);

    @Mappings({
            @Mapping(source = DbSchoolIdentity.Result.Columns.createdOn, target = SchoolIdentityResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbSchoolIdentity.Result.Columns.updatedOn, target = SchoolIdentityResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<SchoolIdentityResponse> toResponse(List<DbSchoolIdentity.Result> data);
}
