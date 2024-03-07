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
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.EcaCourseAcademicMapTable;
import unid.jooqMono.model.tables.pojos.EcaCourseAcademicMapPojo;


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
public class EcaCourseAcademicMapRecord extends UpdatableRecordImpl<EcaCourseAcademicMapRecord> implements Record8<UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.eca_course_academic_map.id</code>.
     */
    public EcaCourseAcademicMapRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.eca_course_academic_map.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.eca_course_academic_map.eca_course_id</code>.
     */
    public EcaCourseAcademicMapRecord setEcaCourseId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.eca_course_academic_map.eca_course_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getEcaCourseId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.eca_course_academic_map.academic_major_id</code>.
     */
    public EcaCourseAcademicMapRecord setAcademicMajorId(@Nonnull UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.eca_course_academic_map.academic_major_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getAcademicMajorId() {
        return (UUID) get(2);
    }

    /**
     * Setter for
     * <code>public.eca_course_academic_map.academic_subject_id</code>.
     */
    public EcaCourseAcademicMapRecord setAcademicSubjectId(@Nullable UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.eca_course_academic_map.academic_subject_id</code>.
     */
    @Nullable
    public UUID getAcademicSubjectId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.eca_course_academic_map.created_on</code>.
     */
    public EcaCourseAcademicMapRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.eca_course_academic_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(4);
    }

    /**
     * Setter for <code>public.eca_course_academic_map.created_by</code>.
     */
    public EcaCourseAcademicMapRecord setCreatedBy(@Nullable String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.eca_course_academic_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.eca_course_academic_map.updated_on</code>.
     */
    public EcaCourseAcademicMapRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.eca_course_academic_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(6);
    }

    /**
     * Setter for <code>public.eca_course_academic_map.updated_by</code>.
     */
    public EcaCourseAcademicMapRecord setUpdatedBy(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.eca_course_academic_map.updated_by</code>.
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
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.ECA_COURSE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.ACADEMIC_MAJOR_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.ACADEMIC_SUBJECT_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field5() {
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field6() {
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field7() {
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field8() {
        return EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID component2() {
        return getEcaCourseId();
    }

    @Override
    @Nonnull
    public UUID component3() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public UUID component4() {
        return getAcademicSubjectId();
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
    @Nonnull
    public UUID value2() {
        return getEcaCourseId();
    }

    @Override
    @Nonnull
    public UUID value3() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public UUID value4() {
        return getAcademicSubjectId();
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
    public EcaCourseAcademicMapRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord value2(@Nonnull UUID value) {
        setEcaCourseId(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord value3(@Nonnull UUID value) {
        setAcademicMajorId(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord value4(@Nullable UUID value) {
        setAcademicSubjectId(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord value5(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord value6(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord value7(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord value8(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public EcaCourseAcademicMapRecord values(@Nonnull UUID value1, @Nonnull UUID value2, @Nonnull UUID value3, @Nullable UUID value4, @Nullable OffsetDateTime value5, @Nullable String value6, @Nullable OffsetDateTime value7, @Nullable String value8) {
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
     * Create a detached EcaCourseAcademicMapRecord
     */
    public EcaCourseAcademicMapRecord() {
        super(EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP);
    }

    /**
     * Create a detached, initialised EcaCourseAcademicMapRecord
     */
    @ConstructorProperties({ "id", "ecaCourseId", "academicMajorId", "academicSubjectId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public EcaCourseAcademicMapRecord(@Nonnull UUID id, @Nonnull UUID ecaCourseId, @Nonnull UUID academicMajorId, @Nullable UUID academicSubjectId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP);

        setId(id);
        setEcaCourseId(ecaCourseId);
        setAcademicMajorId(academicMajorId);
        setAcademicSubjectId(academicSubjectId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised EcaCourseAcademicMapRecord
     */
    public EcaCourseAcademicMapRecord(EcaCourseAcademicMapPojo value) {
        super(EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP);

        if (value != null) {
            setId(value.getId());
            setEcaCourseId(value.getEcaCourseId());
            setAcademicMajorId(value.getAcademicMajorId());
            setAcademicSubjectId(value.getAcademicSubjectId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
