/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row14;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.tables.records._AuditLogWritingTopicRecord;


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
public class _AuditLogWritingTopicTable extends TableImpl<_AuditLogWritingTopicRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public._audit_log_writing_topic</code>
     */
    public static final _AuditLogWritingTopicTable _AUDIT_LOG_WRITING_TOPIC = new _AuditLogWritingTopicTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogWritingTopicRecord> getRecordType() {
        return _AuditLogWritingTopicRecord.class;
    }

    /**
     * The column <code>public._audit_log_writing_topic.audit_seq</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.audit_createdon</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.audit_createdby</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.audit_operation</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.audit_type</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.id</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_writing_topic.description_i18n_id</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, UUID> DESCRIPTION_I18N_ID = createField(DSL.name("description_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public._audit_log_writing_topic.price</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, BigDecimal> PRICE = createField(DSL.name("price"), SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>public._audit_log_writing_topic.currency</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, CurrencyEnum> CURRENCY = createField(DSL.name("currency"), SQLDataType.VARCHAR.asEnumDataType(unid.jooqMono.model.enums.CurrencyEnum.class), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.created_on</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.created_by</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_writing_topic.updated_on</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public._audit_log_writing_topic.updated_by</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_writing_topic.topic_i18n_id</code>.
     */
    public final TableField<_AuditLogWritingTopicRecord, UUID[]> TOPIC_I18N_ID = createField(DSL.name("topic_i18n_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    private _AuditLogWritingTopicTable(Name alias, Table<_AuditLogWritingTopicRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogWritingTopicTable(Name alias, Table<_AuditLogWritingTopicRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public._audit_log_writing_topic</code> table
     * reference
     */
    public _AuditLogWritingTopicTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_WRITING_TOPIC);
    }

    /**
     * Create an aliased <code>public._audit_log_writing_topic</code> table
     * reference
     */
    public _AuditLogWritingTopicTable(Name alias) {
        this(alias, _AUDIT_LOG_WRITING_TOPIC);
    }

    /**
     * Create a <code>public._audit_log_writing_topic</code> table reference
     */
    public _AuditLogWritingTopicTable() {
        this(DSL.name("_audit_log_writing_topic"), null);
    }

    public <O extends Record> _AuditLogWritingTopicTable(Table<O> child, ForeignKey<O, _AuditLogWritingTopicRecord> key) {
        super(child, key, _AUDIT_LOG_WRITING_TOPIC);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogWritingTopicRecord, Long> getIdentity() {
        return (Identity<_AuditLogWritingTopicRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogWritingTopicTable as(String alias) {
        return new _AuditLogWritingTopicTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogWritingTopicTable as(Name alias) {
        return new _AuditLogWritingTopicTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogWritingTopicTable rename(String name) {
        return new _AuditLogWritingTopicTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogWritingTopicTable rename(Name name) {
        return new _AuditLogWritingTopicTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row14<Long, OffsetDateTime, String, String, String, UUID, UUID, BigDecimal, CurrencyEnum, OffsetDateTime, String, OffsetDateTime, String, UUID[]> fieldsRow() {
        return (Row14) super.fieldsRow();
    }
}
