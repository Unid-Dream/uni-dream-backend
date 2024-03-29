/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables;


import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row11;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Keys;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.records.EducatorProfileExtensionRecord;


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
public class EducatorProfileExtensionTable extends TableImpl<EducatorProfileExtensionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.educator_profile_extension</code>
     */
    public static final EducatorProfileExtensionTable EDUCATOR_PROFILE_EXTENSION = new EducatorProfileExtensionTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<EducatorProfileExtensionRecord> getRecordType() {
        return EducatorProfileExtensionRecord.class;
    }

    /**
     * The column <code>public.educator_profile_extension.id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column
     * <code>public.educator_profile_extension.educator_profile_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID> EDUCATOR_PROFILE_ID = createField(DSL.name("educator_profile_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.educator_profile_extension.expertise_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID[]> EXPERTISE_ID = createField(DSL.name("expertise_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile_extension.description</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile_extension.language_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID[]> LANGUAGE_ID = createField(DSL.name("language_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile_extension.city_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID> CITY_ID = createField(DSL.name("city_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public.educator_profile_extension.education_school_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID[]> EDUCATION_SCHOOL_ID = createField(DSL.name("education_school_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile_extension.major_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID[]> MAJOR_ID = createField(DSL.name("major_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile_extension.country_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID> COUNTRY_ID = createField(DSL.name("country_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.educator_profile_extension.education_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID[]> EDUCATION_ID = createField(DSL.name("education_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column
     * <code>public.educator_profile_extension.expertise_description_id</code>.
     */
    public final TableField<EducatorProfileExtensionRecord, UUID[]> EXPERTISE_DESCRIPTION_ID = createField(DSL.name("expertise_description_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    private EducatorProfileExtensionTable(Name alias, Table<EducatorProfileExtensionRecord> aliased) {
        this(alias, aliased, null);
    }

    private EducatorProfileExtensionTable(Name alias, Table<EducatorProfileExtensionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.educator_profile_extension</code> table
     * reference
     */
    public EducatorProfileExtensionTable(String alias) {
        this(DSL.name(alias), EDUCATOR_PROFILE_EXTENSION);
    }

    /**
     * Create an aliased <code>public.educator_profile_extension</code> table
     * reference
     */
    public EducatorProfileExtensionTable(Name alias) {
        this(alias, EDUCATOR_PROFILE_EXTENSION);
    }

    /**
     * Create a <code>public.educator_profile_extension</code> table reference
     */
    public EducatorProfileExtensionTable() {
        this(DSL.name("educator_profile_extension"), null);
    }

    public <O extends Record> EducatorProfileExtensionTable(Table<O> child, ForeignKey<O, EducatorProfileExtensionRecord> key) {
        super(child, key, EDUCATOR_PROFILE_EXTENSION);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<EducatorProfileExtensionRecord> getPrimaryKey() {
        return Keys.EDUCATOR_PROFILE_EXTENSION_PKEY;
    }

    @Override
    @Nonnull
    public EducatorProfileExtensionTable as(String alias) {
        return new EducatorProfileExtensionTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public EducatorProfileExtensionTable as(Name alias) {
        return new EducatorProfileExtensionTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public EducatorProfileExtensionTable rename(String name) {
        return new EducatorProfileExtensionTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public EducatorProfileExtensionTable rename(Name name) {
        return new EducatorProfileExtensionTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row11<UUID, UUID, UUID[], String, UUID[], UUID, UUID[], UUID[], UUID, UUID[], UUID[]> fieldsRow() {
        return (Row11) super.fieldsRow();
    }
}
