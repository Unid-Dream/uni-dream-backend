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
import org.jooq.Row15;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.records._AuditLogStudentSessionSurveyAnswerRecord;


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
public class _AuditLogStudentSessionSurveyAnswerTable extends TableImpl<_AuditLogStudentSessionSurveyAnswerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public._audit_log_student_session_survey_answer</code>
     */
    public static final _AuditLogStudentSessionSurveyAnswerTable _AUDIT_LOG_STUDENT_SESSION_SURVEY_ANSWER = new _AuditLogStudentSessionSurveyAnswerTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogStudentSessionSurveyAnswerRecord> getRecordType() {
        return _AuditLogStudentSessionSurveyAnswerRecord.class;
    }

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.audit_seq</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.audit_createdon</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.audit_createdby</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.audit_operation</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.audit_type</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.id</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.student_session_survey_question_id</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, UUID> STUDENT_SESSION_SURVEY_QUESTION_ID = createField(DSL.name("student_session_survey_question_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.order</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, Integer> ORDER = createField(DSL.name("order"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.description_i18n_id</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, UUID> DESCRIPTION_I18N_ID = createField(DSL.name("description_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.score</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, BigDecimal> SCORE = createField(DSL.name("score"), SQLDataType.NUMERIC, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.require_user_input</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, Boolean> REQUIRE_USER_INPUT = createField(DSL.name("require_user_input"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.created_on</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.created_by</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.updated_on</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_session_survey_answer.updated_by</code>.
     */
    public final TableField<_AuditLogStudentSessionSurveyAnswerRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private _AuditLogStudentSessionSurveyAnswerTable(Name alias, Table<_AuditLogStudentSessionSurveyAnswerRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogStudentSessionSurveyAnswerTable(Name alias, Table<_AuditLogStudentSessionSurveyAnswerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased
     * <code>public._audit_log_student_session_survey_answer</code> table
     * reference
     */
    public _AuditLogStudentSessionSurveyAnswerTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_STUDENT_SESSION_SURVEY_ANSWER);
    }

    /**
     * Create an aliased
     * <code>public._audit_log_student_session_survey_answer</code> table
     * reference
     */
    public _AuditLogStudentSessionSurveyAnswerTable(Name alias) {
        this(alias, _AUDIT_LOG_STUDENT_SESSION_SURVEY_ANSWER);
    }

    /**
     * Create a <code>public._audit_log_student_session_survey_answer</code>
     * table reference
     */
    public _AuditLogStudentSessionSurveyAnswerTable() {
        this(DSL.name("_audit_log_student_session_survey_answer"), null);
    }

    public <O extends Record> _AuditLogStudentSessionSurveyAnswerTable(Table<O> child, ForeignKey<O, _AuditLogStudentSessionSurveyAnswerRecord> key) {
        super(child, key, _AUDIT_LOG_STUDENT_SESSION_SURVEY_ANSWER);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogStudentSessionSurveyAnswerRecord, Long> getIdentity() {
        return (Identity<_AuditLogStudentSessionSurveyAnswerRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyAnswerTable as(String alias) {
        return new _AuditLogStudentSessionSurveyAnswerTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyAnswerTable as(Name alias) {
        return new _AuditLogStudentSessionSurveyAnswerTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyAnswerTable rename(String name) {
        return new _AuditLogStudentSessionSurveyAnswerTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentSessionSurveyAnswerTable rename(Name name) {
        return new _AuditLogStudentSessionSurveyAnswerTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row15<Long, OffsetDateTime, String, String, String, UUID, UUID, Integer, UUID, BigDecimal, Boolean, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }
}
