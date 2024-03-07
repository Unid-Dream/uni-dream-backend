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
import org.jooq.Row16;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.QuestionnaireAnswerTypeEnum;
import unid.jooqMono.model.tables.records._AuditLogStudentQuestionnaireAnswerRecord;


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
public class _AuditLogStudentQuestionnaireAnswerTable extends TableImpl<_AuditLogStudentQuestionnaireAnswerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public._audit_log_student_questionnaire_answer</code>
     */
    public static final _AuditLogStudentQuestionnaireAnswerTable _AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER = new _AuditLogStudentQuestionnaireAnswerTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogStudentQuestionnaireAnswerRecord> getRecordType() {
        return _AuditLogStudentQuestionnaireAnswerRecord.class;
    }

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.audit_seq</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.audit_createdon</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.audit_createdby</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.audit_operation</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.audit_type</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.id</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.student_questionnaire_question_id</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, UUID> STUDENT_QUESTIONNAIRE_QUESTION_ID = createField(DSL.name("student_questionnaire_question_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.order</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, Integer> ORDER = createField(DSL.name("order"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.description_i18n_id</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, UUID> DESCRIPTION_I18N_ID = createField(DSL.name("description_i18n_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.tag_id</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, UUID> TAG_ID = createField(DSL.name("tag_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.score</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, BigDecimal> SCORE = createField(DSL.name("score"), SQLDataType.NUMERIC, this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.type</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, QuestionnaireAnswerTypeEnum> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(unid.jooqMono.model.enums.QuestionnaireAnswerTypeEnum.class), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.created_on</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.created_by</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.updated_on</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_questionnaire_answer.updated_by</code>.
     */
    public final TableField<_AuditLogStudentQuestionnaireAnswerRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private _AuditLogStudentQuestionnaireAnswerTable(Name alias, Table<_AuditLogStudentQuestionnaireAnswerRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogStudentQuestionnaireAnswerTable(Name alias, Table<_AuditLogStudentQuestionnaireAnswerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased
     * <code>public._audit_log_student_questionnaire_answer</code> table
     * reference
     */
    public _AuditLogStudentQuestionnaireAnswerTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER);
    }

    /**
     * Create an aliased
     * <code>public._audit_log_student_questionnaire_answer</code> table
     * reference
     */
    public _AuditLogStudentQuestionnaireAnswerTable(Name alias) {
        this(alias, _AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER);
    }

    /**
     * Create a <code>public._audit_log_student_questionnaire_answer</code>
     * table reference
     */
    public _AuditLogStudentQuestionnaireAnswerTable() {
        this(DSL.name("_audit_log_student_questionnaire_answer"), null);
    }

    public <O extends Record> _AuditLogStudentQuestionnaireAnswerTable(Table<O> child, ForeignKey<O, _AuditLogStudentQuestionnaireAnswerRecord> key) {
        super(child, key, _AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogStudentQuestionnaireAnswerRecord, Long> getIdentity() {
        return (Identity<_AuditLogStudentQuestionnaireAnswerRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerTable as(String alias) {
        return new _AuditLogStudentQuestionnaireAnswerTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerTable as(Name alias) {
        return new _AuditLogStudentQuestionnaireAnswerTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerTable rename(String name) {
        return new _AuditLogStudentQuestionnaireAnswerTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentQuestionnaireAnswerTable rename(Name name) {
        return new _AuditLogStudentQuestionnaireAnswerTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row16 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row16<Long, OffsetDateTime, String, String, String, UUID, UUID, Integer, UUID, UUID, BigDecimal, QuestionnaireAnswerTypeEnum, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row16) super.fieldsRow();
    }
}
