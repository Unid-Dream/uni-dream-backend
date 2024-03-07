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
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.StudentSessionSurveyAnswerTable;
import unid.jooqMono.model.tables.pojos.StudentSessionSurveyAnswerPojo;


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
public class StudentSessionSurveyAnswerRecord extends UpdatableRecordImpl<StudentSessionSurveyAnswerRecord> implements Record10<UUID, UUID, Integer, UUID, BigDecimal, Boolean, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_session_survey_answer.id</code>.
     */
    public StudentSessionSurveyAnswerRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_session_survey_answer.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.student_session_survey_answer.student_session_survey_question_id</code>.
     */
    public StudentSessionSurveyAnswerRecord setStudentSessionSurveyQuestionId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_session_survey_answer.student_session_survey_question_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentSessionSurveyQuestionId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.student_session_survey_answer.order</code>.
     */
    public StudentSessionSurveyAnswerRecord setOrder(@Nonnull Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.student_session_survey_answer.order</code>.
     */
    @NotNull
    @Nonnull
    public Integer getOrder() {
        return (Integer) get(2);
    }

    /**
     * Setter for
     * <code>public.student_session_survey_answer.description_i18n_id</code>.
     */
    public StudentSessionSurveyAnswerRecord setDescriptionI18nId(@Nullable UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_session_survey_answer.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.student_session_survey_answer.score</code>.
     */
    public StudentSessionSurveyAnswerRecord setScore(@Nullable BigDecimal value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.student_session_survey_answer.score</code>.
     */
    @Nullable
    public BigDecimal getScore() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for
     * <code>public.student_session_survey_answer.require_user_input</code>.
     */
    public StudentSessionSurveyAnswerRecord setRequireUserInput(@Nullable Boolean value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_session_survey_answer.require_user_input</code>.
     */
    @Nullable
    public Boolean getRequireUserInput() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>public.student_session_survey_answer.created_on</code>.
     */
    public StudentSessionSurveyAnswerRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.student_session_survey_answer.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(6);
    }

    /**
     * Setter for <code>public.student_session_survey_answer.created_by</code>.
     */
    public StudentSessionSurveyAnswerRecord setCreatedBy(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.student_session_survey_answer.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.student_session_survey_answer.updated_on</code>.
     */
    public StudentSessionSurveyAnswerRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.student_session_survey_answer.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(8);
    }

    /**
     * Setter for <code>public.student_session_survey_answer.updated_by</code>.
     */
    public StudentSessionSurveyAnswerRecord setUpdatedBy(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.student_session_survey_answer.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row10<UUID, UUID, Integer, UUID, BigDecimal, Boolean, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row10<UUID, UUID, Integer, UUID, BigDecimal, Boolean, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.STUDENT_SESSION_SURVEY_QUESTION_ID;
    }

    @Override
    @Nonnull
    public Field<Integer> field3() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.ORDER;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.DESCRIPTION_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<BigDecimal> field5() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.SCORE;
    }

    @Override
    @Nonnull
    public Field<Boolean> field6() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.REQUIRE_USER_INPUT;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field7() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field8() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field9() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID component2() {
        return getStudentSessionSurveyQuestionId();
    }

    @Override
    @Nonnull
    public Integer component3() {
        return getOrder();
    }

    @Override
    @Nullable
    public UUID component4() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public BigDecimal component5() {
        return getScore();
    }

    @Override
    @Nullable
    public Boolean component6() {
        return getRequireUserInput();
    }

    @Override
    @Nullable
    public OffsetDateTime component7() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component8() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component9() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component10() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public UUID value1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID value2() {
        return getStudentSessionSurveyQuestionId();
    }

    @Override
    @Nonnull
    public Integer value3() {
        return getOrder();
    }

    @Override
    @Nullable
    public UUID value4() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public BigDecimal value5() {
        return getScore();
    }

    @Override
    @Nullable
    public Boolean value6() {
        return getRequireUserInput();
    }

    @Override
    @Nullable
    public OffsetDateTime value7() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value8() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value9() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value10() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value2(@Nonnull UUID value) {
        setStudentSessionSurveyQuestionId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value3(@Nonnull Integer value) {
        setOrder(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value4(@Nullable UUID value) {
        setDescriptionI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value5(@Nullable BigDecimal value) {
        setScore(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value6(@Nullable Boolean value) {
        setRequireUserInput(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value7(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value8(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value9(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord value10(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentSessionSurveyAnswerRecord values(@Nonnull UUID value1, @Nonnull UUID value2, @Nonnull Integer value3, @Nullable UUID value4, @Nullable BigDecimal value5, @Nullable Boolean value6, @Nullable OffsetDateTime value7, @Nullable String value8, @Nullable OffsetDateTime value9, @Nullable String value10) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StudentSessionSurveyAnswerRecord
     */
    public StudentSessionSurveyAnswerRecord() {
        super(StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER);
    }

    /**
     * Create a detached, initialised StudentSessionSurveyAnswerRecord
     */
    @ConstructorProperties({ "id", "studentSessionSurveyQuestionId", "order", "descriptionI18nId", "score", "requireUserInput", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public StudentSessionSurveyAnswerRecord(@Nonnull UUID id, @Nonnull UUID studentSessionSurveyQuestionId, @Nonnull Integer order, @Nullable UUID descriptionI18nId, @Nullable BigDecimal score, @Nullable Boolean requireUserInput, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER);

        setId(id);
        setStudentSessionSurveyQuestionId(studentSessionSurveyQuestionId);
        setOrder(order);
        setDescriptionI18nId(descriptionI18nId);
        setScore(score);
        setRequireUserInput(requireUserInput);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised StudentSessionSurveyAnswerRecord
     */
    public StudentSessionSurveyAnswerRecord(StudentSessionSurveyAnswerPojo value) {
        super(StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER);

        if (value != null) {
            setId(value.getId());
            setStudentSessionSurveyQuestionId(value.getStudentSessionSurveyQuestionId());
            setOrder(value.getOrder());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setScore(value.getScore());
            setRequireUserInput(value.getRequireUserInput());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
