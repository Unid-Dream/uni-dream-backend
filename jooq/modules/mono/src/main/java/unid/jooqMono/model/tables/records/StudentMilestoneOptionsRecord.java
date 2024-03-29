/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.enums.MilestoneOptionTypeEnum;
import unid.jooqMono.model.tables.StudentMilestoneOptionsTable;
import unid.jooqMono.model.tables.pojos.StudentMilestoneOptionsPojo;


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
public class StudentMilestoneOptionsRecord extends UpdatableRecordImpl<StudentMilestoneOptionsRecord> implements Record5<UUID, UUID, Integer, UUID, MilestoneOptionTypeEnum> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_milestone_options.id</code>.
     */
    public StudentMilestoneOptionsRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_options.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.student_milestone_options.answer_i18n_id</code>.
     */
    public StudentMilestoneOptionsRecord setAnswerI18nId(@Nullable UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_options.answer_i18n_id</code>.
     */
    @Nullable
    public UUID getAnswerI18nId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.student_milestone_options.sort_id</code>.
     */
    public StudentMilestoneOptionsRecord setSortId(@Nullable Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_options.sort_id</code>.
     */
    @Nullable
    public Integer getSortId() {
        return (Integer) get(2);
    }

    /**
     * Setter for
     * <code>public.student_milestone_options.questionnaire_id</code>.
     */
    public StudentMilestoneOptionsRecord setQuestionnaireId(@Nullable UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_milestone_options.questionnaire_id</code>.
     */
    @Nullable
    public UUID getQuestionnaireId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.student_milestone_options.option_type</code>.
     */
    public StudentMilestoneOptionsRecord setOptionType(@Nonnull MilestoneOptionTypeEnum value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.student_milestone_options.option_type</code>.
     */
    @NotNull
    @Nonnull
    public MilestoneOptionTypeEnum getOptionType() {
        return (MilestoneOptionTypeEnum) get(4);
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
    public Row5<UUID, UUID, Integer, UUID, MilestoneOptionTypeEnum> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row5<UUID, UUID, Integer, UUID, MilestoneOptionTypeEnum> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS.ANSWER_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<Integer> field3() {
        return StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS.SORT_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS.QUESTIONNAIRE_ID;
    }

    @Override
    @Nonnull
    public Field<MilestoneOptionTypeEnum> field5() {
        return StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS.OPTION_TYPE;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID component2() {
        return getAnswerI18nId();
    }

    @Override
    @Nullable
    public Integer component3() {
        return getSortId();
    }

    @Override
    @Nullable
    public UUID component4() {
        return getQuestionnaireId();
    }

    @Override
    @Nonnull
    public MilestoneOptionTypeEnum component5() {
        return getOptionType();
    }

    @Override
    @Nonnull
    public UUID value1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID value2() {
        return getAnswerI18nId();
    }

    @Override
    @Nullable
    public Integer value3() {
        return getSortId();
    }

    @Override
    @Nullable
    public UUID value4() {
        return getQuestionnaireId();
    }

    @Override
    @Nonnull
    public MilestoneOptionTypeEnum value5() {
        return getOptionType();
    }

    @Override
    @Nonnull
    public StudentMilestoneOptionsRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneOptionsRecord value2(@Nullable UUID value) {
        setAnswerI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneOptionsRecord value3(@Nullable Integer value) {
        setSortId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneOptionsRecord value4(@Nullable UUID value) {
        setQuestionnaireId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneOptionsRecord value5(@Nonnull MilestoneOptionTypeEnum value) {
        setOptionType(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentMilestoneOptionsRecord values(@Nonnull UUID value1, @Nullable UUID value2, @Nullable Integer value3, @Nullable UUID value4, @Nonnull MilestoneOptionTypeEnum value5) {
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
     * Create a detached StudentMilestoneOptionsRecord
     */
    public StudentMilestoneOptionsRecord() {
        super(StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS);
    }

    /**
     * Create a detached, initialised StudentMilestoneOptionsRecord
     */
    @ConstructorProperties({ "id", "answerI18nId", "sortId", "questionnaireId", "optionType" })
    public StudentMilestoneOptionsRecord(@Nonnull UUID id, @Nullable UUID answerI18nId, @Nullable Integer sortId, @Nullable UUID questionnaireId, @Nonnull MilestoneOptionTypeEnum optionType) {
        super(StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS);

        setId(id);
        setAnswerI18nId(answerI18nId);
        setSortId(sortId);
        setQuestionnaireId(questionnaireId);
        setOptionType(optionType);
    }

    /**
     * Create a detached, initialised StudentMilestoneOptionsRecord
     */
    public StudentMilestoneOptionsRecord(StudentMilestoneOptionsPojo value) {
        super(StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS);

        if (value != null) {
            setId(value.getId());
            setAnswerI18nId(value.getAnswerI18nId());
            setSortId(value.getSortId());
            setQuestionnaireId(value.getQuestionnaireId());
            setOptionType(value.getOptionType());
        }
    }
}
