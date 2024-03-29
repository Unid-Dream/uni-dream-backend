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
import org.jooq.Row13;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.records._AuditLogEcaCourseAcademicMapRecord;


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
public class _AuditLogEcaCourseAcademicMapTable extends TableImpl<_AuditLogEcaCourseAcademicMapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public._audit_log_eca_course_academic_map</code>
     */
    public static final _AuditLogEcaCourseAcademicMapTable _AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP = new _AuditLogEcaCourseAcademicMapTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogEcaCourseAcademicMapRecord> getRecordType() {
        return _AuditLogEcaCourseAcademicMapRecord.class;
    }

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.audit_seq</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.audit_createdon</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.audit_createdby</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.audit_operation</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.audit_type</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_eca_course_academic_map.id</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.eca_course_id</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, UUID> ECA_COURSE_ID = createField(DSL.name("eca_course_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.academic_major_id</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, UUID> ACADEMIC_MAJOR_ID = createField(DSL.name("academic_major_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.academic_subject_id</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, UUID> ACADEMIC_SUBJECT_ID = createField(DSL.name("academic_subject_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.created_on</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.created_by</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.updated_on</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_eca_course_academic_map.updated_by</code>.
     */
    public final TableField<_AuditLogEcaCourseAcademicMapRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private _AuditLogEcaCourseAcademicMapTable(Name alias, Table<_AuditLogEcaCourseAcademicMapRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogEcaCourseAcademicMapTable(Name alias, Table<_AuditLogEcaCourseAcademicMapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public._audit_log_eca_course_academic_map</code>
     * table reference
     */
    public _AuditLogEcaCourseAcademicMapTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP);
    }

    /**
     * Create an aliased <code>public._audit_log_eca_course_academic_map</code>
     * table reference
     */
    public _AuditLogEcaCourseAcademicMapTable(Name alias) {
        this(alias, _AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP);
    }

    /**
     * Create a <code>public._audit_log_eca_course_academic_map</code> table
     * reference
     */
    public _AuditLogEcaCourseAcademicMapTable() {
        this(DSL.name("_audit_log_eca_course_academic_map"), null);
    }

    public <O extends Record> _AuditLogEcaCourseAcademicMapTable(Table<O> child, ForeignKey<O, _AuditLogEcaCourseAcademicMapRecord> key) {
        super(child, key, _AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogEcaCourseAcademicMapRecord, Long> getIdentity() {
        return (Identity<_AuditLogEcaCourseAcademicMapRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapTable as(String alias) {
        return new _AuditLogEcaCourseAcademicMapTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapTable as(Name alias) {
        return new _AuditLogEcaCourseAcademicMapTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapTable rename(String name) {
        return new _AuditLogEcaCourseAcademicMapTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogEcaCourseAcademicMapTable rename(Name name) {
        return new _AuditLogEcaCourseAcademicMapTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row13<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row13) super.fieldsRow();
    }
}
