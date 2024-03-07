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

import unid.jooqMono.model.enums.AcademicSubjectResourceTypeEnum;


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
public class AcademicSubjectResourcePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID                            id;
    private UUID                            academicSubjectId;
    private AcademicSubjectResourceTypeEnum type;
    private UUID                            titleI18nId;
    private String                          author;
    private String                          url;
    private String                          thumbnail;
    private OffsetDateTime                  createdOn;
    private String                          createdBy;
    private OffsetDateTime                  updatedOn;
    private String                          updatedBy;

    public AcademicSubjectResourcePojo() {}

    public AcademicSubjectResourcePojo(AcademicSubjectResourcePojo value) {
        this.id = value.id;
        this.academicSubjectId = value.academicSubjectId;
        this.type = value.type;
        this.titleI18nId = value.titleI18nId;
        this.author = value.author;
        this.url = value.url;
        this.thumbnail = value.thumbnail;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "id", "academicSubjectId", "type", "titleI18nId", "author", "url", "thumbnail", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public AcademicSubjectResourcePojo(
        @Nonnull UUID                            id,
        @Nonnull UUID                            academicSubjectId,
        @Nullable AcademicSubjectResourceTypeEnum type,
        @Nullable UUID                            titleI18nId,
        @Nullable String                          author,
        @Nullable String                          url,
        @Nullable String                          thumbnail,
        @Nullable OffsetDateTime                  createdOn,
        @Nullable String                          createdBy,
        @Nullable OffsetDateTime                  updatedOn,
        @Nullable String                          updatedBy
    ) {
        this.id = id;
        this.academicSubjectId = academicSubjectId;
        this.type = type;
        this.titleI18nId = titleI18nId;
        this.author = author;
        this.url = url;
        this.thumbnail = thumbnail;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public.academic_subject_resource.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.academic_subject_resource.id</code>.
     */
    public AcademicSubjectResourcePojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public.academic_subject_resource.academic_subject_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getAcademicSubjectId() {
        return this.academicSubjectId;
    }

    /**
     * Setter for
     * <code>public.academic_subject_resource.academic_subject_id</code>.
     */
    public AcademicSubjectResourcePojo setAcademicSubjectId(@Nonnull UUID academicSubjectId) {
        this.academicSubjectId = academicSubjectId;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.type</code>.
     */
    @Nullable
    public AcademicSubjectResourceTypeEnum getType() {
        return this.type;
    }

    /**
     * Setter for <code>public.academic_subject_resource.type</code>.
     */
    public AcademicSubjectResourcePojo setType(@Nullable AcademicSubjectResourceTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return this.titleI18nId;
    }

    /**
     * Setter for <code>public.academic_subject_resource.title_i18n_id</code>.
     */
    public AcademicSubjectResourcePojo setTitleI18nId(@Nullable UUID titleI18nId) {
        this.titleI18nId = titleI18nId;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.author</code>.
     */
    @Nullable
    public String getAuthor() {
        return this.author;
    }

    /**
     * Setter for <code>public.academic_subject_resource.author</code>.
     */
    public AcademicSubjectResourcePojo setAuthor(@Nullable String author) {
        this.author = author;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.url</code>.
     */
    @Nullable
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter for <code>public.academic_subject_resource.url</code>.
     */
    public AcademicSubjectResourcePojo setUrl(@Nullable String url) {
        this.url = url;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.thumbnail</code>.
     */
    @Nullable
    public String getThumbnail() {
        return this.thumbnail;
    }

    /**
     * Setter for <code>public.academic_subject_resource.thumbnail</code>.
     */
    public AcademicSubjectResourcePojo setThumbnail(@Nullable String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public.academic_subject_resource.created_on</code>.
     */
    public AcademicSubjectResourcePojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public.academic_subject_resource.created_by</code>.
     */
    public AcademicSubjectResourcePojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public.academic_subject_resource.updated_on</code>.
     */
    public AcademicSubjectResourcePojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public.academic_subject_resource.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public.academic_subject_resource.updated_by</code>.
     */
    public AcademicSubjectResourcePojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AcademicSubjectResourcePojo (");

        sb.append(id);
        sb.append(", ").append(academicSubjectId);
        sb.append(", ").append(type);
        sb.append(", ").append(titleI18nId);
        sb.append(", ").append(author);
        sb.append(", ").append(url);
        sb.append(", ").append(thumbnail);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
