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
import org.jooq.Row4;
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
import unid.jooqMono.model.tables.records.PassionSubjectRecord;


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
public class PassionSubjectTable extends TableImpl<PassionSubjectRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.passion_subject</code>
     */
    public static final PassionSubjectTable PASSION_SUBJECT = new PassionSubjectTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<PassionSubjectRecord> getRecordType() {
        return PassionSubjectRecord.class;
    }

    /**
     * The column <code>public.passion_subject.id</code>.
     */
    public final TableField<PassionSubjectRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.passion_subject.name_i18n_id</code>.
     */
    public final TableField<PassionSubjectRecord, UUID> NAME_I18N_ID = createField(DSL.name("name_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.passion_subject.desc_i18n_id</code>.
     */
    public final TableField<PassionSubjectRecord, UUID> DESC_I18N_ID = createField(DSL.name("desc_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.passion_subject.major_id</code>.
     */
    public final TableField<PassionSubjectRecord, UUID> MAJOR_ID = createField(DSL.name("major_id"), SQLDataType.UUID, this, "");

    private PassionSubjectTable(Name alias, Table<PassionSubjectRecord> aliased) {
        this(alias, aliased, null);
    }

    private PassionSubjectTable(Name alias, Table<PassionSubjectRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.passion_subject</code> table reference
     */
    public PassionSubjectTable(String alias) {
        this(DSL.name(alias), PASSION_SUBJECT);
    }

    /**
     * Create an aliased <code>public.passion_subject</code> table reference
     */
    public PassionSubjectTable(Name alias) {
        this(alias, PASSION_SUBJECT);
    }

    /**
     * Create a <code>public.passion_subject</code> table reference
     */
    public PassionSubjectTable() {
        this(DSL.name("passion_subject"), null);
    }

    public <O extends Record> PassionSubjectTable(Table<O> child, ForeignKey<O, PassionSubjectRecord> key) {
        super(child, key, PASSION_SUBJECT);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<PassionSubjectRecord> getPrimaryKey() {
        return Keys.PASSION_SUBJECT_PKEY;
    }

    @Override
    @Nonnull
    public PassionSubjectTable as(String alias) {
        return new PassionSubjectTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public PassionSubjectTable as(Name alias) {
        return new PassionSubjectTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PassionSubjectTable rename(String name) {
        return new PassionSubjectTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PassionSubjectTable rename(Name name) {
        return new PassionSubjectTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row4<UUID, UUID, UUID, UUID> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
