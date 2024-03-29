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

import unid.jooqMono.model.tables.EcaProfileSectionTable;
import unid.jooqMono.model.tables.pojos.EcaProfileSectionPojo;
import unid.jooqMono.model.tables.records.EcaProfileSectionRecord;


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
public class EcaProfileSectionDao extends DAOImpl<EcaProfileSectionRecord, EcaProfileSectionPojo, UUID> {

    /**
     * Create a new EcaProfileSectionDao without any configuration
     */
    public EcaProfileSectionDao() {
        super(EcaProfileSectionTable.ECA_PROFILE_SECTION, EcaProfileSectionPojo.class);
    }

    /**
     * Create a new EcaProfileSectionDao with an attached configuration
     */
    public EcaProfileSectionDao(Configuration configuration) {
        super(EcaProfileSectionTable.ECA_PROFILE_SECTION, EcaProfileSectionPojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(EcaProfileSectionPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EcaProfileSectionPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(EcaProfileSectionTable.ECA_PROFILE_SECTION.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<EcaProfileSectionPojo> fetchById(UUID... values) {
        return fetch(EcaProfileSectionTable.ECA_PROFILE_SECTION.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public EcaProfileSectionPojo fetchOneById(UUID value) {
        return fetchOne(EcaProfileSectionTable.ECA_PROFILE_SECTION.ID, value);
    }

    /**
     * Fetch records that have <code>section_i18n_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EcaProfileSectionPojo> fetchRangeOfSectionI18nId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(EcaProfileSectionTable.ECA_PROFILE_SECTION.SECTION_I18N_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>section_i18n_id IN (values)</code>
     */
    @Nonnull
    public List<EcaProfileSectionPojo> fetchBySectionI18nId(UUID... values) {
        return fetch(EcaProfileSectionTable.ECA_PROFILE_SECTION.SECTION_I18N_ID, values);
    }

    /**
     * Fetch records that have <code>sort BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<EcaProfileSectionPojo> fetchRangeOfSort(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(EcaProfileSectionTable.ECA_PROFILE_SECTION.SORT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sort IN (values)</code>
     */
    @Nonnull
    public List<EcaProfileSectionPojo> fetchBySort(Integer... values) {
        return fetch(EcaProfileSectionTable.ECA_PROFILE_SECTION.SORT, values);
    }
}
