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
import org.jooq.Record19;
import org.jooq.Row19;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.tables._AuditLogStudentUploadedWritingTable;
import unid.jooqMono.model.tables.pojos._AuditLogStudentUploadedWritingPojo;


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
public class _AuditLogStudentUploadedWritingRecord extends TableRecordImpl<_AuditLogStudentUploadedWritingRecord> implements Record19<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String, String[], String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.audit_seq</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.audit_createdon</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.audit_createdby</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.audit_operation</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.audit_type</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_student_uploaded_writing.id</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_student_uploaded_writing.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.student_profile_id</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setStudentProfileId(@Nonnull UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.writing_topic_id</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setWritingTopicId(@Nonnull UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.writing_topic_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getWritingTopicId() {
        return (UUID) get(7);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.payment_transaction_id</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setPaymentTransactionId(@Nonnull UUID value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.payment_transaction_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getPaymentTransactionId() {
        return (UUID) get(8);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.uploaded_file</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setUploadedFile(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.uploaded_file</code>.
     */
    @Nullable
    public String getUploadedFile() {
        return (String) get(9);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.grammar_and_expression_review_id</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setGrammarAndExpressionReviewId(@Nullable UUID value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.grammar_and_expression_review_id</code>.
     */
    @Nullable
    public UUID getGrammarAndExpressionReviewId() {
        return (UUID) get(10);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.content_review_id</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setContentReviewId(@Nullable UUID value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.content_review_id</code>.
     */
    @Nullable
    public UUID getContentReviewId() {
        return (UUID) get(11);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.composition_review_id</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setCompositionReviewId(@Nullable UUID value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.composition_review_id</code>.
     */
    @Nullable
    public UUID getCompositionReviewId() {
        return (UUID) get(12);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.created_on</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(13);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.created_by</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setCreatedBy(@Nullable String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(14);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.updated_on</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(15);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.updated_by</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setUpdatedBy(@Nullable String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(16);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.recommended_activity</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setRecommendedActivity(@Nullable String[] value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.recommended_activity</code>.
     */
    @Nullable
    public String[] getRecommendedActivity() {
        return (String[]) get(17);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_writing.recommendation</code>.
     */
    public _AuditLogStudentUploadedWritingRecord setRecommendation(@Nullable String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_writing.recommendation</code>.
     */
    @Nullable
    public String getRecommendation() {
        return (String) get(18);
    }

    // -------------------------------------------------------------------------
    // Record19 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row19<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String, String[], String> fieldsRow() {
        return (Row19) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row19<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String, String[], String> valuesRow() {
        return (Row19) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.STUDENT_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field8() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.WRITING_TOPIC_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field9() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.PAYMENT_TRANSACTION_ID;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.UPLOADED_FILE;
    }

    @Override
    @Nonnull
    public Field<UUID> field11() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.GRAMMAR_AND_EXPRESSION_REVIEW_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field12() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.CONTENT_REVIEW_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field13() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.COMPOSITION_REVIEW_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field14() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field15() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field16() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field17() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.UPDATED_BY;
    }

    @Override
    @Nonnull
    public Field<String[]> field18() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.RECOMMENDED_ACTIVITY;
    }

    @Override
    @Nonnull
    public Field<String> field19() {
        return _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING.RECOMMENDATION;
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
        return getWritingTopicId();
    }

    @Override
    @Nonnull
    public UUID component9() {
        return getPaymentTransactionId();
    }

    @Override
    @Nullable
    public String component10() {
        return getUploadedFile();
    }

    @Override
    @Nullable
    public UUID component11() {
        return getGrammarAndExpressionReviewId();
    }

    @Override
    @Nullable
    public UUID component12() {
        return getContentReviewId();
    }

    @Override
    @Nullable
    public UUID component13() {
        return getCompositionReviewId();
    }

    @Override
    @Nullable
    public OffsetDateTime component14() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component15() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component16() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component17() {
        return getUpdatedBy();
    }

    @Override
    @Nullable
    public String[] component18() {
        return getRecommendedActivity();
    }

    @Override
    @Nullable
    public String component19() {
        return getRecommendation();
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
        return getWritingTopicId();
    }

    @Override
    @Nonnull
    public UUID value9() {
        return getPaymentTransactionId();
    }

    @Override
    @Nullable
    public String value10() {
        return getUploadedFile();
    }

    @Override
    @Nullable
    public UUID value11() {
        return getGrammarAndExpressionReviewId();
    }

    @Override
    @Nullable
    public UUID value12() {
        return getContentReviewId();
    }

    @Override
    @Nullable
    public UUID value13() {
        return getCompositionReviewId();
    }

    @Override
    @Nullable
    public OffsetDateTime value14() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value15() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value16() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value17() {
        return getUpdatedBy();
    }

    @Override
    @Nullable
    public String[] value18() {
        return getRecommendedActivity();
    }

    @Override
    @Nullable
    public String value19() {
        return getRecommendation();
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value7(@Nonnull UUID value) {
        setStudentProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value8(@Nonnull UUID value) {
        setWritingTopicId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value9(@Nonnull UUID value) {
        setPaymentTransactionId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value10(@Nullable String value) {
        setUploadedFile(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value11(@Nullable UUID value) {
        setGrammarAndExpressionReviewId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value12(@Nullable UUID value) {
        setContentReviewId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value13(@Nullable UUID value) {
        setCompositionReviewId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value14(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value15(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value16(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value17(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value18(@Nullable String[] value) {
        setRecommendedActivity(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord value19(@Nullable String value) {
        setRecommendation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedWritingRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nonnull UUID value7, @Nonnull UUID value8, @Nonnull UUID value9, @Nullable String value10, @Nullable UUID value11, @Nullable UUID value12, @Nullable UUID value13, @Nullable OffsetDateTime value14, @Nullable String value15, @Nullable OffsetDateTime value16, @Nullable String value17, @Nullable String[] value18, @Nullable String value19) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogStudentUploadedWritingRecord
     */
    public _AuditLogStudentUploadedWritingRecord() {
        super(_AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING);
    }

    /**
     * Create a detached, initialised _AuditLogStudentUploadedWritingRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentProfileId", "writingTopicId", "paymentTransactionId", "uploadedFile", "grammarAndExpressionReviewId", "contentReviewId", "compositionReviewId", "createdOn", "createdBy", "updatedOn", "updatedBy", "recommendedActivity", "recommendation" })
    public _AuditLogStudentUploadedWritingRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nonnull UUID studentProfileId, @Nonnull UUID writingTopicId, @Nonnull UUID paymentTransactionId, @Nullable String uploadedFile, @Nullable UUID grammarAndExpressionReviewId, @Nullable UUID contentReviewId, @Nullable UUID compositionReviewId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable String[] recommendedActivity, @Nullable String recommendation) {
        super(_AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setStudentProfileId(studentProfileId);
        setWritingTopicId(writingTopicId);
        setPaymentTransactionId(paymentTransactionId);
        setUploadedFile(uploadedFile);
        setGrammarAndExpressionReviewId(grammarAndExpressionReviewId);
        setContentReviewId(contentReviewId);
        setCompositionReviewId(compositionReviewId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
        setRecommendedActivity(recommendedActivity);
        setRecommendation(recommendation);
    }

    /**
     * Create a detached, initialised _AuditLogStudentUploadedWritingRecord
     */
    public _AuditLogStudentUploadedWritingRecord(_AuditLogStudentUploadedWritingPojo value) {
        super(_AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setStudentProfileId(value.getStudentProfileId());
            setWritingTopicId(value.getWritingTopicId());
            setPaymentTransactionId(value.getPaymentTransactionId());
            setUploadedFile(value.getUploadedFile());
            setGrammarAndExpressionReviewId(value.getGrammarAndExpressionReviewId());
            setContentReviewId(value.getContentReviewId());
            setCompositionReviewId(value.getCompositionReviewId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
            setRecommendedActivity(value.getRecommendedActivity());
            setRecommendation(value.getRecommendation());
        }
    }
}
