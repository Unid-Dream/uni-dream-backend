/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.jooqMono.model.enums.GenderEnum;
import unid.jooqMono.model.tables.EducatorProfileTable;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.15.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@lombok.experimental.FieldNameConstants(innerTypeName = "Columns")
public class EducatorProfileRecord extends UpdatableRecordImpl<EducatorProfileRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.educator_profile.id</code>.
     */
    public EducatorProfileRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.educator_profile.user_id</code>.
     */
    public EducatorProfileRecord setUserId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.user_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getUserId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.educator_profile.application_approval</code>.
     */
    public EducatorProfileRecord setApplicationApproval(@Nonnull ApplicationApprovalEnum value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.application_approval</code>.
     */
    @Nonnull
    public ApplicationApprovalEnum getApplicationApproval() {
        return (ApplicationApprovalEnum) get(2);
    }

    /**
     * Setter for <code>public.educator_profile.microsoft_id</code>.
     */
    public EducatorProfileRecord setMicrosoftId(@Nullable String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.microsoft_id</code>.
     */
    @Nullable
    public String getMicrosoftId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.educator_profile.microsoft_email</code>.
     */
    public EducatorProfileRecord setMicrosoftEmail(@Nullable String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.microsoft_email</code>.
     */
    @Nullable
    public String getMicrosoftEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.educator_profile.country_id</code>.
     */
    public EducatorProfileRecord setCountryId(@Nullable UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.country_id</code>.
     */
    @Nullable
    public UUID getCountryId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public.educator_profile.profile_picture</code>.
     */
    public EducatorProfileRecord setProfilePicture(@Nullable String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.profile_picture</code>.
     */
    @Nullable
    public String getProfilePicture() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.educator_profile.phone_country_code</code>.
     */
    public EducatorProfileRecord setPhoneCountryCode(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.phone_country_code</code>.
     */
    @Nullable
    public String getPhoneCountryCode() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.educator_profile.phone</code>.
     */
    public EducatorProfileRecord setPhone(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.phone</code>.
     */
    @Nullable
    public String getPhone() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.educator_profile.hourly_rate</code>.
     */
    public EducatorProfileRecord setHourlyRate(@Nullable Integer value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.hourly_rate</code>.
     */
    @Nullable
    public Integer getHourlyRate() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>public.educator_profile.university_id</code>.
     */
    public EducatorProfileRecord setUniversityId(@Nullable UUID value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.university_id</code>.
     */
    @Nullable
    public UUID getUniversityId() {
        return (UUID) get(10);
    }

    /**
     * Setter for
     * <code>public.educator_profile.university_education_level_id</code>.
     */
    public EducatorProfileRecord setUniversityEducationLevelId(@Nullable UUID value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile.university_education_level_id</code>.
     */
    @Nullable
    public UUID getUniversityEducationLevelId() {
        return (UUID) get(11);
    }

    /**
     * Setter for <code>public.educator_profile.university_identity_id</code>.
     */
    public EducatorProfileRecord setUniversityIdentityId(@Nullable UUID value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.university_identity_id</code>.
     */
    @Nullable
    public UUID getUniversityIdentityId() {
        return (UUID) get(12);
    }

    /**
     * Setter for <code>public.educator_profile.created_on</code>.
     */
    public EducatorProfileRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(13);
    }

    /**
     * Setter for <code>public.educator_profile.created_by</code>.
     */
    public EducatorProfileRecord setCreatedBy(@Nullable String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(14);
    }

    /**
     * Setter for <code>public.educator_profile.updated_on</code>.
     */
    public EducatorProfileRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(15);
    }

    /**
     * Setter for <code>public.educator_profile.updated_by</code>.
     */
    public EducatorProfileRecord setUpdatedBy(@Nullable String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(16);
    }

    /**
     * Setter for <code>public.educator_profile.expertise_id</code>.
     */
    public EducatorProfileRecord setExpertiseId(@Nullable UUID[] value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.expertise_id</code>.
     */
    @Nullable
    public UUID[] getExpertiseId() {
        return (UUID[]) get(17);
    }

    /**
     * Setter for <code>public.educator_profile.description</code>.
     */
    public EducatorProfileRecord setDescription(@Nullable String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.description</code>.
     */
    @Nullable
    public String getDescription() {
        return (String) get(18);
    }

    /**
     * Setter for <code>public.educator_profile.language_id</code>.
     */
    public EducatorProfileRecord setLanguageId(@Nullable UUID[] value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.language_id</code>.
     */
    @Nullable
    public UUID[] getLanguageId() {
        return (UUID[]) get(19);
    }

    /**
     * Setter for <code>public.educator_profile.city_id</code>.
     */
    public EducatorProfileRecord setCityId(@Nullable UUID value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.city_id</code>.
     */
    @Nullable
    public UUID getCityId() {
        return (UUID) get(20);
    }

    /**
     * Setter for <code>public.educator_profile.education_school_id</code>.
     */
    public EducatorProfileRecord setEducationSchoolId(@Nullable UUID[] value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.education_school_id</code>.
     */
    @Nullable
    public UUID[] getEducationSchoolId() {
        return (UUID[]) get(21);
    }

    /**
     * Setter for <code>public.educator_profile.academic_major_id</code>.
     */
    public EducatorProfileRecord setAcademicMajorId(@Nullable UUID[] value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.academic_major_id</code>.
     */
    @Nullable
    public UUID[] getAcademicMajorId() {
        return (UUID[]) get(22);
    }

    /**
     * Setter for <code>public.educator_profile.education_id</code>.
     */
    public EducatorProfileRecord setEducationId(@Nullable UUID[] value) {
        set(23, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.education_id</code>.
     */
    @Nullable
    public UUID[] getEducationId() {
        return (UUID[]) get(23);
    }

    /**
     * Setter for <code>public.educator_profile.timezone</code>.
     */
    public EducatorProfileRecord setTimezone(@Nullable String value) {
        set(24, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.timezone</code>.
     */
    @Size(max = 255)
    @Nullable
    public String getTimezone() {
        return (String) get(24);
    }

    /**
     * Setter for <code>public.educator_profile.expertise_description_id</code>.
     */
    public EducatorProfileRecord setExpertiseDescriptionId(@Nullable UUID[] value) {
        set(25, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.expertise_description_id</code>.
     */
    @Nullable
    public UUID[] getExpertiseDescriptionId() {
        return (UUID[]) get(25);
    }

    /**
     * Setter for <code>public.educator_profile.expertise_description</code>.
     */
    public EducatorProfileRecord setExpertiseDescription(@Nullable String[] value) {
        set(26, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.expertise_description</code>.
     */
    @Nullable
    public String[] getExpertiseDescription() {
        return (String[]) get(26);
    }

    /**
     * Setter for <code>public.educator_profile.gender</code>.
     */
    public EducatorProfileRecord setGender(@Nullable GenderEnum value) {
        set(27, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.gender</code>.
     */
    @Nullable
    public GenderEnum getGender() {
        return (GenderEnum) get(27);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EducatorProfileRecord
     */
    public EducatorProfileRecord() {
        super(EducatorProfileTable.EDUCATOR_PROFILE);
    }

    /**
     * Create a detached, initialised EducatorProfileRecord
     */
    @ConstructorProperties({ "id", "userId", "applicationApproval", "microsoftId", "microsoftEmail", "countryId", "profilePicture", "phoneCountryCode", "phone", "hourlyRate", "universityId", "universityEducationLevelId", "universityIdentityId", "createdOn", "createdBy", "updatedOn", "updatedBy", "expertiseId", "description", "languageId", "cityId", "educationSchoolId", "academicMajorId", "educationId", "timezone", "expertiseDescriptionId", "expertiseDescription", "gender" })
    public EducatorProfileRecord(@Nonnull UUID id, @Nonnull UUID userId, @Nonnull ApplicationApprovalEnum applicationApproval, @Nullable String microsoftId, @Nullable String microsoftEmail, @Nullable UUID countryId, @Nullable String profilePicture, @Nullable String phoneCountryCode, @Nullable String phone, @Nullable Integer hourlyRate, @Nullable UUID universityId, @Nullable UUID universityEducationLevelId, @Nullable UUID universityIdentityId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable UUID[] expertiseId, @Nullable String description, @Nullable UUID[] languageId, @Nullable UUID cityId, @Nullable UUID[] educationSchoolId, @Nullable UUID[] academicMajorId, @Nullable UUID[] educationId, @Nullable String timezone, @Nullable UUID[] expertiseDescriptionId, @Nullable String[] expertiseDescription, @Nullable GenderEnum gender) {
        super(EducatorProfileTable.EDUCATOR_PROFILE);

        setId(id);
        setUserId(userId);
        setApplicationApproval(applicationApproval);
        setMicrosoftId(microsoftId);
        setMicrosoftEmail(microsoftEmail);
        setCountryId(countryId);
        setProfilePicture(profilePicture);
        setPhoneCountryCode(phoneCountryCode);
        setPhone(phone);
        setHourlyRate(hourlyRate);
        setUniversityId(universityId);
        setUniversityEducationLevelId(universityEducationLevelId);
        setUniversityIdentityId(universityIdentityId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
        setExpertiseId(expertiseId);
        setDescription(description);
        setLanguageId(languageId);
        setCityId(cityId);
        setEducationSchoolId(educationSchoolId);
        setAcademicMajorId(academicMajorId);
        setEducationId(educationId);
        setTimezone(timezone);
        setExpertiseDescriptionId(expertiseDescriptionId);
        setExpertiseDescription(expertiseDescription);
        setGender(gender);
    }

    /**
     * Create a detached, initialised EducatorProfileRecord
     */
    public EducatorProfileRecord(EducatorProfilePojo value) {
        super(EducatorProfileTable.EDUCATOR_PROFILE);

        if (value != null) {
            setId(value.getId());
            setUserId(value.getUserId());
            setApplicationApproval(value.getApplicationApproval());
            setMicrosoftId(value.getMicrosoftId());
            setMicrosoftEmail(value.getMicrosoftEmail());
            setCountryId(value.getCountryId());
            setProfilePicture(value.getProfilePicture());
            setPhoneCountryCode(value.getPhoneCountryCode());
            setPhone(value.getPhone());
            setHourlyRate(value.getHourlyRate());
            setUniversityId(value.getUniversityId());
            setUniversityEducationLevelId(value.getUniversityEducationLevelId());
            setUniversityIdentityId(value.getUniversityIdentityId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
            setExpertiseId(value.getExpertiseId());
            setDescription(value.getDescription());
            setLanguageId(value.getLanguageId());
            setCityId(value.getCityId());
            setEducationSchoolId(value.getEducationSchoolId());
            setAcademicMajorId(value.getAcademicMajorId());
            setEducationId(value.getEducationId());
            setTimezone(value.getTimezone());
            setExpertiseDescriptionId(value.getExpertiseDescriptionId());
            setExpertiseDescription(value.getExpertiseDescription());
            setGender(value.getGender());
        }
    }
}
