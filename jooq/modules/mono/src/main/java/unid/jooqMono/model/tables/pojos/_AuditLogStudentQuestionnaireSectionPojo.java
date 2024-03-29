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
public class _AuditLogStudentQuestionnaireSectionPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private UUID           studentQuestionnaireId;
    private OffsetDateTime studentQuestionnaireVersion;
    private Integer        order;
    private UUID           titleI18nId;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public _AuditLogStudentQuestionnaireSectionPojo() {}

    public _AuditLogStudentQuestionnaireSectionPojo(_AuditLogStudentQuestionnaireSectionPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.studentQuestionnaireId = value.studentQuestionnaireId;
        this.studentQuestionnaireVersion = value.studentQuestionnaireVersion;
        this.order = value.order;
        this.titleI18nId = value.titleI18nId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentQuestionnaireId", "studentQuestionnaireVersion", "order", "titleI18nId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogStudentQuestionnaireSectionPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nonnull UUID           studentQuestionnaireId,
        @Nonnull OffsetDateTime studentQuestionnaireVersion,
        @Nonnull Integer        order,
        @Nullable UUID           titleI18nId,
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
        this.studentQuestionnaireId = studentQuestionnaireId;
        this.studentQuestionnaireVersion = studentQuestionnaireVersion;
        this.order = order;
        this.titleI18nId = titleI18nId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.audit_seq</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.audit_createdon</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.audit_createdby</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.audit_operation</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.audit_type</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.id</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.student_questionnaire_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentQuestionnaireId() {
        return this.studentQuestionnaireId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.student_questionnaire_id</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setStudentQuestionnaireId(@Nonnull UUID studentQuestionnaireId) {
        this.studentQuestionnaireId = studentQuestionnaireId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.student_questionnaire_version</code>.
     */
    @NotNull
    @Nonnull
    public OffsetDateTime getStudentQuestionnaireVersion() {
        return this.studentQuestionnaireVersion;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.student_questionnaire_version</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setStudentQuestionnaireVersion(@Nonnull OffsetDateTime studentQuestionnaireVersion) {
        this.studentQuestionnaireVersion = studentQuestionnaireVersion;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.order</code>.
     */
    @NotNull
    @Nonnull
    public Integer getOrder() {
        return this.order;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.order</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setOrder(@Nonnull Integer order) {
        this.order = order;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return this.titleI18nId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.title_i18n_id</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setTitleI18nId(@Nullable UUID titleI18nId) {
        this.titleI18nId = titleI18nId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.created_on</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.created_by</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.updated_on</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_section.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_section.updated_by</code>.
     */
    public _AuditLogStudentQuestionnaireSectionPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogStudentQuestionnaireSectionPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(studentQuestionnaireId);
        sb.append(", ").append(studentQuestionnaireVersion);
        sb.append(", ").append(order);
        sb.append(", ").append(titleI18nId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
