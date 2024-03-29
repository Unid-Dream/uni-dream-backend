/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import unid.jooqMono.model.enums.CurrencyEnum;


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
public class PricingPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID           id;
    private BigDecimal     price;
    private CurrencyEnum   currency;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public PricingPojo() {}

    public PricingPojo(PricingPojo value) {
        this.id = value.id;
        this.price = value.price;
        this.currency = value.currency;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "id", "price", "currency", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public PricingPojo(
        @Nonnull UUID           id,
        @Nullable BigDecimal     price,
        @Nonnull CurrencyEnum   currency,
        @Nullable OffsetDateTime createdOn,
        @Nullable String         createdBy,
        @Nullable OffsetDateTime updatedOn,
        @Nullable String         updatedBy
    ) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public.pricing.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.pricing.id</code>.
     */
    public PricingPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.pricing.price</code>.
     */
    @Nullable
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Setter for <code>public.pricing.price</code>.
     */
    public PricingPojo setPrice(@Nullable BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * Getter for <code>public.pricing.currency</code>.
     */
    @NotNull
    @Nonnull
    public CurrencyEnum getCurrency() {
        return this.currency;
    }

    /**
     * Setter for <code>public.pricing.currency</code>.
     */
    public PricingPojo setCurrency(@Nonnull CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Getter for <code>public.pricing.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.pricing.created_on</code>.
     */
    public PricingPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.pricing.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.pricing.created_by</code>.
     */
    public PricingPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.pricing.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.pricing.updated_on</code>.
     */
    public PricingPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.pricing.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.pricing.updated_by</code>.
     */
    public PricingPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PricingPojo (");

        sb.append(id);
        sb.append(", ").append(price);
        sb.append(", ").append(currency);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
