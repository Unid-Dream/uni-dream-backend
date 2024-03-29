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
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.tables.LanguageTable;
import unid.jooqMono.model.tables.pojos.LanguagePojo;


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
public class LanguageRecord extends UpdatableRecordImpl<LanguageRecord> implements Record7<UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.language.id</code>.
     */
    public LanguageRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.language.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.language.description_i18n_id</code>.
     */
    public LanguageRecord setDescriptionI18nId(@Nullable UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.language.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.language.tag_id</code>.
     */
    public LanguageRecord setTagId(@Nullable UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.language.tag_id</code>.
     */
    @Nullable
    public UUID getTagId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.language.created_on</code>.
     */
    public LanguageRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.language.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(3);
    }

    /**
     * Setter for <code>public.language.created_by</code>.
     */
    public LanguageRecord setCreatedBy(@Nullable String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.language.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.language.updated_on</code>.
     */
    public LanguageRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.language.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(5);
    }

    /**
     * Setter for <code>public.language.updated_by</code>.
     */
    public LanguageRecord setUpdatedBy(@Nullable String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.language.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row7<UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row7<UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return LanguageTable.LANGUAGE.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return LanguageTable.LANGUAGE.DESCRIPTION_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return LanguageTable.LANGUAGE.TAG_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field4() {
        return LanguageTable.LANGUAGE.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return LanguageTable.LANGUAGE.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field6() {
        return LanguageTable.LANGUAGE.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field7() {
        return LanguageTable.LANGUAGE.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID component2() {
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID component3() {
        return getTagId();
    }

    @Override
    @Nullable
    public OffsetDateTime component4() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component5() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component6() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component7() {
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
        return getDescriptionI18nId();
    }

    @Override
    @Nullable
    public UUID value3() {
        return getTagId();
    }

    @Override
    @Nullable
    public OffsetDateTime value4() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value5() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value6() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value7() {
        return getUpdatedBy();
    }

    @Override
    @Nonnull
    public LanguageRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public LanguageRecord value2(@Nullable UUID value) {
        setDescriptionI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public LanguageRecord value3(@Nullable UUID value) {
        setTagId(value);
        return this;
    }

    @Override
    @Nonnull
    public LanguageRecord value4(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public LanguageRecord value5(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public LanguageRecord value6(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public LanguageRecord value7(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public LanguageRecord values(@Nonnull UUID value1, @Nullable UUID value2, @Nullable UUID value3, @Nullable OffsetDateTime value4, @Nullable String value5, @Nullable OffsetDateTime value6, @Nullable String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LanguageRecord
     */
    public LanguageRecord() {
        super(LanguageTable.LANGUAGE);
    }

    /**
     * Create a detached, initialised LanguageRecord
     */
    @ConstructorProperties({ "id", "descriptionI18nId", "tagId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public LanguageRecord(@Nonnull UUID id, @Nullable UUID descriptionI18nId, @Nullable UUID tagId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(LanguageTable.LANGUAGE);

        setId(id);
        setDescriptionI18nId(descriptionI18nId);
        setTagId(tagId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised LanguageRecord
     */
    public LanguageRecord(LanguagePojo value) {
        super(LanguageTable.LANGUAGE);

        if (value != null) {
            setId(value.getId());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setTagId(value.getTagId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
