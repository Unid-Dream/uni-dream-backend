package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerMeta.api.EducatorProfileRequest;
import unid.monoServerMeta.api.EducatorProfileResponse;
import unid.monoServerMeta.api.EducatorProfileUpdateRequest;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                CountryMapper.class,
                LanguageMapper.class,
                SchoolIdentityMapper.class,
                SchoolMapper.class,
                EducationLevelMapper.class,
                TagMapper.class
        }
)
public interface EducatorProfileMapper {

    EducatorProfilePojo toPojo(EducatorProfileRequest data);

    @Mappings({
            @Mapping(target = EducatorProfileRequest.Fields.languages, ignore = true),
            @Mapping(target = EducatorProfileRequest.Fields.expertises, ignore = true)
    })
    void merge(@MappingTarget DbEducatorProfile.Result data, EducatorProfileRequest source);

    void merge(@MappingTarget EducatorProfilePojo data, EducatorProfileRequest source);

    @Mappings({
            @Mapping(target = EducatorProfilePojo.Columns.expertiseId, source = EducatorProfileUpdateRequest.Fields.expertises),
            @Mapping(target = EducatorProfilePojo.Columns.academicMajorId, source = EducatorProfileUpdateRequest.Fields.subjects),
            @Mapping(target = EducatorProfilePojo.Columns.languageId, source = EducatorProfileUpdateRequest.Fields.languages),
            @Mapping(target = EducatorProfilePojo.Columns.hourlyRate, source = EducatorProfileUpdateRequest.Fields.hourly_rate),
            @Mapping(target = EducatorProfilePojo.Columns.description, source = EducatorProfileUpdateRequest.Fields.description),
            @Mapping(target = EducatorProfilePojo.Columns.profilePicture, source = EducatorProfileUpdateRequest.Fields.photo)
    })
    void merge(@MappingTarget EducatorProfilePojo data, EducatorProfileUpdateRequest source);


    @Mappings({
            @Mapping(source = DbEducatorProfile.Result.Columns.createdOn, target = EducatorProfileResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducatorProfile.Result.Columns.updatedOn, target = EducatorProfileResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    EducatorProfileResponse toResponse(DbEducatorProfile.Result data);

    @Mappings({
            @Mapping(source = DbEducatorProfile.Result.Columns.createdOn, target = EducatorProfileResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducatorProfile.Result.Columns.updatedOn, target = EducatorProfileResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<EducatorProfileResponse> toResponse(List<DbEducatorProfile.Result> data);
}
