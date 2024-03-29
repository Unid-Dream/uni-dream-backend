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
public class _AuditLogStudentProfileQuestionnaireAnswerMapPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private UUID           studentProfileQuestionnaireMapId;
    private UUID           questionnaireQuestionId;
    private UUID           questionnaireAnswerId;
    private Integer        questionnaireAnswerOrder;
    private String         questionnaireAnswerAnyInput;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo() {}

    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo(_AuditLogStudentProfileQuestionnaireAnswerMapPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.studentProfileQuestionnaireMapId = value.studentProfileQuestionnaireMapId;
        this.questionnaireQuestionId = value.questionnaireQuestionId;
        this.questionnaireAnswerId = value.questionnaireAnswerId;
        this.questionnaireAnswerOrder = value.questionnaireAnswerOrder;
        this.questionnaireAnswerAnyInput = value.questionnaireAnswerAnyInput;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentProfileQuestionnaireMapId", "questionnaireQuestionId", "questionnaireAnswerId", "questionnaireAnswerOrder", "questionnaireAnswerAnyInput", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nonnull UUID           studentProfileQuestionnaireMapId,
        @Nonnull UUID           questionnaireQuestionId,
        @Nonnull UUID           questionnaireAnswerId,
        @Nonnull Integer        questionnaireAnswerOrder,
        @Nullable String         questionnaireAnswerAnyInput,
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
        this.studentProfileQuestionnaireMapId = studentProfileQuestionnaireMapId;
        this.questionnaireQuestionId = questionnaireQuestionId;
        this.questionnaireAnswerId = questionnaireAnswerId;
        this.questionnaireAnswerOrder = questionnaireAnswerOrder;
        this.questionnaireAnswerAnyInput = questionnaireAnswerAnyInput;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_seq</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_createdon</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_createdby</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_operation</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.audit_type</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.id</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.student_profile_questionnaire_map_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileQuestionnaireMapId() {
        return this.studentProfileQuestionnaireMapId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.student_profile_questionnaire_map_id</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setStudentProfileQuestionnaireMapId(@Nonnull UUID studentProfileQuestionnaireMapId) {
        this.studentProfileQuestionnaireMapId = studentProfileQuestionnaireMapId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_question_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getQuestionnaireQuestionId() {
        return this.questionnaireQuestionId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_question_id</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setQuestionnaireQuestionId(@Nonnull UUID questionnaireQuestionId) {
        this.questionnaireQuestionId = questionnaireQuestionId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_answer_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getQuestionnaireAnswerId() {
        return this.questionnaireAnswerId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_answer_id</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setQuestionnaireAnswerId(@Nonnull UUID questionnaireAnswerId) {
        this.questionnaireAnswerId = questionnaireAnswerId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_answer_order</code>.
     */
    @NotNull
    @Nonnull
    public Integer getQuestionnaireAnswerOrder() {
        return this.questionnaireAnswerOrder;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_answer_order</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setQuestionnaireAnswerOrder(@Nonnull Integer questionnaireAnswerOrder) {
        this.questionnaireAnswerOrder = questionnaireAnswerOrder;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_answer_any_input</code>.
     */
    @Nullable
    public String getQuestionnaireAnswerAnyInput() {
        return this.questionnaireAnswerAnyInput;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.questionnaire_answer_any_input</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setQuestionnaireAnswerAnyInput(@Nullable String questionnaireAnswerAnyInput) {
        this.questionnaireAnswerAnyInput = questionnaireAnswerAnyInput;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.created_on</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.created_by</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.updated_on</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_answer_map.updated_by</code>.
     */
    public _AuditLogStudentProfileQuestionnaireAnswerMapPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogStudentProfileQuestionnaireAnswerMapPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(studentProfileQuestionnaireMapId);
        sb.append(", ").append(questionnaireQuestionId);
        sb.append(", ").append(questionnaireAnswerId);
        sb.append(", ").append(questionnaireAnswerOrder);
        sb.append(", ").append(questionnaireAnswerAnyInput);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
