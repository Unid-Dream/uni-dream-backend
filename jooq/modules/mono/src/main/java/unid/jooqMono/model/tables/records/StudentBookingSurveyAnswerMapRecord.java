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
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.StudentBookingSurveyAnswerMapTable;
import unid.jooqMono.model.tables.pojos.StudentBookingSurveyAnswerMapPojo;


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
public class StudentBookingSurveyAnswerMapRecord extends UpdatableRecordImpl<StudentBookingSurveyAnswerMapRecord> implements Record10<UUID, UUID, UUID, UUID, Integer, String, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_booking_survey_answer_map.id</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_booking_survey_answer_map.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.student_booking_survey_map_id</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setStudentBookingSurveyMapId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.student_booking_survey_map_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentBookingSurveyMapId() {
        return (UUID) get(1);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_question_id</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setStudentSessionSurveyQuestionId(@Nonnull UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_question_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentSessionSurveyQuestionId() {
        return (UUID) get(2);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_answer_id</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setStudentSessionSurveyAnswerId(@Nonnull UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_answer_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentSessionSurveyAnswerId() {
        return (UUID) get(3);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_answer_order</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setStudentSessionSurveyAnswerOrder(@Nonnull Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_answer_order</code>.
     */
    @NotNull
    @Nonnull
    public Integer getStudentSessionSurveyAnswerOrder() {
        return (Integer) get(4);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_answer_input</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setStudentSessionSurveyAnswerInput(@Nullable String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.student_session_survey_answer_input</code>.
     */
    @Nullable
    public String getStudentSessionSurveyAnswerInput() {
        return (String) get(5);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.created_on</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(6);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.created_by</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setCreatedBy(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(7);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.updated_on</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(8);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_answer_map.updated_by</code>.
     */
    public StudentBookingSurveyAnswerMapRecord setUpdatedBy(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_answer_map.updated_by</code>.
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
    public Row10<UUID, UUID, UUID, UUID, Integer, String, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row10<UUID, UUID, UUID, UUID, Integer, String, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.STUDENT_BOOKING_SURVEY_MAP_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.STUDENT_SESSION_SURVEY_QUESTION_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.STUDENT_SESSION_SURVEY_ANSWER_ID;
    }

    @Override
    @Nonnull
    public Field<Integer> field5() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.STUDENT_SESSION_SURVEY_ANSWER_ORDER;
    }

    @Override
    @Nonnull
    public Field<String> field6() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.STUDENT_SESSION_SURVEY_ANSWER_INPUT;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field7() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field8() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field9() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID component2() {
        return getStudentBookingSurveyMapId();
    }

    @Override
    @Nonnull
    public UUID component3() {
        return getStudentSessionSurveyQuestionId();
    }

    @Override
    @Nonnull
    public UUID component4() {
        return getStudentSessionSurveyAnswerId();
    }

    @Override
    @Nonnull
    public Integer component5() {
        return getStudentSessionSurveyAnswerOrder();
    }

    @Override
    @Nullable
    public String component6() {
        return getStudentSessionSurveyAnswerInput();
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
        return getStudentBookingSurveyMapId();
    }

    @Override
    @Nonnull
    public UUID value3() {
        return getStudentSessionSurveyQuestionId();
    }

    @Override
    @Nonnull
    public UUID value4() {
        return getStudentSessionSurveyAnswerId();
    }

    @Override
    @Nonnull
    public Integer value5() {
        return getStudentSessionSurveyAnswerOrder();
    }

    @Override
    @Nullable
    public String value6() {
        return getStudentSessionSurveyAnswerInput();
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
    public StudentBookingSurveyAnswerMapRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value2(@Nonnull UUID value) {
        setStudentBookingSurveyMapId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value3(@Nonnull UUID value) {
        setStudentSessionSurveyQuestionId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value4(@Nonnull UUID value) {
        setStudentSessionSurveyAnswerId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value5(@Nonnull Integer value) {
        setStudentSessionSurveyAnswerOrder(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value6(@Nullable String value) {
        setStudentSessionSurveyAnswerInput(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value7(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value8(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value9(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord value10(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyAnswerMapRecord values(@Nonnull UUID value1, @Nonnull UUID value2, @Nonnull UUID value3, @Nonnull UUID value4, @Nonnull Integer value5, @Nullable String value6, @Nullable OffsetDateTime value7, @Nullable String value8, @Nullable OffsetDateTime value9, @Nullable String value10) {
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
     * Create a detached StudentBookingSurveyAnswerMapRecord
     */
    public StudentBookingSurveyAnswerMapRecord() {
        super(StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP);
    }

    /**
     * Create a detached, initialised StudentBookingSurveyAnswerMapRecord
     */
    @ConstructorProperties({ "id", "studentBookingSurveyMapId", "studentSessionSurveyQuestionId", "studentSessionSurveyAnswerId", "studentSessionSurveyAnswerOrder", "studentSessionSurveyAnswerInput", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public StudentBookingSurveyAnswerMapRecord(@Nonnull UUID id, @Nonnull UUID studentBookingSurveyMapId, @Nonnull UUID studentSessionSurveyQuestionId, @Nonnull UUID studentSessionSurveyAnswerId, @Nonnull Integer studentSessionSurveyAnswerOrder, @Nullable String studentSessionSurveyAnswerInput, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP);

        setId(id);
        setStudentBookingSurveyMapId(studentBookingSurveyMapId);
        setStudentSessionSurveyQuestionId(studentSessionSurveyQuestionId);
        setStudentSessionSurveyAnswerId(studentSessionSurveyAnswerId);
        setStudentSessionSurveyAnswerOrder(studentSessionSurveyAnswerOrder);
        setStudentSessionSurveyAnswerInput(studentSessionSurveyAnswerInput);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised StudentBookingSurveyAnswerMapRecord
     */
    public StudentBookingSurveyAnswerMapRecord(StudentBookingSurveyAnswerMapPojo value) {
        super(StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP);

        if (value != null) {
            setId(value.getId());
            setStudentBookingSurveyMapId(value.getStudentBookingSurveyMapId());
            setStudentSessionSurveyQuestionId(value.getStudentSessionSurveyQuestionId());
            setStudentSessionSurveyAnswerId(value.getStudentSessionSurveyAnswerId());
            setStudentSessionSurveyAnswerOrder(value.getStudentSessionSurveyAnswerOrder());
            setStudentSessionSurveyAnswerInput(value.getStudentSessionSurveyAnswerInput());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
