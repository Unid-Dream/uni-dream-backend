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
import unid.jooqMono.model.tables.InterviewTopicTable;
import unid.jooqMono.model.tables.pojos.InterviewTopicPojo;
import unid.jooqMono.model.tables.records.InterviewTopicRecord;


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
public class InterviewTopicDao extends DAOImpl<InterviewTopicRecord, InterviewTopicPojo, UUID> {

    /**
     * Create a new InterviewTopicDao without any configuration
     */
    public InterviewTopicDao() {
        super(InterviewTopicTable.INTERVIEW_TOPIC, InterviewTopicPojo.class);
    }

    /**
     * Create a new InterviewTopicDao with an attached configuration
     */
    public InterviewTopicDao(Configuration configuration) {
        super(InterviewTopicTable.INTERVIEW_TOPIC, InterviewTopicPojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(InterviewTopicPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchById(UUID... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public InterviewTopicPojo fetchOneById(UUID value) {
        return fetchOne(InterviewTopicTable.INTERVIEW_TOPIC.ID, value);
    }

    /**
     * Fetch records that have <code>description_i18n_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfDescriptionI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.DESCRIPTION_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByDescriptionI18nId(UUID... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.DESCRIPTION_I18N_ID, values);
    }

    /**
     * Fetch records that have <code>price BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfPrice(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.PRICE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>price IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByPrice(BigDecimal... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.PRICE, values);
    }

    /**
     * Fetch records that have <code>currency BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfCurrency(CurrencyEnum lowerInclusive, CurrencyEnum upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.CURRENCY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>currency IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByCurrency(CurrencyEnum... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.CURRENCY, values);
    }

    /**
     * Fetch records that have <code>created_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfCreatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.CREATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_on IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByCreatedOn(OffsetDateTime... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.CREATED_ON, values);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfCreatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByCreatedBy(String... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>updated_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfUpdatedOn(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.UPDATED_ON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_on IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByUpdatedOn(OffsetDateTime... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.UPDATED_ON, values);
    }

    /**
     * Fetch records that have <code>updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfUpdatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.UPDATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_by IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByUpdatedBy(String... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>topic_i18n_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchRangeOfTopicI18nId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(InterviewTopicTable.INTERVIEW_TOPIC.TOPIC_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>topic_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<InterviewTopicPojo> fetchByTopicI18nId(UUID[]... values) {
        return fetch(InterviewTopicTable.INTERVIEW_TOPIC.TOPIC_I18N_ID, values);
    }
}
