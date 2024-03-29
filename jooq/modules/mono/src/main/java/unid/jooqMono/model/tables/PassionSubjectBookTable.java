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
import org.jooq.Row5;
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
import unid.jooqMono.model.tables.records.PassionSubjectBookRecord;


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
public class PassionSubjectBookTable extends TableImpl<PassionSubjectBookRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.passion_subject_book</code>
     */
    public static final PassionSubjectBookTable PASSION_SUBJECT_BOOK = new PassionSubjectBookTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<PassionSubjectBookRecord> getRecordType() {
        return PassionSubjectBookRecord.class;
    }

    /**
     * The column <code>public.passion_subject_book.id</code>.
     */
    public final TableField<PassionSubjectBookRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.passion_subject_book.subject_id</code>.
     */
    public final TableField<PassionSubjectBookRecord, UUID> SUBJECT_ID = createField(DSL.name("subject_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.passion_subject_book.name_i18n_id</code>.
     */
    public final TableField<PassionSubjectBookRecord, UUID> NAME_I18N_ID = createField(DSL.name("name_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.passion_subject_book.author_i18n_id</code>.
     */
    public final TableField<PassionSubjectBookRecord, UUID> AUTHOR_I18N_ID = createField(DSL.name("author_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.passion_subject_book.image</code>.
     */
    public final TableField<PassionSubjectBookRecord, String> IMAGE = createField(DSL.name("image"), SQLDataType.VARCHAR(255), this, "");

    private PassionSubjectBookTable(Name alias, Table<PassionSubjectBookRecord> aliased) {
        this(alias, aliased, null);
    }

    private PassionSubjectBookTable(Name alias, Table<PassionSubjectBookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.passion_subject_book</code> table
     * reference
     */
    public PassionSubjectBookTable(String alias) {
        this(DSL.name(alias), PASSION_SUBJECT_BOOK);
    }

    /**
     * Create an aliased <code>public.passion_subject_book</code> table
     * reference
     */
    public PassionSubjectBookTable(Name alias) {
        this(alias, PASSION_SUBJECT_BOOK);
    }

    /**
     * Create a <code>public.passion_subject_book</code> table reference
     */
    public PassionSubjectBookTable() {
        this(DSL.name("passion_subject_book"), null);
    }

    public <O extends Record> PassionSubjectBookTable(Table<O> child, ForeignKey<O, PassionSubjectBookRecord> key) {
        super(child, key, PASSION_SUBJECT_BOOK);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<PassionSubjectBookRecord> getPrimaryKey() {
        return Keys.PASSION_SUBJECT_BOOK_PKEY;
    }

    @Override
    @Nonnull
    public PassionSubjectBookTable as(String alias) {
        return new PassionSubjectBookTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public PassionSubjectBookTable as(Name alias) {
        return new PassionSubjectBookTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PassionSubjectBookTable rename(String name) {
        return new PassionSubjectBookTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PassionSubjectBookTable rename(Name name) {
        return new PassionSubjectBookTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row5<UUID, UUID, UUID, UUID, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
