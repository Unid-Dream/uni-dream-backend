package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.LanguagePojo;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerMeta.api.LanguageRequest;
import unid.monoServerMeta.api.LanguageResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class
        }
)
public interface LanguageMapper {

    LanguagePojo toPojo(LanguageResponse data);

    void merge(@MappingTarget LanguagePojo data, LanguageRequest source);

    void merge(@MappingTarget DbLanguage.Result data, LanguageRequest source);

    @Mappings({
            @Mapping(source = DbLanguage.Result.Columns.createdOn, target = LanguageResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbLanguage.Result.Columns.updatedOn, target = LanguageResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    LanguageResponse toResponse(DbLanguage.Result data);

    @Mappings({
            @Mapping(source = DbLanguage.Result.Columns.createdOn, target = LanguageResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbLanguage.Result.Columns.updatedOn, target = LanguageResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<LanguageResponse> toResponse(List<DbLanguage.Result> data);
}
