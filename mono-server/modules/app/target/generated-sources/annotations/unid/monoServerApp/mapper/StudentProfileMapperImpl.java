package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.GenderEnum;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentProfileSchoolReportPojo;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfileSchoolReport;
import unid.monoServerMeta.api.StudentProfileRequest;
import unid.monoServerMeta.api.StudentProfileResponse;
import unid.monoServerMeta.api.StudentProfileSchoolReportPayload;
import unid.monoServerMeta.model.Gender;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-27T22:36:02+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class StudentProfileMapperImpl implements StudentProfileMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private CountryMapper countryMapper;

    @Override
    public StudentProfileSchoolReportPojo toPojo(StudentProfileSchoolReportPayload data) {
        if ( data == null ) {
            return null;
        }

        StudentProfileSchoolReportPojo studentProfileSchoolReportPojo = new StudentProfileSchoolReportPojo();

        studentProfileSchoolReportPojo.setId( data.getId() );
        studentProfileSchoolReportPojo.setSecondarySchoolReport( data.getSecondarySchoolReport() );
        studentProfileSchoolReportPojo.setSecondarySchoolReportAcademicYear( data.getSecondarySchoolReportAcademicYear() );
        studentProfileSchoolReportPojo.setSecondarySchoolReportSemester( data.getSecondarySchoolReportSemester() );

        return studentProfileSchoolReportPojo;
    }

    @Override
    public StudentProfileSchoolReportPojo toPojo(DbStudentProfileSchoolReport.Result data) {
        if ( data == null ) {
            return null;
        }

        StudentProfileSchoolReportPojo studentProfileSchoolReportPojo = new StudentProfileSchoolReportPojo();

        studentProfileSchoolReportPojo.setId( data.getId() );
        studentProfileSchoolReportPojo.setStudentProfileId( data.getStudentProfileId() );
        studentProfileSchoolReportPojo.setSecondarySchoolReport( data.getSecondarySchoolReport() );
        studentProfileSchoolReportPojo.setSecondarySchoolReportAcademicYear( data.getSecondarySchoolReportAcademicYear() );
        studentProfileSchoolReportPojo.setSecondarySchoolReportSemester( data.getSecondarySchoolReportSemester() );
        studentProfileSchoolReportPojo.setCreatedOn( data.getCreatedOn() );
        studentProfileSchoolReportPojo.setCreatedBy( data.getCreatedBy() );
        studentProfileSchoolReportPojo.setUpdatedOn( data.getUpdatedOn() );
        studentProfileSchoolReportPojo.setUpdatedBy( data.getUpdatedBy() );

        return studentProfileSchoolReportPojo;
    }

    @Override
    public StudentProfilePojo toPojo(StudentProfileResponse data) {
        if ( data == null ) {
            return null;
        }

        StudentProfilePojo studentProfilePojo = new StudentProfilePojo();

        studentProfilePojo.setId( data.getId() );
        studentProfilePojo.setUserId( data.getUserId() );
        studentProfilePojo.setGender( genderToGenderEnum( data.getGender() ) );
        studentProfilePojo.setCountryId( data.getCountryId() );
        studentProfilePojo.setProfilePicture( data.getProfilePicture() );
        studentProfilePojo.setPhoneCountryCode( data.getPhoneCountryCode() );
        studentProfilePojo.setPhone( data.getPhone() );
        studentProfilePojo.setSecondarySchoolId( data.getSecondarySchoolId() );
        studentProfilePojo.setPreferredUniversity_1Id( data.getPreferredUniversity_1Id() );
        studentProfilePojo.setPreferredUniversity_2Id( data.getPreferredUniversity_2Id() );
        studentProfilePojo.setPreferredUniversity_3Id( data.getPreferredUniversity_3Id() );
        UUID[] preferredOtherUniversityId = data.getPreferredOtherUniversityId();
        if ( preferredOtherUniversityId != null ) {
            studentProfilePojo.setPreferredOtherUniversityId( Arrays.copyOf( preferredOtherUniversityId, preferredOtherUniversityId.length ) );
        }
        studentProfilePojo.setSecondarySchoolGraduationYear( data.getSecondarySchoolGraduationYear() );
        studentProfilePojo.setTimezone( data.getTimezone() );

        return studentProfilePojo;
    }

    @Override
    public StudentProfilePojo toPojo(DbStudentProfile.Result data) {
        if ( data == null ) {
            return null;
        }

        StudentProfilePojo studentProfilePojo = new StudentProfilePojo();

        studentProfilePojo.setId( data.getId() );
        studentProfilePojo.setUserId( data.getUserId() );
        studentProfilePojo.setDateOfBirth( data.getDateOfBirth() );
        studentProfilePojo.setGender( data.getGender() );
        studentProfilePojo.setCountryId( data.getCountryId() );
        studentProfilePojo.setProfilePicture( data.getProfilePicture() );
        studentProfilePojo.setPhoneCountryCode( data.getPhoneCountryCode() );
        studentProfilePojo.setPhone( data.getPhone() );
        studentProfilePojo.setSecondarySchoolId( data.getSecondarySchoolId() );
        studentProfilePojo.setSecondarySchoolEducationLevelId( data.getSecondarySchoolEducationLevelId() );
        studentProfilePojo.setSecondarySchoolCurriculumId( data.getSecondarySchoolCurriculumId() );
        studentProfilePojo.setPreferredUniversity_1Id( data.getPreferredUniversity_1Id() );
        studentProfilePojo.setPreferredUniversity_2Id( data.getPreferredUniversity_2Id() );
        studentProfilePojo.setPreferredUniversity_3Id( data.getPreferredUniversity_3Id() );
        studentProfilePojo.setCreatedOn( data.getCreatedOn() );
        studentProfilePojo.setCreatedBy( data.getCreatedBy() );
        studentProfilePojo.setUpdatedOn( data.getUpdatedOn() );
        studentProfilePojo.setUpdatedBy( data.getUpdatedBy() );
        UUID[] preferredOtherUniversityId = data.getPreferredOtherUniversityId();
        if ( preferredOtherUniversityId != null ) {
            studentProfilePojo.setPreferredOtherUniversityId( Arrays.copyOf( preferredOtherUniversityId, preferredOtherUniversityId.length ) );
        }
        studentProfilePojo.setSecondarySchoolGraduationYear( data.getSecondarySchoolGraduationYear() );
        studentProfilePojo.setTimezone( data.getTimezone() );

        return studentProfilePojo;
    }

    @Override
    public StudentProfilePojo toPojo(StudentProfileRequest data) {
        if ( data == null ) {
            return null;
        }

        StudentProfilePojo studentProfilePojo = new StudentProfilePojo();

        studentProfilePojo.setGender( genderToGenderEnum( data.getGender() ) );
        studentProfilePojo.setCountryId( data.getCountryId() );
        studentProfilePojo.setProfilePicture( data.getProfilePicture() );
        studentProfilePojo.setPhoneCountryCode( data.getPhoneCountryCode() );
        studentProfilePojo.setPhone( data.getPhone() );
        studentProfilePojo.setSecondarySchoolId( data.getSecondarySchoolId() );
        studentProfilePojo.setSecondarySchoolEducationLevelId( data.getSecondarySchoolEducationLevelId() );
        studentProfilePojo.setSecondarySchoolCurriculumId( data.getSecondarySchoolCurriculumId() );
        studentProfilePojo.setPreferredUniversity_1Id( data.getPreferredUniversity_1Id() );
        studentProfilePojo.setPreferredUniversity_2Id( data.getPreferredUniversity_2Id() );
        studentProfilePojo.setPreferredUniversity_3Id( data.getPreferredUniversity_3Id() );
        UUID[] preferredOtherUniversityId = data.getPreferredOtherUniversityId();
        if ( preferredOtherUniversityId != null ) {
            studentProfilePojo.setPreferredOtherUniversityId( Arrays.copyOf( preferredOtherUniversityId, preferredOtherUniversityId.length ) );
        }
        studentProfilePojo.setSecondarySchoolGraduationYear( data.getSecondarySchoolGraduationYear() );
        studentProfilePojo.setTimezone( data.getTimezone() );

        return studentProfilePojo;
    }

    @Override
    public void merge(StudentProfilePojo target, StudentProfileRequest source) {
        if ( source == null ) {
            return;
        }

        target.setGender( genderToGenderEnum( source.getGender() ) );
        target.setCountryId( source.getCountryId() );
        target.setProfilePicture( source.getProfilePicture() );
        target.setPhoneCountryCode( source.getPhoneCountryCode() );
        target.setPhone( source.getPhone() );
        target.setSecondarySchoolId( source.getSecondarySchoolId() );
        target.setSecondarySchoolEducationLevelId( source.getSecondarySchoolEducationLevelId() );
        target.setSecondarySchoolCurriculumId( source.getSecondarySchoolCurriculumId() );
        target.setPreferredUniversity_1Id( source.getPreferredUniversity_1Id() );
        target.setPreferredUniversity_2Id( source.getPreferredUniversity_2Id() );
        target.setPreferredUniversity_3Id( source.getPreferredUniversity_3Id() );
        UUID[] preferredOtherUniversityId = source.getPreferredOtherUniversityId();
        if ( preferredOtherUniversityId != null ) {
            target.setPreferredOtherUniversityId( Arrays.copyOf( preferredOtherUniversityId, preferredOtherUniversityId.length ) );
        }
        else {
            target.setPreferredOtherUniversityId( null );
        }
        target.setSecondarySchoolGraduationYear( source.getSecondarySchoolGraduationYear() );
        target.setTimezone( source.getTimezone() );
    }

    @Override
    public StudentProfileResponse toResponse(DbStudentProfile.Result data) {
        if ( data == null ) {
            return null;
        }

        StudentProfileResponse studentProfileResponse = new StudentProfileResponse();

        studentProfileResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        studentProfileResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        studentProfileResponse.setPreferredUniversity_1Id( data.getPreferredUniversity_1Id() );
        studentProfileResponse.setPreferredUniversity_2Id( data.getPreferredUniversity_2Id() );
        studentProfileResponse.setPreferredUniversity_3Id( data.getPreferredUniversity_3Id() );
        UUID[] preferredOtherUniversityId = data.getPreferredOtherUniversityId();
        if ( preferredOtherUniversityId != null ) {
            studentProfileResponse.setPreferredOtherUniversityId( Arrays.copyOf( preferredOtherUniversityId, preferredOtherUniversityId.length ) );
        }
        studentProfileResponse.setLastNameI18n( i18nMapper.toModel( data.getLastNameI18n() ) );
        studentProfileResponse.setFirstNameI18n( i18nMapper.toModel( data.getFirstNameI18n() ) );
        studentProfileResponse.setId( data.getId() );
        studentProfileResponse.setUserId( data.getUserId() );
        studentProfileResponse.setGender( genderEnumToGender( data.getGender() ) );
        studentProfileResponse.setCountry( countryMapper.toResponse( data.getCountry() ) );
        studentProfileResponse.setCountryId( data.getCountryId() );
        studentProfileResponse.setProfilePicture( data.getProfilePicture() );
        studentProfileResponse.setPhoneCountryCode( data.getPhoneCountryCode() );
        studentProfileResponse.setPhone( data.getPhone() );
        studentProfileResponse.setSecondarySchool( schoolMapper.toResponse( data.getSecondarySchool() ) );
        studentProfileResponse.setSecondarySchoolId( data.getSecondarySchoolId() );
        studentProfileResponse.setSecondarySchoolGraduationYear( data.getSecondarySchoolGraduationYear() );
        studentProfileResponse.setPreferredUniversity_1( schoolMapper.toResponse( data.getPreferredUniversity_1() ) );
        studentProfileResponse.setPreferredUniversity_2( schoolMapper.toResponse( data.getPreferredUniversity_2() ) );
        studentProfileResponse.setPreferredUniversity_3( schoolMapper.toResponse( data.getPreferredUniversity_3() ) );
        studentProfileResponse.setStudentProfileSchoolReports( resultListToStudentProfileSchoolReportPayloadList( data.getStudentProfileSchoolReports() ) );
        studentProfileResponse.setTimezone( data.getTimezone() );

        return studentProfileResponse;
    }

    @Override
    public List<StudentProfileResponse> toResponse(List<DbStudentProfile.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<StudentProfileResponse> list = new ArrayList<StudentProfileResponse>( data.size() );
        for ( DbStudentProfile.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    protected GenderEnum genderToGenderEnum(Gender gender) {
        if ( gender == null ) {
            return null;
        }

        GenderEnum genderEnum;

        switch ( gender ) {
            case MALE: genderEnum = GenderEnum.MALE;
            break;
            case FEMALE: genderEnum = GenderEnum.FEMALE;
            break;
            case SECRET: genderEnum = GenderEnum.SECRET;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + gender );
        }

        return genderEnum;
    }

    protected Gender genderEnumToGender(GenderEnum genderEnum) {
        if ( genderEnum == null ) {
            return null;
        }

        Gender gender;

        switch ( genderEnum ) {
            case MALE: gender = Gender.MALE;
            break;
            case FEMALE: gender = Gender.FEMALE;
            break;
            case SECRET: gender = Gender.SECRET;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + genderEnum );
        }

        return gender;
    }

    protected StudentProfileSchoolReportPayload resultToStudentProfileSchoolReportPayload(DbStudentProfileSchoolReport.Result result) {
        if ( result == null ) {
            return null;
        }

        StudentProfileSchoolReportPayload studentProfileSchoolReportPayload = new StudentProfileSchoolReportPayload();

        studentProfileSchoolReportPayload.setId( result.getId() );
        studentProfileSchoolReportPayload.setSecondarySchoolReport( result.getSecondarySchoolReport() );
        studentProfileSchoolReportPayload.setSecondarySchoolReportAcademicYear( result.getSecondarySchoolReportAcademicYear() );
        studentProfileSchoolReportPayload.setSecondarySchoolReportSemester( result.getSecondarySchoolReportSemester() );

        return studentProfileSchoolReportPayload;
    }

    protected List<StudentProfileSchoolReportPayload> resultListToStudentProfileSchoolReportPayloadList(List<DbStudentProfileSchoolReport.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentProfileSchoolReportPayload> list1 = new ArrayList<StudentProfileSchoolReportPayload>( list.size() );
        for ( DbStudentProfileSchoolReport.Result result : list ) {
            list1.add( resultToStudentProfileSchoolReportPayload( result ) );
        }

        return list1;
    }
}
