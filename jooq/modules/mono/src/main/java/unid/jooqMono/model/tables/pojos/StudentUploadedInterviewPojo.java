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
public class StudentUploadedInterviewPojo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public StudentUploadedInterviewPojo() {}

    public StudentUploadedInterviewPojo(StudentUploadedInterviewPojo value) {
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
    }

    @ConstructorProperties({ "id", "studentProfileId", "interviewTopicId", "paymentTransactionId", "uploadedFile", "contentReviewId", "clarityReviewId", "charismaReviewId", "createdOn", "createdBy", "updatedOn", "updatedBy", "recommendedActivity", "recommendation", "reviewType" })
    public StudentUploadedInterviewPojo(
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
        @Nullable ReviewTypeEnum reviewType
    ) {
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
    }

    /**
     * Getter for <code>public.student_uploaded_interview.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.id</code>.
     */
    public StudentUploadedInterviewPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_uploaded_interview.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return this.studentProfileId;
    }

    /**
     * Setter for
     * <code>public.student_uploaded_interview.student_profile_id</code>.
     */
    public StudentUploadedInterviewPojo setStudentProfileId(@Nonnull UUID studentProfileId) {
        this.studentProfileId = studentProfileId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_uploaded_interview.interview_topic_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getInterviewTopicId() {
        return this.interviewTopicId;
    }

    /**
     * Setter for
     * <code>public.student_uploaded_interview.interview_topic_id</code>.
     */
    public StudentUploadedInterviewPojo setInterviewTopicId(@Nonnull UUID interviewTopicId) {
        this.interviewTopicId = interviewTopicId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_uploaded_interview.payment_transaction_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getPaymentTransactionId() {
        return this.paymentTransactionId;
    }

    /**
     * Setter for
     * <code>public.student_uploaded_interview.payment_transaction_id</code>.
     */
    public StudentUploadedInterviewPojo setPaymentTransactionId(@Nonnull UUID paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
        return this;
    }

    /**
     * Getter for <code>public.student_uploaded_interview.uploaded_file</code>.
     */
    @Nullable
    public String getUploadedFile() {
        return this.uploadedFile;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.uploaded_file</code>.
     */
    public StudentUploadedInterviewPojo setUploadedFile(@Nullable String uploadedFile) {
        this.uploadedFile = uploadedFile;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_uploaded_interview.content_review_id</code>.
     */
    @Nullable
    public UUID getContentReviewId() {
        return this.contentReviewId;
    }

    /**
     * Setter for
     * <code>public.student_uploaded_interview.content_review_id</code>.
     */
    public StudentUploadedInterviewPojo setContentReviewId(@Nullable UUID contentReviewId) {
        this.contentReviewId = contentReviewId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_uploaded_interview.clarity_review_id</code>.
     */
    @Nullable
    public UUID getClarityReviewId() {
        return this.clarityReviewId;
    }

    /**
     * Setter for
     * <code>public.student_uploaded_interview.clarity_review_id</code>.
     */
    public StudentUploadedInterviewPojo setClarityReviewId(@Nullable UUID clarityReviewId) {
        this.clarityReviewId = clarityReviewId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_uploaded_interview.charisma_review_id</code>.
     */
    @Nullable
    public UUID getCharismaReviewId() {
        return this.charismaReviewId;
    }

    /**
     * Setter for
     * <code>public.student_uploaded_interview.charisma_review_id</code>.
     */
    public StudentUploadedInterviewPojo setCharismaReviewId(@Nullable UUID charismaReviewId) {
        this.charismaReviewId = charismaReviewId;
        return this;
    }

    /**
     * Getter for <code>public.student_uploaded_interview.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.created_on</code>.
     */
    public StudentUploadedInterviewPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.student_uploaded_interview.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.created_by</code>.
     */
    public StudentUploadedInterviewPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.student_uploaded_interview.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.updated_on</code>.
     */
    public StudentUploadedInterviewPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.student_uploaded_interview.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.updated_by</code>.
     */
    public StudentUploadedInterviewPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_uploaded_interview.recommended_activity</code>.
     */
    @Nullable
    public String[] getRecommendedActivity() {
        return this.recommendedActivity;
    }

    /**
     * Setter for
     * <code>public.student_uploaded_interview.recommended_activity</code>.
     */
    public StudentUploadedInterviewPojo setRecommendedActivity(@Nullable String[] recommendedActivity) {
        this.recommendedActivity = recommendedActivity;
        return this;
    }

    /**
     * Getter for <code>public.student_uploaded_interview.recommendation</code>.
     */
    @Nullable
    public String getRecommendation() {
        return this.recommendation;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.recommendation</code>.
     */
    public StudentUploadedInterviewPojo setRecommendation(@Nullable String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    /**
     * Getter for <code>public.student_uploaded_interview.review_type</code>.
     */
    @Nullable
    public ReviewTypeEnum getReviewType() {
        return this.reviewType;
    }

    /**
     * Setter for <code>public.student_uploaded_interview.review_type</code>.
     */
    public StudentUploadedInterviewPojo setReviewType(@Nullable ReviewTypeEnum reviewType) {
        this.reviewType = reviewType;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StudentUploadedInterviewPojo (");

        sb.append(id);
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

        sb.append(")");
        return sb.toString();
    }
}
