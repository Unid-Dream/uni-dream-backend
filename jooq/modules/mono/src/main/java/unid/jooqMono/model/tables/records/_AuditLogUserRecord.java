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

import org.jooq.Field;
import org.jooq.Record20;
import org.jooq.Row20;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables._AuditLogUserTable;
import unid.jooqMono.model.tables.pojos._AuditLogUserPojo;


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
public class _AuditLogUserRecord extends TableRecordImpl<_AuditLogUserRecord> implements Record20<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UserRoleEnum, String, String, String, Boolean, String, String, Boolean, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public._audit_log_user.audit_seq</code>.
     */
    public _AuditLogUserRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public._audit_log_user.audit_createdon</code>.
     */
    public _AuditLogUserRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public._audit_log_user.audit_createdby</code>.
     */
    public _AuditLogUserRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public._audit_log_user.audit_operation</code>.
     */
    public _AuditLogUserRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public._audit_log_user.audit_type</code>.
     */
    public _AuditLogUserRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_user.id</code>.
     */
    public _AuditLogUserRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public._audit_log_user.last_name_i18n_id</code>.
     */
    public _AuditLogUserRecord setLastNameI18nId(@Nullable UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.last_name_i18n_id</code>.
     */
    @Nullable
    public UUID getLastNameI18nId() {
        return (UUID) get(6);
    }

    /**
     * Setter for <code>public._audit_log_user.fist_name_i18n_id</code>.
     */
    public _AuditLogUserRecord setFistNameI18nId(@Nullable UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.fist_name_i18n_id</code>.
     */
    @Nullable
    public UUID getFistNameI18nId() {
        return (UUID) get(7);
    }

    /**
     * Setter for <code>public._audit_log_user.user_role</code>.
     */
    public _AuditLogUserRecord setUserRole(@Nonnull UserRoleEnum value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.user_role</code>.
     */
    @NotNull
    @Nonnull
    public UserRoleEnum getUserRole() {
        return (UserRoleEnum) get(8);
    }

    /**
     * Setter for <code>public._audit_log_user.login_id</code>.
     */
    public _AuditLogUserRecord setLoginId(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.login_id</code>.
     */
    @Nullable
    public String getLoginId() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public._audit_log_user.login_password</code>.
     */
    public _AuditLogUserRecord setLoginPassword(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.login_password</code>.
     */
    @Nullable
    public String getLoginPassword() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public._audit_log_user.email</code>.
     */
    public _AuditLogUserRecord setEmail(@Nullable String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.email</code>.
     */
    @Nullable
    public String getEmail() {
        return (String) get(11);
    }

    /**
     * Setter for <code>public._audit_log_user.email_verified</code>.
     */
    public _AuditLogUserRecord setEmailVerified(@Nullable Boolean value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.email_verified</code>.
     */
    @Nullable
    public Boolean getEmailVerified() {
        return (Boolean) get(12);
    }

    /**
     * Setter for <code>public._audit_log_user.google_client_id</code>.
     */
    public _AuditLogUserRecord setGoogleClientId(@Nullable String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.google_client_id</code>.
     */
    @Nullable
    public String getGoogleClientId() {
        return (String) get(13);
    }

    /**
     * Setter for <code>public._audit_log_user.tencent_wechat_client_id</code>.
     */
    public _AuditLogUserRecord setTencentWechatClientId(@Nullable String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.tencent_wechat_client_id</code>.
     */
    @Nullable
    public String getTencentWechatClientId() {
        return (String) get(14);
    }

    /**
     * Setter for <code>public._audit_log_user.deleted</code>.
     */
    public _AuditLogUserRecord setDeleted(@Nullable Boolean value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.deleted</code>.
     */
    @Nullable
    public Boolean getDeleted() {
        return (Boolean) get(15);
    }

    /**
     * Setter for <code>public._audit_log_user.created_on</code>.
     */
    public _AuditLogUserRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(16);
    }

    /**
     * Setter for <code>public._audit_log_user.created_by</code>.
     */
    public _AuditLogUserRecord setCreatedBy(@Nullable String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(17);
    }

    /**
     * Setter for <code>public._audit_log_user.updated_on</code>.
     */
    public _AuditLogUserRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(18);
    }

    /**
     * Setter for <code>public._audit_log_user.updated_by</code>.
     */
    public _AuditLogUserRecord setUpdatedBy(@Nullable String value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_user.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(19);
    }

    // -------------------------------------------------------------------------
    // Record20 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row20<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UserRoleEnum, String, String, String, Boolean, String, String, Boolean, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row20) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row20<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UserRoleEnum, String, String, String, Boolean, String, String, Boolean, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row20) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogUserTable._AUDIT_LOG_USER.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogUserTable._AUDIT_LOG_USER.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogUserTable._AUDIT_LOG_USER.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogUserTable._AUDIT_LOG_USER.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogUserTable._AUDIT_LOG_USER.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogUserTable._AUDIT_LOG_USER.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogUserTable._AUDIT_LOG_USER.LAST_NAME_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field8() {
        return _AuditLogUserTable._AUDIT_LOG_USER.FIST_NAME_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UserRoleEnum> field9() {
        return _AuditLogUserTable._AUDIT_LOG_USER.USER_ROLE;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return _AuditLogUserTable._AUDIT_LOG_USER.LOGIN_ID;
    }

    @Override
    @Nonnull
    public Field<String> field11() {
        return _AuditLogUserTable._AUDIT_LOG_USER.LOGIN_PASSWORD;
    }

    @Override
    @Nonnull
    public Field<String> field12() {
        return _AuditLogUserTable._AUDIT_LOG_USER.EMAIL;
    }

    @Override
    @Nonnull
    public Field<Boolean> field13() {
        return _AuditLogUserTable._AUDIT_LOG_USER.EMAIL_VERIFIED;
    }

    @Override
    @Nonnull
    public Field<String> field14() {
        return _AuditLogUserTable._AUDIT_LOG_USER.GOOGLE_CLIENT_ID;
    }

    @Override
    @Nonnull
    public Field<String> field15() {
        return _AuditLogUserTable._AUDIT_LOG_USER.TENCENT_WECHAT_CLIENT_ID;
    }

    @Override
    @Nonnull
    public Field<Boolean> field16() {
        return _AuditLogUserTable._AUDIT_LOG_USER.DELETED;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field17() {
        return _AuditLogUserTable._AUDIT_LOG_USER.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field18() {
        return _AuditLogUserTable._AUDIT_LOG_USER.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field19() {
        return _AuditLogUserTable._AUDIT_LOG_USER.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field20() {
        return _AuditLogUserTable._AUDIT_LOG_USER.UPDATED_BY;
    }

    @Override
    @Nonnull
    public Long component1() {
        return getAuditSeq();
    }

    @Override
    @Nonnull
    public OffsetDateTime component2() {
        return getAuditCreatedon();
    }

    @Override
    @Nonnull
    public String component3() {
        return getAuditCreatedby();
    }

    @Override
    @Nonnull
    public String component4() {
        return getAuditOperation();
    }

    @Override
    @Nonnull
    public String component5() {
        return getAuditType();
    }

    @Override
    @Nonnull
    public UUID component6() {
        return getId();
    }

    @Override
    @Nullable
    public UUID component7() {
        return getLastNameI18nId();
    }

    @Override
    @Nullable
    public UUID component8() {
        return getFistNameI18nId();
    }

    @Override
    @Nonnull
    public UserRoleEnum component9() {
        return getUserRole();
    }

    @Override
    @Nullable
    public String component10() {
        return getLoginId();
    }

    @Override
    @Nullable
    public String component11() {
        return getLoginPassword();
    }

    @Override
    @Nullable
    public String component12() {
        return getEmail();
    }

    @Override
    @Nullable
    public Boolean component13() {
        return getEmailVerified();
    }

    @Override
    @Nullable
    public String component14() {
        return getGoogleClientId();
    }

    @Override
    @Nullable
    public String component15() {
        return getTencentWechatClientId();
    }

    @Override
    @Nullable
    public Boolean component16() {
        return getDeleted();
    }

    @Override
    @Nullable
    public OffsetDateTime component17() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component18() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component19() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component20() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public Long value1() {
        return getAuditSeq();
    }

    @Override
    @Nonnull
    public OffsetDateTime value2() {
        return getAuditCreatedon();
    }

    @Override
    @Nonnull
    public String value3() {
        return getAuditCreatedby();
    }

    @Override
    @Nonnull
    public String value4() {
        return getAuditOperation();
    }

    @Override
    @Nonnull
    public String value5() {
        return getAuditType();
    }

    @Override
    @Nonnull
    public UUID value6() {
        return getId();
    }

    @Override
    @Nullable
    public UUID value7() {
        return getLastNameI18nId();
    }

    @Override
    @Nullable
    public UUID value8() {
        return getFistNameI18nId();
    }

    @Override
    @Nonnull
    public UserRoleEnum value9() {
        return getUserRole();
    }

    @Override
    @Nullable
    public String value10() {
        return getLoginId();
    }

    @Override
    @Nullable
    public String value11() {
        return getLoginPassword();
    }

    @Override
    @Nullable
    public String value12() {
        return getEmail();
    }

    @Override
    @Nullable
    public Boolean value13() {
        return getEmailVerified();
    }

    @Override
    @Nullable
    public String value14() {
        return getGoogleClientId();
    }

    @Override
    @Nullable
    public String value15() {
        return getTencentWechatClientId();
    }

    @Override
    @Nullable
    public Boolean value16() {
        return getDeleted();
    }

    @Override
    @Nullable
    public OffsetDateTime value17() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value18() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value19() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value20() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value7(@Nullable UUID value) {
        setLastNameI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value8(@Nullable UUID value) {
        setFistNameI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value9(@Nonnull UserRoleEnum value) {
        setUserRole(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value10(@Nullable String value) {
        setLoginId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value11(@Nullable String value) {
        setLoginPassword(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value12(@Nullable String value) {
        setEmail(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value13(@Nullable Boolean value) {
        setEmailVerified(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value14(@Nullable String value) {
        setGoogleClientId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value15(@Nullable String value) {
        setTencentWechatClientId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value16(@Nullable Boolean value) {
        setDeleted(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value17(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value18(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value19(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord value20(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogUserRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nullable UUID value7, @Nullable UUID value8, @Nonnull UserRoleEnum value9, @Nullable String value10, @Nullable String value11, @Nullable String value12, @Nullable Boolean value13, @Nullable String value14, @Nullable String value15, @Nullable Boolean value16, @Nullable OffsetDateTime value17, @Nullable String value18, @Nullable OffsetDateTime value19, @Nullable String value20) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogUserRecord
     */
    public _AuditLogUserRecord() {
        super(_AuditLogUserTable._AUDIT_LOG_USER);
    }

    /**
     * Create a detached, initialised _AuditLogUserRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "lastNameI18nId", "fistNameI18nId", "userRole", "loginId", "loginPassword", "email", "emailVerified", "googleClientId", "tencentWechatClientId", "deleted", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogUserRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nullable UUID lastNameI18nId, @Nullable UUID fistNameI18nId, @Nonnull UserRoleEnum userRole, @Nullable String loginId, @Nullable String loginPassword, @Nullable String email, @Nullable Boolean emailVerified, @Nullable String googleClientId, @Nullable String tencentWechatClientId, @Nullable Boolean deleted, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogUserTable._AUDIT_LOG_USER);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setLastNameI18nId(lastNameI18nId);
        setFistNameI18nId(fistNameI18nId);
        setUserRole(userRole);
        setLoginId(loginId);
        setLoginPassword(loginPassword);
        setEmail(email);
        setEmailVerified(emailVerified);
        setGoogleClientId(googleClientId);
        setTencentWechatClientId(tencentWechatClientId);
        setDeleted(deleted);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised _AuditLogUserRecord
     */
    public _AuditLogUserRecord(_AuditLogUserPojo value) {
        super(_AuditLogUserTable._AUDIT_LOG_USER);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setLastNameI18nId(value.getLastNameI18nId());
            setFistNameI18nId(value.getFistNameI18nId());
            setUserRole(value.getUserRole());
            setLoginId(value.getLoginId());
            setLoginPassword(value.getLoginPassword());
            setEmail(value.getEmail());
            setEmailVerified(value.getEmailVerified());
            setGoogleClientId(value.getGoogleClientId());
            setTencentWechatClientId(value.getTencentWechatClientId());
            setDeleted(value.getDeleted());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
