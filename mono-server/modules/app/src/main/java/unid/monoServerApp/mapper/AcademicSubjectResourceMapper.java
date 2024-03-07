package unid.monoServerApp.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import unid.jooqMono.model.tables.pojos.AcademicSubjectResourcePojo;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
        }
)
public interface AcademicSubjectResourceMapper {

    AcademicSubjectResourcePojo toPojo(AcademicSubjectResourcePayload data);

    @InheritConfiguration
    AcademicSubjectResourcePayload toResponse(DbAcademicSubjectResource.Result data);

    @InheritConfiguration
    List<AcademicSubjectResourcePayload> toResponse(List<DbAcademicSubjectResource.Result> data);
}
