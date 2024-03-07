/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.daos;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import unid.jooqMono.model.enums.DeletedTypeEnum;
import unid.jooqMono.model.tables.SchoolExtensionTable;
import unid.jooqMono.model.tables.pojos.SchoolExtensionPojo;
import unid.jooqMono.model.tables.records.SchoolExtensionRecord;


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
public class SchoolExtensionDao extends DAOImpl<SchoolExtensionRecord, SchoolExtensionPojo, UUID> {

    /**
     * Create a new SchoolExtensionDao without any configuration
     */
    public SchoolExtensionDao() {
        super(SchoolExtensionTable.SCHOOL_EXTENSION, SchoolExtensionPojo.class);
    }

    /**
     * Create a new SchoolExtensionDao with an attached configuration
     */
    public SchoolExtensionDao(Configuration configuration) {
        super(SchoolExtensionTable.SCHOOL_EXTENSION, SchoolExtensionPojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(SchoolExtensionPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(SchoolExtensionTable.SCHOOL_EXTENSION.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchById(UUID... values) {
        return fetch(SchoolExtensionTable.SCHOOL_EXTENSION.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public SchoolExtensionPojo fetchOneById(UUID value) {
        return fetchOne(SchoolExtensionTable.SCHOOL_EXTENSION.ID, value);
    }

    /**
     * Fetch records that have <code>rate BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchRangeOfRate(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(SchoolExtensionTable.SCHOOL_EXTENSION.RATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>rate IN (values)</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchByRate(BigDecimal... values) {
        return fetch(SchoolExtensionTable.SCHOOL_EXTENSION.RATE, values);
    }

    /**
     * Fetch records that have <code>population BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchRangeOfPopulation(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(SchoolExtensionTable.SCHOOL_EXTENSION.POPULATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>population IN (values)</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchByPopulation(BigDecimal... values) {
        return fetch(SchoolExtensionTable.SCHOOL_EXTENSION.POPULATION, values);
    }

    /**
     * Fetch records that have <code>tuition BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchRangeOfTuition(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(SchoolExtensionTable.SCHOOL_EXTENSION.TUITION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>tuition IN (values)</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchByTuition(BigDecimal... values) {
        return fetch(SchoolExtensionTable.SCHOOL_EXTENSION.TUITION, values);
    }

    /**
     * Fetch records that have <code>factor BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchRangeOfFactor(String lowerInclusive, String upperInclusive) {
        return fetchRange(SchoolExtensionTable.SCHOOL_EXTENSION.FACTOR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>factor IN (values)</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchByFactor(String... values) {
        return fetch(SchoolExtensionTable.SCHOOL_EXTENSION.FACTOR, values);
    }

    /**
     * Fetch records that have <code>deleted BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchRangeOfDeleted(DeletedTypeEnum lowerInclusive, DeletedTypeEnum upperInclusive) {
        return fetchRange(SchoolExtensionTable.SCHOOL_EXTENSION.DELETED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>deleted IN (values)</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchByDeleted(DeletedTypeEnum... values) {
        return fetch(SchoolExtensionTable.SCHOOL_EXTENSION.DELETED, values);
    }

    /**
     * Fetch records that have <code>school_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchRangeOfSchoolId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(SchoolExtensionTable.SCHOOL_EXTENSION.SCHOOL_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>school_id IN (values)</code>
     */
    @Nonnull
    public List<SchoolExtensionPojo> fetchBySchoolId(UUID... values) {
        return fetch(SchoolExtensionTable.SCHOOL_EXTENSION.SCHOOL_ID, values);
    }
}
