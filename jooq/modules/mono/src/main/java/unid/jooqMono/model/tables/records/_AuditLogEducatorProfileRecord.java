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

import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.jooqMono.model.tables._AuditLogEducatorProfileTable;
import unid.jooqMono.model.tables.pojos._AuditLogEducatorProfilePojo;


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
public class _AuditLogEducatorProfileRecord extends TableRecordImpl<_AuditLogEducatorProfileRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public._audit_log_educator_profile.audit_seq</code>.
     */
    public _AuditLogEducatorProfileRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.audit_createdon</code>.
     */
    public _AuditLogEducatorProfileRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.audit_createdby</code>.
     */
    public _AuditLogEducatorProfileRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.audit_operation</code>.
     */
    public _AuditLogEducatorProfileRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.audit_type</code>.
     */
    public _AuditLogEducatorProfileRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.id</code>.
     */
    public _AuditLogEducatorProfileRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.user_id</code>.
     */
    public _AuditLogEducatorProfileRecord setUserId(@Nonnull UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.user_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getUserId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.application_approval</code>.
     */
    public _AuditLogEducatorProfileRecord setApplicationApproval(@Nonnull ApplicationApprovalEnum value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.application_approval</code>.
     */
    @NotNull
    @Nonnull
    public ApplicationApprovalEnum getApplicationApproval() {
        return (ApplicationApprovalEnum) get(7);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.microsoft_id</code>.
     */
    public _AuditLogEducatorProfileRecord setMicrosoftId(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.microsoft_id</code>.
     */
    @Nullable
    public String getMicrosoftId() {
        return (String) get(8);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.microsoft_email</code>.
     */
    public _AuditLogEducatorProfileRecord setMicrosoftEmail(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.microsoft_email</code>.
     */
    @Nullable
    public String getMicrosoftEmail() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.country_id</code>.
     */
    public _AuditLogEducatorProfileRecord setCountryId(@Nullable UUID value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.country_id</code>.
     */
    @Nullable
    public UUID getCountryId() {
        return (UUID) get(10);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.profile_picture</code>.
     */
    public _AuditLogEducatorProfileRecord setProfilePicture(@Nullable String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.profile_picture</code>.
     */
    @Nullable
    public String getProfilePicture() {
        return (String) get(11);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.phone_country_code</code>.
     */
    public _AuditLogEducatorProfileRecord setPhoneCountryCode(@Nullable String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.phone_country_code</code>.
     */
    @Nullable
    public String getPhoneCountryCode() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.phone</code>.
     */
    public _AuditLogEducatorProfileRecord setPhone(@Nullable String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.phone</code>.
     */
    @Nullable
    public String getPhone() {
        return (String) get(13);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.hourly_rate</code>.
     */
    public _AuditLogEducatorProfileRecord setHourlyRate(@Nullable Integer value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.hourly_rate</code>.
     */
    @Nullable
    public Integer getHourlyRate() {
        return (Integer) get(14);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.university_id</code>.
     */
    public _AuditLogEducatorProfileRecord setUniversityId(@Nullable UUID value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.university_id</code>.
     */
    @Nullable
    public UUID getUniversityId() {
        return (UUID) get(15);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.university_education_level_id</code>.
     */
    public _AuditLogEducatorProfileRecord setUniversityEducationLevelId(@Nullable UUID value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.university_education_level_id</code>.
     */
    @Nullable
    public UUID getUniversityEducationLevelId() {
        return (UUID) get(16);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.university_identity_id</code>.
     */
    public _AuditLogEducatorProfileRecord setUniversityIdentityId(@Nullable UUID value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.university_identity_id</code>.
     */
    @Nullable
    public UUID getUniversityIdentityId() {
        return (UUID) get(17);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.created_on</code>.
     */
    public _AuditLogEducatorProfileRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(18);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.created_by</code>.
     */
    public _AuditLogEducatorProfileRecord setCreatedBy(@Nullable String value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(19);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.updated_on</code>.
     */
    public _AuditLogEducatorProfileRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(20);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.updated_by</code>.
     */
    public _AuditLogEducatorProfileRecord setUpdatedBy(@Nullable String value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(21);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.expertise_id</code>.
     */
    public _AuditLogEducatorProfileRecord setExpertiseId(@Nullable UUID[] value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.expertise_id</code>.
     */
    @Nullable
    public UUID[] getExpertiseId() {
        return (UUID[]) get(22);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.description</code>.
     */
    public _AuditLogEducatorProfileRecord setDescription(@Nullable String value) {
        set(23, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.description</code>.
     */
    @Nullable
    public String getDescription() {
        return (String) get(23);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.language_id</code>.
     */
    public _AuditLogEducatorProfileRecord setLanguageId(@Nullable UUID[] value) {
        set(24, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.language_id</code>.
     */
    @Nullable
    public UUID[] getLanguageId() {
        return (UUID[]) get(24);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.city_id</code>.
     */
    public _AuditLogEducatorProfileRecord setCityId(@Nullable UUID value) {
        set(25, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.city_id</code>.
     */
    @Nullable
    public UUID getCityId() {
        return (UUID) get(25);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.education_school_id</code>.
     */
    public _AuditLogEducatorProfileRecord setEducationSchoolId(@Nullable UUID[] value) {
        set(26, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.education_school_id</code>.
     */
    @Nullable
    public UUID[] getEducationSchoolId() {
        return (UUID[]) get(26);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.academic_major_id</code>.
     */
    public _AuditLogEducatorProfileRecord setAcademicMajorId(@Nullable UUID[] value) {
        set(27, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.academic_major_id</code>.
     */
    @Nullable
    public UUID[] getAcademicMajorId() {
        return (UUID[]) get(27);
    }

    /**
     * Setter for <code>public._audit_log_educator_profile.education_id</code>.
     */
    public _AuditLogEducatorProfileRecord setEducationId(@Nullable UUID[] value) {
        set(28, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_profile.education_id</code>.
     */
    @Nullable
    public UUID[] getEducationId() {
        return (UUID[]) get(28);
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile.education_description_id</code>.
     */
    public _AuditLogEducatorProfileRecord setEducationDescriptionId(@Nullable UUID[] value) {
        set(29, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile.education_description_id</code>.
     */
    @Nullable
    public UUID[] getEducationDescriptionId() {
        return (UUID[]) get(29);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogEducatorProfileRecord
     */
    public _AuditLogEducatorProfileRecord() {
        super(_AuditLogEducatorProfileTable._AUDIT_LOG_EDUCATOR_PROFILE);
    }

    /**
     * Create a detached, initialised _AuditLogEducatorProfileRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "userId", "applicationApproval", "microsoftId", "microsoftEmail", "countryId", "profilePicture", "phoneCountryCode", "phone", "hourlyRate", "universityId", "universityEducationLevelId", "universityIdentityId", "createdOn", "createdBy", "updatedOn", "updatedBy", "expertiseId", "description", "languageId", "cityId", "educationSchoolId", "academicMajorId", "educationId", "educationDescriptionId" })
    public _AuditLogEducatorProfileRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nonnull UUID userId, @Nonnull ApplicationApprovalEnum applicationApproval, @Nullable String microsoftId, @Nullable String microsoftEmail, @Nullable UUID countryId, @Nullable String profilePicture, @Nullable String phoneCountryCode, @Nullable String phone, @Nullable Integer hourlyRate, @Nullable UUID universityId, @Nullable UUID universityEducationLevelId, @Nullable UUID universityIdentityId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable UUID[] expertiseId, @Nullable String description, @Nullable UUID[] languageId, @Nullable UUID cityId, @Nullable UUID[] educationSchoolId, @Nullable UUID[] academicMajorId, @Nullable UUID[] educationId, @Nullable UUID[] educationDescriptionId) {
        super(_AuditLogEducatorProfileTable._AUDIT_LOG_EDUCATOR_PROFILE);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
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
        setEducationDescriptionId(educationDescriptionId);
    }

    /**
     * Create a detached, initialised _AuditLogEducatorProfileRecord
     */
    public _AuditLogEducatorProfileRecord(_AuditLogEducatorProfilePojo value) {
        super(_AuditLogEducatorProfileTable._AUDIT_LOG_EDUCATOR_PROFILE);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
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
            setEducationDescriptionId(value.getEducationDescriptionId());
        }
    }
}
