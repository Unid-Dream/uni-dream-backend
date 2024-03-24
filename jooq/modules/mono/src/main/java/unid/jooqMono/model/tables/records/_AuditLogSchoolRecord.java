/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.tables._AuditLogSchoolTable;
import unid.jooqMono.model.tables.pojos._AuditLogSchoolPojo;


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
public class _AuditLogSchoolRecord extends TableRecordImpl<_AuditLogSchoolRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public._audit_log_school.audit_seq</code>.
     */
    public _AuditLogSchoolRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public._audit_log_school.audit_createdon</code>.
     */
    public _AuditLogSchoolRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public._audit_log_school.audit_createdby</code>.
     */
    public _AuditLogSchoolRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public._audit_log_school.audit_operation</code>.
     */
    public _AuditLogSchoolRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public._audit_log_school.audit_type</code>.
     */
    public _AuditLogSchoolRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_school.id</code>.
     */
    public _AuditLogSchoolRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public._audit_log_school.school_level</code>.
     */
    public _AuditLogSchoolRecord setSchoolLevel(@Nullable SchoolLevelEnum value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.school_level</code>.
     */
    @Nullable
    public SchoolLevelEnum getSchoolLevel() {
        return (SchoolLevelEnum) get(6);
    }

    /**
     * Setter for <code>public._audit_log_school.name_i18n_id</code>.
     */
    public _AuditLogSchoolRecord setNameI18nId(@Nullable UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.name_i18n_id</code>.
     */
    @Nullable
    public UUID getNameI18nId() {
        return (UUID) get(7);
    }

    /**
     * Setter for <code>public._audit_log_school.country_id</code>.
     */
    public _AuditLogSchoolRecord setCountryId(@Nullable UUID value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.country_id</code>.
     */
    @Nullable
    public UUID getCountryId() {
        return (UUID) get(8);
    }

    /**
     * Setter for <code>public._audit_log_school.city_id</code>.
     */
    public _AuditLogSchoolRecord setCityId(@Nullable UUID value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.city_id</code>.
     */
    @Nullable
    public UUID getCityId() {
        return (UUID) get(9);
    }

    /**
     * Setter for <code>public._audit_log_school.longitude</code>.
     */
    public _AuditLogSchoolRecord setLongitude(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.longitude</code>.
     */
    @Nullable
    public String getLongitude() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public._audit_log_school.latitude</code>.
     */
    public _AuditLogSchoolRecord setLatitude(@Nullable String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.latitude</code>.
     */
    @Nullable
    public String getLatitude() {
        return (String) get(11);
    }

    /**
     * Setter for
     * <code>public._audit_log_school.detailed_address_i18n_id</code>.
     */
    public _AuditLogSchoolRecord setDetailedAddressI18nId(@Nullable UUID value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_school.detailed_address_i18n_id</code>.
     */
    @Nullable
    public UUID getDetailedAddressI18nId() {
        return (UUID) get(12);
    }

    /**
     * Setter for <code>public._audit_log_school.tag_id</code>.
     */
    public _AuditLogSchoolRecord setTagId(@Nullable UUID value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.tag_id</code>.
     */
    @Nullable
    public UUID getTagId() {
        return (UUID) get(13);
    }

    /**
     * Setter for <code>public._audit_log_school.created_on</code>.
     */
    public _AuditLogSchoolRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(14);
    }

    /**
     * Setter for <code>public._audit_log_school.created_by</code>.
     */
    public _AuditLogSchoolRecord setCreatedBy(@Nullable String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(15);
    }

    /**
     * Setter for <code>public._audit_log_school.updated_on</code>.
     */
    public _AuditLogSchoolRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(16);
    }

    /**
     * Setter for <code>public._audit_log_school.updated_by</code>.
     */
    public _AuditLogSchoolRecord setUpdatedBy(@Nullable String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(17);
    }

    /**
     * Setter for <code>public._audit_log_school.rate</code>.
     */
    public _AuditLogSchoolRecord setRate(@Nullable BigDecimal value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.rate</code>.
     */
    @Nullable
    public BigDecimal getRate() {
        return (BigDecimal) get(18);
    }

    /**
     * Setter for <code>public._audit_log_school.population</code>.
     */
    public _AuditLogSchoolRecord setPopulation(@Nullable BigDecimal value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.population</code>.
     */
    @Nullable
    public BigDecimal getPopulation() {
        return (BigDecimal) get(19);
    }

    /**
     * Setter for <code>public._audit_log_school.tuition</code>.
     */
    public _AuditLogSchoolRecord setTuition(@Nullable BigDecimal value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.tuition</code>.
     */
    @Nullable
    public BigDecimal getTuition() {
        return (BigDecimal) get(20);
    }

    /**
     * Setter for <code>public._audit_log_school.factor</code>.
     */
    public _AuditLogSchoolRecord setFactor(@Nullable String value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.factor</code>.
     */
    @Nullable
    public String getFactor() {
        return (String) get(21);
    }

    /**
     * Setter for <code>public._audit_log_school.description</code>.
     */
    public _AuditLogSchoolRecord setDescription(@Nullable String value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_school.description</code>.
     */
    @Nullable
    public String getDescription() {
        return (String) get(22);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogSchoolRecord
     */
    public _AuditLogSchoolRecord() {
        super(_AuditLogSchoolTable._AUDIT_LOG_SCHOOL);
    }

    /**
     * Create a detached, initialised _AuditLogSchoolRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "schoolLevel", "nameI18nId", "countryId", "cityId", "longitude", "latitude", "detailedAddressI18nId", "tagId", "createdOn", "createdBy", "updatedOn", "updatedBy", "rate", "population", "tuition", "factor", "description" })
    public _AuditLogSchoolRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nullable SchoolLevelEnum schoolLevel, @Nullable UUID nameI18nId, @Nullable UUID countryId, @Nullable UUID cityId, @Nullable String longitude, @Nullable String latitude, @Nullable UUID detailedAddressI18nId, @Nullable UUID tagId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable BigDecimal rate, @Nullable BigDecimal population, @Nullable BigDecimal tuition, @Nullable String factor, @Nullable String description) {
        super(_AuditLogSchoolTable._AUDIT_LOG_SCHOOL);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setSchoolLevel(schoolLevel);
        setNameI18nId(nameI18nId);
        setCountryId(countryId);
        setCityId(cityId);
        setLongitude(longitude);
        setLatitude(latitude);
        setDetailedAddressI18nId(detailedAddressI18nId);
        setTagId(tagId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
        setRate(rate);
        setPopulation(population);
        setTuition(tuition);
        setFactor(factor);
        setDescription(description);
    }

    /**
     * Create a detached, initialised _AuditLogSchoolRecord
     */
    public _AuditLogSchoolRecord(_AuditLogSchoolPojo value) {
        super(_AuditLogSchoolTable._AUDIT_LOG_SCHOOL);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setSchoolLevel(value.getSchoolLevel());
            setNameI18nId(value.getNameI18nId());
            setCountryId(value.getCountryId());
            setCityId(value.getCityId());
            setLongitude(value.getLongitude());
            setLatitude(value.getLatitude());
            setDetailedAddressI18nId(value.getDetailedAddressI18nId());
            setTagId(value.getTagId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
            setRate(value.getRate());
            setPopulation(value.getPopulation());
            setTuition(value.getTuition());
            setFactor(value.getFactor());
            setDescription(value.getDescription());
        }
    }
}
