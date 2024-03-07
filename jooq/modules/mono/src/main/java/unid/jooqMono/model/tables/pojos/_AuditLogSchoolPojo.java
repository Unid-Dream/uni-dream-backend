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

import unid.jooqMono.model.enums.SchoolLevelEnum;


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
public class _AuditLogSchoolPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long            auditSeq;
    private OffsetDateTime  auditCreatedon;
    private String          auditCreatedby;
    private String          auditOperation;
    private String          auditType;
    private UUID            id;
    private SchoolLevelEnum schoolLevel;
    private UUID            nameI18nId;
    private UUID            countryId;
    private UUID            cityId;
    private String          longitude;
    private String          latitude;
    private UUID            detailedAddressI18nId;
    private UUID            tagId;
    private OffsetDateTime  createdOn;
    private String          createdBy;
    private OffsetDateTime  updatedOn;
    private String          updatedBy;

    public _AuditLogSchoolPojo() {}

    public _AuditLogSchoolPojo(_AuditLogSchoolPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.schoolLevel = value.schoolLevel;
        this.nameI18nId = value.nameI18nId;
        this.countryId = value.countryId;
        this.cityId = value.cityId;
        this.longitude = value.longitude;
        this.latitude = value.latitude;
        this.detailedAddressI18nId = value.detailedAddressI18nId;
        this.tagId = value.tagId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "schoolLevel", "nameI18nId", "countryId", "cityId", "longitude", "latitude", "detailedAddressI18nId", "tagId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogSchoolPojo(
        @Nonnull Long            auditSeq,
        @Nonnull OffsetDateTime  auditCreatedon,
        @Nonnull String          auditCreatedby,
        @Nonnull String          auditOperation,
        @Nonnull String          auditType,
        @Nonnull UUID            id,
        @Nullable SchoolLevelEnum schoolLevel,
        @Nullable UUID            nameI18nId,
        @Nullable UUID            countryId,
        @Nullable UUID            cityId,
        @Nullable String          longitude,
        @Nullable String          latitude,
        @Nullable UUID            detailedAddressI18nId,
        @Nullable UUID            tagId,
        @Nullable OffsetDateTime  createdOn,
        @Nullable String          createdBy,
        @Nullable OffsetDateTime  updatedOn,
        @Nullable String          updatedBy
    ) {
        this.auditSeq = auditSeq;
        this.auditCreatedon = auditCreatedon;
        this.auditCreatedby = auditCreatedby;
        this.auditOperation = auditOperation;
        this.auditType = auditType;
        this.id = id;
        this.schoolLevel = schoolLevel;
        this.nameI18nId = nameI18nId;
        this.countryId = countryId;
        this.cityId = cityId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.detailedAddressI18nId = detailedAddressI18nId;
        this.tagId = tagId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for <code>public._audit_log_school.audit_seq</code>.
     */
    public _AuditLogSchoolPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for <code>public._audit_log_school.audit_createdon</code>.
     */
    public _AuditLogSchoolPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for <code>public._audit_log_school.audit_createdby</code>.
     */
    public _AuditLogSchoolPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for <code>public._audit_log_school.audit_operation</code>.
     */
    public _AuditLogSchoolPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for <code>public._audit_log_school.audit_type</code>.
     */
    public _AuditLogSchoolPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public._audit_log_school.id</code>.
     */
    public _AuditLogSchoolPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.school_level</code>.
     */
    @Nullable
    public SchoolLevelEnum getSchoolLevel() {
        return this.schoolLevel;
    }

    /**
     * Setter for <code>public._audit_log_school.school_level</code>.
     */
    public _AuditLogSchoolPojo setSchoolLevel(@Nullable SchoolLevelEnum schoolLevel) {
        this.schoolLevel = schoolLevel;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.name_i18n_id</code>.
     */
    @Nullable
    public UUID getNameI18nId() {
        return this.nameI18nId;
    }

    /**
     * Setter for <code>public._audit_log_school.name_i18n_id</code>.
     */
    public _AuditLogSchoolPojo setNameI18nId(@Nullable UUID nameI18nId) {
        this.nameI18nId = nameI18nId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.country_id</code>.
     */
    @Nullable
    public UUID getCountryId() {
        return this.countryId;
    }

    /**
     * Setter for <code>public._audit_log_school.country_id</code>.
     */
    public _AuditLogSchoolPojo setCountryId(@Nullable UUID countryId) {
        this.countryId = countryId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.city_id</code>.
     */
    @Nullable
    public UUID getCityId() {
        return this.cityId;
    }

    /**
     * Setter for <code>public._audit_log_school.city_id</code>.
     */
    public _AuditLogSchoolPojo setCityId(@Nullable UUID cityId) {
        this.cityId = cityId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.longitude</code>.
     */
    @Nullable
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * Setter for <code>public._audit_log_school.longitude</code>.
     */
    public _AuditLogSchoolPojo setLongitude(@Nullable String longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.latitude</code>.
     */
    @Nullable
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * Setter for <code>public._audit_log_school.latitude</code>.
     */
    public _AuditLogSchoolPojo setLatitude(@Nullable String latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_school.detailed_address_i18n_id</code>.
     */
    @Nullable
    public UUID getDetailedAddressI18nId() {
        return this.detailedAddressI18nId;
    }

    /**
     * Setter for
     * <code>public._audit_log_school.detailed_address_i18n_id</code>.
     */
    public _AuditLogSchoolPojo setDetailedAddressI18nId(@Nullable UUID detailedAddressI18nId) {
        this.detailedAddressI18nId = detailedAddressI18nId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.tag_id</code>.
     */
    @Nullable
    public UUID getTagId() {
        return this.tagId;
    }

    /**
     * Setter for <code>public._audit_log_school.tag_id</code>.
     */
    public _AuditLogSchoolPojo setTagId(@Nullable UUID tagId) {
        this.tagId = tagId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public._audit_log_school.created_on</code>.
     */
    public _AuditLogSchoolPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public._audit_log_school.created_by</code>.
     */
    public _AuditLogSchoolPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public._audit_log_school.updated_on</code>.
     */
    public _AuditLogSchoolPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public._audit_log_school.updated_by</code>.
     */
    public _AuditLogSchoolPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogSchoolPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(schoolLevel);
        sb.append(", ").append(nameI18nId);
        sb.append(", ").append(countryId);
        sb.append(", ").append(cityId);
        sb.append(", ").append(longitude);
        sb.append(", ").append(latitude);
        sb.append(", ").append(detailedAddressI18nId);
        sb.append(", ").append(tagId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
