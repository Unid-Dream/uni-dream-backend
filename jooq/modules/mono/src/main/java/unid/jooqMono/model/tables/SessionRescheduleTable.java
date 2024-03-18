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
import org.jooq.Row13;
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
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables.records.SessionRescheduleRecord;


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
public class SessionRescheduleTable extends TableImpl<SessionRescheduleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.session_reschedule</code>
     */
    public static final SessionRescheduleTable SESSION_RESCHEDULE = new SessionRescheduleTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<SessionRescheduleRecord> getRecordType() {
        return SessionRescheduleRecord.class;
    }

    /**
     * The column <code>public.session_reschedule.id</code>.
     */
    public final TableField<SessionRescheduleRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.session_reschedule.educator_calendar_id</code>.
     */
    public final TableField<SessionRescheduleRecord, UUID> EDUCATOR_CALENDAR_ID = createField(DSL.name("educator_calendar_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.session_reschedule.start_time_utc</code>.
     */
    public final TableField<SessionRescheduleRecord, OffsetDateTime> START_TIME_UTC = createField(DSL.name("start_time_utc"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.session_reschedule.end_time_utc</code>.
     */
    public final TableField<SessionRescheduleRecord, OffsetDateTime> END_TIME_UTC = createField(DSL.name("end_time_utc"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.session_reschedule.user_role</code>.
     */
    public final TableField<SessionRescheduleRecord, UserRoleEnum> USER_ROLE = createField(DSL.name("user_role"), SQLDataType.VARCHAR.asEnumDataType(unid.jooqMono.model.enums.UserRoleEnum.class), this, "");

    /**
     * The column <code>public.session_reschedule.reason</code>.
     */
    public final TableField<SessionRescheduleRecord, String> REASON = createField(DSL.name("reason"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.session_reschedule.accpet</code>.
     */
    public final TableField<SessionRescheduleRecord, Boolean> ACCPET = createField(DSL.name("accpet"), SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.session_reschedule.admin_user_id</code>.
     */
    public final TableField<SessionRescheduleRecord, UUID> ADMIN_USER_ID = createField(DSL.name("admin_user_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.session_reschedule.created_on</code>.
     */
    public final TableField<SessionRescheduleRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.session_reschedule.created_by</code>.
     */
    public final TableField<SessionRescheduleRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.session_reschedule.updated_on</code>.
     */
    public final TableField<SessionRescheduleRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.session_reschedule.updated_by</code>.
     */
    public final TableField<SessionRescheduleRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.session_reschedule.user_id</code>.
     */
    public final TableField<SessionRescheduleRecord, UUID> USER_ID = createField(DSL.name("user_id"), SQLDataType.UUID, this, "");

    private SessionRescheduleTable(Name alias, Table<SessionRescheduleRecord> aliased) {
        this(alias, aliased, null);
    }

    private SessionRescheduleTable(Name alias, Table<SessionRescheduleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.session_reschedule</code> table reference
     */
    public SessionRescheduleTable(String alias) {
        this(DSL.name(alias), SESSION_RESCHEDULE);
    }

    /**
     * Create an aliased <code>public.session_reschedule</code> table reference
     */
    public SessionRescheduleTable(Name alias) {
        this(alias, SESSION_RESCHEDULE);
    }

    /**
     * Create a <code>public.session_reschedule</code> table reference
     */
    public SessionRescheduleTable() {
        this(DSL.name("session_reschedule"), null);
    }

    public <O extends Record> SessionRescheduleTable(Table<O> child, ForeignKey<O, SessionRescheduleRecord> key) {
        super(child, key, SESSION_RESCHEDULE);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<SessionRescheduleRecord> getPrimaryKey() {
        return Keys.SESSION_RESCHEDULE_PKEY;
    }

    @Override
    @Nonnull
    public SessionRescheduleTable as(String alias) {
        return new SessionRescheduleTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public SessionRescheduleTable as(Name alias) {
        return new SessionRescheduleTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public SessionRescheduleTable rename(String name) {
        return new SessionRescheduleTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public SessionRescheduleTable rename(Name name) {
        return new SessionRescheduleTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row13<UUID, UUID, OffsetDateTime, OffsetDateTime, UserRoleEnum, String, Boolean, UUID, OffsetDateTime, String, OffsetDateTime, String, UUID> fieldsRow() {
        return (Row13) super.fieldsRow();
    }
}