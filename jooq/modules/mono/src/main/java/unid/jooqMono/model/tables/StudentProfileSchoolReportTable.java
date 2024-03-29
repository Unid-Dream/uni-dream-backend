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
import org.jooq.Row9;
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
import unid.jooqMono.model.tables.records.StudentProfileSchoolReportRecord;


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
public class StudentProfileSchoolReportTable extends TableImpl<StudentProfileSchoolReportRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public.student_profile_school_report</code>
     */
    public static final StudentProfileSchoolReportTable STUDENT_PROFILE_SCHOOL_REPORT = new StudentProfileSchoolReportTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<StudentProfileSchoolReportRecord> getRecordType() {
        return StudentProfileSchoolReportRecord.class;
    }

    /**
     * The column <code>public.student_profile_school_report.id</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column
     * <code>public.student_profile_school_report.student_profile_id</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, UUID> STUDENT_PROFILE_ID = createField(DSL.name("student_profile_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public.student_profile_school_report.secondary_school_report</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, String> SECONDARY_SCHOOL_REPORT = createField(DSL.name("secondary_school_report"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public.student_profile_school_report.secondary_school_report_academic_year</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, String> SECONDARY_SCHOOL_REPORT_ACADEMIC_YEAR = createField(DSL.name("secondary_school_report_academic_year"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public.student_profile_school_report.secondary_school_report_semester</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, String> SECONDARY_SCHOOL_REPORT_SEMESTER = createField(DSL.name("secondary_school_report_semester"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.student_profile_school_report.created_on</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.student_profile_school_report.created_by</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.student_profile_school_report.updated_on</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.student_profile_school_report.updated_by</code>.
     */
    public final TableField<StudentProfileSchoolReportRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private StudentProfileSchoolReportTable(Name alias, Table<StudentProfileSchoolReportRecord> aliased) {
        this(alias, aliased, null);
    }

    private StudentProfileSchoolReportTable(Name alias, Table<StudentProfileSchoolReportRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.student_profile_school_report</code> table
     * reference
     */
    public StudentProfileSchoolReportTable(String alias) {
        this(DSL.name(alias), STUDENT_PROFILE_SCHOOL_REPORT);
    }

    /**
     * Create an aliased <code>public.student_profile_school_report</code> table
     * reference
     */
    public StudentProfileSchoolReportTable(Name alias) {
        this(alias, STUDENT_PROFILE_SCHOOL_REPORT);
    }

    /**
     * Create a <code>public.student_profile_school_report</code> table
     * reference
     */
    public StudentProfileSchoolReportTable() {
        this(DSL.name("student_profile_school_report"), null);
    }

    public <O extends Record> StudentProfileSchoolReportTable(Table<O> child, ForeignKey<O, StudentProfileSchoolReportRecord> key) {
        super(child, key, STUDENT_PROFILE_SCHOOL_REPORT);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<StudentProfileSchoolReportRecord> getPrimaryKey() {
        return Keys.STUDENT_PROFILE_SCHOOL_REPORT_PKEY;
    }

    @Override
    @Nonnull
    public List<ForeignKey<StudentProfileSchoolReportRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STUDENT_PROFILE_SCHOOL_REPORT__FK_STUD_PROF_SCHL_REPT_PROF);
    }

    private transient StudentProfileTable _studentProfile;

    public StudentProfileTable studentProfile() {
        if (_studentProfile == null)
            _studentProfile = new StudentProfileTable(this, Keys.STUDENT_PROFILE_SCHOOL_REPORT__FK_STUD_PROF_SCHL_REPT_PROF);

        return _studentProfile;
    }

    @Override
    @Nonnull
    public StudentProfileSchoolReportTable as(String alias) {
        return new StudentProfileSchoolReportTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public StudentProfileSchoolReportTable as(Name alias) {
        return new StudentProfileSchoolReportTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public StudentProfileSchoolReportTable rename(String name) {
        return new StudentProfileSchoolReportTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public StudentProfileSchoolReportTable rename(Name name) {
        return new StudentProfileSchoolReportTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row9<UUID, UUID, String, String, String, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
