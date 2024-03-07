/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables;


import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
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
import unid.jooqMono.model.tables.records.CourseEventRecord;


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
public class CourseEventTable extends TableImpl<CourseEventRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.course_event</code>
     */
    public static final CourseEventTable COURSE_EVENT = new CourseEventTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<CourseEventRecord> getRecordType() {
        return CourseEventRecord.class;
    }

    /**
     * The column <code>public.course_event.id</code>.
     */
    public final TableField<CourseEventRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.course_event.event_id</code>.
     */
    public final TableField<CourseEventRecord, UUID> EVENT_ID = createField(DSL.name("event_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.course_event.course_id</code>.
     */
    public final TableField<CourseEventRecord, UUID> COURSE_ID = createField(DSL.name("course_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.course_event.created_on</code>.
     */
    public final TableField<CourseEventRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.course_event.created_by</code>.
     */
    public final TableField<CourseEventRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.course_event.updated_on</code>.
     */
    public final TableField<CourseEventRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.course_event.updated_by</code>.
     */
    public final TableField<CourseEventRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private CourseEventTable(Name alias, Table<CourseEventRecord> aliased) {
        this(alias, aliased, null);
    }

    private CourseEventTable(Name alias, Table<CourseEventRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.course_event</code> table reference
     */
    public CourseEventTable(String alias) {
        this(DSL.name(alias), COURSE_EVENT);
    }

    /**
     * Create an aliased <code>public.course_event</code> table reference
     */
    public CourseEventTable(Name alias) {
        this(alias, COURSE_EVENT);
    }

    /**
     * Create a <code>public.course_event</code> table reference
     */
    public CourseEventTable() {
        this(DSL.name("course_event"), null);
    }

    public <O extends Record> CourseEventTable(Table<O> child, ForeignKey<O, CourseEventRecord> key) {
        super(child, key, COURSE_EVENT);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<CourseEventRecord> getPrimaryKey() {
        return Keys.COURSE_EVENT_PKEY;
    }

    @Override
    @Nonnull
    public List<UniqueKey<CourseEventRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.UQ_COUR_EVET_EVET);
    }

    @Override
    @Nonnull
    public List<ForeignKey<CourseEventRecord, ?>> getReferences() {
        return Arrays.asList(Keys.COURSE_EVENT__FK_COUR_EVET_EVET, Keys.COURSE_EVENT__FK_COUR_EVET_ACAD_MAJ);
    }

    private transient EventTable _event;
    private transient CourseTable _course;

    public EventTable event() {
        if (_event == null)
            _event = new EventTable(this, Keys.COURSE_EVENT__FK_COUR_EVET_EVET);

        return _event;
    }

    public CourseTable course() {
        if (_course == null)
            _course = new CourseTable(this, Keys.COURSE_EVENT__FK_COUR_EVET_ACAD_MAJ);

        return _course;
    }

    @Override
    @Nonnull
    public CourseEventTable as(String alias) {
        return new CourseEventTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public CourseEventTable as(Name alias) {
        return new CourseEventTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public CourseEventTable rename(String name) {
        return new CourseEventTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public CourseEventTable rename(Name name) {
        return new CourseEventTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row7<UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
