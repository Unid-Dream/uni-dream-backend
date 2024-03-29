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
public class _AuditLogStudentBookingSurveyMapPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private UUID           educatorCalendarId;
    private UUID           studentSessionSurveyId;
    private OffsetDateTime studentSessionSurveyVersionAsked;
    private OffsetDateTime studentSessionSurveyVersionCompleted;
    private Boolean        completed;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public _AuditLogStudentBookingSurveyMapPojo() {}

    public _AuditLogStudentBookingSurveyMapPojo(_AuditLogStudentBookingSurveyMapPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.educatorCalendarId = value.educatorCalendarId;
        this.studentSessionSurveyId = value.studentSessionSurveyId;
        this.studentSessionSurveyVersionAsked = value.studentSessionSurveyVersionAsked;
        this.studentSessionSurveyVersionCompleted = value.studentSessionSurveyVersionCompleted;
        this.completed = value.completed;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "educatorCalendarId", "studentSessionSurveyId", "studentSessionSurveyVersionAsked", "studentSessionSurveyVersionCompleted", "completed", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogStudentBookingSurveyMapPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nonnull UUID           educatorCalendarId,
        @Nonnull UUID           studentSessionSurveyId,
        @Nonnull OffsetDateTime studentSessionSurveyVersionAsked,
        @Nullable OffsetDateTime studentSessionSurveyVersionCompleted,
        @Nullable Boolean        completed,
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
        this.educatorCalendarId = educatorCalendarId;
        this.studentSessionSurveyId = studentSessionSurveyId;
        this.studentSessionSurveyVersionAsked = studentSessionSurveyVersionAsked;
        this.studentSessionSurveyVersionCompleted = studentSessionSurveyVersionCompleted;
        this.completed = completed;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.audit_seq</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.audit_createdon</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.audit_createdby</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.audit_operation</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.audit_type</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_student_booking_survey_map.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public._audit_log_student_booking_survey_map.id</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.educator_calendar_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getEducatorCalendarId() {
        return this.educatorCalendarId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.educator_calendar_id</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setEducatorCalendarId(@Nonnull UUID educatorCalendarId) {
        this.educatorCalendarId = educatorCalendarId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.student_session_survey_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentSessionSurveyId() {
        return this.studentSessionSurveyId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.student_session_survey_id</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setStudentSessionSurveyId(@Nonnull UUID studentSessionSurveyId) {
        this.studentSessionSurveyId = studentSessionSurveyId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.student_session_survey_version_asked</code>.
     */
    @NotNull
    @Nonnull
    public OffsetDateTime getStudentSessionSurveyVersionAsked() {
        return this.studentSessionSurveyVersionAsked;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.student_session_survey_version_asked</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setStudentSessionSurveyVersionAsked(@Nonnull OffsetDateTime studentSessionSurveyVersionAsked) {
        this.studentSessionSurveyVersionAsked = studentSessionSurveyVersionAsked;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.student_session_survey_version_completed</code>.
     */
    @Nullable
    public OffsetDateTime getStudentSessionSurveyVersionCompleted() {
        return this.studentSessionSurveyVersionCompleted;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.student_session_survey_version_completed</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setStudentSessionSurveyVersionCompleted(@Nullable OffsetDateTime studentSessionSurveyVersionCompleted) {
        this.studentSessionSurveyVersionCompleted = studentSessionSurveyVersionCompleted;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.completed</code>.
     */
    @Nullable
    public Boolean getCompleted() {
        return this.completed;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.completed</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setCompleted(@Nullable Boolean completed) {
        this.completed = completed;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.created_on</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.created_by</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.updated_on</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_booking_survey_map.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_booking_survey_map.updated_by</code>.
     */
    public _AuditLogStudentBookingSurveyMapPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogStudentBookingSurveyMapPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(educatorCalendarId);
        sb.append(", ").append(studentSessionSurveyId);
        sb.append(", ").append(studentSessionSurveyVersionAsked);
        sb.append(", ").append(studentSessionSurveyVersionCompleted);
        sb.append(", ").append(completed);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
