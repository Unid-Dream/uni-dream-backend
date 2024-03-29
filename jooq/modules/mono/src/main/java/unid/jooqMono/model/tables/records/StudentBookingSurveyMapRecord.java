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

import unid.jooqMono.model.tables.StudentBookingSurveyMapTable;
import unid.jooqMono.model.tables.pojos.StudentBookingSurveyMapPojo;


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
public class StudentBookingSurveyMapRecord extends UpdatableRecordImpl<StudentBookingSurveyMapRecord> implements Record10<UUID, UUID, UUID, OffsetDateTime, OffsetDateTime, Boolean, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_booking_survey_map.id</code>.
     */
    public StudentBookingSurveyMapRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_booking_survey_map.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_map.educator_calendar_id</code>.
     */
    public StudentBookingSurveyMapRecord setEducatorCalendarId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_map.educator_calendar_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getEducatorCalendarId() {
        return (UUID) get(1);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_map.student_session_survey_id</code>.
     */
    public StudentBookingSurveyMapRecord setStudentSessionSurveyId(@Nonnull UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_map.student_session_survey_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentSessionSurveyId() {
        return (UUID) get(2);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_map.student_session_survey_version_asked</code>.
     */
    public StudentBookingSurveyMapRecord setStudentSessionSurveyVersionAsked(@Nonnull OffsetDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_map.student_session_survey_version_asked</code>.
     */
    @NotNull
    @Nonnull
    public OffsetDateTime getStudentSessionSurveyVersionAsked() {
        return (OffsetDateTime) get(3);
    }

    /**
     * Setter for
     * <code>public.student_booking_survey_map.student_session_survey_version_completed</code>.
     */
    public StudentBookingSurveyMapRecord setStudentSessionSurveyVersionCompleted(@Nullable OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_booking_survey_map.student_session_survey_version_completed</code>.
     */
    @Nullable
    public OffsetDateTime getStudentSessionSurveyVersionCompleted() {
        return (OffsetDateTime) get(4);
    }

    /**
     * Setter for <code>public.student_booking_survey_map.completed</code>.
     */
    public StudentBookingSurveyMapRecord setCompleted(@Nullable Boolean value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.student_booking_survey_map.completed</code>.
     */
    @Nullable
    public Boolean getCompleted() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>public.student_booking_survey_map.created_on</code>.
     */
    public StudentBookingSurveyMapRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.student_booking_survey_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(6);
    }

    /**
     * Setter for <code>public.student_booking_survey_map.created_by</code>.
     */
    public StudentBookingSurveyMapRecord setCreatedBy(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.student_booking_survey_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.student_booking_survey_map.updated_on</code>.
     */
    public StudentBookingSurveyMapRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.student_booking_survey_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(8);
    }

    /**
     * Setter for <code>public.student_booking_survey_map.updated_by</code>.
     */
    public StudentBookingSurveyMapRecord setUpdatedBy(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.student_booking_survey_map.updated_by</code>.
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
    public Row10<UUID, UUID, UUID, OffsetDateTime, OffsetDateTime, Boolean, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row10<UUID, UUID, UUID, OffsetDateTime, OffsetDateTime, Boolean, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.EDUCATOR_CALENDAR_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.STUDENT_SESSION_SURVEY_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field4() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.STUDENT_SESSION_SURVEY_VERSION_ASKED;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field5() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.STUDENT_SESSION_SURVEY_VERSION_COMPLETED;
    }

    @Override
    @Nonnull
    public Field<Boolean> field6() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.COMPLETED;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field7() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field8() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field9() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field10() {
        return StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID component2() {
        return getEducatorCalendarId();
    }

    @Override
    @Nonnull
    public UUID component3() {
        return getStudentSessionSurveyId();
    }

    @Override
    @Nonnull
    public OffsetDateTime component4() {
        return getStudentSessionSurveyVersionAsked();
    }

    @Override
    @Nullable
    public OffsetDateTime component5() {
        return getStudentSessionSurveyVersionCompleted();
    }

    @Override
    @Nullable
    public Boolean component6() {
        return getCompleted();
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
        return getEducatorCalendarId();
    }

    @Override
    @Nonnull
    public UUID value3() {
        return getStudentSessionSurveyId();
    }

    @Override
    @Nonnull
    public OffsetDateTime value4() {
        return getStudentSessionSurveyVersionAsked();
    }

    @Override
    @Nullable
    public OffsetDateTime value5() {
        return getStudentSessionSurveyVersionCompleted();
    }

    @Override
    @Nullable
    public Boolean value6() {
        return getCompleted();
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
    public StudentBookingSurveyMapRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value2(@Nonnull UUID value) {
        setEducatorCalendarId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value3(@Nonnull UUID value) {
        setStudentSessionSurveyId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value4(@Nonnull OffsetDateTime value) {
        setStudentSessionSurveyVersionAsked(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value5(@Nullable OffsetDateTime value) {
        setStudentSessionSurveyVersionCompleted(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value6(@Nullable Boolean value) {
        setCompleted(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value7(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value8(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value9(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord value10(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapRecord values(@Nonnull UUID value1, @Nonnull UUID value2, @Nonnull UUID value3, @Nonnull OffsetDateTime value4, @Nullable OffsetDateTime value5, @Nullable Boolean value6, @Nullable OffsetDateTime value7, @Nullable String value8, @Nullable OffsetDateTime value9, @Nullable String value10) {
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
     * Create a detached StudentBookingSurveyMapRecord
     */
    public StudentBookingSurveyMapRecord() {
        super(StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP);
    }

    /**
     * Create a detached, initialised StudentBookingSurveyMapRecord
     */
    @ConstructorProperties({ "id", "educatorCalendarId", "studentSessionSurveyId", "studentSessionSurveyVersionAsked", "studentSessionSurveyVersionCompleted", "completed", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public StudentBookingSurveyMapRecord(@Nonnull UUID id, @Nonnull UUID educatorCalendarId, @Nonnull UUID studentSessionSurveyId, @Nonnull OffsetDateTime studentSessionSurveyVersionAsked, @Nullable OffsetDateTime studentSessionSurveyVersionCompleted, @Nullable Boolean completed, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP);

        setId(id);
        setEducatorCalendarId(educatorCalendarId);
        setStudentSessionSurveyId(studentSessionSurveyId);
        setStudentSessionSurveyVersionAsked(studentSessionSurveyVersionAsked);
        setStudentSessionSurveyVersionCompleted(studentSessionSurveyVersionCompleted);
        setCompleted(completed);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised StudentBookingSurveyMapRecord
     */
    public StudentBookingSurveyMapRecord(StudentBookingSurveyMapPojo value) {
        super(StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP);

        if (value != null) {
            setId(value.getId());
            setEducatorCalendarId(value.getEducatorCalendarId());
            setStudentSessionSurveyId(value.getStudentSessionSurveyId());
            setStudentSessionSurveyVersionAsked(value.getStudentSessionSurveyVersionAsked());
            setStudentSessionSurveyVersionCompleted(value.getStudentSessionSurveyVersionCompleted());
            setCompleted(value.getCompleted());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
