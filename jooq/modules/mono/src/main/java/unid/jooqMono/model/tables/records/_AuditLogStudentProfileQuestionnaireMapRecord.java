/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import org.jooq.Field;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.tables._AuditLogStudentProfileQuestionnaireMapTable;
import unid.jooqMono.model.tables.pojos._AuditLogStudentProfileQuestionnaireMapPojo;


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
public class _AuditLogStudentProfileQuestionnaireMapRecord extends TableRecordImpl<_AuditLogStudentProfileQuestionnaireMapRecord> implements Record15<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, OffsetDateTime, LocalDateTime, Boolean, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_seq</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_createdon</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_createdby</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_operation</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_type</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.id</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_profile_id</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setStudentProfileId(@Nonnull UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_questionnaire_id</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setStudentQuestionnaireId(@Nonnull UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_questionnaire_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentQuestionnaireId() {
        return (UUID) get(7);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_questionnaire_version_asked</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setStudentQuestionnaireVersionAsked(@Nonnull OffsetDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_questionnaire_version_asked</code>.
     */
    @NotNull
    @Nonnull
    public OffsetDateTime getStudentQuestionnaireVersionAsked() {
        return (OffsetDateTime) get(8);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_questionnaire_version_completed</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setStudentQuestionnaireVersionCompleted(@Nullable LocalDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.student_questionnaire_version_completed</code>.
     */
    @Nullable
    public LocalDateTime getStudentQuestionnaireVersionCompleted() {
        return (LocalDateTime) get(9);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.completed</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setCompleted(@Nullable Boolean value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.completed</code>.
     */
    @Nullable
    public Boolean getCompleted() {
        return (Boolean) get(10);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.created_on</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(11);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.created_by</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setCreatedBy(@Nullable String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(12);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.updated_on</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(13);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_profile_questionnaire_map.updated_by</code>.
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord setUpdatedBy(@Nullable String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_profile_questionnaire_map.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(14);
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row15<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, OffsetDateTime, LocalDateTime, Boolean, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row15<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, OffsetDateTime, LocalDateTime, Boolean, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row15) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.STUDENT_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field8() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.STUDENT_QUESTIONNAIRE_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field9() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.STUDENT_QUESTIONNAIRE_VERSION_ASKED;
    }

    @Override
    @Nonnull
    public Field<LocalDateTime> field10() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.STUDENT_QUESTIONNAIRE_VERSION_COMPLETED;
    }

    @Override
    @Nonnull
    public Field<Boolean> field11() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.COMPLETED;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field12() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field13() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field14() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field15() {
        return _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP.UPDATED_BY;
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
        return getStudentProfileId();
    }

    @Override
    @Nonnull
    public UUID component8() {
        return getStudentQuestionnaireId();
    }

    @Override
    @Nonnull
    public OffsetDateTime component9() {
        return getStudentQuestionnaireVersionAsked();
    }

    @Override
    @Nullable
    public LocalDateTime component10() {
        return getStudentQuestionnaireVersionCompleted();
    }

    @Override
    @Nullable
    public Boolean component11() {
        return getCompleted();
    }

    @Override
    @Nullable
    public OffsetDateTime component12() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component13() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component14() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component15() {
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
        return getStudentProfileId();
    }

    @Override
    @Nonnull
    public UUID value8() {
        return getStudentQuestionnaireId();
    }

    @Override
    @Nonnull
    public OffsetDateTime value9() {
        return getStudentQuestionnaireVersionAsked();
    }

    @Override
    @Nullable
    public LocalDateTime value10() {
        return getStudentQuestionnaireVersionCompleted();
    }

    @Override
    @Nullable
    public Boolean value11() {
        return getCompleted();
    }

    @Override
    @Nullable
    public OffsetDateTime value12() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value13() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value14() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value15() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value7(@Nonnull UUID value) {
        setStudentProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value8(@Nonnull UUID value) {
        setStudentQuestionnaireId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value9(@Nonnull OffsetDateTime value) {
        setStudentQuestionnaireVersionAsked(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value10(@Nullable LocalDateTime value) {
        setStudentQuestionnaireVersionCompleted(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value11(@Nullable Boolean value) {
        setCompleted(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value12(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value13(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value14(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord value15(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentProfileQuestionnaireMapRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nonnull UUID value7, @Nonnull UUID value8, @Nonnull OffsetDateTime value9, @Nullable LocalDateTime value10, @Nullable Boolean value11, @Nullable OffsetDateTime value12, @Nullable String value13, @Nullable OffsetDateTime value14, @Nullable String value15) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogStudentProfileQuestionnaireMapRecord
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord() {
        super(_AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP);
    }

    /**
     * Create a detached, initialised
     * _AuditLogStudentProfileQuestionnaireMapRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentProfileId", "studentQuestionnaireId", "studentQuestionnaireVersionAsked", "studentQuestionnaireVersionCompleted", "completed", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogStudentProfileQuestionnaireMapRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nonnull UUID studentProfileId, @Nonnull UUID studentQuestionnaireId, @Nonnull OffsetDateTime studentQuestionnaireVersionAsked, @Nullable LocalDateTime studentQuestionnaireVersionCompleted, @Nullable Boolean completed, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setStudentProfileId(studentProfileId);
        setStudentQuestionnaireId(studentQuestionnaireId);
        setStudentQuestionnaireVersionAsked(studentQuestionnaireVersionAsked);
        setStudentQuestionnaireVersionCompleted(studentQuestionnaireVersionCompleted);
        setCompleted(completed);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised
     * _AuditLogStudentProfileQuestionnaireMapRecord
     */
    public _AuditLogStudentProfileQuestionnaireMapRecord(_AuditLogStudentProfileQuestionnaireMapPojo value) {
        super(_AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setStudentProfileId(value.getStudentProfileId());
            setStudentQuestionnaireId(value.getStudentQuestionnaireId());
            setStudentQuestionnaireVersionAsked(value.getStudentQuestionnaireVersionAsked());
            setStudentQuestionnaireVersionCompleted(value.getStudentQuestionnaireVersionCompleted());
            setCompleted(value.getCompleted());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
