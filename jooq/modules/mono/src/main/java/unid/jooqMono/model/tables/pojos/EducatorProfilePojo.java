/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.jooqMono.model.enums.GenderEnum;


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
public class EducatorProfilePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID                    id;
    private UUID                    userId;
    private ApplicationApprovalEnum applicationApproval;
    private String                  microsoftId;
    private String                  microsoftEmail;
    private UUID                    countryId;
    private String                  profilePicture;
    private String                  phoneCountryCode;
    private String                  phone;
    private Integer                 hourlyRate;
    private UUID                    universityId;
    private UUID                    universityEducationLevelId;
    private UUID                    universityIdentityId;
    private OffsetDateTime          createdOn;
    private String                  createdBy;
    private OffsetDateTime          updatedOn;
    private String                  updatedBy;
    private UUID[]                  expertiseId;
    private String                  description;
    private UUID[]                  languageId;
    private UUID                    cityId;
    private UUID[]                  educationSchoolId;
    private UUID[]                  academicMajorId;
    private UUID[]                  educationId;
    private String                  timezone;
    private UUID[]                  expertiseDescriptionId;
    private String[]                expertiseDescription;
    private GenderEnum              gender;

    public EducatorProfilePojo() {}

    public EducatorProfilePojo(EducatorProfilePojo value) {
        this.id = value.id;
        this.userId = value.userId;
        this.applicationApproval = value.applicationApproval;
        this.microsoftId = value.microsoftId;
        this.microsoftEmail = value.microsoftEmail;
        this.countryId = value.countryId;
        this.profilePicture = value.profilePicture;
        this.phoneCountryCode = value.phoneCountryCode;
        this.phone = value.phone;
        this.hourlyRate = value.hourlyRate;
        this.universityId = value.universityId;
        this.universityEducationLevelId = value.universityEducationLevelId;
        this.universityIdentityId = value.universityIdentityId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
        this.expertiseId = value.expertiseId;
        this.description = value.description;
        this.languageId = value.languageId;
        this.cityId = value.cityId;
        this.educationSchoolId = value.educationSchoolId;
        this.academicMajorId = value.academicMajorId;
        this.educationId = value.educationId;
        this.timezone = value.timezone;
        this.expertiseDescriptionId = value.expertiseDescriptionId;
        this.expertiseDescription = value.expertiseDescription;
        this.gender = value.gender;
    }

    @ConstructorProperties({ "id", "userId", "applicationApproval", "microsoftId", "microsoftEmail", "countryId", "profilePicture", "phoneCountryCode", "phone", "hourlyRate", "universityId", "universityEducationLevelId", "universityIdentityId", "createdOn", "createdBy", "updatedOn", "updatedBy", "expertiseId", "description", "languageId", "cityId", "educationSchoolId", "academicMajorId", "educationId", "timezone", "expertiseDescriptionId", "expertiseDescription", "gender" })
    public EducatorProfilePojo(
        @Nonnull UUID                    id,
        @Nonnull UUID                    userId,
        @Nonnull ApplicationApprovalEnum applicationApproval,
        @Nullable String                  microsoftId,
        @Nullable String                  microsoftEmail,
        @Nullable UUID                    countryId,
        @Nullable String                  profilePicture,
        @Nullable String                  phoneCountryCode,
        @Nullable String                  phone,
        @Nullable Integer                 hourlyRate,
        @Nullable UUID                    universityId,
        @Nullable UUID                    universityEducationLevelId,
        @Nullable UUID                    universityIdentityId,
        @Nullable OffsetDateTime          createdOn,
        @Nullable String                  createdBy,
        @Nullable OffsetDateTime          updatedOn,
        @Nullable String                  updatedBy,
        @Nullable UUID[]                  expertiseId,
        @Nullable String                  description,
        @Nullable UUID[]                  languageId,
        @Nullable UUID                    cityId,
        @Nullable UUID[]                  educationSchoolId,
        @Nullable UUID[]                  academicMajorId,
        @Nullable UUID[]                  educationId,
        @Nullable String                  timezone,
        @Nullable UUID[]                  expertiseDescriptionId,
        @Nullable String[]                expertiseDescription,
        @Nullable GenderEnum              gender
    ) {
        this.id = id;
        this.userId = userId;
        this.applicationApproval = applicationApproval;
        this.microsoftId = microsoftId;
        this.microsoftEmail = microsoftEmail;
        this.countryId = countryId;
        this.profilePicture = profilePicture;
        this.phoneCountryCode = phoneCountryCode;
        this.phone = phone;
        this.hourlyRate = hourlyRate;
        this.universityId = universityId;
        this.universityEducationLevelId = universityEducationLevelId;
        this.universityIdentityId = universityIdentityId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.expertiseId = expertiseId;
        this.description = description;
        this.languageId = languageId;
        this.cityId = cityId;
        this.educationSchoolId = educationSchoolId;
        this.academicMajorId = academicMajorId;
        this.educationId = educationId;
        this.timezone = timezone;
        this.expertiseDescriptionId = expertiseDescriptionId;
        this.expertiseDescription = expertiseDescription;
        this.gender = gender;
    }

    /**
     * Getter for <code>public.educator_profile.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.educator_profile.id</code>.
     */
    public EducatorProfilePojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.user_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.educator_profile.user_id</code>.
     */
    public EducatorProfilePojo setUserId(@Nonnull UUID userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.application_approval</code>.
     */
    @Nonnull
    public ApplicationApprovalEnum getApplicationApproval() {
        return this.applicationApproval;
    }

    /**
     * Setter for <code>public.educator_profile.application_approval</code>.
     */
    public EducatorProfilePojo setApplicationApproval(@Nonnull ApplicationApprovalEnum applicationApproval) {
        this.applicationApproval = applicationApproval;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.microsoft_id</code>.
     */
    @Nullable
    public String getMicrosoftId() {
        return this.microsoftId;
    }

    /**
     * Setter for <code>public.educator_profile.microsoft_id</code>.
     */
    public EducatorProfilePojo setMicrosoftId(@Nullable String microsoftId) {
        this.microsoftId = microsoftId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.microsoft_email</code>.
     */
    @Nullable
    public String getMicrosoftEmail() {
        return this.microsoftEmail;
    }

    /**
     * Setter for <code>public.educator_profile.microsoft_email</code>.
     */
    public EducatorProfilePojo setMicrosoftEmail(@Nullable String microsoftEmail) {
        this.microsoftEmail = microsoftEmail;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.country_id</code>.
     */
    @Nullable
    public UUID getCountryId() {
        return this.countryId;
    }

    /**
     * Setter for <code>public.educator_profile.country_id</code>.
     */
    public EducatorProfilePojo setCountryId(@Nullable UUID countryId) {
        this.countryId = countryId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.profile_picture</code>.
     */
    @Nullable
    public String getProfilePicture() {
        return this.profilePicture;
    }

    /**
     * Setter for <code>public.educator_profile.profile_picture</code>.
     */
    public EducatorProfilePojo setProfilePicture(@Nullable String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.phone_country_code</code>.
     */
    @Nullable
    public String getPhoneCountryCode() {
        return this.phoneCountryCode;
    }

    /**
     * Setter for <code>public.educator_profile.phone_country_code</code>.
     */
    public EducatorProfilePojo setPhoneCountryCode(@Nullable String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.phone</code>.
     */
    @Nullable
    public String getPhone() {
        return this.phone;
    }

    /**
     * Setter for <code>public.educator_profile.phone</code>.
     */
    public EducatorProfilePojo setPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.hourly_rate</code>.
     */
    @Nullable
    public Integer getHourlyRate() {
        return this.hourlyRate;
    }

    /**
     * Setter for <code>public.educator_profile.hourly_rate</code>.
     */
    public EducatorProfilePojo setHourlyRate(@Nullable Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.university_id</code>.
     */
    @Nullable
    public UUID getUniversityId() {
        return this.universityId;
    }

    /**
     * Setter for <code>public.educator_profile.university_id</code>.
     */
    public EducatorProfilePojo setUniversityId(@Nullable UUID universityId) {
        this.universityId = universityId;
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile.university_education_level_id</code>.
     */
    @Nullable
    public UUID getUniversityEducationLevelId() {
        return this.universityEducationLevelId;
    }

    /**
     * Setter for
     * <code>public.educator_profile.university_education_level_id</code>.
     */
    public EducatorProfilePojo setUniversityEducationLevelId(@Nullable UUID universityEducationLevelId) {
        this.universityEducationLevelId = universityEducationLevelId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.university_identity_id</code>.
     */
    @Nullable
    public UUID getUniversityIdentityId() {
        return this.universityIdentityId;
    }

    /**
     * Setter for <code>public.educator_profile.university_identity_id</code>.
     */
    public EducatorProfilePojo setUniversityIdentityId(@Nullable UUID universityIdentityId) {
        this.universityIdentityId = universityIdentityId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.educator_profile.created_on</code>.
     */
    public EducatorProfilePojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.educator_profile.created_by</code>.
     */
    public EducatorProfilePojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.educator_profile.updated_on</code>.
     */
    public EducatorProfilePojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.educator_profile.updated_by</code>.
     */
    public EducatorProfilePojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.expertise_id</code>.
     */
    @Nullable
    public UUID[] getExpertiseId() {
        return this.expertiseId;
    }

    /**
     * Setter for <code>public.educator_profile.expertise_id</code>.
     */
    public EducatorProfilePojo setExpertiseId(@Nullable UUID[] expertiseId) {
        this.expertiseId = expertiseId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.description</code>.
     */
    @Nullable
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.educator_profile.description</code>.
     */
    public EducatorProfilePojo setDescription(@Nullable String description) {
        this.description = description;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.language_id</code>.
     */
    @Nullable
    public UUID[] getLanguageId() {
        return this.languageId;
    }

    /**
     * Setter for <code>public.educator_profile.language_id</code>.
     */
    public EducatorProfilePojo setLanguageId(@Nullable UUID[] languageId) {
        this.languageId = languageId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.city_id</code>.
     */
    @Nullable
    public UUID getCityId() {
        return this.cityId;
    }

    /**
     * Setter for <code>public.educator_profile.city_id</code>.
     */
    public EducatorProfilePojo setCityId(@Nullable UUID cityId) {
        this.cityId = cityId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.education_school_id</code>.
     */
    @Nullable
    public UUID[] getEducationSchoolId() {
        return this.educationSchoolId;
    }

    /**
     * Setter for <code>public.educator_profile.education_school_id</code>.
     */
    public EducatorProfilePojo setEducationSchoolId(@Nullable UUID[] educationSchoolId) {
        this.educationSchoolId = educationSchoolId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.academic_major_id</code>.
     */
    @Nullable
    public UUID[] getAcademicMajorId() {
        return this.academicMajorId;
    }

    /**
     * Setter for <code>public.educator_profile.academic_major_id</code>.
     */
    public EducatorProfilePojo setAcademicMajorId(@Nullable UUID[] academicMajorId) {
        this.academicMajorId = academicMajorId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.education_id</code>.
     */
    @Nullable
    public UUID[] getEducationId() {
        return this.educationId;
    }

    /**
     * Setter for <code>public.educator_profile.education_id</code>.
     */
    public EducatorProfilePojo setEducationId(@Nullable UUID[] educationId) {
        this.educationId = educationId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.timezone</code>.
     */
    @Size(max = 255)
    @Nullable
    public String getTimezone() {
        return this.timezone;
    }

    /**
     * Setter for <code>public.educator_profile.timezone</code>.
     */
    public EducatorProfilePojo setTimezone(@Nullable String timezone) {
        this.timezone = timezone;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.expertise_description_id</code>.
     */
    @Nullable
    public UUID[] getExpertiseDescriptionId() {
        return this.expertiseDescriptionId;
    }

    /**
     * Setter for <code>public.educator_profile.expertise_description_id</code>.
     */
    public EducatorProfilePojo setExpertiseDescriptionId(@Nullable UUID[] expertiseDescriptionId) {
        this.expertiseDescriptionId = expertiseDescriptionId;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.expertise_description</code>.
     */
    @Nullable
    public String[] getExpertiseDescription() {
        return this.expertiseDescription;
    }

    /**
     * Setter for <code>public.educator_profile.expertise_description</code>.
     */
    public EducatorProfilePojo setExpertiseDescription(@Nullable String[] expertiseDescription) {
        this.expertiseDescription = expertiseDescription;
        return this;
    }

    /**
     * Getter for <code>public.educator_profile.gender</code>.
     */
    @Nullable
    public GenderEnum getGender() {
        return this.gender;
    }

    /**
     * Setter for <code>public.educator_profile.gender</code>.
     */
    public EducatorProfilePojo setGender(@Nullable GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EducatorProfilePojo (");

        sb.append(id);
        sb.append(", ").append(userId);
        sb.append(", ").append(applicationApproval);
        sb.append(", ").append(microsoftId);
        sb.append(", ").append(microsoftEmail);
        sb.append(", ").append(countryId);
        sb.append(", ").append(profilePicture);
        sb.append(", ").append(phoneCountryCode);
        sb.append(", ").append(phone);
        sb.append(", ").append(hourlyRate);
        sb.append(", ").append(universityId);
        sb.append(", ").append(universityEducationLevelId);
        sb.append(", ").append(universityIdentityId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);
        sb.append(", ").append(Arrays.toString(expertiseId));
        sb.append(", ").append(description);
        sb.append(", ").append(Arrays.toString(languageId));
        sb.append(", ").append(cityId);
        sb.append(", ").append(Arrays.toString(educationSchoolId));
        sb.append(", ").append(Arrays.toString(academicMajorId));
        sb.append(", ").append(Arrays.toString(educationId));
        sb.append(", ").append(timezone);
        sb.append(", ").append(Arrays.toString(expertiseDescriptionId));
        sb.append(", ").append(Arrays.toString(expertiseDescription));
        sb.append(", ").append(gender);

        sb.append(")");
        return sb.toString();
    }
}
