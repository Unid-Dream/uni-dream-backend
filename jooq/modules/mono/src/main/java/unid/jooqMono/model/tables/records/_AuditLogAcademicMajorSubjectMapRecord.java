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

import unid.jooqMono.model.tables._AuditLogAcademicMajorSubjectMapTable;
import unid.jooqMono.model.tables.pojos._AuditLogAcademicMajorSubjectMapPojo;


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
public class _AuditLogAcademicMajorSubjectMapRecord extends TableRecordImpl<_AuditLogAcademicMajorSubjectMapRecord> implements Record12<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.audit_seq</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.audit_createdon</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.audit_createdby</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.audit_operation</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.audit_type</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_academic_major_subject_map.id</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_academic_major_subject_map.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.academic_major_id</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setAcademicMajorId(@Nonnull UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.academic_major_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getAcademicMajorId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.academic_subject_id</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setAcademicSubjectId(@Nonnull UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.academic_subject_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getAcademicSubjectId() {
        return (UUID) get(7);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.created_on</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(8);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.created_by</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setCreatedBy(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(9);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.updated_on</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(10);
    }

    /**
     * Setter for
     * <code>public._audit_log_academic_major_subject_map.updated_by</code>.
     */
    public _AuditLogAcademicMajorSubjectMapRecord setUpdatedBy(@Nullable String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_academic_major_subject_map.updated_by</code>.
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
    public Row12<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row12<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_MAJOR_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field8() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.ACADEMIC_SUBJECT_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field9() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field11() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field12() {
        return _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP.UPDATED_BY;
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
    @Nonnull
    public UUID component7() {
        return getAcademicMajorId();
    }

    @Override
    @Nonnull
    public UUID component8() {
        return getAcademicSubjectId();
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
    @Nonnull
    public UUID value7() {
        return getAcademicMajorId();
    }

    @Override
    @Nonnull
    public UUID value8() {
        return getAcademicSubjectId();
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
    public _AuditLogAcademicMajorSubjectMapRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value7(@Nonnull UUID value) {
        setAcademicMajorId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value8(@Nonnull UUID value) {
        setAcademicSubjectId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value9(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value10(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value11(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord value12(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogAcademicMajorSubjectMapRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nonnull UUID value7, @Nonnull UUID value8, @Nullable OffsetDateTime value9, @Nullable String value10, @Nullable OffsetDateTime value11, @Nullable String value12) {
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
     * Create a detached _AuditLogAcademicMajorSubjectMapRecord
     */
    public _AuditLogAcademicMajorSubjectMapRecord() {
        super(_AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP);
    }

    /**
     * Create a detached, initialised _AuditLogAcademicMajorSubjectMapRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "academicMajorId", "academicSubjectId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogAcademicMajorSubjectMapRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nonnull UUID academicMajorId, @Nonnull UUID academicSubjectId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setAcademicMajorId(academicMajorId);
        setAcademicSubjectId(academicSubjectId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised _AuditLogAcademicMajorSubjectMapRecord
     */
    public _AuditLogAcademicMajorSubjectMapRecord(_AuditLogAcademicMajorSubjectMapPojo value) {
        super(_AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setAcademicMajorId(value.getAcademicMajorId());
            setAcademicSubjectId(value.getAcademicSubjectId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
