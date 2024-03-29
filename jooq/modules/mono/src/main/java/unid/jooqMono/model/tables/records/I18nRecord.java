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

import unid.jooqMono.model.tables.I18nTable;
import unid.jooqMono.model.tables.pojos.I18nPojo;


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
public class I18nRecord extends UpdatableRecordImpl<I18nRecord> implements Record8<UUID, String, String, String, OffsetDateTime, String, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.i18n.id</code>.
     */
    public I18nRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.i18n.english</code>.
     */
    public I18nRecord setEnglish(@Nullable String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.english</code>.
     */
    @Nullable
    public String getEnglish() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.i18n.chinese_traditional</code>.
     */
    public I18nRecord setChineseTraditional(@Nullable String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.chinese_traditional</code>.
     */
    @Nullable
    public String getChineseTraditional() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.i18n.chinese_simplified</code>.
     */
    public I18nRecord setChineseSimplified(@Nullable String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.chinese_simplified</code>.
     */
    @Nullable
    public String getChineseSimplified() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.i18n.created_on</code>.
     */
    public I18nRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(4);
    }

    /**
     * Setter for <code>public.i18n.created_by</code>.
     */
    public I18nRecord setCreatedBy(@Nullable String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.i18n.updated_on</code>.
     */
    public I18nRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(6);
    }

    /**
     * Setter for <code>public.i18n.updated_by</code>.
     */
    public I18nRecord setUpdatedBy(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.i18n.updated_by</code>.
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
    public Row8<UUID, String, String, String, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row8<UUID, String, String, String, OffsetDateTime, String, OffsetDateTime, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return I18nTable.I18N.ID;
    }

    @Override
    @Nonnull
    public Field<String> field2() {
        return I18nTable.I18N.ENGLISH;
    }

    @Override
    @Nonnull
    public Field<String> field3() {
        return I18nTable.I18N.CHINESE_TRADITIONAL;
    }

    @Override
    @Nonnull
    public Field<String> field4() {
        return I18nTable.I18N.CHINESE_SIMPLIFIED;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field5() {
        return I18nTable.I18N.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field6() {
        return I18nTable.I18N.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field7() {
        return I18nTable.I18N.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field8() {
        return I18nTable.I18N.UPDATED_BY;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nullable
    public String component2() {
        return getEnglish();
    }

    @Override
    @Nullable
    public String component3() {
        return getChineseTraditional();
    }

    @Override
    @Nullable
    public String component4() {
        return getChineseSimplified();
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
    public String value2() {
        return getEnglish();
    }

    @Override
    @Nullable
    public String value3() {
        return getChineseTraditional();
    }

    @Override
    @Nullable
    public String value4() {
        return getChineseSimplified();
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
    public I18nRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord value2(@Nullable String value) {
        setEnglish(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord value3(@Nullable String value) {
        setChineseTraditional(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord value4(@Nullable String value) {
        setChineseSimplified(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord value5(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord value6(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord value7(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord value8(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public I18nRecord values(@Nonnull UUID value1, @Nullable String value2, @Nullable String value3, @Nullable String value4, @Nullable OffsetDateTime value5, @Nullable String value6, @Nullable OffsetDateTime value7, @Nullable String value8) {
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
     * Create a detached I18nRecord
     */
    public I18nRecord() {
        super(I18nTable.I18N);
    }

    /**
     * Create a detached, initialised I18nRecord
     */
    @ConstructorProperties({ "id", "english", "chineseTraditional", "chineseSimplified", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public I18nRecord(@Nonnull UUID id, @Nullable String english, @Nullable String chineseTraditional, @Nullable String chineseSimplified, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy) {
        super(I18nTable.I18N);

        setId(id);
        setEnglish(english);
        setChineseTraditional(chineseTraditional);
        setChineseSimplified(chineseSimplified);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
    }

    /**
     * Create a detached, initialised I18nRecord
     */
    public I18nRecord(I18nPojo value) {
        super(I18nTable.I18N);

        if (value != null) {
            setId(value.getId());
            setEnglish(value.getEnglish());
            setChineseTraditional(value.getChineseTraditional());
            setChineseSimplified(value.getChineseSimplified());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
        }
    }
}
