/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;


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
public class _AuditLogEducatorProfileExpertiseAcademicMapPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private UUID           educatorProfileId;
    private UUID           expertiseId;
    private UUID           academicMajorId;
    private UUID           academicSubjectId;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public _AuditLogEducatorProfileExpertiseAcademicMapPojo() {}

    public _AuditLogEducatorProfileExpertiseAcademicMapPojo(_AuditLogEducatorProfileExpertiseAcademicMapPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.educatorProfileId = value.educatorProfileId;
        this.expertiseId = value.expertiseId;
        this.academicMajorId = value.academicMajorId;
        this.academicSubjectId = value.academicSubjectId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "educatorProfileId", "expertiseId", "academicMajorId", "academicSubjectId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nonnull UUID           educatorProfileId,
        @Nonnull UUID           expertiseId,
        @Nullable UUID           academicMajorId,
        @Nullable UUID           academicSubjectId,
        @Nullable OffsetDateTime createdOn,
        @Nullable String         createdBy,
        @Nullable OffsetDateTime updatedOn,
        @Nullable String         updatedBy
    ) {
        this.auditSeq = auditSeq;
        this.auditCreatedon = auditCreatedon;
        this.auditCreatedby = auditCreatedby;
        this.auditOperation = auditOperation;
        this.auditType = auditType;
        this.id = id;
        this.educatorProfileId = educatorProfileId;
        this.expertiseId = expertiseId;
        this.academicMajorId = academicMajorId;
        this.academicSubjectId = academicSubjectId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_seq</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_createdon</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_createdby</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_operation</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.audit_type</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.id</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.educator_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getEducatorProfileId() {
        return this.educatorProfileId;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.educator_profile_id</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setEducatorProfileId(@Nonnull UUID educatorProfileId) {
        this.educatorProfileId = educatorProfileId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.expertise_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getExpertiseId() {
        return this.expertiseId;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.expertise_id</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setExpertiseId(@Nonnull UUID expertiseId) {
        this.expertiseId = expertiseId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.academic_major_id</code>.
     */
    @Nullable
    public UUID getAcademicMajorId() {
        return this.academicMajorId;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.academic_major_id</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setAcademicMajorId(@Nullable UUID academicMajorId) {
        this.academicMajorId = academicMajorId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.academic_subject_id</code>.
     */
    @Nullable
    public UUID getAcademicSubjectId() {
        return this.academicSubjectId;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.academic_subject_id</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setAcademicSubjectId(@Nullable UUID academicSubjectId) {
        this.academicSubjectId = academicSubjectId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.created_on</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.created_by</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.updated_on</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_profile_expertise_academic_map.updated_by</code>.
     */
    public _AuditLogEducatorProfileExpertiseAcademicMapPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogEducatorProfileExpertiseAcademicMapPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(educatorProfileId);
        sb.append(", ").append(expertiseId);
        sb.append(", ").append(academicMajorId);
        sb.append(", ").append(academicSubjectId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
