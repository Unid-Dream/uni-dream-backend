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

import unid.jooqMono.model.enums.UserRoleEnum;


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
public class _AuditLogUserPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private UUID           lastNameI18nId;
    private UUID           fistNameI18nId;
    private UserRoleEnum   userRole;
    private String         loginId;
    private String         loginPassword;
    private String         email;
    private Boolean        emailVerified;
    private String         googleClientId;
    private String         tencentWechatClientId;
    private Boolean        deleted;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public _AuditLogUserPojo() {}

    public _AuditLogUserPojo(_AuditLogUserPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.lastNameI18nId = value.lastNameI18nId;
        this.fistNameI18nId = value.fistNameI18nId;
        this.userRole = value.userRole;
        this.loginId = value.loginId;
        this.loginPassword = value.loginPassword;
        this.email = value.email;
        this.emailVerified = value.emailVerified;
        this.googleClientId = value.googleClientId;
        this.tencentWechatClientId = value.tencentWechatClientId;
        this.deleted = value.deleted;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "lastNameI18nId", "fistNameI18nId", "userRole", "loginId", "loginPassword", "email", "emailVerified", "googleClientId", "tencentWechatClientId", "deleted", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogUserPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nullable UUID           lastNameI18nId,
        @Nullable UUID           fistNameI18nId,
        @Nonnull UserRoleEnum   userRole,
        @Nullable String         loginId,
        @Nullable String         loginPassword,
        @Nullable String         email,
        @Nullable Boolean        emailVerified,
        @Nullable String         googleClientId,
        @Nullable String         tencentWechatClientId,
        @Nullable Boolean        deleted,
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
        this.lastNameI18nId = lastNameI18nId;
        this.fistNameI18nId = fistNameI18nId;
        this.userRole = userRole;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.email = email;
        this.emailVerified = emailVerified;
        this.googleClientId = googleClientId;
        this.tencentWechatClientId = tencentWechatClientId;
        this.deleted = deleted;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for <code>public._audit_log_user.audit_seq</code>.
     */
    public _AuditLogUserPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for <code>public._audit_log_user.audit_createdon</code>.
     */
    public _AuditLogUserPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for <code>public._audit_log_user.audit_createdby</code>.
     */
    public _AuditLogUserPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for <code>public._audit_log_user.audit_operation</code>.
     */
    public _AuditLogUserPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for <code>public._audit_log_user.audit_type</code>.
     */
    public _AuditLogUserPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public._audit_log_user.id</code>.
     */
    public _AuditLogUserPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.last_name_i18n_id</code>.
     */
    @Nullable
    public UUID getLastNameI18nId() {
        return this.lastNameI18nId;
    }

    /**
     * Setter for <code>public._audit_log_user.last_name_i18n_id</code>.
     */
    public _AuditLogUserPojo setLastNameI18nId(@Nullable UUID lastNameI18nId) {
        this.lastNameI18nId = lastNameI18nId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.fist_name_i18n_id</code>.
     */
    @Nullable
    public UUID getFistNameI18nId() {
        return this.fistNameI18nId;
    }

    /**
     * Setter for <code>public._audit_log_user.fist_name_i18n_id</code>.
     */
    public _AuditLogUserPojo setFistNameI18nId(@Nullable UUID fistNameI18nId) {
        this.fistNameI18nId = fistNameI18nId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.user_role</code>.
     */
    @NotNull
    @Nonnull
    public UserRoleEnum getUserRole() {
        return this.userRole;
    }

    /**
     * Setter for <code>public._audit_log_user.user_role</code>.
     */
    public _AuditLogUserPojo setUserRole(@Nonnull UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.login_id</code>.
     */
    @Nullable
    public String getLoginId() {
        return this.loginId;
    }

    /**
     * Setter for <code>public._audit_log_user.login_id</code>.
     */
    public _AuditLogUserPojo setLoginId(@Nullable String loginId) {
        this.loginId = loginId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.login_password</code>.
     */
    @Nullable
    public String getLoginPassword() {
        return this.loginPassword;
    }

    /**
     * Setter for <code>public._audit_log_user.login_password</code>.
     */
    public _AuditLogUserPojo setLoginPassword(@Nullable String loginPassword) {
        this.loginPassword = loginPassword;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.email</code>.
     */
    @Nullable
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>public._audit_log_user.email</code>.
     */
    public _AuditLogUserPojo setEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.email_verified</code>.
     */
    @Nullable
    public Boolean getEmailVerified() {
        return this.emailVerified;
    }

    /**
     * Setter for <code>public._audit_log_user.email_verified</code>.
     */
    public _AuditLogUserPojo setEmailVerified(@Nullable Boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.google_client_id</code>.
     */
    @Nullable
    public String getGoogleClientId() {
        return this.googleClientId;
    }

    /**
     * Setter for <code>public._audit_log_user.google_client_id</code>.
     */
    public _AuditLogUserPojo setGoogleClientId(@Nullable String googleClientId) {
        this.googleClientId = googleClientId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.tencent_wechat_client_id</code>.
     */
    @Nullable
    public String getTencentWechatClientId() {
        return this.tencentWechatClientId;
    }

    /**
     * Setter for <code>public._audit_log_user.tencent_wechat_client_id</code>.
     */
    public _AuditLogUserPojo setTencentWechatClientId(@Nullable String tencentWechatClientId) {
        this.tencentWechatClientId = tencentWechatClientId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.deleted</code>.
     */
    @Nullable
    public Boolean getDeleted() {
        return this.deleted;
    }

    /**
     * Setter for <code>public._audit_log_user.deleted</code>.
     */
    public _AuditLogUserPojo setDeleted(@Nullable Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public._audit_log_user.created_on</code>.
     */
    public _AuditLogUserPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public._audit_log_user.created_by</code>.
     */
    public _AuditLogUserPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public._audit_log_user.updated_on</code>.
     */
    public _AuditLogUserPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public._audit_log_user.updated_by</code>.
     */
    public _AuditLogUserPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogUserPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(lastNameI18nId);
        sb.append(", ").append(fistNameI18nId);
        sb.append(", ").append(userRole);
        sb.append(", ").append(loginId);
        sb.append(", ").append(loginPassword);
        sb.append(", ").append(email);
        sb.append(", ").append(emailVerified);
        sb.append(", ").append(googleClientId);
        sb.append(", ").append(tencentWechatClientId);
        sb.append(", ").append(deleted);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
