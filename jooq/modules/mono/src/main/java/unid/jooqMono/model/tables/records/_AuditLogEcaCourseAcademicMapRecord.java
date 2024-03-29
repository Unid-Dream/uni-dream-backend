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

import unid.jooqMono.model.tables._AuditLogEcaCourseAcademicMapTable;
import unid.jooqMono.model.tables.pojos._AuditLogEcaCourseAcademicMapPojo;


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
public class _AuditLogEcaCourseAcademicMapRecord extends TableRecordImpl<_AuditLogEcaCourseAcademicMapRecord> implements Record13<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.audit_seq</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.audit_createdon</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.audit_createdby</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.audit_operation</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.audit_type</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_eca_course_academic_map.id</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_eca_course_academic_map.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.eca_course_id</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setEcaCourseId(@Nonnull UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.eca_course_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getEcaCourseId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.academic_major_id</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setAcademicMajorId(@Nonnull UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.academic_major_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getAcademicMajorId() {
        return (UUID) get(7);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.academic_subject_id</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setAcademicSubjectId(@Nullable UUID value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.academic_subject_id</code>.
     */
    @Nullable
    public UUID getAcademicSubjectId() {
        return (UUID) get(8);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.created_on</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(9);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.created_by</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setCreatedBy(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(10);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.updated_on</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(11);
    }

    /**
     * Setter for
     * <code>public._audit_log_eca_course_academic_map.updated_by</code>.
     */
    public _AuditLogEcaCourseAcademicMapRecord setUpdatedBy(@Nullable String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_eca_course_academic_map.updated_by</code>.
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
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.ECA_COURSE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field8() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.ACADEMIC_MAJOR_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field9() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.ACADEMIC_SUBJECT_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field10() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field11() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field12() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field13() {
        return _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP.UPDATED_BY;
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
        return getEcaCourseId();
    }

    @Override
    @Nonnull
    public UUID component8() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public UUID component9() {
        return getAcademicSubjectId();
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
    @Nonnull
    public UUID value7() {
        return getEcaCourseId();
    }

    @Override
    @Nonnull
    public UUID value8() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public UUID value9() {
        return getAcademicSubjectId();
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
    public _AuditLogEcaCourseAcademicMapRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value7(@Nonnull UUID value) {
        setEcaCourseId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value8(@Nonnull UUID value) {
        setAcademicMajorId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value9(@Nullable UUID value) {
        setAcademicSubjectId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value10(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value11(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value12(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord value13(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nonnull UUID value7, @Nonnull UUID value8, @Nullable UUID value9, @Nullable OffsetDateTime value10, @Nullable String value11, @Nullable OffsetDateTime value12, @Nullable String value13) {
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
     * Create a detached _AuditLogEcaCourseAcademicMapRecord
     */
    public _AuditLogEcaCourseAcademicMapRecord() {
        super(_AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP);
    }

    /**
     * Create a detached, initialised _AuditLogEcaCourseAcademicMapRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "ecaCourseId", "academicMajorId", "academicSubjectId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogEcaCourseAcademicMapRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nonnull UUID ecaCourseId, @Nonnull UUID academicMajorId, @Nullable UUID academicSubjectId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setEcaCourseId(ecaCourseId);
        setAcademicMajorId(academicMajorId);
        setAcademicSubjectId(academicSubjectId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised _AuditLogEcaCourseAcademicMapRecord
     */
    public _AuditLogEcaCourseAcademicMapRecord(_AuditLogEcaCourseAcademicMapPojo value) {
        super(_AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setEcaCourseId(value.getEcaCourseId());
            setAcademicMajorId(value.getAcademicMajorId());
            setAcademicSubjectId(value.getAcademicSubjectId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
