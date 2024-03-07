package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.jooqMono.model.tables.pojos.SchoolExtensionPojo;
import unid.jooqMono.model.tables.pojos.SchoolPojo;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerMeta.api.CityResponse;
import unid.monoServerMeta.api.SchoolMapResponse;
import unid.monoServerMeta.api.SchoolRequest;
import unid.monoServerMeta.api.SchoolResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SchoolLevel;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                CityMapper.class,
                TagMapper.class
        }
)
public interface SchoolMapper {

    SchoolPojo toPojo(SchoolResponse data);

    void merge(@MappingTarget SchoolPojo data, SchoolRequest source);

    @Mappings({
            @Mapping(source = DbSchool.Result.Columns.createdOn, target = SchoolResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbSchool.Result.Columns.updatedOn, target = SchoolResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    SchoolResponse toResponse(DbSchool.Result data);

    @Mappings({
            @Mapping(source = DbSchool.Result.Columns.createdOn, target = SchoolResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbSchool.Result.Columns.updatedOn, target = SchoolResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<SchoolResponse> toResponse(List<DbSchool.Result> data);


    default SchoolResponse toResponse(SchoolPojo pojo,
                                         I18n nameI18n,
                                         CityResponse cityResponse,
                                         I18n tagI18n,
                                         SchoolExtensionPojo extension){
        SchoolResponse response = new SchoolResponse();
        response.setId(pojo.getId());
        response.setSchoolLevel(SchoolLevel.UNIVERSITY);
        response.setNameI18n(nameI18n);
        response.setCity(cityResponse);
//        response.(countryI18n);
//        response.setTag(tagI18n);
//        response.setLatitude(pojo.getLatitude());
//        response.setLongitude(pojo.getLongitude());
//        response.setRate(extension.getRate());
//        response.setPopulation(extension.getPopulation());
//        response.setTuition(extension.getTuition());
//        response.setFactor(extension.getFactor());
        return response;
    }

}
