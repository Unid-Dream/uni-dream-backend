/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables;


import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
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
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.SessionOpTypeEnum;
import unid.jooqMono.model.tables.records.SessionOpLogRecord;


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
public class SessionOpLogTable extends TableImpl<SessionOpLogRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.session_op_log</code>
     */
    public static final SessionOpLogTable SESSION_OP_LOG = new SessionOpLogTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<SessionOpLogRecord> getRecordType() {
        return SessionOpLogRecord.class;
    }

    /**
     * The column <code>public.session_op_log.id</code>.
     */
    public final TableField<SessionOpLogRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.session_op_log.time_utc</code>.
     */
    public final TableField<SessionOpLogRecord, OffsetDateTime> TIME_UTC = createField(DSL.name("time_utc"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.session_op_log.user_id</code>.
     */
    public final TableField<SessionOpLogRecord, UUID> USER_ID = createField(DSL.name("user_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.session_op_log.status</code>.
     */
    public final TableField<SessionOpLogRecord, BookingStatusEnum> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.asEnumDataType(unid.jooqMono.model.enums.BookingStatusEnum.class), this, "");

    /**
     * The column <code>public.session_op_log.op_type</code>.
     */
    public final TableField<SessionOpLogRecord, SessionOpTypeEnum> OP_TYPE = createField(DSL.name("op_type"), SQLDataType.VARCHAR.asEnumDataType(unid.jooqMono.model.enums.SessionOpTypeEnum.class), this, "");

    /**
     * The column <code>public.session_op_log.transaction_id</code>.
     */
    public final TableField<SessionOpLogRecord, UUID> TRANSACTION_ID = createField(DSL.name("transaction_id"), SQLDataType.UUID, this, "");

    private SessionOpLogTable(Name alias, Table<SessionOpLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private SessionOpLogTable(Name alias, Table<SessionOpLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.session_op_log</code> table reference
     */
    public SessionOpLogTable(String alias) {
        this(DSL.name(alias), SESSION_OP_LOG);
    }

    /**
     * Create an aliased <code>public.session_op_log</code> table reference
     */
    public SessionOpLogTable(Name alias) {
        this(alias, SESSION_OP_LOG);
    }

    /**
     * Create a <code>public.session_op_log</code> table reference
     */
    public SessionOpLogTable() {
        this(DSL.name("session_op_log"), null);
    }

    public <O extends Record> SessionOpLogTable(Table<O> child, ForeignKey<O, SessionOpLogRecord> key) {
        super(child, key, SESSION_OP_LOG);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<SessionOpLogRecord> getPrimaryKey() {
        return Keys.CALENDAR_OP_LOG_PKEY;
    }

    @Override
    @Nonnull
    public SessionOpLogTable as(String alias) {
        return new SessionOpLogTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public SessionOpLogTable as(Name alias) {
        return new SessionOpLogTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public SessionOpLogTable rename(String name) {
        return new SessionOpLogTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public SessionOpLogTable rename(Name name) {
        return new SessionOpLogTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row6<UUID, OffsetDateTime, UUID, BookingStatusEnum, SessionOpTypeEnum, UUID> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}