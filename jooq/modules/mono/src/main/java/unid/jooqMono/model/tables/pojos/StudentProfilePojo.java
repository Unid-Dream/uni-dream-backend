/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class StudentProfilePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID           id;
    private UUID           userId;
    private LocalDate      dateOfBirth;
    private GenderEnum     gender;
    private UUID           countryId;
    private String         profilePicture;
    private String         phoneCountryCode;
    private String         phone;
    private UUID           secondarySchoolId;
    private UUID           secondarySchoolEducationLevelId;
    private UUID           secondarySchoolCurriculumId;
    private UUID           preferredUniversity_1Id;
    private UUID           preferredUniversity_2Id;
    private UUID           preferredUniversity_3Id;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;
    private UUID[]         preferredOtherUniversityId;
    private String         secondarySchoolGraduationYear;
    private String         timezone;

    public StudentProfilePojo() {}

    public StudentProfilePojo(StudentProfilePojo value) {
        this.id = value.id;
        this.userId = value.userId;
        this.dateOfBirth = value.dateOfBirth;
        this.gender = value.gender;
        this.countryId = value.countryId;
        this.profilePicture = value.profilePicture;
        this.phoneCountryCode = value.phoneCountryCode;
        this.phone = value.phone;
        this.secondarySchoolId = value.secondarySchoolId;
        this.secondarySchoolEducationLevelId = value.secondarySchoolEducationLevelId;
        this.secondarySchoolCurriculumId = value.secondarySchoolCurriculumId;
        this.preferredUniversity_1Id = value.preferredUniversity_1Id;
        this.preferredUniversity_2Id = value.preferredUniversity_2Id;
        this.preferredUniversity_3Id = value.preferredUniversity_3Id;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
        this.preferredOtherUniversityId = value.preferredOtherUniversityId;
        this.secondarySchoolGraduationYear = value.secondarySchoolGraduationYear;
        this.timezone = value.timezone;
    }

    @ConstructorProperties({ "id", "userId", "dateOfBirth", "gender", "countryId", "profilePicture", "phoneCountryCode", "phone", "secondarySchoolId", "secondarySchoolEducationLevelId", "secondarySchoolCurriculumId", "preferredUniversity_1Id", "preferredUniversity_2Id", "preferredUniversity_3Id", "createdOn", "createdBy", "updatedOn", "updatedBy", "preferredOtherUniversityId", "secondarySchoolGraduationYear", "timezone" })
    public StudentProfilePojo(
        @Nonnull UUID           id,
        @Nonnull UUID           userId,
        @Nullable LocalDate      dateOfBirth,
        @Nullable GenderEnum     gender,
        @Nullable UUID           countryId,
        @Nullable String         profilePicture,
        @Nullable String         phoneCountryCode,
        @Nullable String         phone,
        @Nullable UUID           secondarySchoolId,
        @Nullable UUID           secondarySchoolEducationLevelId,
        @Nullable UUID           secondarySchoolCurriculumId,
        @Nullable UUID           preferredUniversity_1Id,
        @Nullable UUID           preferredUniversity_2Id,
        @Nullable UUID           preferredUniversity_3Id,
        @Nullable OffsetDateTime createdOn,
        @Nullable String         createdBy,
        @Nullable OffsetDateTime updatedOn,
        @Nullable String         updatedBy,
        @Nullable UUID[]         preferredOtherUniversityId,
        @Nullable String         secondarySchoolGraduationYear,
        @Nullable String         timezone
    ) {
        this.id = id;
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.countryId = countryId;
        this.profilePicture = profilePicture;
        this.phoneCountryCode = phoneCountryCode;
        this.phone = phone;
        this.secondarySchoolId = secondarySchoolId;
        this.secondarySchoolEducationLevelId = secondarySchoolEducationLevelId;
        this.secondarySchoolCurriculumId = secondarySchoolCurriculumId;
        this.preferredUniversity_1Id = preferredUniversity_1Id;
        this.preferredUniversity_2Id = preferredUniversity_2Id;
        this.preferredUniversity_3Id = preferredUniversity_3Id;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.preferredOtherUniversityId = preferredOtherUniversityId;
        this.secondarySchoolGraduationYear = secondarySchoolGraduationYear;
        this.timezone = timezone;
    }

    /**
     * Getter for <code>public.student_profile.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.student_profile.id</code>.
     */
    public StudentProfilePojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.user_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.student_profile.user_id</code>.
     */
    public StudentProfilePojo setUserId(@Nonnull UUID userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.date_of_birth</code>.
     */
    @Nullable
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Setter for <code>public.student_profile.date_of_birth</code>.
     */
    public StudentProfilePojo setDateOfBirth(@Nullable LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.gender</code>.
     */
    @Nullable
    public GenderEnum getGender() {
        return this.gender;
    }

    /**
     * Setter for <code>public.student_profile.gender</code>.
     */
    public StudentProfilePojo setGender(@Nullable GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.country_id</code>.
     */
    @Nullable
    public UUID getCountryId() {
        return this.countryId;
    }

    /**
     * Setter for <code>public.student_profile.country_id</code>.
     */
    public StudentProfilePojo setCountryId(@Nullable UUID countryId) {
        this.countryId = countryId;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.profile_picture</code>.
     */
    @Nullable
    public String getProfilePicture() {
        return this.profilePicture;
    }

    /**
     * Setter for <code>public.student_profile.profile_picture</code>.
     */
    public StudentProfilePojo setProfilePicture(@Nullable String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.phone_country_code</code>.
     */
    @Nullable
    public String getPhoneCountryCode() {
        return this.phoneCountryCode;
    }

    /**
     * Setter for <code>public.student_profile.phone_country_code</code>.
     */
    public StudentProfilePojo setPhoneCountryCode(@Nullable String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.phone</code>.
     */
    @Nullable
    public String getPhone() {
        return this.phone;
    }

    /**
     * Setter for <code>public.student_profile.phone</code>.
     */
    public StudentProfilePojo setPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.secondary_school_id</code>.
     */
    @Nullable
    public UUID getSecondarySchoolId() {
        return this.secondarySchoolId;
    }

    /**
     * Setter for <code>public.student_profile.secondary_school_id</code>.
     */
    public StudentProfilePojo setSecondarySchoolId(@Nullable UUID secondarySchoolId) {
        this.secondarySchoolId = secondarySchoolId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile.secondary_school_education_level_id</code>.
     */
    @Nullable
    public UUID getSecondarySchoolEducationLevelId() {
        return this.secondarySchoolEducationLevelId;
    }

    /**
     * Setter for
     * <code>public.student_profile.secondary_school_education_level_id</code>.
     */
    public StudentProfilePojo setSecondarySchoolEducationLevelId(@Nullable UUID secondarySchoolEducationLevelId) {
        this.secondarySchoolEducationLevelId = secondarySchoolEducationLevelId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile.secondary_school_curriculum_id</code>.
     */
    @Nullable
    public UUID getSecondarySchoolCurriculumId() {
        return this.secondarySchoolCurriculumId;
    }

    /**
     * Setter for
     * <code>public.student_profile.secondary_school_curriculum_id</code>.
     */
    public StudentProfilePojo setSecondarySchoolCurriculumId(@Nullable UUID secondarySchoolCurriculumId) {
        this.secondarySchoolCurriculumId = secondarySchoolCurriculumId;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.preferred_university_1_id</code>.
     */
    @Nullable
    public UUID getPreferredUniversity_1Id() {
        return this.preferredUniversity_1Id;
    }

    /**
     * Setter for <code>public.student_profile.preferred_university_1_id</code>.
     */
    public StudentProfilePojo setPreferredUniversity_1Id(@Nullable UUID preferredUniversity_1Id) {
        this.preferredUniversity_1Id = preferredUniversity_1Id;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.preferred_university_2_id</code>.
     */
    @Nullable
    public UUID getPreferredUniversity_2Id() {
        return this.preferredUniversity_2Id;
    }

    /**
     * Setter for <code>public.student_profile.preferred_university_2_id</code>.
     */
    public StudentProfilePojo setPreferredUniversity_2Id(@Nullable UUID preferredUniversity_2Id) {
        this.preferredUniversity_2Id = preferredUniversity_2Id;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.preferred_university_3_id</code>.
     */
    @Nullable
    public UUID getPreferredUniversity_3Id() {
        return this.preferredUniversity_3Id;
    }

    /**
     * Setter for <code>public.student_profile.preferred_university_3_id</code>.
     */
    public StudentProfilePojo setPreferredUniversity_3Id(@Nullable UUID preferredUniversity_3Id) {
        this.preferredUniversity_3Id = preferredUniversity_3Id;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.student_profile.created_on</code>.
     */
    public StudentProfilePojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.student_profile.created_by</code>.
     */
    public StudentProfilePojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.student_profile.updated_on</code>.
     */
    public StudentProfilePojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.student_profile.updated_by</code>.
     */
    public StudentProfilePojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile.preferred_other_university_id</code>.
     */
    @Nullable
    public UUID[] getPreferredOtherUniversityId() {
        return this.preferredOtherUniversityId;
    }

    /**
     * Setter for
     * <code>public.student_profile.preferred_other_university_id</code>.
     */
    public StudentProfilePojo setPreferredOtherUniversityId(@Nullable UUID[] preferredOtherUniversityId) {
        this.preferredOtherUniversityId = preferredOtherUniversityId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile.secondary_school_graduation_year</code>.
     */
    @Size(max = 100)
    @Nullable
    public String getSecondarySchoolGraduationYear() {
        return this.secondarySchoolGraduationYear;
    }

    /**
     * Setter for
     * <code>public.student_profile.secondary_school_graduation_year</code>.
     */
    public StudentProfilePojo setSecondarySchoolGraduationYear(@Nullable String secondarySchoolGraduationYear) {
        this.secondarySchoolGraduationYear = secondarySchoolGraduationYear;
        return this;
    }

    /**
     * Getter for <code>public.student_profile.timezone</code>.
     */
    @Size(max = 255)
    @Nullable
    public String getTimezone() {
        return this.timezone;
    }

    /**
     * Setter for <code>public.student_profile.timezone</code>.
     */
    public StudentProfilePojo setTimezone(@Nullable String timezone) {
        this.timezone = timezone;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StudentProfilePojo (");

        sb.append(id);
        sb.append(", ").append(userId);
        sb.append(", ").append(dateOfBirth);
        sb.append(", ").append(gender);
        sb.append(", ").append(countryId);
        sb.append(", ").append(profilePicture);
        sb.append(", ").append(phoneCountryCode);
        sb.append(", ").append(phone);
        sb.append(", ").append(secondarySchoolId);
        sb.append(", ").append(secondarySchoolEducationLevelId);
        sb.append(", ").append(secondarySchoolCurriculumId);
        sb.append(", ").append(preferredUniversity_1Id);
        sb.append(", ").append(preferredUniversity_2Id);
        sb.append(", ").append(preferredUniversity_3Id);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);
        sb.append(", ").append(Arrays.toString(preferredOtherUniversityId));
        sb.append(", ").append(secondarySchoolGraduationYear);
        sb.append(", ").append(timezone);

        sb.append(")");
        return sb.toString();
    }
}
