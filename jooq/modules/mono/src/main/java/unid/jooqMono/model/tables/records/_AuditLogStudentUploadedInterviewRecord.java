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
import org.jooq.Record21;
import org.jooq.Row21;
import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.enums.ReviewTypeEnum;
import unid.jooqMono.model.tables._AuditLogStudentUploadedInterviewTable;
import unid.jooqMono.model.tables.pojos._AuditLogStudentUploadedInterviewPojo;


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
public class _AuditLogStudentUploadedInterviewRecord extends TableRecordImpl<_AuditLogStudentUploadedInterviewRecord> implements Record21<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String, String[], String, ReviewTypeEnum, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_seq</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdon</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdby</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_operation</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_type</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_student_uploaded_interview.id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_student_uploaded_interview.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.student_profile_id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setStudentProfileId(@Nonnull UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.interview_topic_id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setInterviewTopicId(@Nonnull UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.interview_topic_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getInterviewTopicId() {
        return (UUID) get(7);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.payment_transaction_id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setPaymentTransactionId(@Nonnull UUID value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.payment_transaction_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getPaymentTransactionId() {
        return (UUID) get(8);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.uploaded_file</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setUploadedFile(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.uploaded_file</code>.
     */
    @Nullable
    public String getUploadedFile() {
        return (String) get(9);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.content_review_id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setContentReviewId(@Nullable UUID value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.content_review_id</code>.
     */
    @Nullable
    public UUID getContentReviewId() {
        return (UUID) get(10);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.clarity_review_id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setClarityReviewId(@Nullable UUID value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.clarity_review_id</code>.
     */
    @Nullable
    public UUID getClarityReviewId() {
        return (UUID) get(11);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.charisma_review_id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setCharismaReviewId(@Nullable UUID value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.charisma_review_id</code>.
     */
    @Nullable
    public UUID getCharismaReviewId() {
        return (UUID) get(12);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.created_on</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(13);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.created_by</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setCreatedBy(@Nullable String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(14);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.updated_on</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(15);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.updated_by</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setUpdatedBy(@Nullable String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(16);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.recommended_activity</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setRecommendedActivity(@Nullable String[] value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.recommended_activity</code>.
     */
    @Nullable
    public String[] getRecommendedActivity() {
        return (String[]) get(17);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.recommendation</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setRecommendation(@Nullable String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.recommendation</code>.
     */
    @Nullable
    public String getRecommendation() {
        return (String) get(18);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.review_type</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setReviewType(@Nullable ReviewTypeEnum value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.review_type</code>.
     */
    @Nullable
    public ReviewTypeEnum getReviewType() {
        return (ReviewTypeEnum) get(19);
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.educator_profile_id</code>.
     */
    public _AuditLogStudentUploadedInterviewRecord setEducatorProfileId(@Nullable UUID value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.educator_profile_id</code>.
     */
    @Nullable
    public UUID getEducatorProfileId() {
        return (UUID) get(20);
    }

    // -------------------------------------------------------------------------
    // Record21 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row21<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String, String[], String, ReviewTypeEnum, UUID> fieldsRow() {
        return (Row21) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row21<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String, String[], String, ReviewTypeEnum, UUID> valuesRow() {
        return (Row21) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<Long> field1() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.AUDIT_SEQ;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.AUDIT_CREATEDON;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.AUDIT_CREATEDBY;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.AUDIT_OPERATION;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.AUDIT_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.STUDENT_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field8() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.INTERVIEW_TOPIC_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field9() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.PAYMENT_TRANSACTION_ID;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.UPLOADED_FILE;
    }

    @Override
    @Nonnull
    public Field<UUID> field11() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.CONTENT_REVIEW_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field12() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.CLARITY_REVIEW_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field13() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.CHARISMA_REVIEW_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field14() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field15() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field16() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field17() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.UPDATED_BY;
    }

    @Override
    @Nonnull
    public Field<String[]> field18() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.RECOMMENDED_ACTIVITY;
    }

    @Override
    @Nonnull
    public Field<String> field19() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.RECOMMENDATION;
    }

    @Override
    @Nonnull
    public Field<ReviewTypeEnum> field20() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.REVIEW_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field21() {
        return _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW.EDUCATOR_PROFILE_ID;
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
        return getInterviewTopicId();
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
        return getContentReviewId();
    }

    @Override
    @Nullable
    public UUID component12() {
        return getClarityReviewId();
    }

    @Override
    @Nullable
    public UUID component13() {
        return getCharismaReviewId();
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
    @Nullable
    public ReviewTypeEnum component20() {
        return getReviewType();
    }

    @Override
    @Nullable
    public UUID component21() {
        return getEducatorProfileId();
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
        return getInterviewTopicId();
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
        return getContentReviewId();
    }

    @Override
    @Nullable
    public UUID value12() {
        return getClarityReviewId();
    }

    @Override
    @Nullable
    public UUID value13() {
        return getCharismaReviewId();
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
    @Nullable
    public ReviewTypeEnum value20() {
        return getReviewType();
    }

    @Override
    @Nullable
    public UUID value21() {
        return getEducatorProfileId();
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value1(@Nonnull Long value) {
        setAuditSeq(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value2(@Nonnull OffsetDateTime value) {
        setAuditCreatedon(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value3(@Nonnull String value) {
        setAuditCreatedby(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value4(@Nonnull String value) {
        setAuditOperation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value5(@Nonnull String value) {
        setAuditType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value6(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value7(@Nonnull UUID value) {
        setStudentProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value8(@Nonnull UUID value) {
        setInterviewTopicId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value9(@Nonnull UUID value) {
        setPaymentTransactionId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value10(@Nullable String value) {
        setUploadedFile(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value11(@Nullable UUID value) {
        setContentReviewId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value12(@Nullable UUID value) {
        setClarityReviewId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value13(@Nullable UUID value) {
        setCharismaReviewId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value14(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value15(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value16(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value17(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value18(@Nullable String[] value) {
        setRecommendedActivity(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value19(@Nullable String value) {
        setRecommendation(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value20(@Nullable ReviewTypeEnum value) {
        setReviewType(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord value21(@Nullable UUID value) {
        setEducatorProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewRecord values(@Nonnull Long value1, @Nonnull OffsetDateTime value2, @Nonnull String value3, @Nonnull String value4, @Nonnull String value5, @Nonnull UUID value6, @Nonnull UUID value7, @Nonnull UUID value8, @Nonnull UUID value9, @Nullable String value10, @Nullable UUID value11, @Nullable UUID value12, @Nullable UUID value13, @Nullable OffsetDateTime value14, @Nullable String value15, @Nullable OffsetDateTime value16, @Nullable String value17, @Nullable String[] value18, @Nullable String value19, @Nullable ReviewTypeEnum value20, @Nullable UUID value21) {
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
        value21(value21);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogStudentUploadedInterviewRecord
     */
    public _AuditLogStudentUploadedInterviewRecord() {
        super(_AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW);
    }

    /**
     * Create a detached, initialised _AuditLogStudentUploadedInterviewRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentProfileId", "interviewTopicId", "paymentTransactionId", "uploadedFile", "contentReviewId", "clarityReviewId", "charismaReviewId", "createdOn", "createdBy", "updatedOn", "updatedBy", "recommendedActivity", "recommendation", "reviewType", "educatorProfileId" })
    public _AuditLogStudentUploadedInterviewRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nonnull UUID studentProfileId, @Nonnull UUID interviewTopicId, @Nonnull UUID paymentTransactionId, @Nullable String uploadedFile, @Nullable UUID contentReviewId, @Nullable UUID clarityReviewId, @Nullable UUID charismaReviewId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable String[] recommendedActivity, @Nullable String recommendation, @Nullable ReviewTypeEnum reviewType, @Nullable UUID educatorProfileId) {
        super(_AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setStudentProfileId(studentProfileId);
        setInterviewTopicId(interviewTopicId);
        setPaymentTransactionId(paymentTransactionId);
        setUploadedFile(uploadedFile);
        setContentReviewId(contentReviewId);
        setClarityReviewId(clarityReviewId);
        setCharismaReviewId(charismaReviewId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
        setRecommendedActivity(recommendedActivity);
        setRecommendation(recommendation);
        setReviewType(reviewType);
        setEducatorProfileId(educatorProfileId);
    }

    /**
     * Create a detached, initialised _AuditLogStudentUploadedInterviewRecord
     */
    public _AuditLogStudentUploadedInterviewRecord(_AuditLogStudentUploadedInterviewPojo value) {
        super(_AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setStudentProfileId(value.getStudentProfileId());
            setInterviewTopicId(value.getInterviewTopicId());
            setPaymentTransactionId(value.getPaymentTransactionId());
            setUploadedFile(value.getUploadedFile());
            setContentReviewId(value.getContentReviewId());
            setClarityReviewId(value.getClarityReviewId());
            setCharismaReviewId(value.getCharismaReviewId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
            setRecommendedActivity(value.getRecommendedActivity());
            setRecommendation(value.getRecommendation());
            setReviewType(value.getReviewType());
            setEducatorProfileId(value.getEducatorProfileId());
        }
    }
}
