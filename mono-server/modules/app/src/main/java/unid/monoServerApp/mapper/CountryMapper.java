package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.CountryPojo;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerMeta.api.CountryRequest;
import unid.monoServerMeta.api.CountryResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class
        }
)
public interface CountryMapper {

    CountryPojo toPojo(CountryResponse data);

    void merge(@MappingTarget DbCountry.Result data, CountryRequest source);

//    @Mappings({
//            @Mapping(source = DbCountry.Result.Columns.createdOn, target = CountryResponse.BaseResponseFields.createdOnUtc),
//            @Mapping(source = DbCountry.Result.Columns.updatedOn, target = CountryResponse.BaseResponseFields.updatedOnUtc)
//    })
    @InheritConfiguration
    CountryResponse toResponse(DbCountry.Result data);

//    @Mappings({
//            @Mapping(source = DbCountry.Result.Columns.createdOn, target = CountryResponse.BaseResponseFields.createdOnUtc),
//            @Mapping(source = DbCountry.Result.Columns.updatedOn, target = CountryResponse.BaseResponseFields.updatedOnUtc)
//    })
    @InheritConfiguration
    List<CountryResponse> toResponse(List<DbCountry.Result> data);
}
