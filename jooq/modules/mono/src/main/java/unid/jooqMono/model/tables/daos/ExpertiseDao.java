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

import unid.jooqMono.model.tables.ExpertiseTable;
import unid.jooqMono.model.tables.pojos.ExpertisePojo;
import unid.jooqMono.model.tables.records.ExpertiseRecord;


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
public class ExpertiseDao extends DAOImpl<ExpertiseRecord, ExpertisePojo, UUID> {

    /**
     * Create a new ExpertiseDao without any configuration
     */
    public ExpertiseDao() {
        super(ExpertiseTable.EXPERTISE, ExpertisePojo.class);
    }

    /**
     * Create a new ExpertiseDao with an attached configuration
     */
    public ExpertiseDao(Configuration configuration) {
        super(ExpertiseTable.EXPERTISE, ExpertisePojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(ExpertisePojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(ExpertiseTable.EXPERTISE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchById(UUID... values) {
        return fetch(ExpertiseTable.EXPERTISE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public ExpertisePojo fetchOneById(UUID value) {
        return fetchOne(ExpertiseTable.EXPERTISE.ID, value);
    }

    /**
     * Fetch records that have <code>description_i18n_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchRangeOfDescriptionI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(ExpertiseTable.EXPERTISE.DESCRIPTION_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchByDescriptionI18nId(UUID... values) {
        return fetch(ExpertiseTable.EXPERTISE.DESCRIPTION_I18N_ID, values);
    }

    /**
     * Fetch a unique record that has <code>description_i18n_id = value</code>
     */
    @Nullable
    public ExpertisePojo fetchOneByDescriptionI18nId(UUID value) {
        return fetchOne(ExpertiseTable.EXPERTISE.DESCRIPTION_I18N_ID, value);
    }

    /**
     * Fetch records that have <code>tag_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchRangeOfTagId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(ExpertiseTable.EXPERTISE.TAG_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>tag_id IN (values)</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchByTagId(UUID... values) {
        return fetch(ExpertiseTable.EXPERTISE.TAG_ID, values);
    }

    /**
     * Fetch records that have <code>created_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchRangeOfCreatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(ExpertiseTable.EXPERTISE.CREATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_on IN (values)</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchByCreatedOn(OffsetDateTime... values) {
        return fetch(ExpertiseTable.EXPERTISE.CREATED_ON, values);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchRangeOfCreatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(ExpertiseTable.EXPERTISE.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchByCreatedBy(String... values) {
        return fetch(ExpertiseTable.EXPERTISE.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>updated_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchRangeOfUpdatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(ExpertiseTable.EXPERTISE.UPDATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_on IN (values)</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchByUpdatedOn(OffsetDateTime... values) {
        return fetch(ExpertiseTable.EXPERTISE.UPDATED_ON, values);
    }

    /**
     * Fetch records that have <code>updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchRangeOfUpdatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(ExpertiseTable.EXPERTISE.UPDATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_by IN (values)</code>
     */
    @Nonnull
    public List<ExpertisePojo> fetchByUpdatedBy(String... values) {
        return fetch(ExpertiseTable.EXPERTISE.UPDATED_BY, values);
    }
}
