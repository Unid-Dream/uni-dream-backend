/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import org.jooq.Field;
import org.jooq.Record16;
import org.jooq.Row16;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.enums.QuestionnaireAnswerTypeEnum;
import unid.jooqMono.model.tables._AuditLogStudentQuestionnaireAnswerTable;
import unid.jooqMono.model.tables.pojos._AuditLogStudentQuestionnaireAnswerPojo;


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
public class _AuditLogStudentQuestionnaireAnswerRecord extends TableRecordImpl<_AuditLogStudentQuestionnaireAnswerRecord> implements Record16<Long, OffsetDateTime, String, String, String, UUID, UUID, Integer, UUID, UUID, BigDecimal, QuestionnaireAnswerTypeEnum, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.audit_seq</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.audit_createdon</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.audit_createdby</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.audit_operation</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.audit_type</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.id</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.student_questionnaire_question_id</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setStudentQuestionnaireQuestionId(@Nonnull UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.student_questionnaire_question_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentQuestionnaireQuestionId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.order</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setOrder(@Nonnull Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.order</code>.
     */
    @NotNull
    @Nonnull
    public Integer getOrder() {
        return (Integer) get(7);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.description_i18n_id</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setDescriptionI18nId(@Nullable UUID value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(8);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.tag_id</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setTagId(@Nullable UUID value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.tag_id</code>.
     */
    @Nullable
    public UUID getTagId() {
        return (UUID) get(9);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.score</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setScore(@Nullable BigDecimal value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.score</code>.
     */
    @Nullable
    public BigDecimal getScore() {
        return (BigDecimal) get(10);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.type</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setType(@Nonnull QuestionnaireAnswerTypeEnum value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.type</code>.
     */
    @NotNull
    @Nonnull
    public QuestionnaireAnswerTypeEnum getType() {
        return (QuestionnaireAnswerTypeEnum) get(11);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.created_on</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(12);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.created_by</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setCreatedBy(@Nullable String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(13);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.updated_on</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(14);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_questionnaire_answer.updated_by</code>.
     */
    public _AuditLogStudentQuestionnaireAnswerRecord setUpdatedBy(@Nullable String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_questionnaire_answer.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(15);
    }

    // -------------------------------------------------------------------------
    // Record16 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row16<Long, OffsetDateTime, String, String, String, UUID, UUID, Integer, UUID, UUID, BigDecimal, QuestionnaireAnswerTypeEnum, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row16<Long, OffsetDateTime, String, String, String, UUID, UUID, Integer, UUID, UUID, BigDecimal, QuestionnaireAnswerTypeEnum, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row16) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.STUDENT_QUESTIONNAIRE_QUESTION_ID;
    }

    @Override
    @Nonnull
    public Field<Integer> field8() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.ORDER;
    }

    @Override
    @Nonnull
    public Field<UUID> field9() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.DESCRIPTION_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field10() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.TAG_ID;
    }

    @Override
    @Nonnull
    public Field<BigDecimal> field11() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.SCORE;
    }

    @Override
    @Nonnull
    public Field<QuestionnaireAnswerTypeEnum> field12() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.TYPE;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field13() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field14() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field15() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field16() {
        return _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER.UPDATED_BY;
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
        return getStudentQuestionnaireQuestionId();
    }

    @Override
    @Nonnull
    public Integer component8() {
        return getOrder();
    }

    @Override
    @Nullable
    public UUID component9() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID component10() {
        return getTagId();
    }

    @Override
    @Nullable
    public BigDecimal component11() {
        return getScore();
    }

    @Override
    @Nonnull
    public QuestionnaireAnswerTypeEnum component12() {
        return getType();
    }

    @Override
    @Nullable
    public OffsetDateTime component13() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component14() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component15() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component16() {
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
        return getStudentQuestionnaireQuestionId();
    }

    @Override
    @Nonnull
    public Integer value8() {
        return getOrder();
    }

    @Override
    @Nullable
    public UUID value9() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID value10() {
        return getTagId();
    }

    @Override
    @Nullable
    public BigDecimal value11() {
        return getScore();
    }

    @Override
    @Nonnull
    public QuestionnaireAnswerTypeEnum value12() {
        return getType();
    }

    @Override
    @Nullable
    public OffsetDateTime value13() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value14() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value15() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value16() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value7(@Nonnull UUID value) {
        setStudentQuestionnaireQuestionId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value8(@Nonnull Integer value) {
        setOrder(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value9(@Nullable UUID value) {
        setDescriptionI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value10(@Nullable UUID value) {
        setTagId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value11(@Nullable BigDecimal value) {
        setScore(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value12(@Nonnull QuestionnaireAnswerTypeEnum value) {
        setType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value13(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value14(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value15(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord value16(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nonnull UUID value7, @Nonnull Integer value8, @Nullable UUID value9, @Nullable UUID value10, @Nullable BigDecimal value11, @Nonnull QuestionnaireAnswerTypeEnum value12, @Nullable OffsetDateTime value13, @Nullable String value14, @Nullable OffsetDateTime value15, @Nullable String value16) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogStudentQuestionnaireAnswerRecord
     */
    public _AuditLogStudentQuestionnaireAnswerRecord() {
        super(_AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER);
    }

    /**
     * Create a detached, initialised _AuditLogStudentQuestionnaireAnswerRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentQuestionnaireQuestionId", "order", "descriptionI18nId", "tagId", "score", "type", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogStudentQuestionnaireAnswerRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nonnull UUID studentQuestionnaireQuestionId, @Nonnull Integer order, @Nullable UUID descriptionI18nId, @Nullable UUID tagId, @Nullable BigDecimal score, @Nonnull QuestionnaireAnswerTypeEnum type, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(_AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setStudentQuestionnaireQuestionId(studentQuestionnaireQuestionId);
        setOrder(order);
        setDescriptionI18nId(descriptionI18nId);
        setTagId(tagId);
        setScore(score);
        setType(type);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised _AuditLogStudentQuestionnaireAnswerRecord
     */
    public _AuditLogStudentQuestionnaireAnswerRecord(_AuditLogStudentQuestionnaireAnswerPojo value) {
        super(_AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setStudentQuestionnaireQuestionId(value.getStudentQuestionnaireQuestionId());
            setOrder(value.getOrder());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setTagId(value.getTagId());
            setScore(value.getScore());
            setType(value.getType());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
