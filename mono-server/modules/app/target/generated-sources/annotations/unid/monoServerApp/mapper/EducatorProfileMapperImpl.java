package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.GenderEnum;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbEducatorSchool;
import unid.monoServerMeta.api.AcademicMajorResponse;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;
import unid.monoServerMeta.api.AcademicSubjectResponse;
import unid.monoServerMeta.api.EducatorLevelResponse;
import unid.monoServerMeta.api.EducatorProfileRequest;
import unid.monoServerMeta.api.EducatorProfileResponse;
import unid.monoServerMeta.api.EducatorProfileSimpleRequest;
import unid.monoServerMeta.api.EducatorProfileSimpleResponse;
import unid.monoServerMeta.api.ExpertiseResponse;
import unid.monoServerMeta.model.Gender;
import unid.monoServerMeta.model.I18n;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T00:44:02+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EducatorProfileMapperImpl implements EducatorProfileMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private LanguageMapper languageMapper;
    @Autowired
    private SchoolIdentityMapper schoolIdentityMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private EducationLevelMapper educationLevelMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private EducatorSchoolMapper educatorSchoolMapper;

    @Override
    public EducatorProfilePojo toPojo(EducatorProfileRequest data) {
        if ( data == null ) {
            return null;
        }

        EducatorProfilePojo educatorProfilePojo = new EducatorProfilePojo();

        educatorProfilePojo.setCountryId( data.getCountryId() );

        return educatorProfilePojo;
    }

    @Override
    public EducatorProfileSimpleResponse toSimpleResponse(DbEducatorProfile.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorProfileSimpleResponse educatorProfileSimpleResponse = new EducatorProfileSimpleResponse();

        educatorProfileSimpleResponse.setEducationLevel( resultListToEducatorLevelResponseList( data.getEducationLevel() ) );
        educatorProfileSimpleResponse.setFirstNameI18n( i18nMapper.toModel( data.getFirstNameI18n() ) );
        educatorProfileSimpleResponse.setLastNameI18n( i18nMapper.toModel( data.getLastNameI18n() ) );
        educatorProfileSimpleResponse.setPhoneCountryCode( data.getPhoneCountryCode() );
        educatorProfileSimpleResponse.setPhone( data.getPhone() );
        educatorProfileSimpleResponse.setEmail( data.getEmail() );
        educatorProfileSimpleResponse.setCountryId( data.getCountryId() );
        educatorProfileSimpleResponse.setTimezone( data.getTimezone() );
        educatorProfileSimpleResponse.setDescription( data.getDescription() );
        educatorProfileSimpleResponse.setExpertiseDescription( stringArrayToStringList( data.getExpertiseDescription() ) );
        educatorProfileSimpleResponse.setExpertiseId( uUIDArrayToUUIDList( data.getExpertiseId() ) );
        educatorProfileSimpleResponse.setLanguageId( uUIDArrayToUUIDList( data.getLanguageId() ) );
        educatorProfileSimpleResponse.setProfilePicture( data.getProfilePicture() );
        educatorProfileSimpleResponse.setHourlyRate( data.getHourlyRate() );
        educatorProfileSimpleResponse.setGender( genderEnumToGender( data.getGender() ) );

        return educatorProfileSimpleResponse;
    }

    @Override
    public void merge(DbEducatorProfile.Result data, EducatorProfileRequest source) {
        if ( source == null ) {
            return;
        }

        data.setCountryId( source.getCountryId() );
        if ( source.getFirstNameI18n() != null ) {
            if ( data.getFirstNameI18n() == null ) {
                data.setFirstNameI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getFirstNameI18n(), source.getFirstNameI18n() );
        }
        else {
            data.setFirstNameI18n( null );
        }
        if ( source.getLastNameI18n() != null ) {
            if ( data.getLastNameI18n() == null ) {
                data.setLastNameI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getLastNameI18n(), source.getLastNameI18n() );
        }
        else {
            data.setLastNameI18n( null );
        }
    }

    @Override
    public void merge(EducatorProfilePojo data, EducatorProfileRequest source) {
        if ( source == null ) {
            return;
        }

        data.setCountryId( source.getCountryId() );
    }

    @Override
    public void merge(EducatorProfilePojo data, EducatorProfileSimpleRequest source) {
        if ( source == null ) {
            return;
        }

        data.setCountryId( source.getCountryId() );
        data.setProfilePicture( source.getProfilePicture() );
        data.setHourlyRate( source.getHourlyRate() );
        data.setExpertiseId( uUIDListToUUIDArray( source.getExpertiseId() ) );
        data.setDescription( source.getDescription() );
        data.setLanguageId( uUIDListToUUIDArray( source.getLanguageId() ) );
        data.setTimezone( source.getTimezone() );
        data.setExpertiseDescription( stringListToStringArray( source.getExpertiseDescription() ) );
        data.setGender( genderToGenderEnum( source.getGender() ) );
    }

    @Override
    public EducatorProfileResponse toResponse(DbEducatorProfile.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorProfileResponse educatorProfileResponse = new EducatorProfileResponse();

        educatorProfileResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        educatorProfileResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        educatorProfileResponse.setId( data.getId() );
        educatorProfileResponse.setUserId( data.getUserId() );
        educatorProfileResponse.setCountry( countryMapper.toResponse( data.getCountry() ) );
        educatorProfileResponse.setProfilePicture( data.getProfilePicture() );
        educatorProfileResponse.setPhoneCountryCode( data.getPhoneCountryCode() );
        educatorProfileResponse.setPhone( data.getPhone() );
        educatorProfileResponse.setHourlyRate( data.getHourlyRate() );
        educatorProfileResponse.setUniversity( schoolMapper.toResponse( data.getUniversity() ) );
        educatorProfileResponse.setUniversityEducationLevel( educationLevelMapper.toResponse( data.getUniversityEducationLevel() ) );
        educatorProfileResponse.setUniversityIdentity( schoolIdentityMapper.toResponse( data.getUniversityIdentity() ) );
        educatorProfileResponse.setLanguages( languageMapper.toResponse( data.getLanguages() ) );
        educatorProfileResponse.setExpertises( resultListToExpertiseResponseList( data.getExpertises() ) );
        educatorProfileResponse.setDescription( data.getDescription() );
        educatorProfileResponse.setFirstNameI18n( i18nMapper.toModel( data.getFirstNameI18n() ) );
        educatorProfileResponse.setLastNameI18n( i18nMapper.toModel( data.getLastNameI18n() ) );

        return educatorProfileResponse;
    }

    @Override
    public List<EducatorProfileResponse> toResponse(List<DbEducatorProfile.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<EducatorProfileResponse> list = new ArrayList<EducatorProfileResponse>( data.size() );
        for ( DbEducatorProfile.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    protected List<EducatorLevelResponse> resultListToEducatorLevelResponseList(List<DbEducatorSchool.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<EducatorLevelResponse> list1 = new ArrayList<EducatorLevelResponse>( list.size() );
        for ( DbEducatorSchool.Result result : list ) {
            list1.add( educatorSchoolMapper.toPojo( result ) );
        }

        return list1;
    }

    protected List<String> stringArrayToStringList(String[] stringArray) {
        if ( stringArray == null ) {
            return null;
        }

        List<String> list = new ArrayList<String>( stringArray.length );
        for ( String string : stringArray ) {
            list.add( string );
        }

        return list;
    }

    protected List<UUID> uUIDArrayToUUIDList(UUID[] uUIDArray) {
        if ( uUIDArray == null ) {
            return null;
        }

        List<UUID> list = new ArrayList<UUID>( uUIDArray.length );
        for ( UUID uUID : uUIDArray ) {
            list.add( uUID );
        }

        return list;
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

    protected UUID[] uUIDListToUUIDArray(List<UUID> list) {
        if ( list == null ) {
            return null;
        }

        UUID[] uUIDTmp = new UUID[list.size()];
        int i = 0;
        for ( UUID uUID : list ) {
            uUIDTmp[i] = uUID;
            i++;
        }

        return uUIDTmp;
    }

    protected String[] stringListToStringArray(List<String> list) {
        if ( list == null ) {
            return null;
        }

        String[] stringTmp = new String[list.size()];
        int i = 0;
        for ( String string : list ) {
            stringTmp[i] = string;
            i++;
        }

        return stringTmp;
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

    protected AcademicSubjectResourcePayload resultToAcademicSubjectResourcePayload(DbAcademicSubjectResource.Result result) {
        if ( result == null ) {
            return null;
        }

        AcademicSubjectResourcePayload academicSubjectResourcePayload = new AcademicSubjectResourcePayload();

        academicSubjectResourcePayload.setId( result.getId() );
        academicSubjectResourcePayload.setTitleI18n( i18nMapper.toModel( result.getTitleI18n() ) );
        academicSubjectResourcePayload.setAuthorI18n( i18nMapper.toModel( result.getAuthorI18n() ) );
        academicSubjectResourcePayload.setUrl( result.getUrl() );
        if ( result.getType() != null ) {
            academicSubjectResourcePayload.setType( result.getType().name() );
        }

        return academicSubjectResourcePayload;
    }

    protected List<AcademicSubjectResourcePayload> resultListToAcademicSubjectResourcePayloadList(List<DbAcademicSubjectResource.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<AcademicSubjectResourcePayload> list1 = new ArrayList<AcademicSubjectResourcePayload>( list.size() );
        for ( DbAcademicSubjectResource.Result result : list ) {
            list1.add( resultToAcademicSubjectResourcePayload( result ) );
        }

        return list1;
    }

    protected List<I18n> resultListToI18nList(List<DbI18N.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<I18n> list1 = new ArrayList<I18n>( list.size() );
        for ( DbI18N.Result result : list ) {
            list1.add( i18nMapper.toModel( result ) );
        }

        return list1;
    }

    protected AcademicSubjectResponse resultToAcademicSubjectResponse(DbAcademicSubject.Result result) {
        if ( result == null ) {
            return null;
        }

        AcademicSubjectResponse academicSubjectResponse = new AcademicSubjectResponse();

        academicSubjectResponse.setId( result.getId() );
        academicSubjectResponse.setTitleI18n( i18nMapper.toModel( result.getTitleI18n() ) );
        academicSubjectResponse.setDescriptionI18n( i18nMapper.toModel( result.getDescriptionI18n() ) );
        academicSubjectResponse.setBooks( resultListToAcademicSubjectResourcePayloadList( result.getBooks() ) );
        academicSubjectResponse.setPodcasts( resultListToAcademicSubjectResourcePayloadList( result.getPodcasts() ) );
        academicSubjectResponse.setVideos( resultListToAcademicSubjectResourcePayloadList( result.getVideos() ) );
        academicSubjectResponse.setAnswers( resultListToI18nList( result.getAnswers() ) );

        return academicSubjectResponse;
    }

    protected List<AcademicSubjectResponse> resultListToAcademicSubjectResponseList(List<DbAcademicSubject.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<AcademicSubjectResponse> list1 = new ArrayList<AcademicSubjectResponse>( list.size() );
        for ( DbAcademicSubject.Result result : list ) {
            list1.add( resultToAcademicSubjectResponse( result ) );
        }

        return list1;
    }

    protected AcademicMajorResponse resultToAcademicMajorResponse(DbAcademicMajor.Result result) {
        if ( result == null ) {
            return null;
        }

        AcademicMajorResponse academicMajorResponse = new AcademicMajorResponse();

        academicMajorResponse.setId( result.getId() );
        academicMajorResponse.setTitleI18n( i18nMapper.toModel( result.getTitleI18n() ) );
        academicMajorResponse.setDescriptionI18n( i18nMapper.toModel( result.getDescriptionI18n() ) );
        academicMajorResponse.setIconPath( result.getIconPath() );
        academicMajorResponse.setSubjects( resultListToAcademicSubjectResponseList( result.getSubjects() ) );

        return academicMajorResponse;
    }

    protected List<AcademicMajorResponse> resultListToAcademicMajorResponseList(List<DbAcademicMajor.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<AcademicMajorResponse> list1 = new ArrayList<AcademicMajorResponse>( list.size() );
        for ( DbAcademicMajor.Result result : list ) {
            list1.add( resultToAcademicMajorResponse( result ) );
        }

        return list1;
    }

    protected ExpertiseResponse resultToExpertiseResponse(DbExpertise.Result result) {
        if ( result == null ) {
            return null;
        }

        ExpertiseResponse expertiseResponse = new ExpertiseResponse();

        expertiseResponse.setId( result.getId() );
        expertiseResponse.setDescriptionI18n( i18nMapper.toModel( result.getDescriptionI18n() ) );
        expertiseResponse.setTag( tagMapper.toResponse( result.getTag() ) );
        expertiseResponse.setMajors( resultListToAcademicMajorResponseList( result.getMajors() ) );

        return expertiseResponse;
    }

    protected List<ExpertiseResponse> resultListToExpertiseResponseList(List<DbExpertise.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<ExpertiseResponse> list1 = new ArrayList<ExpertiseResponse>( list.size() );
        for ( DbExpertise.Result result : list ) {
            list1.add( resultToExpertiseResponse( result ) );
        }

        return list1;
    }
}
