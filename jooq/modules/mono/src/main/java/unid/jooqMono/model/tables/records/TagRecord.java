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

import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.TagTable;
import unid.jooqMono.model.tables.pojos.TagPojo;


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
public class TagRecord extends UpdatableRecordImpl<TagRecord> implements Record7<UUID, UUID, TagCategoryEnum, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.tag.id</code>.
     */
    public TagRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.tag.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.tag.description_i18n_id</code>.
     */
    public TagRecord setDescriptionI18nId(@Nullable UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.tag.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.tag.tag_category</code>.
     */
    public TagRecord setTagCategory(@Nullable TagCategoryEnum value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.tag.tag_category</code>.
     */
    @Nullable
    public TagCategoryEnum getTagCategory() {
        return (TagCategoryEnum) get(2);
    }

    /**
     * Setter for <code>public.tag.created_on</code>.
     */
    public TagRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.tag.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(3);
    }

    /**
     * Setter for <code>public.tag.created_by</code>.
     */
    public TagRecord setCreatedBy(@Nullable String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.tag.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.tag.updated_on</code>.
     */
    public TagRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.tag.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(5);
    }

    /**
     * Setter for <code>public.tag.updated_by</code>.
     */
    public TagRecord setUpdatedBy(@Nullable String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.tag.updated_by</code>.
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
    public Row7<UUID, UUID, TagCategoryEnum, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row7<UUID, UUID, TagCategoryEnum, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return TagTable.TAG.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return TagTable.TAG.DESCRIPTION_I18N_ID;
    }

    @Override
    @Nonnull
    public Field<TagCategoryEnum> field3() {
        return TagTable.TAG.TAG_CATEGORY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field4() {
        return TagTable.TAG.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field5() {
        return TagTable.TAG.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field6() {
        return TagTable.TAG.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field7() {
        return TagTable.TAG.UPDATED_BY;
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
    public TagCategoryEnum component3() {
        return getTagCategory();
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
    public TagCategoryEnum value3() {
        return getTagCategory();
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
    public TagRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public TagRecord value2(@Nullable UUID value) {
        setDescriptionI18nId(value);
        return this;
    }

    @Override
    @Nonnull
    public TagRecord value3(@Nullable TagCategoryEnum value) {
        setTagCategory(value);
        return this;
    }

    @Override
    @Nonnull
    public TagRecord value4(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public TagRecord value5(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public TagRecord value6(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public TagRecord value7(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public TagRecord values(@Nonnull UUID value1, @Nullable UUID value2, @Nullable TagCategoryEnum value3, @Nullable OffsetDateTime value4, @Nullable String value5, @Nullable OffsetDateTime value6, @Nullable String value7) {
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
     * Create a detached TagRecord
     */
    public TagRecord() {
        super(TagTable.TAG);
    }

    /**
     * Create a detached, initialised TagRecord
     */
    @ConstructorProperties({ "id", "descriptionI18nId", "tagCategory", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public TagRecord(@Nonnull UUID id, @Nullable UUID descriptionI18nId, @Nullable TagCategoryEnum tagCategory, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(TagTable.TAG);

        setId(id);
        setDescriptionI18nId(descriptionI18nId);
        setTagCategory(tagCategory);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised TagRecord
     */
    public TagRecord(TagPojo value) {
        super(TagTable.TAG);

        if (value != null) {
            setId(value.getId());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setTagCategory(value.getTagCategory());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
