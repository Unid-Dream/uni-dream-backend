/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.StudentEcaProfileMapTable;
import unid.jooqMono.model.tables.pojos.StudentEcaProfileMapPojo;


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
public class StudentEcaProfileMapRecord extends UpdatableRecordImpl<StudentEcaProfileMapRecord> implements Record5<UUID, UUID, UUID, UUID, UUID[]> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_eca_profile_map.id</code>.
     */
    public StudentEcaProfileMapRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_eca_profile_map.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.student_eca_profile_map.student_profile_id</code>.
     */
    public StudentEcaProfileMapRecord setStudentProfileId(@Nullable UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_eca_profile_map.student_profile_id</code>.
     */
    @Nullable
    public UUID getStudentProfileId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.student_eca_profile_map.eca_profile_id</code>.
     */
    public StudentEcaProfileMapRecord setEcaProfileId(@Nullable UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.student_eca_profile_map.eca_profile_id</code>.
     */
    @Nullable
    public UUID getEcaProfileId() {
        return (UUID) get(2);
    }

    /**
     * Setter for
     * <code>public.student_eca_profile_map.eca_profile_section_id</code>.
     */
    public StudentEcaProfileMapRecord setEcaProfileSectionId(@Nullable UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_eca_profile_map.eca_profile_section_id</code>.
     */
    @Nullable
    public UUID getEcaProfileSectionId() {
        return (UUID) get(3);
    }

    /**
     * Setter for
     * <code>public.student_eca_profile_map.eca_profile_option_checked_id</code>.
     */
    public StudentEcaProfileMapRecord setEcaProfileOptionCheckedId(@Nullable UUID[] value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_eca_profile_map.eca_profile_option_checked_id</code>.
     */
    @Nullable
    public UUID[] getEcaProfileOptionCheckedId() {
        return (UUID[]) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row5<UUID, UUID, UUID, UUID, UUID[]> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row5<UUID, UUID, UUID, UUID, UUID[]> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.STUDENT_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID;
    }

    @Override
    @Nonnull
    public Field<UUID[]> field5() {
        return StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_OPTION_CHECKED_ID;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID component2() {
        return getStudentProfileId();
    }

    @Override
    @Nullable
    public UUID component3() {
        return getEcaProfileId();
    }

    @Override
    @Nullable
    public UUID component4() {
        return getEcaProfileSectionId();
    }

    @Override
    @Nullable
    public UUID[] component5() {
        return getEcaProfileOptionCheckedId();
    }

    @Override
    @Nonnull
    public UUID value1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID value2() {
        return getStudentProfileId();
    }

    @Override
    @Nullable
    public UUID value3() {
        return getEcaProfileId();
    }

    @Override
    @Nullable
    public UUID value4() {
        return getEcaProfileSectionId();
    }

    @Override
    @Nullable
    public UUID[] value5() {
        return getEcaProfileOptionCheckedId();
    }

    @Override
    @Nonnull
    public StudentEcaProfileMapRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentEcaProfileMapRecord value2(@Nullable UUID value) {
        setStudentProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentEcaProfileMapRecord value3(@Nullable UUID value) {
        setEcaProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentEcaProfileMapRecord value4(@Nullable UUID value) {
        setEcaProfileSectionId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentEcaProfileMapRecord value5(@Nullable UUID[] value) {
        setEcaProfileOptionCheckedId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentEcaProfileMapRecord values(@Nonnull UUID value1, @Nullable UUID value2, @Nullable UUID value3, @Nullable UUID value4, @Nullable UUID[] value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StudentEcaProfileMapRecord
     */
    public StudentEcaProfileMapRecord() {
        super(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP);
    }

    /**
     * Create a detached, initialised StudentEcaProfileMapRecord
     */
    @ConstructorProperties({ "id", "studentProfileId", "ecaProfileId", "ecaProfileSectionId", "ecaProfileOptionCheckedId" })
    public StudentEcaProfileMapRecord(@Nonnull UUID id, @Nullable UUID studentProfileId, @Nullable UUID ecaProfileId, @Nullable UUID ecaProfileSectionId, @Nullable UUID[] ecaProfileOptionCheckedId) {
        super(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP);

        setId(id);
        setStudentProfileId(studentProfileId);
        setEcaProfileId(ecaProfileId);
        setEcaProfileSectionId(ecaProfileSectionId);
        setEcaProfileOptionCheckedId(ecaProfileOptionCheckedId);
    }

    /**
     * Create a detached, initialised StudentEcaProfileMapRecord
     */
    public StudentEcaProfileMapRecord(StudentEcaProfileMapPojo value) {
        super(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP);

        if (value != null) {
            setId(value.getId());
            setStudentProfileId(value.getStudentProfileId());
            setEcaProfileId(value.getEcaProfileId());
            setEcaProfileSectionId(value.getEcaProfileSectionId());
            setEcaProfileOptionCheckedId(value.getEcaProfileOptionCheckedId());
        }
    }
}
