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
import unid.jooqMono.model.tables.records.PassionSubjectPodcastRecord;


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
public class PassionSubjectPodcastTable extends TableImpl<PassionSubjectPodcastRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.passion_subject_podcast</code>
     */
    public static final PassionSubjectPodcastTable PASSION_SUBJECT_PODCAST = new PassionSubjectPodcastTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<PassionSubjectPodcastRecord> getRecordType() {
        return PassionSubjectPodcastRecord.class;
    }

    /**
     * The column <code>public.passion_subject_podcast.id</code>.
     */
    public final TableField<PassionSubjectPodcastRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.passion_subject_podcast.name_i18n_id</code>.
     */
    public final TableField<PassionSubjectPodcastRecord, UUID> NAME_I18N_ID = createField(DSL.name("name_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.passion_subject_podcast.author_i18n_id</code>.
     */
    public final TableField<PassionSubjectPodcastRecord, UUID> AUTHOR_I18N_ID = createField(DSL.name("author_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.passion_subject_podcast.url</code>.
     */
    public final TableField<PassionSubjectPodcastRecord, String> URL = createField(DSL.name("url"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.passion_subject_podcast.subject_id</code>.
     */
    public final TableField<PassionSubjectPodcastRecord, UUID> SUBJECT_ID = createField(DSL.name("subject_id"), SQLDataType.UUID, this, "");

    private PassionSubjectPodcastTable(Name alias, Table<PassionSubjectPodcastRecord> aliased) {
        this(alias, aliased, null);
    }

    private PassionSubjectPodcastTable(Name alias, Table<PassionSubjectPodcastRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.passion_subject_podcast</code> table
     * reference
     */
    public PassionSubjectPodcastTable(String alias) {
        this(DSL.name(alias), PASSION_SUBJECT_PODCAST);
    }

    /**
     * Create an aliased <code>public.passion_subject_podcast</code> table
     * reference
     */
    public PassionSubjectPodcastTable(Name alias) {
        this(alias, PASSION_SUBJECT_PODCAST);
    }

    /**
     * Create a <code>public.passion_subject_podcast</code> table reference
     */
    public PassionSubjectPodcastTable() {
        this(DSL.name("passion_subject_podcast"), null);
    }

    public <O extends Record> PassionSubjectPodcastTable(Table<O> child, ForeignKey<O, PassionSubjectPodcastRecord> key) {
        super(child, key, PASSION_SUBJECT_PODCAST);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<PassionSubjectPodcastRecord> getPrimaryKey() {
        return Keys.PASSION_SUBJECT_PODCAST_PKEY;
    }

    @Override
    @Nonnull
    public PassionSubjectPodcastTable as(String alias) {
        return new PassionSubjectPodcastTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public PassionSubjectPodcastTable as(Name alias) {
        return new PassionSubjectPodcastTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PassionSubjectPodcastTable rename(String name) {
        return new PassionSubjectPodcastTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public PassionSubjectPodcastTable rename(Name name) {
        return new PassionSubjectPodcastTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row5<UUID, UUID, UUID, String, UUID> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
