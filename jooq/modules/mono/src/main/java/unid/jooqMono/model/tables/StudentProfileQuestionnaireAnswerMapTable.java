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
import unid.jooqMono.model.tables.records.StudentProfileQuestionnaireAnswerMapRecord;


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
public class StudentProfileQuestionnaireAnswerMapTable extends TableImpl<StudentProfileQuestionnaireAnswerMapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>public.student_profile_questionnaire_answer_map</code>
     */
    public static final StudentProfileQuestionnaireAnswerMapTable STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP = new StudentProfileQuestionnaireAnswerMapTable();

    /**
     * The class holding records for this type
     */
    @Override
    @Nonnull
    public Class<StudentProfileQuestionnaireAnswerMapRecord> getRecordType() {
        return StudentProfileQuestionnaireAnswerMapRecord.class;
    }

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.id</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.student_profile_questionnaire_map_id</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, UUID> STUDENT_PROFILE_QUESTIONNAIRE_MAP_ID = createField(DSL.name("student_profile_questionnaire_map_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.questionnaire_question_id</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, UUID> QUESTIONNAIRE_QUESTION_ID = createField(DSL.name("questionnaire_question_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.questionnaire_answer_id</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, UUID> QUESTIONNAIRE_ANSWER_ID = createField(DSL.name("questionnaire_answer_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.questionnaire_answer_order</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, Integer> QUESTIONNAIRE_ANSWER_ORDER = createField(DSL.name("questionnaire_answer_order"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.questionnaire_answer_any_input</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, String> QUESTIONNAIRE_ANSWER_ANY_INPUT = createField(DSL.name("questionnaire_answer_any_input"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.created_on</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, OffsetDateTime> CREATED_ON = createField(DSL.name("created_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.created_by</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.updated_on</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, OffsetDateTime> UPDATED_ON = createField(DSL.name("updated_on"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column
     * <code>public.student_profile_questionnaire_answer_map.updated_by</code>.
     */
    public final TableField<StudentProfileQuestionnaireAnswerMapRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.CLOB, this, "");

    private StudentProfileQuestionnaireAnswerMapTable(Name alias, Table<StudentProfileQuestionnaireAnswerMapRecord> aliased) {
        this(alias, aliased, null);
    }

    private StudentProfileQuestionnaireAnswerMapTable(Name alias, Table<StudentProfileQuestionnaireAnswerMapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased
     * <code>public.student_profile_questionnaire_answer_map</code> table
     * reference
     */
    public StudentProfileQuestionnaireAnswerMapTable(String alias) {
        this(DSL.name(alias), STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP);
    }

    /**
     * Create an aliased
     * <code>public.student_profile_questionnaire_answer_map</code> table
     * reference
     */
    public StudentProfileQuestionnaireAnswerMapTable(Name alias) {
        this(alias, STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP);
    }

    /**
     * Create a <code>public.student_profile_questionnaire_answer_map</code>
     * table reference
     */
    public StudentProfileQuestionnaireAnswerMapTable() {
        this(DSL.name("student_profile_questionnaire_answer_map"), null);
    }

    public <O extends Record> StudentProfileQuestionnaireAnswerMapTable(Table<O> child, ForeignKey<O, StudentProfileQuestionnaireAnswerMapRecord> key) {
        super(child, key, STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP);
    }

    @Override
    @Nonnull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @Nonnull
    public UniqueKey<StudentProfileQuestionnaireAnswerMapRecord> getPrimaryKey() {
        return Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP_PKEY;
    }

    @Override
    @Nonnull
    public List<ForeignKey<StudentProfileQuestionnaireAnswerMapRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_ID, Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_QUET, Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_QUET_ANS_UQ, Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_QUET_ANS);
    }

    private transient StudentProfileQuestionnaireMapTable _studentProfileQuestionnaireMap;
    private transient StudentQuestionnaireQuestionTable _studentQuestionnaireQuestion;
    private transient StudentQuestionnaireAnswerTable _fkStudProfQuetAnsMapQuetAnsUq;
    private transient StudentQuestionnaireAnswerTable _fkStudProfQuetAnsMapQuetAns;

    public StudentProfileQuestionnaireMapTable studentProfileQuestionnaireMap() {
        if (_studentProfileQuestionnaireMap == null)
            _studentProfileQuestionnaireMap = new StudentProfileQuestionnaireMapTable(this, Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_ID);

        return _studentProfileQuestionnaireMap;
    }

    public StudentQuestionnaireQuestionTable studentQuestionnaireQuestion() {
        if (_studentQuestionnaireQuestion == null)
            _studentQuestionnaireQuestion = new StudentQuestionnaireQuestionTable(this, Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_QUET);

        return _studentQuestionnaireQuestion;
    }

    public StudentQuestionnaireAnswerTable fkStudProfQuetAnsMapQuetAnsUq() {
        if (_fkStudProfQuetAnsMapQuetAnsUq == null)
            _fkStudProfQuetAnsMapQuetAnsUq = new StudentQuestionnaireAnswerTable(this, Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_QUET_ANS_UQ);

        return _fkStudProfQuetAnsMapQuetAnsUq;
    }

    public StudentQuestionnaireAnswerTable fkStudProfQuetAnsMapQuetAns() {
        if (_fkStudProfQuetAnsMapQuetAns == null)
            _fkStudProfQuetAnsMapQuetAns = new StudentQuestionnaireAnswerTable(this, Keys.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP__FK_STUD_PROF_QUET_ANS_MAP_QUET_ANS);

        return _fkStudProfQuetAnsMapQuetAns;
    }

    @Override
    @Nonnull
    public StudentProfileQuestionnaireAnswerMapTable as(String alias) {
        return new StudentProfileQuestionnaireAnswerMapTable(DSL.name(alias), this);
    }

    @Override
    @Nonnull
    public StudentProfileQuestionnaireAnswerMapTable as(Name alias) {
        return new StudentProfileQuestionnaireAnswerMapTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public StudentProfileQuestionnaireAnswerMapTable rename(String name) {
        return new StudentProfileQuestionnaireAnswerMapTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @Nonnull
    public StudentProfileQuestionnaireAnswerMapTable rename(Name name) {
        return new StudentProfileQuestionnaireAnswerMapTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row10<UUID, UUID, UUID, UUID, Integer, String, OffsetDateTime, String, OffsetDateTime, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
