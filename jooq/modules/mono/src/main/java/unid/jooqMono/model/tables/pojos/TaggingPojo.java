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
import javax.validation.constraints.NotNull;

import unid.jooqMono.model.enums.TagTargetEnum;


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
public class TaggingPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID           id;
    private TagTargetEnum  target;
    private UUID           targetId;
    private UUID           tagId;
    private OffsetDateTime createdOn;
    private String         createdBy;
    private OffsetDateTime updatedOn;
    private String         updatedBy;

    public TaggingPojo() {}

    public TaggingPojo(TaggingPojo value) {
        this.id = value.id;
        this.target = value.target;
        this.targetId = value.targetId;
        this.tagId = value.tagId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "id", "target", "targetId", "tagId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public TaggingPojo(
        @Nonnull UUID           id,
        @Nonnull TagTargetEnum  target,
        @Nonnull UUID           targetId,
        @Nonnull UUID           tagId,
        @Nullable OffsetDateTime createdOn,
        @Nullable String         createdBy,
        @Nullable OffsetDateTime updatedOn,
        @Nullable String         updatedBy
    ) {
        this.id = id;
        this.target = target;
        this.targetId = targetId;
        this.tagId = tagId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public.tagging.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.tagging.id</code>.
     */
    public TaggingPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.tagging.target</code>.
     */
    @NotNull
    @Nonnull
    public TagTargetEnum getTarget() {
        return this.target;
    }

    /**
     * Setter for <code>public.tagging.target</code>.
     */
    public TaggingPojo setTarget(@Nonnull TagTargetEnum target) {
        this.target = target;
        return this;
    }

    /**
     * Getter for <code>public.tagging.target_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getTargetId() {
        return this.targetId;
    }

    /**
     * Setter for <code>public.tagging.target_id</code>.
     */
    public TaggingPojo setTargetId(@Nonnull UUID targetId) {
        this.targetId = targetId;
        return this;
    }

    /**
     * Getter for <code>public.tagging.tag_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getTagId() {
        return this.tagId;
    }

    /**
     * Setter for <code>public.tagging.tag_id</code>.
     */
    public TaggingPojo setTagId(@Nonnull UUID tagId) {
        this.tagId = tagId;
        return this;
    }

    /**
     * Getter for <code>public.tagging.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.tagging.created_on</code>.
     */
    public TaggingPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.tagging.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.tagging.created_by</code>.
     */
    public TaggingPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.tagging.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.tagging.updated_on</code>.
     */
    public TaggingPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.tagging.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.tagging.updated_by</code>.
     */
    public TaggingPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TaggingPojo (");

        sb.append(id);
        sb.append(", ").append(target);
        sb.append(", ").append(targetId);
        sb.append(", ").append(tagId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
