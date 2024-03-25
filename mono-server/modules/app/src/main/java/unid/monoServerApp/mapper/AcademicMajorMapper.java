package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.AcademicMajorPojo;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerMeta.api.AcademicMajorPayload;
import unid.monoServerMeta.api.AcademicMajorRequest;
import unid.monoServerMeta.api.AcademicMajorResponse;
import unid.monoServerMeta.api.version2.request.AcademicMajorCreateRequest;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class,
                AcademicSubjectMapper.class
        }
)
public interface AcademicMajorMapper {

    AcademicMajorPojo toPojo(AcademicMajorResponse data);


    @Mapping(target = AcademicMajorRequest.Fields.subjects, ignore = true)
    void merge(@MappingTarget DbAcademicMajor.Result data, AcademicMajorRequest source);

    void merge(@MappingTarget AcademicMajorPojo data, AcademicMajorRequest source);

    void merge(@MappingTarget AcademicMajorPojo data, AcademicMajorPayload source);
    void merge(@MappingTarget AcademicMajorPojo data, AcademicMajorCreateRequest source);


    AcademicMajorPayload toResponse(AcademicMajorPojo data);


    @Mappings({
            @Mapping(source = DbAcademicMajor.Result.Columns.createdOn, target = AcademicMajorResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbAcademicMajor.Result.Columns.updatedOn, target = AcademicMajorResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    AcademicMajorResponse toResponse(DbAcademicMajor.Result data);

    @Mappings({
            @Mapping(source = DbAcademicMajor.Result.Columns.createdOn, target = AcademicMajorResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbAcademicMajor.Result.Columns.updatedOn, target = AcademicMajorResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<AcademicMajorResponse> toResponse(List<DbAcademicMajor.Result> data);
}
