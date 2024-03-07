package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.ExpertisePojo;
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerMeta.api.ExpertiseRequest;
import unid.monoServerMeta.api.ExpertiseResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class,
                AcademicMajorMapper.class
        }
)
public interface ExpertiseMapper {

    ExpertisePojo toPojo(ExpertiseResponse data);

    @Mapping(target = ExpertiseRequest.Fields.majors, ignore = true)
    void merge(@MappingTarget DbExpertise.Result data, ExpertiseRequest source);

    void merge(@MappingTarget ExpertisePojo data, ExpertiseRequest source);

    @Mappings({
            @Mapping(source = DbExpertise.Result.Columns.createdOn, target = ExpertiseResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbExpertise.Result.Columns.updatedOn, target = ExpertiseResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    ExpertiseResponse toResponse(DbExpertise.Result data);

    @Mappings({
            @Mapping(source = DbExpertise.Result.Columns.createdOn, target = ExpertiseResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbExpertise.Result.Columns.updatedOn, target = ExpertiseResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<ExpertiseResponse> toResponse(List<DbExpertise.Result> data);
}
