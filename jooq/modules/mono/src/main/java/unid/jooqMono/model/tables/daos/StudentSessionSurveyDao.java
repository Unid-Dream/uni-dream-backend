/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.daos;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;

import unid.jooqMono.model.tables.StudentSessionSurveyTable;
import unid.jooqMono.model.tables.pojos.StudentSessionSurveyPojo;
import unid.jooqMono.model.tables.records.StudentSessionSurveyRecord;


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
public class StudentSessionSurveyDao extends DAOImpl<StudentSessionSurveyRecord, StudentSessionSurveyPojo, Record2<UUID, OffsetDateTime>> {

    /**
     * Create a new StudentSessionSurveyDao without any configuration
     */
    public StudentSessionSurveyDao() {
        super(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY, StudentSessionSurveyPojo.class);
    }

    /**
     * Create a new StudentSessionSurveyDao with an attached configuration
     */
    public StudentSessionSurveyDao(Configuration configuration) {
        super(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY, StudentSessionSurveyPojo.class, configuration);
    }

    @Override
    @Nonnull
    public Record2<UUID, OffsetDateTime> getId(StudentSessionSurveyPojo object) {
        return compositeKeyRecord(object.getId(), object.getVersion());
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchById(UUID... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.ID, values);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfVersion(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByVersion(OffsetDateTime... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.VERSION, values);
    }

    /**
     * Fetch records that have <code>title_i18n_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfTitleI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.TITLE_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>title_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByTitleI18nId(UUID... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.TITLE_I18N_ID, values);
    }

    /**
     * Fetch a unique record that has <code>title_i18n_id = value</code>
     */
    @Nullable
    public StudentSessionSurveyPojo fetchOneByTitleI18nId(UUID value) {
        return fetchOne(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.TITLE_I18N_ID, value);
    }

    /**
     * Fetch records that have <code>sub_title_i18n_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfSubTitleI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.SUB_TITLE_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sub_title_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchBySubTitleI18nId(UUID... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.SUB_TITLE_I18N_ID, values);
    }

    /**
     * Fetch a unique record that has <code>sub_title_i18n_id = value</code>
     */
    @Nullable
    public StudentSessionSurveyPojo fetchOneBySubTitleI18nId(UUID value) {
        return fetchOne(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.SUB_TITLE_I18N_ID, value);
    }

    /**
     * Fetch records that have <code>description_i18n_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfDescriptionI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.DESCRIPTION_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByDescriptionI18nId(UUID... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.DESCRIPTION_I18N_ID, values);
    }

    /**
     * Fetch a unique record that has <code>description_i18n_id = value</code>
     */
    @Nullable
    public StudentSessionSurveyPojo fetchOneByDescriptionI18nId(UUID value) {
        return fetchOne(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.DESCRIPTION_I18N_ID, value);
    }

    /**
     * Fetch records that have <code>mandatory BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfMandatory(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.MANDATORY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>mandatory IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByMandatory(Boolean... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.MANDATORY, values);
    }

    /**
     * Fetch records that have <code>created_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfCreatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.CREATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_on IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByCreatedOn(OffsetDateTime... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.CREATED_ON, values);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfCreatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByCreatedBy(String... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>updated_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfUpdatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.UPDATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_on IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByUpdatedOn(OffsetDateTime... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.UPDATED_ON, values);
    }

    /**
     * Fetch records that have <code>updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchRangeOfUpdatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.UPDATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_by IN (values)</code>
     */
    @Nonnull
    public List<StudentSessionSurveyPojo> fetchByUpdatedBy(String... values) {
        return fetch(StudentSessionSurveyTable.STUDENT_SESSION_SURVEY.UPDATED_BY, values);
    }
}
