package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.AcademicSubjectPojo;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerMeta.api.AcademicSubjectRequest;
import unid.monoServerMeta.api.AcademicSubjectResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class,
                AcademicSubjectResourceMapper.class
        }
)
public interface AcademicSubjectMapper {

    AcademicSubjectPojo toPojo(AcademicSubjectResponse data);

    void merge(@MappingTarget DbAcademicSubject.Result data, AcademicSubjectRequest source);

    void merge(@MappingTarget AcademicSubjectPojo data, AcademicSubjectRequest source);

    @Mappings({
            @Mapping(source = DbAcademicSubject.Result.Columns.createdOn, target = AcademicSubjectResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbAcademicSubject.Result.Columns.updatedOn, target = AcademicSubjectResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    AcademicSubjectResponse toResponse(DbAcademicSubject.Result data);

    @Mappings({
            @Mapping(source = DbAcademicSubject.Result.Columns.createdOn, target = AcademicSubjectResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbAcademicSubject.Result.Columns.updatedOn, target = AcademicSubjectResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<AcademicSubjectResponse> toResponse(List<DbAcademicSubject.Result> data);
}
