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
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.tables._AuditLogOpportunityTable;
import unid.jooqMono.model.tables.pojos._AuditLogOpportunityPojo;


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
public class _AuditLogOpportunityRecord extends TableRecordImpl<_AuditLogOpportunityRecord> implements Record11<Long, OffsetDateTime, String, String, String, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public._audit_log_opportunity.audit_seq</code>.
     */
    public _AuditLogOpportunityRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.audit_createdon</code>.
     */
    public _AuditLogOpportunityRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.audit_createdby</code>.
     */
    public _AuditLogOpportunityRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.audit_operation</code>.
     */
    public _AuditLogOpportunityRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.audit_type</code>.
     */
    public _AuditLogOpportunityRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.id</code>.
     */
    public _AuditLogOpportunityRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.title_i18n_id</code>.
     */
    public _AuditLogOpportunityRecord setTitleI18nId(@Nullable UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return (UUID) get(6);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.created_on</code>.
     */
    public _AuditLogOpportunityRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(7);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.created_by</code>.
     */
    public _AuditLogOpportunityRecord setCreatedBy(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.updated_on</code>.
     */
    public _AuditLogOpportunityRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(9);
    }

    /**
     * Setter for <code>public._audit_log_opportunity.updated_by</code>.
     */
    public _AuditLogOpportunityRecord setUpdatedBy(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_opportunity.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row11<Long, OffsetDateTime, String, String, String, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row11<Long, OffsetDateTime, String, String, String, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.TITLE_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field8() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field9() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field10() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field11() {
        return _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY.UPDATED_BY;
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
        return getTitleI18nId();
    }

    @Override
    @Nullable
    public OffsetDateTime component8() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component9() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component10() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component11() {
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
        return getTitleI18nId();
    }

    @Override
    @Nullable
    public OffsetDateTime value8() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value9() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value10() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value11() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value7(@Nullable UUID value) {
        setTitleI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value8(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value9(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value10(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord value11(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogOpportunityRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nullable UUID value7, @Nullable OffsetDateTime value8, @Nullable String value9, @Nullable OffsetDateTime value10, @Nullable String value11) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogOpportunityRecord
     */
    public _AuditLogOpportunityRecord() {
        super(_AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY);
    }

    /**
     * Create a detached, initialised _AuditLogOpportunityRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "titleI18nId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogOpportunityRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nullable UUID titleI18nId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setTitleI18nId(titleI18nId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised _AuditLogOpportunityRecord
     */
    public _AuditLogOpportunityRecord(_AuditLogOpportunityPojo value) {
        super(_AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setTitleI18nId(value.getTitleI18nId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
