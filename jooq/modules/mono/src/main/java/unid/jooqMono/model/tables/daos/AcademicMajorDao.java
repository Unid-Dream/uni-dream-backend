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
import org.jooq.impl.DAOImpl;

import unid.jooqMono.model.tables.AcademicMajorTable;
import unid.jooqMono.model.tables.pojos.AcademicMajorPojo;
import unid.jooqMono.model.tables.records.AcademicMajorRecord;


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
public class AcademicMajorDao extends DAOImpl<AcademicMajorRecord, AcademicMajorPojo, UUID> {

    /**
     * Create a new AcademicMajorDao without any configuration
     */
    public AcademicMajorDao() {
        super(AcademicMajorTable.ACADEMIC_MAJOR, AcademicMajorPojo.class);
    }

    /**
     * Create a new AcademicMajorDao with an attached configuration
     */
    public AcademicMajorDao(Configuration configuration) {
        super(AcademicMajorTable.ACADEMIC_MAJOR, AcademicMajorPojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(AcademicMajorPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchById(UUID... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public AcademicMajorPojo fetchOneById(UUID value) {
        return fetchOne(AcademicMajorTable.ACADEMIC_MAJOR.ID, value);
    }

    /**
     * Fetch records that have <code>title_i18n_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfTitleI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.TITLE_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>title_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByTitleI18nId(UUID... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.TITLE_I18N_ID, values);
    }

    /**
     * Fetch a unique record that has <code>title_i18n_id = value</code>
     */
    @Nullable
    public AcademicMajorPojo fetchOneByTitleI18nId(UUID value) {
        return fetchOne(AcademicMajorTable.ACADEMIC_MAJOR.TITLE_I18N_ID, value);
    }

    /**
     * Fetch records that have <code>description_i18n_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfDescriptionI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.DESCRIPTION_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByDescriptionI18nId(UUID... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.DESCRIPTION_I18N_ID, values);
    }

    /**
     * Fetch a unique record that has <code>description_i18n_id = value</code>
     */
    @Nullable
    public AcademicMajorPojo fetchOneByDescriptionI18nId(UUID value) {
        return fetchOne(AcademicMajorTable.ACADEMIC_MAJOR.DESCRIPTION_I18N_ID, value);
    }

    /**
     * Fetch records that have <code>tag_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfTagId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.TAG_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>tag_id IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByTagId(UUID... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.TAG_ID, values);
    }

    /**
     * Fetch records that have <code>created_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfCreatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.CREATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_on IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByCreatedOn(OffsetDateTime... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.CREATED_ON, values);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfCreatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByCreatedBy(String... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>updated_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfUpdatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.UPDATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_on IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByUpdatedOn(OffsetDateTime... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.UPDATED_ON, values);
    }

    /**
     * Fetch records that have <code>updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfUpdatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.UPDATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_by IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByUpdatedBy(String... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>icon_path BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfIconPath(String lowerInclusive, String upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.ICON_PATH, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>icon_path IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchByIconPath(String... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.ICON_PATH, values);
    }

    /**
     * Fetch records that have <code>serial_number BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOfSerialNumber(String lowerInclusive, String upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR.SERIAL_NUMBER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>serial_number IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchBySerialNumber(String... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR.SERIAL_NUMBER, values);
    }

    /**
     * Fetch records that have <code>_deleted BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchRangeOf_Deleted(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(AcademicMajorTable.ACADEMIC_MAJOR._DELETED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>_deleted IN (values)</code>
     */
    @Nonnull
    public List<AcademicMajorPojo> fetchBy_Deleted(Boolean... values) {
        return fetch(AcademicMajorTable.ACADEMIC_MAJOR._DELETED, values);
    }
}
