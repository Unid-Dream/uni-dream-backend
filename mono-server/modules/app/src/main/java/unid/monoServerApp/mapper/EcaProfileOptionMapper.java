package unid.monoServerApp.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileOption;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileSection;
import unid.monoServerMeta.api.EcaProfileOptionResponse;
import unid.monoServerMeta.api.EcaProfileSectionResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class
        }
)
public interface EcaProfileOptionMapper {

    @InheritConfiguration
    EcaProfileOptionResponse toResponse(DbEcaProfileOption.Result data);
    List<EcaProfileOptionResponse> toResponse(List<DbEcaProfileOption.Result> list);


}
