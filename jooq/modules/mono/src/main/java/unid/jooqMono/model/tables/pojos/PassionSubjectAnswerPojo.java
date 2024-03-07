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
public class PassionSubjectAnswerPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID answerI18nId;
    private UUID subjectId;

    public PassionSubjectAnswerPojo() {}

    public PassionSubjectAnswerPojo(PassionSubjectAnswerPojo value) {
        this.id = value.id;
        this.answerI18nId = value.answerI18nId;
        this.subjectId = value.subjectId;
    }

    @ConstructorProperties({ "id", "answerI18nId", "subjectId" })
    public PassionSubjectAnswerPojo(
        @Nonnull UUID id,
        @Nullable UUID answerI18nId,
        @Nullable UUID subjectId
    ) {
        this.id = id;
        this.answerI18nId = answerI18nId;
        this.subjectId = subjectId;
    }

    /**
     * Getter for <code>public.passion_subject_answer.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.passion_subject_answer.id</code>.
     */
    public PassionSubjectAnswerPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.passion_subject_answer.answer_i18n_id</code>.
     */
    @Nullable
    public UUID getAnswerI18nId() {
        return this.answerI18nId;
    }

    /**
     * Setter for <code>public.passion_subject_answer.answer_i18n_id</code>.
     */
    public PassionSubjectAnswerPojo setAnswerI18nId(@Nullable UUID answerI18nId) {
        this.answerI18nId = answerI18nId;
        return this;
    }

    /**
     * Getter for <code>public.passion_subject_answer.subject_id</code>.
     */
    @Nullable
    public UUID getSubjectId() {
        return this.subjectId;
    }

    /**
     * Setter for <code>public.passion_subject_answer.subject_id</code>.
     */
    public PassionSubjectAnswerPojo setSubjectId(@Nullable UUID subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PassionSubjectAnswerPojo (");

        sb.append(id);
        sb.append(", ").append(answerI18nId);
        sb.append(", ").append(subjectId);

        sb.append(")");
        return sb.toString();
    }
}
