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
public class _AuditLogStudentProfileSchoolReportPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private UUID           studentProfileId;
    private String         secondarySchoolReport;
    private String         secondarySchoolReportAcademicYear;
    private String         secondarySchoolReportSemester;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public _AuditLogStudentProfileSchoolReportPojo() {}

    public _AuditLogStudentProfileSchoolReportPojo(_AuditLogStudentProfileSchoolReportPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.studentProfileId = value.studentProfileId;
        this.secondarySchoolReport = value.secondarySchoolReport;
        this.secondarySchoolReportAcademicYear = value.secondarySchoolReportAcademicYear;
        this.secondarySchoolReportSemester = value.secondarySchoolReportSemester;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentProfileId", "secondarySchoolReport", "secondarySchoolReportAcademicYear", "secondarySchoolReportSemester", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogStudentProfileSchoolReportPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nonnull UUID           studentProfileId,
        @Nullable String         secondarySchoolReport,
        @Nullable String         secondarySchoolReportAcademicYear,
        @Nullable String         secondarySchoolReportSemester,
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
        this.studentProfileId = studentProfileId;
        this.secondarySchoolReport = secondarySchoolReport;
        this.secondarySchoolReportAcademicYear = secondarySchoolReportAcademicYear;
        this.secondarySchoolReportSemester = secondarySchoolReportSemester;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.audit_seq</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.audit_createdon</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.audit_createdby</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.audit_operation</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.audit_type</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.id</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return this.studentProfileId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.student_profile_id</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setStudentProfileId(@Nonnull UUID studentProfileId) {
        this.studentProfileId = studentProfileId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.secondary_school_report</code>.
     */
    @Nullable
    public String getSecondarySchoolReport() {
        return this.secondarySchoolReport;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.secondary_school_report</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setSecondarySchoolReport(@Nullable String secondarySchoolReport) {
        this.secondarySchoolReport = secondarySchoolReport;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.secondary_school_report_academic_year</code>.
     */
    @Nullable
    public String getSecondarySchoolReportAcademicYear() {
        return this.secondarySchoolReportAcademicYear;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.secondary_school_report_academic_year</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setSecondarySchoolReportAcademicYear(@Nullable String secondarySchoolReportAcademicYear) {
        this.secondarySchoolReportAcademicYear = secondarySchoolReportAcademicYear;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.secondary_school_report_semester</code>.
     */
    @Nullable
    public String getSecondarySchoolReportSemester() {
        return this.secondarySchoolReportSemester;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.secondary_school_report_semester</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setSecondarySchoolReportSemester(@Nullable String secondarySchoolReportSemester) {
        this.secondarySchoolReportSemester = secondarySchoolReportSemester;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.created_on</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.created_by</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.updated_on</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_school_report.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_school_report.updated_by</code>.
     */
    public _AuditLogStudentProfileSchoolReportPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogStudentProfileSchoolReportPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(studentProfileId);
        sb.append(", ").append(secondarySchoolReport);
        sb.append(", ").append(secondarySchoolReportAcademicYear);
        sb.append(", ").append(secondarySchoolReportSemester);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
