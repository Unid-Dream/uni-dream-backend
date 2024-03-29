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

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.CourseTable;
import unid.jooqMono.model.tables.pojos.CoursePojo;


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
public class CourseRecord extends UpdatableRecordImpl<CourseRecord> implements Record8<UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.course.id</code>.
     */
    public CourseRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.course.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.course.title_i18n_id</code>.
     */
    public CourseRecord setTitleI18nId(@Nullable UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.course.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.course.description_i18n_id</code>.
     */
    public CourseRecord setDescriptionI18nId(@Nullable UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.course.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.course.academic_major_id</code>.
     */
    public CourseRecord setAcademicMajorId(@Nullable UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.course.academic_major_id</code>.
     */
    @Nullable
    public UUID getAcademicMajorId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.course.created_on</code>.
     */
    public CourseRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.course.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(4);
    }

    /**
     * Setter for <code>public.course.created_by</code>.
     */
    public CourseRecord setCreatedBy(@Nullable String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.course.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.course.updated_on</code>.
     */
    public CourseRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.course.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(6);
    }

    /**
     * Setter for <code>public.course.updated_by</code>.
     */
    public CourseRecord setUpdatedBy(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.course.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(7);
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
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row8<UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row8<UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return CourseTable.COURSE.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return CourseTable.COURSE.TITLE_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return CourseTable.COURSE.DESCRIPTION_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return CourseTable.COURSE.ACADEMIC_MAJOR_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field5() {
        return CourseTable.COURSE.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field6() {
        return CourseTable.COURSE.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field7() {
        return CourseTable.COURSE.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field8() {
        return CourseTable.COURSE.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID component2() {
        return getTitleI18nId();
    }

    @Override
    @Nullable
    public UUID component3() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID component4() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public OffsetDateTime component5() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component6() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component7() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component8() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public UUID value1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID value2() {
        return getTitleI18nId();
    }

    @Override
    @Nullable
    public UUID value3() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID value4() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public OffsetDateTime value5() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value6() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value7() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value8() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public CourseRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord value2(@Nullable UUID value) {
        setTitleI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord value3(@Nullable UUID value) {
        setDescriptionI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord value4(@Nullable UUID value) {
        setAcademicMajorId(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord value5(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord value6(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord value7(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord value8(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public CourseRecord values(@Nonnull UUID value1, @Nullable UUID value2, @Nullable UUID value3, @Nullable UUID value4, @Nullable OffsetDateTime value5, @Nullable String value6, @Nullable OffsetDateTime value7, @Nullable String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CourseRecord
     */
    public CourseRecord() {
        super(CourseTable.COURSE);
    }

    /**
     * Create a detached, initialised CourseRecord
     */
    @ConstructorProperties({ "id", "titleI18nId", "descriptionI18nId", "academicMajorId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public CourseRecord(@Nonnull UUID id, @Nullable UUID titleI18nId, @Nullable UUID descriptionI18nId, @Nullable UUID academicMajorId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(CourseTable.COURSE);

        setId(id);
        setTitleI18nId(titleI18nId);
        setDescriptionI18nId(descriptionI18nId);
        setAcademicMajorId(academicMajorId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised CourseRecord
     */
    public CourseRecord(CoursePojo value) {
        super(CourseTable.COURSE);

        if (value != null) {
            setId(value.getId());
            setTitleI18nId(value.getTitleI18nId());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setAcademicMajorId(value.getAcademicMajorId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
