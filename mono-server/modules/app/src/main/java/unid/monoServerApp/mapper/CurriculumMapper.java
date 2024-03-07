package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.CurriculumPojo;
import unid.monoServerApp.database.table.curriculum.DbCurriculum;
import unid.monoServerMeta.api.CurriculumRequest;
import unid.monoServerMeta.api.CurriculumResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class
        }
)
public interface CurriculumMapper {

    CurriculumPojo toPojo(CurriculumResponse data);

    void merge(@MappingTarget CurriculumPojo data, CurriculumRequest source);

    void merge(@MappingTarget DbCurriculum.Result data, CurriculumRequest source);

    @Mappings({
            @Mapping(source = DbCurriculum.Result.Columns.createdOn, target = CurriculumResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbCurriculum.Result.Columns.updatedOn, target = CurriculumResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    CurriculumResponse toResponse(DbCurriculum.Result data);

    @Mappings({
            @Mapping(source = DbCurriculum.Result.Columns.createdOn, target = CurriculumResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbCurriculum.Result.Columns.updatedOn, target = CurriculumResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<CurriculumResponse> toResponse(List<DbCurriculum.Result> data);
}
