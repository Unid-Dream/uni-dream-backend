/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.daos;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.tables.PricingTable;
import unid.jooqMono.model.tables.pojos.PricingPojo;
import unid.jooqMono.model.tables.records.PricingRecord;


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
public class PricingDao extends DAOImpl<PricingRecord, PricingPojo, UUID> {

    /**
     * Create a new PricingDao without any configuration
     */
    public PricingDao() {
        super(PricingTable.PRICING, PricingPojo.class);
    }

    /**
     * Create a new PricingDao with an attached configuration
     */
    public PricingDao(Configuration configuration) {
        super(PricingTable.PRICING, PricingPojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(PricingPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<PricingPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(PricingTable.PRICING.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<PricingPojo> fetchById(UUID... values) {
        return fetch(PricingTable.PRICING.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public PricingPojo fetchOneById(UUID value) {
        return fetchOne(PricingTable.PRICING.ID, value);
    }

    /**
     * Fetch records that have <code>price BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<PricingPojo> fetchRangeOfPrice(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(PricingTable.PRICING.PRICE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>price IN (values)</code>
     */
    @Nonnull
    public List<PricingPojo> fetchByPrice(BigDecimal... values) {
        return fetch(PricingTable.PRICING.PRICE, values);
    }

    /**
     * Fetch records that have <code>currency BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<PricingPojo> fetchRangeOfCurrency(CurrencyEnum lowerInclusive, CurrencyEnum upperInclusive) {
        return fetchRange(PricingTable.PRICING.CURRENCY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>currency IN (values)</code>
     */
    @Nonnull
    public List<PricingPojo> fetchByCurrency(CurrencyEnum... values) {
        return fetch(PricingTable.PRICING.CURRENCY, values);
    }

    /**
     * Fetch records that have <code>created_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<PricingPojo> fetchRangeOfCreatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(PricingTable.PRICING.CREATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_on IN (values)</code>
     */
    @Nonnull
    public List<PricingPojo> fetchByCreatedOn(OffsetDateTime... values) {
        return fetch(PricingTable.PRICING.CREATED_ON, values);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<PricingPojo> fetchRangeOfCreatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(PricingTable.PRICING.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    @Nonnull
    public List<PricingPojo> fetchByCreatedBy(String... values) {
        return fetch(PricingTable.PRICING.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>updated_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<PricingPojo> fetchRangeOfUpdatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(PricingTable.PRICING.UPDATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_on IN (values)</code>
     */
    @Nonnull
    public List<PricingPojo> fetchByUpdatedOn(OffsetDateTime... values) {
        return fetch(PricingTable.PRICING.UPDATED_ON, values);
    }

    /**
     * Fetch records that have <code>updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<PricingPojo> fetchRangeOfUpdatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(PricingTable.PRICING.UPDATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_by IN (values)</code>
     */
    @Nonnull
    public List<PricingPojo> fetchByUpdatedBy(String... values) {
        return fetch(PricingTable.PRICING.UPDATED_BY, values);
    }
}
