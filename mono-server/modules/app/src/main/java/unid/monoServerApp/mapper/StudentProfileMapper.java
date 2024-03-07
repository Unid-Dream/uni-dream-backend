package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentProfileSchoolReportPojo;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfileSchoolReport;
import unid.monoServerMeta.api.StudentProfileRequest;
import unid.monoServerMeta.api.StudentProfileResponse;
import unid.monoServerMeta.api.StudentProfileSchoolReportPayload;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class,
                SchoolMapper.class,
                CountryMapper.class,
                CurriculumMapper.class,
                EducationLevelMapper.class
        }
)
public interface StudentProfileMapper {
    StudentProfileSchoolReportPojo toPojo(StudentProfileSchoolReportPayload data);

    StudentProfileSchoolReportPojo toPojo(DbStudentProfileSchoolReport.Result data);

    StudentProfilePojo toPojo(StudentProfileResponse data);

    StudentProfilePojo toPojo(DbStudentProfile.Result data);

    StudentProfilePojo toPojo(StudentProfileRequest data);

    void merge(@MappingTarget StudentProfilePojo target, StudentProfileRequest source);






    @Mappings({
            @Mapping(source = DbStudentProfile.Result.Columns.createdOn, target = StudentProfileResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbStudentProfile.Result.Columns.updatedOn, target = StudentProfileResponse.BaseResponseFields.updatedOnUtc),
            @Mapping(source = DbStudentProfile.Result.Columns.preferredUniversity_1Id, target = StudentProfileResponse.Fields.preferredUniversity_1Id),
            @Mapping(source = DbStudentProfile.Result.Columns.preferredUniversity_2Id, target = StudentProfileResponse.Fields.preferredUniversity_2Id),
            @Mapping(source = DbStudentProfile.Result.Columns.preferredUniversity_3Id, target = StudentProfileResponse.Fields.preferredUniversity_3Id),
            @Mapping(source = DbStudentProfile.Result.Columns.preferredOtherUniversityId, target = StudentProfileResponse.Fields.preferredOtherUniversityId)
    })
    @InheritConfiguration
    StudentProfileResponse toResponse(DbStudentProfile.Result data);

    @Mappings({
            @Mapping(source = DbStudentProfile.Result.Columns.createdOn, target = StudentProfileResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbStudentProfile.Result.Columns.updatedOn, target = StudentProfileResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<StudentProfileResponse> toResponse(List<DbStudentProfile.Result> data);
}
