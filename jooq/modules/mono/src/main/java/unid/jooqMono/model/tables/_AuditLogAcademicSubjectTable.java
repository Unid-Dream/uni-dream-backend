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
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row15;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.records._AuditLogAcademicSubjectRecord;


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
public class _AuditLogAcademicSubjectTable extends TableImpl<_AuditLogAcademicSubjectRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public._audit_log_academic_subject</code>
     */
    public static final _AuditLogAcademicSubjectTable _AUDIT_LOG_ACADEMIC_SUBJECT = new _AuditLogAcademicSubjectTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogAcademicSubjectRecord> getRecordType() {
        return _AuditLogAcademicSubjectRecord.class;
    }

    /**
     * The column <code>public._audit_log_academic_subject.audit_seq</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public._audit_log_academic_subject.audit_createdon</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column
     * <code>public._audit_log_academic_subject.audit_createdby</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_academic_subject.audit_operation</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_academic_subject.audit_type</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_academic_subject.id</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_academic_subject.title_i18n_id</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, UUID> TITLE_I18N_ID = createField(DSL.name("title_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_academic_subject.description_i18n_id</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, UUID> DESCRIPTION_I18N_ID = createField(DSL.name("description_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_academic_subject.description_master_degree_i18n_id</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, UUID> DESCRIPTION_MASTER_DEGREE_I18N_ID = createField(DSL.name("description_master_degree_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_academic_subject.description_phd_i18n_id</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, UUID> DESCRIPTION_PHD_I18N_ID = createField(DSL.name("description_phd_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public._audit_log_academic_subject.tag_id</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, UUID> TAG_ID = createField(DSL.name("tag_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public._audit_log_academic_subject.created_on</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public._audit_log_academic_subject.created_by</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_academic_subject.updated_on</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public._audit_log_academic_subject.updated_by</code>.
     */
    public final TableField<_AuditLogAcademicSubjectRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private _AuditLogAcademicSubjectTable(Name alias, Table<_AuditLogAcademicSubjectRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogAcademicSubjectTable(Name alias, Table<_AuditLogAcademicSubjectRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public._audit_log_academic_subject</code> table
     * reference
     */
    public _AuditLogAcademicSubjectTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_ACADEMIC_SUBJECT);
    }

    /**
     * Create an aliased <code>public._audit_log_academic_subject</code> table
     * reference
     */
    public _AuditLogAcademicSubjectTable(Name alias) {
        this(alias, _AUDIT_LOG_ACADEMIC_SUBJECT);
    }

    /**
     * Create a <code>public._audit_log_academic_subject</code> table reference
     */
    public _AuditLogAcademicSubjectTable() {
        this(DSL.name("_audit_log_academic_subject"), null);
    }

    public <O extends Record> _AuditLogAcademicSubjectTable(Table<O> child, ForeignKey<O, _AuditLogAcademicSubjectRecord> key) {
        super(child, key, _AUDIT_LOG_ACADEMIC_SUBJECT);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogAcademicSubjectRecord, Long> getIdentity() {
        return (Identity<_AuditLogAcademicSubjectRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogAcademicSubjectTable as(String alias) {
        return new _AuditLogAcademicSubjectTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogAcademicSubjectTable as(Name alias) {
        return new _AuditLogAcademicSubjectTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogAcademicSubjectTable rename(String name) {
        return new _AuditLogAcademicSubjectTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogAcademicSubjectTable rename(Name name) {
        return new _AuditLogAcademicSubjectTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row15<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }
}
