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
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.enums.AcademicSubjectResourceTypeEnum;
import unid.jooqMono.model.tables.AcademicSubjectResourceTable;
import unid.jooqMono.model.tables.pojos.AcademicSubjectResourcePojo;


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
public class AcademicSubjectResourceRecord extends UpdatableRecordImpl<AcademicSubjectResourceRecord> implements Record11<UUID, UUID, AcademicSubjectResourceTypeEnum, UUID, String, String, String, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.academic_subject_resource.id</code>.
     */
    public AcademicSubjectResourceRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.academic_subject_resource.academic_subject_id</code>.
     */
    public AcademicSubjectResourceRecord setAcademicSubjectId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.academic_subject_resource.academic_subject_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getAcademicSubjectId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.academic_subject_resource.type</code>.
     */
    public AcademicSubjectResourceRecord setType(@Nullable AcademicSubjectResourceTypeEnum value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.type</code>.
     */
    @Nullable
    public AcademicSubjectResourceTypeEnum getType() {
        return (AcademicSubjectResourceTypeEnum) get(2);
    }

    /**
     * Setter for <code>public.academic_subject_resource.title_i18n_id</code>.
     */
    public AcademicSubjectResourceRecord setTitleI18nId(@Nullable UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.academic_subject_resource.author</code>.
     */
    public AcademicSubjectResourceRecord setAuthor(@Nullable String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.author</code>.
     */
    @Nullable
    public String getAuthor() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.academic_subject_resource.url</code>.
     */
    public AcademicSubjectResourceRecord setUrl(@Nullable String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.url</code>.
     */
    @Nullable
    public String getUrl() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.academic_subject_resource.thumbnail</code>.
     */
    public AcademicSubjectResourceRecord setThumbnail(@Nullable String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.thumbnail</code>.
     */
    @Nullable
    public String getThumbnail() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.academic_subject_resource.created_on</code>.
     */
    public AcademicSubjectResourceRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(7);
    }

    /**
     * Setter for <code>public.academic_subject_resource.created_by</code>.
     */
    public AcademicSubjectResourceRecord setCreatedBy(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.academic_subject_resource.updated_on</code>.
     */
    public AcademicSubjectResourceRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(9);
    }

    /**
     * Setter for <code>public.academic_subject_resource.updated_by</code>.
     */
    public AcademicSubjectResourceRecord setUpdatedBy(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(10);
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
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row11<UUID, UUID, AcademicSubjectResourceTypeEnum, UUID, String, String, String, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row11<UUID, UUID, AcademicSubjectResourceTypeEnum, UUID, String, String, String, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.ACADEMIC_SUBJECT_ID;
    }

    @Override
    @Nonnull
    public Field<AcademicSubjectResourceTypeEnum> field3() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field4() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.TITLE_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.AUTHOR;
    }

    @Override
    @Nonnull
    public Field<String> field6() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.URL;
    }

    @Override
    @Nonnull
    public Field<String> field7() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.THUMBNAIL;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field8() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field9() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field10() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field11() {
        return AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nonnull
    public UUID component2() {
        return getAcademicSubjectId();
    }

    @Override
    @Nullable
    public AcademicSubjectResourceTypeEnum component3() {
        return getType();
    }

    @Override
    @Nullable
    public UUID component4() {
        return getTitleI18nId();
    }

    @Override
    @Nullable
    public String component5() {
        return getAuthor();
    }

    @Override
    @Nullable
    public String component6() {
        return getUrl();
    }

    @Override
    @Nullable
    public String component7() {
        return getThumbnail();
    }

    @Override
    @Nullable
    public OffsetDateTime component8() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component9() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component10() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component11() {
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
        return getAcademicSubjectId();
    }

    @Override
    @Nullable
    public AcademicSubjectResourceTypeEnum value3() {
        return getType();
    }

    @Override
    @Nullable
    public UUID value4() {
        return getTitleI18nId();
    }

    @Override
    @Nullable
    public String value5() {
        return getAuthor();
    }

    @Override
    @Nullable
    public String value6() {
        return getUrl();
    }

    @Override
    @Nullable
    public String value7() {
        return getThumbnail();
    }

    @Override
    @Nullable
    public OffsetDateTime value8() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value9() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value10() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value11() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value2(@Nonnull UUID value) {
        setAcademicSubjectId(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value3(@Nullable AcademicSubjectResourceTypeEnum value) {
        setType(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value4(@Nullable UUID value) {
        setTitleI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value5(@Nullable String value) {
        setAuthor(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value6(@Nullable String value) {
        setUrl(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value7(@Nullable String value) {
        setThumbnail(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value8(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value9(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value10(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord value11(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public AcademicSubjectResourceRecord values(@Nonnull UUID value1, @Nonnull UUID value2, @Nullable AcademicSubjectResourceTypeEnum value3, @Nullable UUID value4, @Nullable String value5, @Nullable String value6, @Nullable String value7, @Nullable OffsetDateTime value8, @Nullable String value9, @Nullable OffsetDateTime value10, @Nullable String value11) {
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
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AcademicSubjectResourceRecord
     */
    public AcademicSubjectResourceRecord() {
        super(AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE);
    }

    /**
     * Create a detached, initialised AcademicSubjectResourceRecord
     */
    @ConstructorProperties({ "id", "academicSubjectId", "type", "titleI18nId", "author", "url", "thumbnail", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public AcademicSubjectResourceRecord(@Nonnull UUID id, @Nonnull UUID academicSubjectId, @Nullable AcademicSubjectResourceTypeEnum type, @Nullable UUID titleI18nId, @Nullable String author, @Nullable String url, @Nullable String thumbnail, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE);

        setId(id);
        setAcademicSubjectId(academicSubjectId);
        setType(type);
        setTitleI18nId(titleI18nId);
        setAuthor(author);
        setUrl(url);
        setThumbnail(thumbnail);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised AcademicSubjectResourceRecord
     */
    public AcademicSubjectResourceRecord(AcademicSubjectResourcePojo value) {
        super(AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE);

        if (value != null) {
            setId(value.getId());
            setAcademicSubjectId(value.getAcademicSubjectId());
            setType(value.getType());
            setTitleI18nId(value.getTitleI18nId());
            setAuthor(value.getAuthor());
            setUrl(value.getUrl());
            setThumbnail(value.getThumbnail());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
