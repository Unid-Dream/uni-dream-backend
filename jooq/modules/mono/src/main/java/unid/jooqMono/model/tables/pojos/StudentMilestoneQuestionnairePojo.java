/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;


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
public class StudentMilestoneQuestionnairePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID    id;
    private UUID    questionI18nId;
    private Integer sortId;
    private Boolean singleChoice;

    public StudentMilestoneQuestionnairePojo() {}

    public StudentMilestoneQuestionnairePojo(StudentMilestoneQuestionnairePojo value) {
        this.id = value.id;
        this.questionI18nId = value.questionI18nId;
        this.sortId = value.sortId;
        this.singleChoice = value.singleChoice;
    }

    @ConstructorProperties({ "id", "questionI18nId", "sortId", "singleChoice" })
    public StudentMilestoneQuestionnairePojo(
        @Nonnull UUID    id,
        @Nullable UUID    questionI18nId,
        @Nullable Integer sortId,
        @Nullable Boolean singleChoice
    ) {
        this.id = id;
        this.questionI18nId = questionI18nId;
        this.sortId = sortId;
        this.singleChoice = singleChoice;
    }

    /**
     * Getter for <code>public.student_milestone_questionnaire.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.student_milestone_questionnaire.id</code>.
     */
    public StudentMilestoneQuestionnairePojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_milestone_questionnaire.question_i18n_id</code>.
     */
    @Nullable
    public UUID getQuestionI18nId() {
        return this.questionI18nId;
    }

    /**
     * Setter for
     * <code>public.student_milestone_questionnaire.question_i18n_id</code>.
     */
    public StudentMilestoneQuestionnairePojo setQuestionI18nId(@Nullable UUID questionI18nId) {
        this.questionI18nId = questionI18nId;
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_questionnaire.sort_id</code>.
     */
    @Nullable
    public Integer getSortId() {
        return this.sortId;
    }

    /**
     * Setter for <code>public.student_milestone_questionnaire.sort_id</code>.
     */
    public StudentMilestoneQuestionnairePojo setSortId(@Nullable Integer sortId) {
        this.sortId = sortId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_milestone_questionnaire.single_choice</code>.
     */
    @Nullable
    public Boolean getSingleChoice() {
        return this.singleChoice;
    }

    /**
     * Setter for
     * <code>public.student_milestone_questionnaire.single_choice</code>.
     */
    public StudentMilestoneQuestionnairePojo setSingleChoice(@Nullable Boolean singleChoice) {
        this.singleChoice = singleChoice;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StudentMilestoneQuestionnairePojo (");

        sb.append(id);
        sb.append(", ").append(questionI18nId);
        sb.append(", ").append(sortId);
        sb.append(", ").append(singleChoice);

        sb.append(")");
        return sb.toString();
    }
}
