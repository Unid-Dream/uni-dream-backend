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
import org.jooq.Row10;
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
import unid.jooqMono.model.tables.records.StudentBookingSurveyMapRecord;


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
public class StudentBookingSurveyMapTable extends TableImpl<StudentBookingSurveyMapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.student_booking_survey_map</code>
     */
    public static final StudentBookingSurveyMapTable STUDENT_BOOKING_SURVEY_MAP = new StudentBookingSurveyMapTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<StudentBookingSurveyMapRecord> getRecordType() {
        return StudentBookingSurveyMapRecord.class;
    }

    /**
     * The column <code>public.student_booking_survey_map.id</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column
     * <code>public.student_booking_survey_map.educator_calendar_id</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, UUID> EDUCATOR_CALENDAR_ID = createField(DSL.name("educator_calendar_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public.student_booking_survey_map.student_session_survey_id</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, UUID> STUDENT_SESSION_SURVEY_ID = createField(DSL.name("student_session_survey_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public.student_booking_survey_map.student_session_survey_version_asked</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, OffsetDateTime> STUDENT_SESSION_SURVEY_VERSION_ASKED = createField(DSL.name("student_session_survey_version_asked"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "");

    /**
     * The column
     * <code>public.student_booking_survey_map.student_session_survey_version_completed</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, OffsetDateTime> STUDENT_SESSION_SURVEY_VERSION_COMPLETED = createField(DSL.name("student_session_survey_version_completed"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.student_booking_survey_map.completed</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, Boolean> COMPLETED = createField(DSL.name("completed"), SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.student_booking_survey_map.created_on</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.student_booking_survey_map.created_by</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.student_booking_survey_map.updated_on</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.student_booking_survey_map.updated_by</code>.
     */
    public final TableField<StudentBookingSurveyMapRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private StudentBookingSurveyMapTable(Name alias, Table<StudentBookingSurveyMapRecord> aliased) {
        this(alias, aliased, null);
    }

    private StudentBookingSurveyMapTable(Name alias, Table<StudentBookingSurveyMapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.student_booking_survey_map</code> table
     * reference
     */
    public StudentBookingSurveyMapTable(String alias) {
        this(DSL.name(alias), STUDENT_BOOKING_SURVEY_MAP);
    }

    /**
     * Create an aliased <code>public.student_booking_survey_map</code> table
     * reference
     */
    public StudentBookingSurveyMapTable(Name alias) {
        this(alias, STUDENT_BOOKING_SURVEY_MAP);
    }

    /**
     * Create a <code>public.student_booking_survey_map</code> table reference
     */
    public StudentBookingSurveyMapTable() {
        this(DSL.name("student_booking_survey_map"), null);
    }

    public <O extends Record> StudentBookingSurveyMapTable(Table<O> child, ForeignKey<O, StudentBookingSurveyMapRecord> key) {
        super(child, key, STUDENT_BOOKING_SURVEY_MAP);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<StudentBookingSurveyMapRecord> getPrimaryKey() {
        return Keys.STUDENT_BOOKING_SURVEY_MAP_PKEY;
    }

    @Override
    @Nonnull
    public List<UniqueKey<StudentBookingSurveyMapRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.UQ_STUD_BOOK_SURV_MAP);
    }

    @Override
    @Nonnull
    public List<ForeignKey<StudentBookingSurveyMapRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STUDENT_BOOKING_SURVEY_MAP__FK_STUD_BOOK_SURV_MAP_EDUC_STUD_BOOK, Keys.STUDENT_BOOKING_SURVEY_MAP__FK_STUD_BOOK_SURV_MAP_SURV, Keys.STUDENT_BOOKING_SURVEY_MAP__FK_STUD_BOOK_SURV_MAP_SURV2);
    }

    private transient EducatorCalendarTable _educatorCalendar;
    private transient StudentSessionSurveyTable _fkStudBookSurvMapSurv;
    private transient StudentSessionSurveyTable _fkStudBookSurvMapSurv2;

    public EducatorCalendarTable educatorCalendar() {
        if (_educatorCalendar == null)
            _educatorCalendar = new EducatorCalendarTable(this, Keys.STUDENT_BOOKING_SURVEY_MAP__FK_STUD_BOOK_SURV_MAP_EDUC_STUD_BOOK);

        return _educatorCalendar;
    }

    public StudentSessionSurveyTable fkStudBookSurvMapSurv() {
        if (_fkStudBookSurvMapSurv == null)
            _fkStudBookSurvMapSurv = new StudentSessionSurveyTable(this, Keys.STUDENT_BOOKING_SURVEY_MAP__FK_STUD_BOOK_SURV_MAP_SURV);

        return _fkStudBookSurvMapSurv;
    }

    public StudentSessionSurveyTable fkStudBookSurvMapSurv2() {
        if (_fkStudBookSurvMapSurv2 == null)
            _fkStudBookSurvMapSurv2 = new StudentSessionSurveyTable(this, Keys.STUDENT_BOOKING_SURVEY_MAP__FK_STUD_BOOK_SURV_MAP_SURV2);

        return _fkStudBookSurvMapSurv2;
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapTable as(String alias) {
        return new StudentBookingSurveyMapTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public StudentBookingSurveyMapTable as(Name alias) {
        return new StudentBookingSurveyMapTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public StudentBookingSurveyMapTable rename(String name) {
        return new StudentBookingSurveyMapTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public StudentBookingSurveyMapTable rename(Name name) {
        return new StudentBookingSurveyMapTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row10<UUID, UUID, UUID, OffsetDateTime, OffsetDateTime, Boolean, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
