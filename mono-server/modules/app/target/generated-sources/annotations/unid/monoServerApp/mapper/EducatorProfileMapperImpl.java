package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubjectResource;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.AcademicMajorResponse;
import unid.monoServerMeta.api.AcademicSubjectResourcePayload;
import unid.monoServerMeta.api.AcademicSubjectResponse;
import unid.monoServerMeta.api.EducatorProfileRequest;
import unid.monoServerMeta.api.EducatorProfileResponse;
import unid.monoServerMeta.api.EducatorProfileUpdateRequest;
import unid.monoServerMeta.api.ExpertiseResponse;
import unid.monoServerMeta.model.I18n;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-10T20:56:04+0800",
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
    public void merge(DbEducatorProfile.Result data, EducatorProfileRequest source) {
        if ( source == null ) {
            return;
        }

        data.setCountryId( source.getCountryId() );
    }

    @Override
    public void merge(EducatorProfilePojo data, EducatorProfileRequest source) {
        if ( source == null ) {
            return;
        }

        data.setCountryId( source.getCountryId() );
    }

    @Override
    public void merge(EducatorProfilePojo data, EducatorProfileUpdateRequest source) {
        if ( source == null ) {
            return;
        }

        data.setExpertiseId( uUIDListToUUIDArray( source.getExpertises() ) );
        data.setAcademicMajorId( uUIDListToUUIDArray( source.getSubjects() ) );
        data.setLanguageId( uUIDListToUUIDArray( source.getLanguages() ) );
        data.setHourlyRate( source.getHourly_rate() );
        data.setDescription( source.getDescription() );
        data.setProfilePicture( source.getPhoto() );
        data.setCountryId( source.getCountryId() );
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
