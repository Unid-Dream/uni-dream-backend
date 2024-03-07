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
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables._AuditLogTagTable;
import unid.jooqMono.model.tables.pojos._AuditLogTagPojo;


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
public class _AuditLogTagRecord extends TableRecordImpl<_AuditLogTagRecord> implements Record12<Long, OffsetDateTime, String, String, String, UUID, UUID, TagCategoryEnum, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public._audit_log_tag.audit_seq</code>.
     */
    public _AuditLogTagRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public._audit_log_tag.audit_createdon</code>.
     */
    public _AuditLogTagRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public._audit_log_tag.audit_createdby</code>.
     */
    public _AuditLogTagRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public._audit_log_tag.audit_operation</code>.
     */
    public _AuditLogTagRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public._audit_log_tag.audit_type</code>.
     */
    public _AuditLogTagRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_tag.id</code>.
     */
    public _AuditLogTagRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public._audit_log_tag.description_i18n_id</code>.
     */
    public _AuditLogTagRecord setDescriptionI18nId(@Nullable UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(6);
    }

    /**
     * Setter for <code>public._audit_log_tag.tag_category</code>.
     */
    public _AuditLogTagRecord setTagCategory(@Nullable TagCategoryEnum value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.tag_category</code>.
     */
    @Nullable
    public TagCategoryEnum getTagCategory() {
        return (TagCategoryEnum) get(7);
    }

    /**
     * Setter for <code>public._audit_log_tag.created_on</code>.
     */
    public _AuditLogTagRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(8);
    }

    /**
     * Setter for <code>public._audit_log_tag.created_by</code>.
     */
    public _AuditLogTagRecord setCreatedBy(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public._audit_log_tag.updated_on</code>.
     */
    public _AuditLogTagRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(10);
    }

    /**
     * Setter for <code>public._audit_log_tag.updated_by</code>.
     */
    public _AuditLogTagRecord setUpdatedBy(@Nullable String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_tag.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row12<Long, OffsetDateTime, String, String, String, UUID, UUID, TagCategoryEnum, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row12<Long, OffsetDateTime, String, String, String, UUID, UUID, TagCategoryEnum, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.DESCRIPTION_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<TagCategoryEnum> field8() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.TAG_CATEGORY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field9() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field11() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field12() {
        return _AuditLogTagTable._AUDIT_LOG_TAG.UPDATED_BY;
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
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public TagCategoryEnum component8() {
        return getTagCategory();
    }

    @Override
    @Nullable
    public OffsetDateTime component9() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component10() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component11() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component12() {
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
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public TagCategoryEnum value8() {
        return getTagCategory();
    }

    @Override
    @Nullable
    public OffsetDateTime value9() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value10() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value11() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value12() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value7(@Nullable UUID value) {
        setDescriptionI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value8(@Nullable TagCategoryEnum value) {
        setTagCategory(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value9(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value10(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value11(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord value12(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogTagRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nullable UUID value7, @Nullable TagCategoryEnum value8, @Nullable OffsetDateTime value9, @Nullable String value10, @Nullable OffsetDateTime value11, @Nullable String value12) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogTagRecord
     */
    public _AuditLogTagRecord() {
        super(_AuditLogTagTable._AUDIT_LOG_TAG);
    }

    /**
     * Create a detached, initialised _AuditLogTagRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "descriptionI18nId", "tagCategory", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogTagRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nullable UUID descriptionI18nId, @Nullable TagCategoryEnum tagCategory, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogTagTable._AUDIT_LOG_TAG);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setDescriptionI18nId(descriptionI18nId);
        setTagCategory(tagCategory);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised _AuditLogTagRecord
     */
    public _AuditLogTagRecord(_AuditLogTagPojo value) {
        super(_AuditLogTagTable._AUDIT_LOG_TAG);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setTagCategory(value.getTagCategory());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
