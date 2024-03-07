package unid.monoServerApp.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileSection;
import unid.monoServerApp.database.table.ecaProfile.DbStudentEcaProfileMap;
import unid.monoServerMeta.api.EcaProfileSectionResponse;
import unid.monoServerMeta.api.StudentEcaProfileSectionResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class
        }
)
public interface StudentEcaProfileMapMapper {
    @Mappings({
            @Mapping(source = DbEcaProfileSection.Result.Fields.section,target = EcaProfileSectionResponse.Fields.section),
    })
    @InheritConfiguration
    StudentEcaProfileSectionResponse toResponse(DbStudentEcaProfileMap.Result data);

    List<StudentEcaProfileSectionResponse> toResponse(List<DbStudentEcaProfileMap.Result> list);


}
