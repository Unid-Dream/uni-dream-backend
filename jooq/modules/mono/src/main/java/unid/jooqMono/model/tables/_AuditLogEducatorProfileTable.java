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
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.jooqMono.model.enums.GenderEnum;
import unid.jooqMono.model.tables.records._AuditLogEducatorProfileRecord;


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
public class _AuditLogEducatorProfileTable extends TableImpl<_AuditLogEducatorProfileRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public._audit_log_educator_profile</code>
     */
    public static final _AuditLogEducatorProfileTable _AUDIT_LOG_EDUCATOR_PROFILE = new _AuditLogEducatorProfileTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<_AuditLogEducatorProfileRecord> getRecordType() {
        return _AuditLogEducatorProfileRecord.class;
    }

    /**
     * The column <code>public._audit_log_educator_profile.audit_seq</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, Long> AUDIT_SEQ = createField(DSL.name("audit_seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.audit_createdon</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, OffsetDateTime> AUDIT_CREATEDON = createField(DSL.name("audit_createdon"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.audit_createdby</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> AUDIT_CREATEDBY = createField(DSL.name("audit_createdby"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.audit_operation</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> AUDIT_OPERATION = createField(DSL.name("audit_operation"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.audit_type</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> AUDIT_TYPE = createField(DSL.name("audit_type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.user_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID> USER_ID = createField(DSL.name("user_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.application_approval</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, ApplicationApprovalEnum> APPLICATION_APPROVAL = createField(DSL.name("application_approval"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(unid.jooqMono.model.enums.ApplicationApprovalEnum.class), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.microsoft_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> MICROSOFT_ID = createField(DSL.name("microsoft_id"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.microsoft_email</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> MICROSOFT_EMAIL = createField(DSL.name("microsoft_email"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.country_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID> COUNTRY_ID = createField(DSL.name("country_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.profile_picture</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> PROFILE_PICTURE = createField(DSL.name("profile_picture"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.phone_country_code</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> PHONE_COUNTRY_CODE = createField(DSL.name("phone_country_code"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.phone</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.hourly_rate</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, Integer> HOURLY_RATE = createField(DSL.name("hourly_rate"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.university_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID> UNIVERSITY_ID = createField(DSL.name("university_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.university_education_level_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID> UNIVERSITY_EDUCATION_LEVEL_ID = createField(DSL.name("university_education_level_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.university_identity_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID> UNIVERSITY_IDENTITY_ID = createField(DSL.name("university_identity_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.created_on</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.created_by</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.updated_on</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.updated_by</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.expertise_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID[]> EXPERTISE_ID = createField(DSL.name("expertise_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.description</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public._audit_log_educator_profile.language_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID[]> LANGUAGE_ID = createField(DSL.name("language_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.city_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID> CITY_ID = createField(DSL.name("city_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.education_school_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID[]> EDUCATION_SCHOOL_ID = createField(DSL.name("education_school_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.academic_major_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID[]> ACADEMIC_MAJOR_ID = createField(DSL.name("academic_major_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.education_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID[]> EDUCATION_ID = createField(DSL.name("education_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.timezone</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String> TIMEZONE = createField(DSL.name("timezone"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.expertise_description_id</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, UUID[]> EXPERTISE_DESCRIPTION_ID = createField(DSL.name("expertise_description_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column
     * <code>public._audit_log_educator_profile.expertise_description</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, String[]> EXPERTISE_DESCRIPTION = createField(DSL.name("expertise_description"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public._audit_log_educator_profile.gender</code>.
     */
    public final TableField<_AuditLogEducatorProfileRecord, GenderEnum> GENDER = createField(DSL.name("gender"), SQLDataType.VARCHAR.asEnumDataType(unid.jooqMono.model.enums.GenderEnum.class), this, "");

    private _AuditLogEducatorProfileTable(Name alias, Table<_AuditLogEducatorProfileRecord> aliased) {
        this(alias, aliased, null);
    }

    private _AuditLogEducatorProfileTable(Name alias, Table<_AuditLogEducatorProfileRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public._audit_log_educator_profile</code> table
     * reference
     */
    public _AuditLogEducatorProfileTable(String alias) {
        this(DSL.name(alias), _AUDIT_LOG_EDUCATOR_PROFILE);
    }

    /**
     * Create an aliased <code>public._audit_log_educator_profile</code> table
     * reference
     */
    public _AuditLogEducatorProfileTable(Name alias) {
        this(alias, _AUDIT_LOG_EDUCATOR_PROFILE);
    }

    /**
     * Create a <code>public._audit_log_educator_profile</code> table reference
     */
    public _AuditLogEducatorProfileTable() {
        this(DSL.name("_audit_log_educator_profile"), null);
    }

    public <O extends Record> _AuditLogEducatorProfileTable(Table<O> child, ForeignKey<O, _AuditLogEducatorProfileRecord> key) {
        super(child, key, _AUDIT_LOG_EDUCATOR_PROFILE);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public Identity<_AuditLogEducatorProfileRecord, Long> getIdentity() {
        return (Identity<_AuditLogEducatorProfileRecord, Long>) super.getIdentity();
    }

    @Override
    @Nonnull
    public _AuditLogEducatorProfileTable as(String alias) {
        return new _AuditLogEducatorProfileTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public _AuditLogEducatorProfileTable as(Name alias) {
        return new _AuditLogEducatorProfileTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogEducatorProfileTable rename(String name) {
        return new _AuditLogEducatorProfileTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public _AuditLogEducatorProfileTable rename(Name name) {
        return new _AuditLogEducatorProfileTable(name, null);
    }
}
