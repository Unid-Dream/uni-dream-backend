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
import org.jooq.JSONB;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.StudentMilestoneMapTable;
import unid.jooqMono.model.tables.pojos.StudentMilestoneMapPojo;


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
public class StudentMilestoneMapRecord extends UpdatableRecordImpl<StudentMilestoneMapRecord> implements Record3<UUID, UUID, JSONB> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_milestone_map.id</code>.
     */
    public StudentMilestoneMapRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_map.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.student_milestone_map.student_profile_id</code>.
     */
    public StudentMilestoneMapRecord setStudentProfileId(@Nullable UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_map.student_profile_id</code>.
     */
    @Nullable
    public UUID getStudentProfileId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.student_milestone_map.questionnaire</code>.
     */
    public StudentMilestoneMapRecord setQuestionnaire(@Nullable JSONB value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_map.questionnaire</code>.
     */
    @Nullable
    public JSONB getQuestionnaire() {
        return (JSONB) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row3<UUID, UUID, JSONB> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row3<UUID, UUID, JSONB> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return StudentMilestoneMapTable.STUDENT_MILESTONE_MAP.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return StudentMilestoneMapTable.STUDENT_MILESTONE_MAP.STUDENT_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<JSONB> field3() {
        return StudentMilestoneMapTable.STUDENT_MILESTONE_MAP.QUESTIONNAIRE;
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
    public JSONB component3() {
        return getQuestionnaire();
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
    public JSONB value3() {
        return getQuestionnaire();
    }

    @Override
    @Nonnull
    public StudentMilestoneMapRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneMapRecord value2(@Nullable UUID value) {
        setStudentProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneMapRecord value3(@Nullable JSONB value) {
        setQuestionnaire(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneMapRecord values(@Nonnull UUID value1, @Nullable UUID value2, @Nullable JSONB value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StudentMilestoneMapRecord
     */
    public StudentMilestoneMapRecord() {
        super(StudentMilestoneMapTable.STUDENT_MILESTONE_MAP);
    }

    /**
     * Create a detached, initialised StudentMilestoneMapRecord
     */
    @ConstructorProperties({ "id", "studentProfileId", "questionnaire" })
    public StudentMilestoneMapRecord(@Nonnull UUID id, @Nullable UUID studentProfileId, @Nullable JSONB questionnaire) {
        super(StudentMilestoneMapTable.STUDENT_MILESTONE_MAP);

        setId(id);
        setStudentProfileId(studentProfileId);
        setQuestionnaire(questionnaire);
    }

    /**
     * Create a detached, initialised StudentMilestoneMapRecord
     */
    public StudentMilestoneMapRecord(StudentMilestoneMapPojo value) {
        super(StudentMilestoneMapTable.STUDENT_MILESTONE_MAP);

        if (value != null) {
            setId(value.getId());
            setStudentProfileId(value.getStudentProfileId());
            setQuestionnaire(value.getQuestionnaire());
        }
    }
}
