/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import unid.jooqMono.model.enums.QuestionnaireQuestionTypeEnum;


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
public class StudentQuestionnaireQuestionPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID                          id;
    private UUID                          studentQuestionnaireSectionId;
    private Integer                       order;
    private UUID                          descriptionI18nId;
    private Boolean                       mandatory;
    private QuestionnaireQuestionTypeEnum type;
    private OffsetDateTime                createdOn;
    private String                        createdBy;
    private OffsetDateTime                updatedOn;
    private String                        updatedBy;

    public StudentQuestionnaireQuestionPojo() {}

    public StudentQuestionnaireQuestionPojo(StudentQuestionnaireQuestionPojo value) {
        this.id = value.id;
        this.studentQuestionnaireSectionId = value.studentQuestionnaireSectionId;
        this.order = value.order;
        this.descriptionI18nId = value.descriptionI18nId;
        this.mandatory = value.mandatory;
        this.type = value.type;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "id", "studentQuestionnaireSectionId", "order", "descriptionI18nId", "mandatory", "type", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public StudentQuestionnaireQuestionPojo(
        @Nonnull UUID                          id,
        @Nonnull UUID                          studentQuestionnaireSectionId,
        @Nonnull Integer                       order,
        @Nullable UUID                          descriptionI18nId,
        @Nullable Boolean                       mandatory,
        @Nonnull QuestionnaireQuestionTypeEnum type,
        @Nullable OffsetDateTime                createdOn,
        @Nullable String                        createdBy,
        @Nullable OffsetDateTime                updatedOn,
        @Nullable String                        updatedBy
    ) {
        this.id = id;
        this.studentQuestionnaireSectionId = studentQuestionnaireSectionId;
        this.order = order;
        this.descriptionI18nId = descriptionI18nId;
        this.mandatory = mandatory;
        this.type = type;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.id</code>.
     */
    public StudentQuestionnaireQuestionPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_questionnaire_question.student_questionnaire_section_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentQuestionnaireSectionId() {
        return this.studentQuestionnaireSectionId;
    }

    /**
     * Setter for
     * <code>public.student_questionnaire_question.student_questionnaire_section_id</code>.
     */
    public StudentQuestionnaireQuestionPojo setStudentQuestionnaireSectionId(@Nonnull UUID studentQuestionnaireSectionId) {
        this.studentQuestionnaireSectionId = studentQuestionnaireSectionId;
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.order</code>.
     */
    @NotNull
    @Nonnull
    public Integer getOrder() {
        return this.order;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.order</code>.
     */
    public StudentQuestionnaireQuestionPojo setOrder(@Nonnull Integer order) {
        this.order = order;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_questionnaire_question.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return this.descriptionI18nId;
    }

    /**
     * Setter for
     * <code>public.student_questionnaire_question.description_i18n_id</code>.
     */
    public StudentQuestionnaireQuestionPojo setDescriptionI18nId(@Nullable UUID descriptionI18nId) {
        this.descriptionI18nId = descriptionI18nId;
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.mandatory</code>.
     */
    @Nullable
    public Boolean getMandatory() {
        return this.mandatory;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.mandatory</code>.
     */
    public StudentQuestionnaireQuestionPojo setMandatory(@Nullable Boolean mandatory) {
        this.mandatory = mandatory;
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.type</code>.
     */
    @Nonnull
    public QuestionnaireQuestionTypeEnum getType() {
        return this.type;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.type</code>.
     */
    public StudentQuestionnaireQuestionPojo setType(@Nonnull QuestionnaireQuestionTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.created_on</code>.
     */
    public StudentQuestionnaireQuestionPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.created_by</code>.
     */
    public StudentQuestionnaireQuestionPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.updated_on</code>.
     */
    public StudentQuestionnaireQuestionPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_question.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.student_questionnaire_question.updated_by</code>.
     */
    public StudentQuestionnaireQuestionPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StudentQuestionnaireQuestionPojo (");

        sb.append(id);
        sb.append(", ").append(studentQuestionnaireSectionId);
        sb.append(", ").append(order);
        sb.append(", ").append(descriptionI18nId);
        sb.append(", ").append(mandatory);
        sb.append(", ").append(type);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
