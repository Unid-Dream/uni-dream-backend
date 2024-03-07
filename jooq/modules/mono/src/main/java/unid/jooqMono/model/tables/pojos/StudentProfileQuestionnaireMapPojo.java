/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;


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
public class StudentProfileQuestionnaireMapPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID           id;
    private UUID           studentProfileId;
    private UUID           studentQuestionnaireId;
    private OffsetDateTime studentQuestionnaireVersionAsked;
    private LocalDateTime  studentQuestionnaireVersionCompleted;
    private Boolean        completed;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public StudentProfileQuestionnaireMapPojo() {}

    public StudentProfileQuestionnaireMapPojo(StudentProfileQuestionnaireMapPojo value) {
        this.id = value.id;
        this.studentProfileId = value.studentProfileId;
        this.studentQuestionnaireId = value.studentQuestionnaireId;
        this.studentQuestionnaireVersionAsked = value.studentQuestionnaireVersionAsked;
        this.studentQuestionnaireVersionCompleted = value.studentQuestionnaireVersionCompleted;
        this.completed = value.completed;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "id", "studentProfileId", "studentQuestionnaireId", "studentQuestionnaireVersionAsked", "studentQuestionnaireVersionCompleted", "completed", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public StudentProfileQuestionnaireMapPojo(
        @Nonnull UUID           id,
        @Nonnull UUID           studentProfileId,
        @Nonnull UUID           studentQuestionnaireId,
        @Nonnull OffsetDateTime studentQuestionnaireVersionAsked,
        @Nullable LocalDateTime  studentQuestionnaireVersionCompleted,
        @Nullable Boolean        completed,
        @Nullable OffsetDateTime createdOn,
        @Nullable String         createdBy,
        @Nullable OffsetDateTime updatedOn,
        @Nullable String         updatedBy
    ) {
        this.id = id;
        this.studentProfileId = studentProfileId;
        this.studentQuestionnaireId = studentQuestionnaireId;
        this.studentQuestionnaireVersionAsked = studentQuestionnaireVersionAsked;
        this.studentQuestionnaireVersionCompleted = studentQuestionnaireVersionCompleted;
        this.completed = completed;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public.student_profile_questionnaire_map.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.student_profile_questionnaire_map.id</code>.
     */
    public StudentProfileQuestionnaireMapPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return this.studentProfileId;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.student_profile_id</code>.
     */
    public StudentProfileQuestionnaireMapPojo setStudentProfileId(@Nonnull UUID studentProfileId) {
        this.studentProfileId = studentProfileId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.student_questionnaire_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentQuestionnaireId() {
        return this.studentQuestionnaireId;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.student_questionnaire_id</code>.
     */
    public StudentProfileQuestionnaireMapPojo setStudentQuestionnaireId(@Nonnull UUID studentQuestionnaireId) {
        this.studentQuestionnaireId = studentQuestionnaireId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.student_questionnaire_version_asked</code>.
     */
    @NotNull
    @Nonnull
    public OffsetDateTime getStudentQuestionnaireVersionAsked() {
        return this.studentQuestionnaireVersionAsked;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.student_questionnaire_version_asked</code>.
     */
    public StudentProfileQuestionnaireMapPojo setStudentQuestionnaireVersionAsked(@Nonnull OffsetDateTime studentQuestionnaireVersionAsked) {
        this.studentQuestionnaireVersionAsked = studentQuestionnaireVersionAsked;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.student_questionnaire_version_completed</code>.
     */
    @Nullable
    public LocalDateTime getStudentQuestionnaireVersionCompleted() {
        return this.studentQuestionnaireVersionCompleted;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.student_questionnaire_version_completed</code>.
     */
    public StudentProfileQuestionnaireMapPojo setStudentQuestionnaireVersionCompleted(@Nullable LocalDateTime studentQuestionnaireVersionCompleted) {
        this.studentQuestionnaireVersionCompleted = studentQuestionnaireVersionCompleted;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.completed</code>.
     */
    @Nullable
    public Boolean getCompleted() {
        return this.completed;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.completed</code>.
     */
    public StudentProfileQuestionnaireMapPojo setCompleted(@Nullable Boolean completed) {
        this.completed = completed;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.created_on</code>.
     */
    public StudentProfileQuestionnaireMapPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.created_by</code>.
     */
    public StudentProfileQuestionnaireMapPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.updated_on</code>.
     */
    public StudentProfileQuestionnaireMapPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_profile_questionnaire_map.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for
     * <code>public.student_profile_questionnaire_map.updated_by</code>.
     */
    public StudentProfileQuestionnaireMapPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StudentProfileQuestionnaireMapPojo (");

        sb.append(id);
        sb.append(", ").append(studentProfileId);
        sb.append(", ").append(studentQuestionnaireId);
        sb.append(", ").append(studentQuestionnaireVersionAsked);
        sb.append(", ").append(studentQuestionnaireVersionCompleted);
        sb.append(", ").append(completed);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
