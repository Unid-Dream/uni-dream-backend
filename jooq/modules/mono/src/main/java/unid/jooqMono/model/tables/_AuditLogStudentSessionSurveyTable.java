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
import unid.jooqMono.model.tables.records._AuditLogStudentSessionSurveyRecord;


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
public class _AuditLogStudentSessionSurveyTable extends TableImpl<_AuditLogStudentSessionSurveyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public._audit_log_student_session_survey</code>
     */
    public static final _AuditLogStudentSessionSurveyTable _AUDIT_LOG_STUDENT_SESSION_SURVEY = new _AuditLogStudentSessionSurveyTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogStudentSessionSurveyRecord> getRecordType() {
        return _AuditLogStudentSessionSurveyRecord.class;
    }

    /**
     * The column
     * <code>public._audit_log_student_session_survey.audit_seq</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.audit_createdon</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.audit_createdby</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.audit_operation</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.audit_type</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_student_session_survey.id</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_student_session_survey.version</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, OffsetDateTime> VERSION = createField(DSL.name("version"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.title_i18n_id</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, UUID> TITLE_I18N_ID = createField(DSL.name("title_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.sub_title_i18n_id</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, UUID> SUB_TITLE_I18N_ID = createField(DSL.name("sub_title_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.description_i18n_id</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, UUID> DESCRIPTION_I18N_ID = createField(DSL.name("description_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.mandatory</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, Boolean> MANDATORY = createField(DSL.name("mandatory"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.created_on</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.created_by</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.updated_on</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey.updated_by</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private _AuditLogStudentSessionSurveyTable(Name alias, Table<_AuditLogStudentSessionSurveyRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogStudentSessionSurveyTable(Name alias, Table<_AuditLogStudentSessionSurveyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public._audit_log_student_session_survey</code>
     * table reference
     */
    public _AuditLogStudentSessionSurveyTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_STUDENT_SESSION_SURVEY);
    }

    /**
     * Create an aliased <code>public._audit_log_student_session_survey</code>
     * table reference
     */
    public _AuditLogStudentSessionSurveyTable(Name alias) {
        this(alias, _AUDIT_LOG_STUDENT_SESSION_SURVEY);
    }

    /**
     * Create a <code>public._audit_log_student_session_survey</code> table
     * reference
     */
    public _AuditLogStudentSessionSurveyTable() {
        this(DSL.name("_audit_log_student_session_survey"), null);
    }

    public <O extends Record> _AuditLogStudentSessionSurveyTable(Table<O> child, ForeignKey<O, _AuditLogStudentSessionSurveyRecord> key) {
        super(child, key, _AUDIT_LOG_STUDENT_SESSION_SURVEY);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogStudentSessionSurveyRecord, Long> getIdentity() {
        return (Identity<_AuditLogStudentSessionSurveyRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyTable as(String alias) {
        return new _AuditLogStudentSessionSurveyTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyTable as(Name alias) {
        return new _AuditLogStudentSessionSurveyTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyTable rename(String name) {
        return new _AuditLogStudentSessionSurveyTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyTable rename(Name name) {
        return new _AuditLogStudentSessionSurveyTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row15<Long, OffsetDateTime, String, String, String, UUID, OffsetDateTime, UUID, UUID, UUID, Boolean, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }
}
