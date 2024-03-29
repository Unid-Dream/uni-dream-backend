/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import org.jooq.impl.TableRecordImpl;

import unid.jooqMono.model.enums.EventStatusEnum;
import unid.jooqMono.model.enums.EventTypeEnum;
import unid.jooqMono.model.tables._AuditLogEventTable;
import unid.jooqMono.model.tables.pojos._AuditLogEventPojo;


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
public class _AuditLogEventRecord extends TableRecordImpl<_AuditLogEventRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public._audit_log_event.audit_seq</code>.
     */
    public _AuditLogEventRecord setAuditSeq(@Nonnull Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public._audit_log_event.audit_createdon</code>.
     */
    public _AuditLogEventRecord setAuditCreatedon(@Nonnull OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public._audit_log_event.audit_createdby</code>.
     */
    public _AuditLogEventRecord setAuditCreatedby(@Nonnull String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public._audit_log_event.audit_operation</code>.
     */
    public _AuditLogEventRecord setAuditOperation(@Nonnull String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public._audit_log_event.audit_type</code>.
     */
    public _AuditLogEventRecord setAuditType(@Nonnull String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public._audit_log_event.id</code>.
     */
    public _AuditLogEventRecord setId(@Nonnull UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public._audit_log_event.title_i18n_id</code>.
     */
    public _AuditLogEventRecord setTitleI18nId(@Nullable UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.title_i18n_id</code>.
     */
    @Nullable
    public UUID getTitleI18nId() {
        return (UUID) get(6);
    }

    /**
     * Setter for <code>public._audit_log_event.description_i18n_id</code>.
     */
    public _AuditLogEventRecord setDescriptionI18nId(@Nullable UUID value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.description_i18n_id</code>.
     */
    @Nullable
    public UUID getDescriptionI18nId() {
        return (UUID) get(7);
    }

    /**
     * Setter for <code>public._audit_log_event.event_type</code>.
     */
    public _AuditLogEventRecord setEventType(@Nonnull EventTypeEnum value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.event_type</code>.
     */
    @NotNull
    @Nonnull
    public EventTypeEnum getEventType() {
        return (EventTypeEnum) get(8);
    }

    /**
     * Setter for <code>public._audit_log_event.start_date</code>.
     */
    public _AuditLogEventRecord setStartDate(@Nullable LocalDate value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.start_date</code>.
     */
    @Nullable
    public LocalDate getStartDate() {
        return (LocalDate) get(9);
    }

    /**
     * Setter for <code>public._audit_log_event.end_date</code>.
     */
    public _AuditLogEventRecord setEndDate(@Nullable LocalDate value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.end_date</code>.
     */
    @Nullable
    public LocalDate getEndDate() {
        return (LocalDate) get(10);
    }

    /**
     * Setter for <code>public._audit_log_event.start_time</code>.
     */
    public _AuditLogEventRecord setStartTime(@Nullable LocalTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.start_time</code>.
     */
    @Nullable
    public LocalTime getStartTime() {
        return (LocalTime) get(11);
    }

    /**
     * Setter for <code>public._audit_log_event.end_time</code>.
     */
    public _AuditLogEventRecord setEndTime(@Nullable LocalTime value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.end_time</code>.
     */
    @Nullable
    public LocalTime getEndTime() {
        return (LocalTime) get(12);
    }

    /**
     * Setter for <code>public._audit_log_event.max_number_of_student</code>.
     */
    public _AuditLogEventRecord setMaxNumberOfStudent(@Nullable BigDecimal value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.max_number_of_student</code>.
     */
    @Nullable
    public BigDecimal getMaxNumberOfStudent() {
        return (BigDecimal) get(13);
    }

    /**
     * Setter for <code>public._audit_log_event.fee</code>.
     */
    public _AuditLogEventRecord setFee(@Nullable BigDecimal value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.fee</code>.
     */
    @Nullable
    public BigDecimal getFee() {
        return (BigDecimal) get(14);
    }

    /**
     * Setter for <code>public._audit_log_event.poster_image</code>.
     */
    public _AuditLogEventRecord setPosterImage(@Nullable String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.poster_image</code>.
     */
    @Nullable
    public String getPosterImage() {
        return (String) get(15);
    }

    /**
     * Setter for <code>public._audit_log_event.price</code>.
     */
    public _AuditLogEventRecord setPrice(@Nullable BigDecimal value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.price</code>.
     */
    @Nullable
    public BigDecimal getPrice() {
        return (BigDecimal) get(16);
    }

    /**
     * Setter for <code>public._audit_log_event.created_on</code>.
     */
    public _AuditLogEventRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(17);
    }

    /**
     * Setter for <code>public._audit_log_event.created_by</code>.
     */
    public _AuditLogEventRecord setCreatedBy(@Nullable String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(18);
    }

    /**
     * Setter for <code>public._audit_log_event.updated_on</code>.
     */
    public _AuditLogEventRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(19);
    }

    /**
     * Setter for <code>public._audit_log_event.updated_by</code>.
     */
    public _AuditLogEventRecord setUpdatedBy(@Nullable String value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(20);
    }

    /**
     * Setter for <code>public._audit_log_event.educator_profile_id</code>.
     */
    public _AuditLogEventRecord setEducatorProfileId(@Nullable UUID value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.educator_profile_id</code>.
     */
    @Nullable
    public UUID getEducatorProfileId() {
        return (UUID) get(21);
    }

    /**
     * Setter for <code>public._audit_log_event.agenda_i18n_id</code>.
     */
    public _AuditLogEventRecord setAgendaI18nId(@Nullable UUID value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.agenda_i18n_id</code>.
     */
    @Nullable
    public UUID getAgendaI18nId() {
        return (UUID) get(22);
    }

    /**
     * Setter for <code>public._audit_log_event.event_status</code>.
     */
    public _AuditLogEventRecord setEventStatus(@Nullable EventStatusEnum value) {
        set(23, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.event_status</code>.
     */
    @Nullable
    public EventStatusEnum getEventStatus() {
        return (EventStatusEnum) get(23);
    }

    /**
     * Setter for <code>public._audit_log_event.academic_major_id</code>.
     */
    public _AuditLogEventRecord setAcademicMajorId(@Nullable UUID value) {
        set(24, value);
        return this;
    }

    /**
     * Getter for <code>public._audit_log_event.academic_major_id</code>.
     */
    @Nullable
    public UUID getAcademicMajorId() {
        return (UUID) get(24);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached _AuditLogEventRecord
     */
    public _AuditLogEventRecord() {
        super(_AuditLogEventTable._AUDIT_LOG_EVENT);
    }

    /**
     * Create a detached, initialised _AuditLogEventRecord
     */
    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "titleI18nId", "descriptionI18nId", "eventType", "startDate", "endDate", "startTime", "endTime", "maxNumberOfStudent", "fee", "posterImage", "price", "createdOn", "createdBy", "updatedOn", "updatedBy", "educatorProfileId", "agendaI18nId", "eventStatus", "academicMajorId" })
    public _AuditLogEventRecord(@Nonnull Long auditSeq, @Nonnull OffsetDateTime auditCreatedon, @Nonnull String auditCreatedby, @Nonnull String auditOperation, @Nonnull String auditType, @Nonnull UUID id, @Nullable UUID titleI18nId, @Nullable UUID descriptionI18nId, @Nonnull EventTypeEnum eventType, @Nullable LocalDate startDate, @Nullable LocalDate endDate, @Nullable LocalTime startTime, @Nullable LocalTime endTime, @Nullable BigDecimal maxNumberOfStudent, @Nullable BigDecimal fee, @Nullable String posterImage, @Nullable BigDecimal price, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable UUID educatorProfileId, @Nullable UUID agendaI18nId, @Nullable EventStatusEnum eventStatus, @Nullable UUID academicMajorId) {
        super(_AuditLogEventTable._AUDIT_LOG_EVENT);

        setAuditSeq(auditSeq);
        setAuditCreatedon(auditCreatedon);
        setAuditCreatedby(auditCreatedby);
        setAuditOperation(auditOperation);
        setAuditType(auditType);
        setId(id);
        setTitleI18nId(titleI18nId);
        setDescriptionI18nId(descriptionI18nId);
        setEventType(eventType);
        setStartDate(startDate);
        setEndDate(endDate);
        setStartTime(startTime);
        setEndTime(endTime);
        setMaxNumberOfStudent(maxNumberOfStudent);
        setFee(fee);
        setPosterImage(posterImage);
        setPrice(price);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
        setEducatorProfileId(educatorProfileId);
        setAgendaI18nId(agendaI18nId);
        setEventStatus(eventStatus);
        setAcademicMajorId(academicMajorId);
    }

    /**
     * Create a detached, initialised _AuditLogEventRecord
     */
    public _AuditLogEventRecord(_AuditLogEventPojo value) {
        super(_AuditLogEventTable._AUDIT_LOG_EVENT);

        if (value != null) {
            setAuditSeq(value.getAuditSeq());
            setAuditCreatedon(value.getAuditCreatedon());
            setAuditCreatedby(value.getAuditCreatedby());
            setAuditOperation(value.getAuditOperation());
            setAuditType(value.getAuditType());
            setId(value.getId());
            setTitleI18nId(value.getTitleI18nId());
            setDescriptionI18nId(value.getDescriptionI18nId());
            setEventType(value.getEventType());
            setStartDate(value.getStartDate());
            setEndDate(value.getEndDate());
            setStartTime(value.getStartTime());
            setEndTime(value.getEndTime());
            setMaxNumberOfStudent(value.getMaxNumberOfStudent());
            setFee(value.getFee());
            setPosterImage(value.getPosterImage());
            setPrice(value.getPrice());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
            setEducatorProfileId(value.getEducatorProfileId());
            setAgendaI18nId(value.getAgendaI18nId());
            setEventStatus(value.getEventStatus());
            setAcademicMajorId(value.getAcademicMajorId());
        }
    }
}
