package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.CityPojo;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerMeta.api.CityRequest;
import unid.monoServerMeta.api.CityResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                CountryMapper.class
        }
)
public interface CityMapper {

    CityPojo toPojo(CityResponse data);

    void merge(@MappingTarget DbCity.Result data, CityRequest source);

    @Mappings({
            @Mapping(source = DbCity.Result.Columns.createdOn, target = CityResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbCity.Result.Columns.updatedOn, target = CityResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    CityResponse toResponse(DbCity.Result data);

    @Mappings({
            @Mapping(source = DbCity.Result.Columns.createdOn, target = CityResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbCity.Result.Columns.updatedOn, target = CityResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<CityResponse> toResponse(List<DbCity.Result> data);
}
