/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.daos;


import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import unid.jooqMono.model.tables.EducatorProfileExtensionTable;
import unid.jooqMono.model.tables.pojos.EducatorProfileExtensionPojo;
import unid.jooqMono.model.tables.records.EducatorProfileExtensionRecord;


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
public class EducatorProfileExtensionDao extends DAOImpl<EducatorProfileExtensionRecord, EducatorProfileExtensionPojo, UUID> {

    /**
     * Create a new EducatorProfileExtensionDao without any configuration
     */
    public EducatorProfileExtensionDao() {
        super(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION, EducatorProfileExtensionPojo.class);
    }

    /**
     * Create a new EducatorProfileExtensionDao with an attached configuration
     */
    public EducatorProfileExtensionDao(Configuration configuration) {
        super(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION, EducatorProfileExtensionPojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(EducatorProfileExtensionPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchById(UUID... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public EducatorProfileExtensionPojo fetchOneById(UUID value) {
        return fetchOne(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.ID, value);
    }

    /**
     * Fetch records that have <code>educator_profile_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfEducatorProfileId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EDUCATOR_PROFILE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>educator_profile_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByEducatorProfileId(UUID... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EDUCATOR_PROFILE_ID, values);
    }

    /**
     * Fetch records that have <code>expertise_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfExpertiseId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EXPERTISE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>expertise_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByExpertiseId(UUID[]... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EXPERTISE_ID, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByDescription(String... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>language_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfLanguageId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.LANGUAGE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>language_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByLanguageId(UUID[]... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.LANGUAGE_ID, values);
    }

    /**
     * Fetch records that have <code>city_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfCityId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.CITY_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>city_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByCityId(UUID... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.CITY_ID, values);
    }

    /**
     * Fetch records that have <code>education_school_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfEducationSchoolId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EDUCATION_SCHOOL_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>education_school_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByEducationSchoolId(UUID[]... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EDUCATION_SCHOOL_ID, values);
    }

    /**
     * Fetch records that have <code>major_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfMajorId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.MAJOR_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>major_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByMajorId(UUID[]... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.MAJOR_ID, values);
    }

    /**
     * Fetch records that have <code>country_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfCountryId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.COUNTRY_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>country_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByCountryId(UUID... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.COUNTRY_ID, values);
    }

    /**
     * Fetch records that have <code>education_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfEducationId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EDUCATION_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>education_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByEducationId(UUID[]... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EDUCATION_ID, values);
    }

    /**
     * Fetch records that have <code>expertise_description_id BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchRangeOfExpertiseDescriptionId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EXPERTISE_DESCRIPTION_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>expertise_description_id IN (values)</code>
     */
    @Nonnull
    public List<EducatorProfileExtensionPojo> fetchByExpertiseDescriptionId(UUID[]... values) {
        return fetch(EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION.EXPERTISE_DESCRIPTION_ID, values);
    }
}
