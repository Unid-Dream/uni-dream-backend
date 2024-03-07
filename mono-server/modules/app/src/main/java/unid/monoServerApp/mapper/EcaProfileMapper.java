package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducationLevelPojo;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileOption;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileSection;
import unid.monoServerApp.database.table.ecaProfile.DbStudentEcaProfileMap;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerMeta.api.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                EcaProfileOptionMapper.class
        }
)
public interface EcaProfileMapper {


    @InheritConfiguration
    EcaProfileSectionResponse toResponse(DbEcaProfileSection.Result data);

    List<EcaProfileSectionResponse> toResponse(List<DbEcaProfileSection.Result> list);



}
