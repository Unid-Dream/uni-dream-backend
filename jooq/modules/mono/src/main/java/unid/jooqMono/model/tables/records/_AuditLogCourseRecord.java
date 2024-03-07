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
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.tables._AuditLogCourseTable;
import unid.jooqMono.model.tables.pojos._AuditLogCoursePojo;


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
public class _AuditLogCourseRecord extends TableRecordImpl<_AuditLogCourseRecord> implements Record13<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public._audit_log_course.audit_seq</code>.
     */
    public _AuditLogCourseRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public._audit_log_course.audit_createdon</code>.
     */
    public _AuditLogCourseRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public._audit_log_course.audit_createdby</code>.
     */
    public _AuditLogCourseRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public._audit_log_course.audit_operation</code>.
     */
    public _AuditLogCourseRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public._audit_log_course.audit_type</code>.
     */
    public _AuditLogCourseRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_course.id</code>.
     */
    public _AuditLogCourseRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public._audit_log_course.title_i18n_id</code>.
     */
    public _AuditLogCourseRecord setTitleI18nId(@Nullable UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return (UUID) get(6);
    }

    /**
     * Setter for <code>public._audit_log_course.description_i18n_id</code>.
     */
    public _AuditLogCourseRecord setDescriptionI18nId(@Nullable UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(7);
    }

    /**
     * Setter for <code>public._audit_log_course.academic_major_id</code>.
     */
    public _AuditLogCourseRecord setAcademicMajorId(@Nullable UUID value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.academic_major_id</code>.
     */
    @Nullable
    public UUID getAcademicMajorId() {
        return (UUID) get(8);
    }

    /**
     * Setter for <code>public._audit_log_course.created_on</code>.
     */
    public _AuditLogCourseRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(9);
    }

    /**
     * Setter for <code>public._audit_log_course.created_by</code>.
     */
    public _AuditLogCourseRecord setCreatedBy(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public._audit_log_course.updated_on</code>.
     */
    public _AuditLogCourseRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(11);
    }

    /**
     * Setter for <code>public._audit_log_course.updated_by</code>.
     */
    public _AuditLogCourseRecord setUpdatedBy(@Nullable String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_course.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(12);
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row13<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row13<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row13) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.TITLE_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field8() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.DESCRIPTION_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field9() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.ACADEMIC_MAJOR_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field10() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field11() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field12() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field13() {
        return _AuditLogCourseTable._AUDIT_LOG_COURSE.UPDATED_BY;
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
    public UUID component8() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID component9() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public OffsetDateTime component10() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component11() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component12() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component13() {
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
    public UUID value8() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID value9() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public OffsetDateTime value10() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value11() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value12() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value13() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value7(@Nullable UUID value) {
        setTitleI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value8(@Nullable UUID value) {
        setDescriptionI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value9(@Nullable UUID value) {
        setAcademicMajorId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value10(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value11(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value12(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord value13(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogCourseRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nullable UUID value7, @Nullable UUID value8, @Nullable UUID value9, @Nullable OffsetDateTime value10, @Nullable String value11, @Nullable OffsetDateTime value12, @Nullable String value13) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogCourseRecord
     */
    public _AuditLogCourseRecord() {
        super(_AuditLogCourseTable._AUDIT_LOG_COURSE);
    }

    /**
     * Create a detached, initialised _AuditLogCourseRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "titleI18nId", "descriptionI18nId", "academicMajorId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogCourseRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nullable UUID titleI18nId, @Nullable UUID descriptionI18nId, @Nullable UUID academicMajorId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogCourseTable._AUDIT_LOG_COURSE);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setTitleI18nId(titleI18nId);
        setDescriptionI18nId(descriptionI18nId);
        setAcademicMajorId(academicMajorId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised _AuditLogCourseRecord
     */
    public _AuditLogCourseRecord(_AuditLogCoursePojo value) {
        super(_AuditLogCourseTable._AUDIT_LOG_COURSE);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setTitleI18nId(value.getTitleI18nId());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setAcademicMajorId(value.getAcademicMajorId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
