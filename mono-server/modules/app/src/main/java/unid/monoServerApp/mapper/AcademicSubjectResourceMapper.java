package unid.monoServerApp.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.AcademicSubjectResourcePojo;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerMeta.api.AcademicSubjectPayload;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;
import unid.monoServerMeta.api.AcademicSubjectResponse;

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

    AcademicSubjectResourcePojo toPojo(AcademicSubjectPayload.AcademicSubjectResourcePayload data);

    @InheritConfiguration
    @Mappings({
            @Mapping(source = DbAcademicSubjectResource.Result.Fields.authorI18n, target = AcademicSubjectResourcePayload.Fields.authorI18n),
            @Mapping(source = AcademicSubjectResourcePojo.Columns.thumbnail, target = AcademicSubjectResourcePayload.Fields.image),
            @Mapping(source = AcademicSubjectResourcePojo.Columns.url, target = AcademicSubjectResourcePayload.Fields.url),
            @Mapping(source = AcademicSubjectResourcePojo.Columns.type, target = AcademicSubjectResourcePayload.Fields.type)
    })
    AcademicSubjectResourcePayload toResponse(DbAcademicSubjectResource.Result data);

    @InheritConfiguration
    List<AcademicSubjectResourcePayload> toResponse(List<DbAcademicSubjectResource.Result> data);
}
