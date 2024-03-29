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
public class _AuditLogI18nPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private String         english;
    private String         chineseTraditional;
    private String         chineseSimplified;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public _AuditLogI18nPojo() {}

    public _AuditLogI18nPojo(_AuditLogI18nPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.english = value.english;
        this.chineseTraditional = value.chineseTraditional;
        this.chineseSimplified = value.chineseSimplified;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "english", "chineseTraditional", "chineseSimplified", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogI18nPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nullable String         english,
        @Nullable String         chineseTraditional,
        @Nullable String         chineseSimplified,
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
        this.english = english;
        this.chineseTraditional = chineseTraditional;
        this.chineseSimplified = chineseSimplified;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public._audit_log_i18n.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for <code>public._audit_log_i18n.audit_seq</code>.
     */
    public _AuditLogI18nPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for <code>public._audit_log_i18n.audit_createdon</code>.
     */
    public _AuditLogI18nPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for <code>public._audit_log_i18n.audit_createdby</code>.
     */
    public _AuditLogI18nPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for <code>public._audit_log_i18n.audit_operation</code>.
     */
    public _AuditLogI18nPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for <code>public._audit_log_i18n.audit_type</code>.
     */
    public _AuditLogI18nPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public._audit_log_i18n.id</code>.
     */
    public _AuditLogI18nPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.english</code>.
     */
    @Nullable
    public String getEnglish() {
        return this.english;
    }

    /**
     * Setter for <code>public._audit_log_i18n.english</code>.
     */
    public _AuditLogI18nPojo setEnglish(@Nullable String english) {
        this.english = english;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.chinese_traditional</code>.
     */
    @Nullable
    public String getChineseTraditional() {
        return this.chineseTraditional;
    }

    /**
     * Setter for <code>public._audit_log_i18n.chinese_traditional</code>.
     */
    public _AuditLogI18nPojo setChineseTraditional(@Nullable String chineseTraditional) {
        this.chineseTraditional = chineseTraditional;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.chinese_simplified</code>.
     */
    @Nullable
    public String getChineseSimplified() {
        return this.chineseSimplified;
    }

    /**
     * Setter for <code>public._audit_log_i18n.chinese_simplified</code>.
     */
    public _AuditLogI18nPojo setChineseSimplified(@Nullable String chineseSimplified) {
        this.chineseSimplified = chineseSimplified;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public._audit_log_i18n.created_on</code>.
     */
    public _AuditLogI18nPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public._audit_log_i18n.created_by</code>.
     */
    public _AuditLogI18nPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public._audit_log_i18n.updated_on</code>.
     */
    public _AuditLogI18nPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_i18n.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public._audit_log_i18n.updated_by</code>.
     */
    public _AuditLogI18nPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogI18nPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(english);
        sb.append(", ").append(chineseTraditional);
        sb.append(", ").append(chineseSimplified);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
