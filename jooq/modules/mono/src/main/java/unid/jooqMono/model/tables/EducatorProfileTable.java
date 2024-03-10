/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables;


import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
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
import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.jooqMono.model.tables.records.EducatorProfileRecord;


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
public class EducatorProfileTable extends TableImpl<EducatorProfileRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.educator_profile</code>
     */
    public static final EducatorProfileTable EDUCATOR_PROFILE = new EducatorProfileTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<EducatorProfileRecord> getRecordType() {
        return EducatorProfileRecord.class;
    }

    /**
     * The column <code>public.educator_profile.id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.educator_profile.user_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID> USER_ID = createField(DSL.name("user_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.educator_profile.application_approval</code>.
     */
    public final TableField<EducatorProfileRecord, ApplicationApprovalEnum> APPLICATION_APPROVAL = createField(DSL.name("application_approval"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field("'PENDING'::application_approval", SQLDataType.VARCHAR)).asEnumDataType(unid.jooqMono.model.enums.ApplicationApprovalEnum.class), this, "");

    /**
     * The column <code>public.educator_profile.microsoft_id</code>.
     */
    public final TableField<EducatorProfileRecord, String> MICROSOFT_ID = createField(DSL.name("microsoft_id"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.microsoft_email</code>.
     */
    public final TableField<EducatorProfileRecord, String> MICROSOFT_EMAIL = createField(DSL.name("microsoft_email"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.country_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID> COUNTRY_ID = createField(DSL.name("country_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.educator_profile.profile_picture</code>.
     */
    public final TableField<EducatorProfileRecord, String> PROFILE_PICTURE = createField(DSL.name("profile_picture"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.phone_country_code</code>.
     */
    public final TableField<EducatorProfileRecord, String> PHONE_COUNTRY_CODE = createField(DSL.name("phone_country_code"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.phone</code>.
     */
    public final TableField<EducatorProfileRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.hourly_rate</code>.
     */
    public final TableField<EducatorProfileRecord, Integer> HOURLY_RATE = createField(DSL.name("hourly_rate"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.educator_profile.university_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID> UNIVERSITY_ID = createField(DSL.name("university_id"), SQLDataType.UUID, this, "");

    /**
     * The column
     * <code>public.educator_profile.university_education_level_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID> UNIVERSITY_EDUCATION_LEVEL_ID = createField(DSL.name("university_education_level_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.educator_profile.university_identity_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID> UNIVERSITY_IDENTITY_ID = createField(DSL.name("university_identity_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.educator_profile.created_on</code>.
     */
    public final TableField<EducatorProfileRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.educator_profile.created_by</code>.
     */
    public final TableField<EducatorProfileRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.updated_on</code>.
     */
    public final TableField<EducatorProfileRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.educator_profile.updated_by</code>.
     */
    public final TableField<EducatorProfileRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.expertise_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID[]> EXPERTISE_ID = createField(DSL.name("expertise_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile.description</code>.
     */
    public final TableField<EducatorProfileRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.educator_profile.language_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID[]> LANGUAGE_ID = createField(DSL.name("language_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile.city_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID> CITY_ID = createField(DSL.name("city_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.educator_profile.education_school_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID[]> EDUCATION_SCHOOL_ID = createField(DSL.name("education_school_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile.academic_major_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID[]> ACADEMIC_MAJOR_ID = createField(DSL.name("academic_major_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile.education_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID[]> EDUCATION_ID = createField(DSL.name("education_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile.expertise_description_id</code>.
     */
    public final TableField<EducatorProfileRecord, UUID[]> EXPERTISE_DESCRIPTION_ID = createField(DSL.name("expertise_description_id"), SQLDataType.UUID.getArrayDataType(), this, "");

    /**
     * The column <code>public.educator_profile.timezone</code>.
     */
    public final TableField<EducatorProfileRecord, String> TIMEZONE = createField(DSL.name("timezone"), SQLDataType.VARCHAR(255), this, "");

    private EducatorProfileTable(Name alias, Table<EducatorProfileRecord> aliased) {
        this(alias, aliased, null);
    }

    private EducatorProfileTable(Name alias, Table<EducatorProfileRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.educator_profile</code> table reference
     */
    public EducatorProfileTable(String alias) {
        this(DSL.name(alias), EDUCATOR_PROFILE);
    }

    /**
     * Create an aliased <code>public.educator_profile</code> table reference
     */
    public EducatorProfileTable(Name alias) {
        this(alias, EDUCATOR_PROFILE);
    }

    /**
     * Create a <code>public.educator_profile</code> table reference
     */
    public EducatorProfileTable() {
        this(DSL.name("educator_profile"), null);
    }

    public <O extends Record> EducatorProfileTable(Table<O> child, ForeignKey<O, EducatorProfileRecord> key) {
        super(child, key, EDUCATOR_PROFILE);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<EducatorProfileRecord> getPrimaryKey() {
        return Keys.EDUCATOR_PROFILE_PKEY;
    }

    @Override
    @Nonnull
    public List<UniqueKey<EducatorProfileRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.EDUCATOR_PROFILE_ID_KEY, Keys.EDUCATOR_PROFILE_USER_ID_KEY);
    }

    @Override
    @Nonnull
    public List<ForeignKey<EducatorProfileRecord, ?>> getReferences() {
        return Arrays.asList(Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_USER, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_COUT, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_UITY, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_EDU_LEVL, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_UITY_IDTY);
    }

    private transient UserTable _user;
    private transient CountryTable _country;
    private transient SchoolTable _school;
    private transient EducationLevelTable _educationLevel;
    private transient SchoolIdentityTable _schoolIdentity;

    public UserTable user() {
        if (_user == null)
            _user = new UserTable(this, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_USER);

        return _user;
    }

    public CountryTable country() {
        if (_country == null)
            _country = new CountryTable(this, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_COUT);

        return _country;
    }

    public SchoolTable school() {
        if (_school == null)
            _school = new SchoolTable(this, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_UITY);

        return _school;
    }

    public EducationLevelTable educationLevel() {
        if (_educationLevel == null)
            _educationLevel = new EducationLevelTable(this, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_EDU_LEVL);

        return _educationLevel;
    }

    public SchoolIdentityTable schoolIdentity() {
        if (_schoolIdentity == null)
            _schoolIdentity = new SchoolIdentityTable(this, Keys.EDUCATOR_PROFILE__FK_EDUC_PROF_UITY_IDTY);

        return _schoolIdentity;
    }

    @Override
    @Nonnull
    public EducatorProfileTable as(String alias) {
        return new EducatorProfileTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public EducatorProfileTable as(Name alias) {
        return new EducatorProfileTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public EducatorProfileTable rename(String name) {
        return new EducatorProfileTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public EducatorProfileTable rename(Name name) {
        return new EducatorProfileTable(name, null);
    }
}
