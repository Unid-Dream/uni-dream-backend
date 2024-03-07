package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducationLevelPojo;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerMeta.api.EducationLevelRequest;
import unid.monoServerMeta.api.EducationLevelResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class
        }
)
public interface EducationLevelMapper {

    EducationLevelPojo toPojo(EducationLevelResponse data);

    void merge(@MappingTarget EducationLevelPojo data, EducationLevelRequest source);

    void merge(@MappingTarget DbEducationLevel.Result data, EducationLevelRequest source);

    @Mappings({
            @Mapping(source = DbEducationLevel.Result.Columns.createdOn, target = EducationLevelResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducationLevel.Result.Columns.updatedOn, target = EducationLevelResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    EducationLevelResponse toResponse(DbEducationLevel.Result data);

    @Mappings({
            @Mapping(source = DbEducationLevel.Result.Columns.createdOn, target = EducationLevelResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducationLevel.Result.Columns.updatedOn, target = EducationLevelResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<EducationLevelResponse> toResponse(List<DbEducationLevel.Result> data);
}
