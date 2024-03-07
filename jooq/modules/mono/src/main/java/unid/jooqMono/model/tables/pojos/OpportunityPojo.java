/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;


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
@lombok.experimental.FieldNameConstants(innerTypeName = "Columns")
public class OpportunityPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID           id;
    private UUID           titleI18nId;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public OpportunityPojo() {}

    public OpportunityPojo(OpportunityPojo value) {
        this.id = value.id;
        this.titleI18nId = value.titleI18nId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "id", "titleI18nId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public OpportunityPojo(
        @Nonnull UUID           id,
        @Nullable UUID           titleI18nId,
        @Nullable OffsetDateTime createdOn,
        @Nullable String         createdBy,
        @Nullable OffsetDateTime updatedOn,
        @Nullable String         updatedBy
    ) {
        this.id = id;
        this.titleI18nId = titleI18nId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public.opportunity.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.opportunity.id</code>.
     */
    public OpportunityPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.opportunity.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return this.titleI18nId;
    }

    /**
     * Setter for <code>public.opportunity.title_i18n_id</code>.
     */
    public OpportunityPojo setTitleI18nId(@Nullable UUID titleI18nId) {
        this.titleI18nId = titleI18nId;
        return this;
    }

    /**
     * Getter for <code>public.opportunity.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.opportunity.created_on</code>.
     */
    public OpportunityPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.opportunity.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.opportunity.created_by</code>.
     */
    public OpportunityPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.opportunity.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.opportunity.updated_on</code>.
     */
    public OpportunityPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.opportunity.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.opportunity.updated_by</code>.
     */
    public OpportunityPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OpportunityPojo (");

        sb.append(id);
        sb.append(", ").append(titleI18nId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
