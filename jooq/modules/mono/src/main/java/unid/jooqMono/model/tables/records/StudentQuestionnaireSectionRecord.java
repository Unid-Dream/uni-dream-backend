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

import unid.jooqMono.model.tables.StudentQuestionnaireSectionTable;
import unid.jooqMono.model.tables.pojos.StudentQuestionnaireSectionPojo;


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
public class StudentQuestionnaireSectionRecord extends UpdatableRecordImpl<StudentQuestionnaireSectionRecord> implements Record9<UUID, UUID, OffsetDateTime, Integer, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_questionnaire_section.id</code>.
     */
    public StudentQuestionnaireSectionRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_section.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.student_questionnaire_section.student_questionnaire_id</code>.
     */
    public StudentQuestionnaireSectionRecord setStudentQuestionnaireId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_questionnaire_section.student_questionnaire_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentQuestionnaireId() {
        return (UUID) get(1);
    }

    /**
     * Setter for
     * <code>public.student_questionnaire_section.student_questionnaire_version</code>.
     */
    public StudentQuestionnaireSectionRecord setStudentQuestionnaireVersion(@Nonnull OffsetDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_questionnaire_section.student_questionnaire_version</code>.
     */
    @NotNull
    @Nonnull
    public OffsetDateTime getStudentQuestionnaireVersion() {
        return (OffsetDateTime) get(2);
    }

    /**
     * Setter for <code>public.student_questionnaire_section.order</code>.
     */
    public StudentQuestionnaireSectionRecord setOrder(@Nonnull Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_section.order</code>.
     */
    @NotNull
    @Nonnull
    public Integer getOrder() {
        return (Integer) get(3);
    }

    /**
     * Setter for
     * <code>public.student_questionnaire_section.title_i18n_id</code>.
     */
    public StudentQuestionnaireSectionRecord setTitleI18nId(@Nullable UUID value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_questionnaire_section.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return (UUID) get(4);
    }

    /**
     * Setter for <code>public.student_questionnaire_section.created_on</code>.
     */
    public StudentQuestionnaireSectionRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_section.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(5);
    }

    /**
     * Setter for <code>public.student_questionnaire_section.created_by</code>.
     */
    public StudentQuestionnaireSectionRecord setCreatedBy(@Nullable String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_section.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.student_questionnaire_section.updated_on</code>.
     */
    public StudentQuestionnaireSectionRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_section.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(7);
    }

    /**
     * Setter for <code>public.student_questionnaire_section.updated_by</code>.
     */
    public StudentQuestionnaireSectionRecord setUpdatedBy(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.student_questionnaire_section.updated_by</code>.
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
    public Row9<UUID, UUID, OffsetDateTime, Integer, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row9<UUID, UUID, OffsetDateTime, Integer, UUID, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.STUDENT_QUESTIONNAIRE_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field3() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.STUDENT_QUESTIONNAIRE_VERSION;
    }

    @Override
    @Nonnull
    public Field<Integer> field4() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.ORDER;
    }

    @Override
    @Nonnull
    public Field<UUID> field5() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.TITLE_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field6() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field7() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field8() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field9() {
        return StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID component2() {
        return getStudentQuestionnaireId();
    }

    @Override
    @Nonnull
    public OffsetDateTime component3() {
        return getStudentQuestionnaireVersion();
    }

    @Override
    @Nonnull
    public Integer component4() {
        return getOrder();
    }

    @Override
    @Nullable
    public UUID component5() {
        return getTitleI18nId();
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
        return getStudentQuestionnaireId();
    }

    @Override
    @Nonnull
    public OffsetDateTime value3() {
        return getStudentQuestionnaireVersion();
    }

    @Override
    @Nonnull
    public Integer value4() {
        return getOrder();
    }

    @Override
    @Nullable
    public UUID value5() {
        return getTitleI18nId();
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
    public StudentQuestionnaireSectionRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value2(@Nonnull UUID value) {
        setStudentQuestionnaireId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value3(@Nonnull OffsetDateTime value) {
        setStudentQuestionnaireVersion(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value4(@Nonnull Integer value) {
        setOrder(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value5(@Nullable UUID value) {
        setTitleI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value6(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value7(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value8(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord value9(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public StudentQuestionnaireSectionRecord values(@Nonnull UUID value1, @Nonnull UUID value2, @Nonnull OffsetDateTime value3, @Nonnull Integer value4, @Nullable UUID value5, @Nullable OffsetDateTime value6, @Nullable String value7, @Nullable OffsetDateTime value8, @Nullable String value9) {
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
     * Create a detached StudentQuestionnaireSectionRecord
     */
    public StudentQuestionnaireSectionRecord() {
        super(StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION);
    }

    /**
     * Create a detached, initialised StudentQuestionnaireSectionRecord
     */
    @ConstructorProperties({ "id", "studentQuestionnaireId", "studentQuestionnaireVersion", "order", "titleI18nId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public StudentQuestionnaireSectionRecord(@Nonnull UUID id, @Nonnull UUID studentQuestionnaireId, @Nonnull OffsetDateTime studentQuestionnaireVersion, @Nonnull Integer order, @Nullable UUID titleI18nId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION);

        setId(id);
        setStudentQuestionnaireId(studentQuestionnaireId);
        setStudentQuestionnaireVersion(studentQuestionnaireVersion);
        setOrder(order);
        setTitleI18nId(titleI18nId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised StudentQuestionnaireSectionRecord
     */
    public StudentQuestionnaireSectionRecord(StudentQuestionnaireSectionPojo value) {
        super(StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION);

        if (value != null) {
            setId(value.getId());
            setStudentQuestionnaireId(value.getStudentQuestionnaireId());
            setStudentQuestionnaireVersion(value.getStudentQuestionnaireVersion());
            setOrder(value.getOrder());
            setTitleI18nId(value.getTitleI18nId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
