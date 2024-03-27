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
import org.jooq.Row21;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.ReviewTypeEnum;
import unid.jooqMono.model.tables.records._AuditLogStudentUploadedInterviewRecord;


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
public class _AuditLogStudentUploadedInterviewTable extends TableImpl<_AuditLogStudentUploadedInterviewRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public._audit_log_student_uploaded_interview</code>
     */
    public static final _AuditLogStudentUploadedInterviewTable _AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW = new _AuditLogStudentUploadedInterviewTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogStudentUploadedInterviewRecord> getRecordType() {
        return _AuditLogStudentUploadedInterviewRecord.class;
    }

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.audit_seq</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.audit_createdon</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.audit_createdby</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.audit_operation</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.audit_type</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_student_uploaded_interview.id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.student_profile_id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> STUDENT_PROFILE_ID = createField(DSL.name("student_profile_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.interview_topic_id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> INTERVIEW_TOPIC_ID = createField(DSL.name("interview_topic_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.payment_transaction_id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> PAYMENT_TRANSACTION_ID = createField(DSL.name("payment_transaction_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.uploaded_file</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String> UPLOADED_FILE = createField(DSL.name("uploaded_file"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.content_review_id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> CONTENT_REVIEW_ID = createField(DSL.name("content_review_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.clarity_review_id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> CLARITY_REVIEW_ID = createField(DSL.name("clarity_review_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.charisma_review_id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> CHARISMA_REVIEW_ID = createField(DSL.name("charisma_review_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.created_on</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.created_by</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.updated_on</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.updated_by</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.recommended_activity</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String[]> RECOMMENDED_ACTIVITY = createField(DSL.name("recommended_activity"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.recommendation</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, String> RECOMMENDATION = createField(DSL.name("recommendation"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.review_type</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, ReviewTypeEnum> REVIEW_TYPE = createField(DSL.name("review_type"), SQLDataType.VARCHAR.asEnumDataType(unid.jooqMono.model.enums.ReviewTypeEnum.class), this, "");

    /**
     * The column
     * <code>public._audit_log_student_uploaded_interview.educator_profile_id</code>.
     */
    public final TableField<_AuditLogStudentUploadedInterviewRecord, UUID> EDUCATOR_PROFILE_ID = createField(DSL.name("educator_profile_id"), SQLDataType.UUID, this, "");

    private _AuditLogStudentUploadedInterviewTable(Name alias, Table<_AuditLogStudentUploadedInterviewRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogStudentUploadedInterviewTable(Name alias, Table<_AuditLogStudentUploadedInterviewRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased
     * <code>public._audit_log_student_uploaded_interview</code> table reference
     */
    public _AuditLogStudentUploadedInterviewTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW);
    }

    /**
     * Create an aliased
     * <code>public._audit_log_student_uploaded_interview</code> table reference
     */
    public _AuditLogStudentUploadedInterviewTable(Name alias) {
        this(alias, _AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW);
    }

    /**
     * Create a <code>public._audit_log_student_uploaded_interview</code> table
     * reference
     */
    public _AuditLogStudentUploadedInterviewTable() {
        this(DSL.name("_audit_log_student_uploaded_interview"), null);
    }

    public <O extends Record> _AuditLogStudentUploadedInterviewTable(Table<O> child, ForeignKey<O, _AuditLogStudentUploadedInterviewRecord> key) {
        super(child, key, _AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogStudentUploadedInterviewRecord, Long> getIdentity() {
        return (Identity<_AuditLogStudentUploadedInterviewRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewTable as(String alias) {
        return new _AuditLogStudentUploadedInterviewTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewTable as(Name alias) {
        return new _AuditLogStudentUploadedInterviewTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewTable rename(String name) {
        return new _AuditLogStudentUploadedInterviewTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogStudentUploadedInterviewTable rename(Name name) {
        return new _AuditLogStudentUploadedInterviewTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row21 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row21<Long, OffsetDateTime, String, String, String, UUID, UUID, UUID, UUID, String, UUID, UUID, UUID, OffsetDateTime, String, OffsetDateTime, String, String[], String, ReviewTypeEnum, UUID> fieldsRow() {
        return (Row21) super.fieldsRow();
    }
}
