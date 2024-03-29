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
import javax.validation.constraints.NotNull;

import unid.jooqMono.model.enums.MilestoneOptionTypeEnum;


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
public class StudentMilestoneOptionsPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID                    id;
    private UUID                    answerI18nId;
    private Integer                 sortId;
    private UUID                    questionnaireId;
    private MilestoneOptionTypeEnum optionType;

    public StudentMilestoneOptionsPojo() {}

    public StudentMilestoneOptionsPojo(StudentMilestoneOptionsPojo value) {
        this.id = value.id;
        this.answerI18nId = value.answerI18nId;
        this.sortId = value.sortId;
        this.questionnaireId = value.questionnaireId;
        this.optionType = value.optionType;
    }

    @ConstructorProperties({ "id", "answerI18nId", "sortId", "questionnaireId", "optionType" })
    public StudentMilestoneOptionsPojo(
        @Nonnull UUID                    id,
        @Nullable UUID                    answerI18nId,
        @Nullable Integer                 sortId,
        @Nullable UUID                    questionnaireId,
        @Nonnull MilestoneOptionTypeEnum optionType
    ) {
        this.id = id;
        this.answerI18nId = answerI18nId;
        this.sortId = sortId;
        this.questionnaireId = questionnaireId;
        this.optionType = optionType;
    }

    /**
     * Getter for <code>public.student_milestone_options.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.student_milestone_options.id</code>.
     */
    public StudentMilestoneOptionsPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_options.answer_i18n_id</code>.
     */
    @Nullable
    public UUID getAnswerI18nId() {
        return this.answerI18nId;
    }

    /**
     * Setter for <code>public.student_milestone_options.answer_i18n_id</code>.
     */
    public StudentMilestoneOptionsPojo setAnswerI18nId(@Nullable UUID answerI18nId) {
        this.answerI18nId = answerI18nId;
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_options.sort_id</code>.
     */
    @Nullable
    public Integer getSortId() {
        return this.sortId;
    }

    /**
     * Setter for <code>public.student_milestone_options.sort_id</code>.
     */
    public StudentMilestoneOptionsPojo setSortId(@Nullable Integer sortId) {
        this.sortId = sortId;
        return this;
    }

    /**
     * Getter for
     * <code>public.student_milestone_options.questionnaire_id</code>.
     */
    @Nullable
    public UUID getQuestionnaireId() {
        return this.questionnaireId;
    }

    /**
     * Setter for
     * <code>public.student_milestone_options.questionnaire_id</code>.
     */
    public StudentMilestoneOptionsPojo setQuestionnaireId(@Nullable UUID questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_options.option_type</code>.
     */
    @NotNull
    @Nonnull
    public MilestoneOptionTypeEnum getOptionType() {
        return this.optionType;
    }

    /**
     * Setter for <code>public.student_milestone_options.option_type</code>.
     */
    public StudentMilestoneOptionsPojo setOptionType(@Nonnull MilestoneOptionTypeEnum optionType) {
        this.optionType = optionType;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StudentMilestoneOptionsPojo (");

        sb.append(id);
        sb.append(", ").append(answerI18nId);
        sb.append(", ").append(sortId);
        sb.append(", ").append(questionnaireId);
        sb.append(", ").append(optionType);

        sb.append(")");
        return sb.toString();
    }
}
