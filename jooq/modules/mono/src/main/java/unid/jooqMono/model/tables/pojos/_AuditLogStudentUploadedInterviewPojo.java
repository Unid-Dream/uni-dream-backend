/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import unid.jooqMono.model.enums.ReviewTypeEnum;


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
public class _AuditLogStudentUploadedInterviewPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long           auditSeq;
    private OffsetDateTime auditCreatedon;
    private String         auditCreatedby;
    private String         auditOperation;
    private String         auditType;
    private UUID           id;
    private UUID           studentProfileId;
    private UUID           interviewTopicId;
    private UUID           paymentTransactionId;
    private String         uploadedFile;
    private UUID           contentReviewId;
    private UUID           clarityReviewId;
    private UUID           charismaReviewId;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;
    private String[]       recommendedActivity;
    private String         recommendation;
    private ReviewTypeEnum reviewType;
    private UUID           educatorProfileId;

    public _AuditLogStudentUploadedInterviewPojo() {}

    public _AuditLogStudentUploadedInterviewPojo(_AuditLogStudentUploadedInterviewPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.studentProfileId = value.studentProfileId;
        this.interviewTopicId = value.interviewTopicId;
        this.paymentTransactionId = value.paymentTransactionId;
        this.uploadedFile = value.uploadedFile;
        this.contentReviewId = value.contentReviewId;
        this.clarityReviewId = value.clarityReviewId;
        this.charismaReviewId = value.charismaReviewId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
        this.recommendedActivity = value.recommendedActivity;
        this.recommendation = value.recommendation;
        this.reviewType = value.reviewType;
        this.educatorProfileId = value.educatorProfileId;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "studentProfileId", "interviewTopicId", "paymentTransactionId", "uploadedFile", "contentReviewId", "clarityReviewId", "charismaReviewId", "createdOn", "createdBy", "updatedOn", "updatedBy", "recommendedActivity", "recommendation", "reviewType", "educatorProfileId" })
    public _AuditLogStudentUploadedInterviewPojo(
        @Nonnull Long           auditSeq,
        @Nonnull OffsetDateTime auditCreatedon,
        @Nonnull String         auditCreatedby,
        @Nonnull String         auditOperation,
        @Nonnull String         auditType,
        @Nonnull UUID           id,
        @Nonnull UUID           studentProfileId,
        @Nonnull UUID           interviewTopicId,
        @Nonnull UUID           paymentTransactionId,
        @Nullable String         uploadedFile,
        @Nullable UUID           contentReviewId,
        @Nullable UUID           clarityReviewId,
        @Nullable UUID           charismaReviewId,
        @Nullable OffsetDateTime createdOn,
        @Nullable String         createdBy,
        @Nullable OffsetDateTime updatedOn,
        @Nullable String         updatedBy,
        @Nullable String[]       recommendedActivity,
        @Nullable String         recommendation,
        @Nullable ReviewTypeEnum reviewType,
        @Nullable UUID           educatorProfileId
    ) {
        this.auditSeq = auditSeq;
        this.auditCreatedon = auditCreatedon;
        this.auditCreatedby = auditCreatedby;
        this.auditOperation = auditOperation;
        this.auditType = auditType;
        this.id = id;
        this.studentProfileId = studentProfileId;
        this.interviewTopicId = interviewTopicId;
        this.paymentTransactionId = paymentTransactionId;
        this.uploadedFile = uploadedFile;
        this.contentReviewId = contentReviewId;
        this.clarityReviewId = clarityReviewId;
        this.charismaReviewId = charismaReviewId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.recommendedActivity = recommendedActivity;
        this.recommendation = recommendation;
        this.reviewType = reviewType;
        this.educatorProfileId = educatorProfileId;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_seq</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdon</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_createdby</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_operation</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.audit_type</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_student_uploaded_interview.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public._audit_log_student_uploaded_interview.id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return this.studentProfileId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.student_profile_id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setStudentProfileId(@Nonnull UUID studentProfileId) {
        this.studentProfileId = studentProfileId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.interview_topic_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getInterviewTopicId() {
        return this.interviewTopicId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.interview_topic_id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setInterviewTopicId(@Nonnull UUID interviewTopicId) {
        this.interviewTopicId = interviewTopicId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.payment_transaction_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getPaymentTransactionId() {
        return this.paymentTransactionId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.payment_transaction_id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setPaymentTransactionId(@Nonnull UUID paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.uploaded_file</code>.
     */
    @Nullable
    public String getUploadedFile() {
        return this.uploadedFile;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.uploaded_file</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setUploadedFile(@Nullable String uploadedFile) {
        this.uploadedFile = uploadedFile;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.content_review_id</code>.
     */
    @Nullable
    public UUID getContentReviewId() {
        return this.contentReviewId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.content_review_id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setContentReviewId(@Nullable UUID contentReviewId) {
        this.contentReviewId = contentReviewId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.clarity_review_id</code>.
     */
    @Nullable
    public UUID getClarityReviewId() {
        return this.clarityReviewId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.clarity_review_id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setClarityReviewId(@Nullable UUID clarityReviewId) {
        this.clarityReviewId = clarityReviewId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.charisma_review_id</code>.
     */
    @Nullable
    public UUID getCharismaReviewId() {
        return this.charismaReviewId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.charisma_review_id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setCharismaReviewId(@Nullable UUID charismaReviewId) {
        this.charismaReviewId = charismaReviewId;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.created_on</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.created_by</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.updated_on</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.updated_by</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.recommended_activity</code>.
     */
    @Nullable
    public String[] getRecommendedActivity() {
        return this.recommendedActivity;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.recommended_activity</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setRecommendedActivity(@Nullable String[] recommendedActivity) {
        this.recommendedActivity = recommendedActivity;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.recommendation</code>.
     */
    @Nullable
    public String getRecommendation() {
        return this.recommendation;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.recommendation</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setRecommendation(@Nullable String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.review_type</code>.
     */
    @Nullable
    public ReviewTypeEnum getReviewType() {
        return this.reviewType;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.review_type</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setReviewType(@Nullable ReviewTypeEnum reviewType) {
        this.reviewType = reviewType;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_student_uploaded_interview.educator_profile_id</code>.
     */
    @Nullable
    public UUID getEducatorProfileId() {
        return this.educatorProfileId;
    }

    /**
     * Setter for
     * <code>public._audit_log_student_uploaded_interview.educator_profile_id</code>.
     */
    public _AuditLogStudentUploadedInterviewPojo setEducatorProfileId(@Nullable UUID educatorProfileId) {
        this.educatorProfileId = educatorProfileId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogStudentUploadedInterviewPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(studentProfileId);
        sb.append(", ").append(interviewTopicId);
        sb.append(", ").append(paymentTransactionId);
        sb.append(", ").append(uploadedFile);
        sb.append(", ").append(contentReviewId);
        sb.append(", ").append(clarityReviewId);
        sb.append(", ").append(charismaReviewId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);
        sb.append(", ").append(Arrays.toString(recommendedActivity));
        sb.append(", ").append(recommendation);
        sb.append(", ").append(reviewType);
        sb.append(", ").append(educatorProfileId);

        sb.append(")");
        return sb.toString();
    }
}
