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
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.EducatorProfileExpertiseAcademicMapTable;
import unid.jooqMono.model.tables.pojos.EducatorProfileExpertiseAcademicMapPojo;


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
public class EducatorProfileExpertiseAcademicMapRecord extends UpdatableRecordImpl<EducatorProfileExpertiseAcademicMapRecord> implements Record9<UUID, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.id</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.educator_profile_id</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setEducatorProfileId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.educator_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getEducatorProfileId() {
        return (UUID) get(1);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.expertise_id</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setExpertiseId(@Nonnull UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.expertise_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getExpertiseId() {
        return (UUID) get(2);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.academic_major_id</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setAcademicMajorId(@Nullable UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.academic_major_id</code>.
     */
    @Nullable
    public UUID getAcademicMajorId() {
        return (UUID) get(3);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.academic_subject_id</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setAcademicSubjectId(@Nullable UUID value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.academic_subject_id</code>.
     */
    @Nullable
    public UUID getAcademicSubjectId() {
        return (UUID) get(4);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.created_on</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(5);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.created_by</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setCreatedBy(@Nullable String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(6);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.updated_on</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(7);
    }

    /**
     * Setter for
     * <code>public.educator_profile_expertise_academic_map.updated_by</code>.
     */
    public EducatorProfileExpertiseAcademicMapRecord setUpdatedBy(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.educator_profile_expertise_academic_map.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(8);
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
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row9<UUID, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row9<UUID, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.EDUCATOR_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.EXPERTISE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.ACADEMIC_MAJOR_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field5() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.ACADEMIC_SUBJECT_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field6() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field7() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field8() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field9() {
        return EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID component2() {
        return getEducatorProfileId();
    }

    @Override
    @Nonnull
    public UUID component3() {
        return getExpertiseId();
    }

    @Override
    @Nullable
    public UUID component4() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public UUID component5() {
        return getAcademicSubjectId();
    }

    @Override
    @Nullable
    public OffsetDateTime component6() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component7() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component8() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component9() {
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
        return getEducatorProfileId();
    }

    @Override
    @Nonnull
    public UUID value3() {
        return getExpertiseId();
    }

    @Override
    @Nullable
    public UUID value4() {
        return getAcademicMajorId();
    }

    @Override
    @Nullable
    public UUID value5() {
        return getAcademicSubjectId();
    }

    @Override
    @Nullable
    public OffsetDateTime value6() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value7() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value8() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value9() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value2(@Nonnull UUID value) {
        setEducatorProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value3(@Nonnull UUID value) {
        setExpertiseId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value4(@Nullable UUID value) {
        setAcademicMajorId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value5(@Nullable UUID value) {
        setAcademicSubjectId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value6(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value7(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value8(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord value9(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorProfileExpertiseAcademicMapRecord values(@Nonnull UUID value1, @Nonnull UUID value2, @Nonnull UUID value3, @Nullable UUID value4, @Nullable UUID value5, @Nullable OffsetDateTime value6, @Nullable String value7, @Nullable OffsetDateTime value8, @Nullable String value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EducatorProfileExpertiseAcademicMapRecord
     */
    public EducatorProfileExpertiseAcademicMapRecord() {
        super(EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP);
    }

    /**
     * Create a detached, initialised EducatorProfileExpertiseAcademicMapRecord
     */
    @ConstructorProperties({ "id", "educatorProfileId", "expertiseId", "academicMajorId", "academicSubjectId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public EducatorProfileExpertiseAcademicMapRecord(@Nonnull UUID id, @Nonnull UUID educatorProfileId, @Nonnull UUID expertiseId, @Nullable UUID academicMajorId, @Nullable UUID academicSubjectId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP);

        setId(id);
        setEducatorProfileId(educatorProfileId);
        setExpertiseId(expertiseId);
        setAcademicMajorId(academicMajorId);
        setAcademicSubjectId(academicSubjectId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised EducatorProfileExpertiseAcademicMapRecord
     */
    public EducatorProfileExpertiseAcademicMapRecord(EducatorProfileExpertiseAcademicMapPojo value) {
        super(EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP);

        if (value != null) {
            setId(value.getId());
            setEducatorProfileId(value.getEducatorProfileId());
            setExpertiseId(value.getExpertiseId());
            setAcademicMajorId(value.getAcademicMajorId());
            setAcademicSubjectId(value.getAcademicSubjectId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
