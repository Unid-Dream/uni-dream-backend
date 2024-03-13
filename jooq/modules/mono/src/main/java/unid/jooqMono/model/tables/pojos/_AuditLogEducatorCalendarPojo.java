/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;

import unid.jooqMono.model.enums.BookingStatusEnum;


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
public class _AuditLogEducatorCalendarPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              auditSeq;
    private OffsetDateTime    auditCreatedon;
    private String            auditCreatedby;
    private String            auditOperation;
    private String            auditType;
    private UUID              id;
    private UUID              educatorProfileId;
    private LocalDate         date;
    private LocalTime         hourStart;
    private LocalTime         hourEnd;
    private BookingStatusEnum bookingStatus;
    private UUID              paymentTransactionId;
    private String            meetingUrl;
    private String            meetingId;
    private OffsetDateTime    createdOn;
    private String            createdBy;
    private OffsetDateTime    updatedOn;
    private String            updatedBy;

    public _AuditLogEducatorCalendarPojo() {}

    public _AuditLogEducatorCalendarPojo(_AuditLogEducatorCalendarPojo value) {
        this.auditSeq = value.auditSeq;
        this.auditCreatedon = value.auditCreatedon;
        this.auditCreatedby = value.auditCreatedby;
        this.auditOperation = value.auditOperation;
        this.auditType = value.auditType;
        this.id = value.id;
        this.educatorProfileId = value.educatorProfileId;
        this.date = value.date;
        this.hourStart = value.hourStart;
        this.hourEnd = value.hourEnd;
        this.bookingStatus = value.bookingStatus;
        this.paymentTransactionId = value.paymentTransactionId;
        this.meetingUrl = value.meetingUrl;
        this.meetingId = value.meetingId;
        this.createdOn = value.createdOn;
        this.createdBy = value.createdBy;
        this.updatedOn = value.updatedOn;
        this.updatedBy = value.updatedBy;
    }

    @ConstructorProperties({ "auditSeq", "auditCreatedon", "auditCreatedby", "auditOperation", "auditType", "id", "educatorProfileId", "date", "hourStart", "hourEnd", "bookingStatus", "paymentTransactionId", "meetingUrl", "meetingId", "createdOn", "createdBy", "updatedOn", "updatedBy" })
    public _AuditLogEducatorCalendarPojo(
        @Nonnull Long              auditSeq,
        @Nonnull OffsetDateTime    auditCreatedon,
        @Nonnull String            auditCreatedby,
        @Nonnull String            auditOperation,
        @Nonnull String            auditType,
        @Nonnull UUID              id,
        @Nullable UUID              educatorProfileId,
        @Nullable LocalDate         date,
        @Nullable LocalTime         hourStart,
        @Nullable LocalTime         hourEnd,
        @Nullable BookingStatusEnum bookingStatus,
        @Nullable UUID              paymentTransactionId,
        @Nullable String            meetingUrl,
        @Nullable String            meetingId,
        @Nullable OffsetDateTime    createdOn,
        @Nullable String            createdBy,
        @Nullable OffsetDateTime    updatedOn,
        @Nullable String            updatedBy
    ) {
        this.auditSeq = auditSeq;
        this.auditCreatedon = auditCreatedon;
        this.auditCreatedby = auditCreatedby;
        this.auditOperation = auditOperation;
        this.auditType = auditType;
        this.id = id;
        this.educatorProfileId = educatorProfileId;
        this.date = date;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.bookingStatus = bookingStatus;
        this.paymentTransactionId = paymentTransactionId;
        this.meetingUrl = meetingUrl;
        this.meetingId = meetingId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.audit_seq</code>.
     */
    @Nonnull
    public Long getAuditSeq() {
        return this.auditSeq;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.audit_seq</code>.
     */
    public _AuditLogEducatorCalendarPojo setAuditSeq(@Nonnull Long auditSeq) {
        this.auditSeq = auditSeq;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_calendar.audit_createdon</code>.
     */
    @Nonnull
    public OffsetDateTime getAuditCreatedon() {
        return this.auditCreatedon;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_calendar.audit_createdon</code>.
     */
    public _AuditLogEducatorCalendarPojo setAuditCreatedon(@Nonnull OffsetDateTime auditCreatedon) {
        this.auditCreatedon = auditCreatedon;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_calendar.audit_createdby</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditCreatedby() {
        return this.auditCreatedby;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_calendar.audit_createdby</code>.
     */
    public _AuditLogEducatorCalendarPojo setAuditCreatedby(@Nonnull String auditCreatedby) {
        this.auditCreatedby = auditCreatedby;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_calendar.audit_operation</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditOperation() {
        return this.auditOperation;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_calendar.audit_operation</code>.
     */
    public _AuditLogEducatorCalendarPojo setAuditOperation(@Nonnull String auditOperation) {
        this.auditOperation = auditOperation;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.audit_type</code>.
     */
    @NotNull
    @Nonnull
    public String getAuditType() {
        return this.auditType;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.audit_type</code>.
     */
    public _AuditLogEducatorCalendarPojo setAuditType(@Nonnull String auditType) {
        this.auditType = auditType;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.id</code>.
     */
    public _AuditLogEducatorCalendarPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_calendar.educator_profile_id</code>.
     */
    @Nullable
    public UUID getEducatorProfileId() {
        return this.educatorProfileId;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_calendar.educator_profile_id</code>.
     */
    public _AuditLogEducatorCalendarPojo setEducatorProfileId(@Nullable UUID educatorProfileId) {
        this.educatorProfileId = educatorProfileId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.date</code>.
     */
    @Nullable
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.date</code>.
     */
    public _AuditLogEducatorCalendarPojo setDate(@Nullable LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.hour_start</code>.
     */
    @Nullable
    public LocalTime getHourStart() {
        return this.hourStart;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.hour_start</code>.
     */
    public _AuditLogEducatorCalendarPojo setHourStart(@Nullable LocalTime hourStart) {
        this.hourStart = hourStart;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.hour_end</code>.
     */
    @Nullable
    public LocalTime getHourEnd() {
        return this.hourEnd;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.hour_end</code>.
     */
    public _AuditLogEducatorCalendarPojo setHourEnd(@Nullable LocalTime hourEnd) {
        this.hourEnd = hourEnd;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_calendar.booking_status</code>.
     */
    @Nullable
    public BookingStatusEnum getBookingStatus() {
        return this.bookingStatus;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_calendar.booking_status</code>.
     */
    public _AuditLogEducatorCalendarPojo setBookingStatus(@Nullable BookingStatusEnum bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }

    /**
     * Getter for
     * <code>public._audit_log_educator_calendar.payment_transaction_id</code>.
     */
    @Nullable
    public UUID getPaymentTransactionId() {
        return this.paymentTransactionId;
    }

    /**
     * Setter for
     * <code>public._audit_log_educator_calendar.payment_transaction_id</code>.
     */
    public _AuditLogEducatorCalendarPojo setPaymentTransactionId(@Nullable UUID paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.meeting_url</code>.
     */
    @Nullable
    public String getMeetingUrl() {
        return this.meetingUrl;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.meeting_url</code>.
     */
    public _AuditLogEducatorCalendarPojo setMeetingUrl(@Nullable String meetingUrl) {
        this.meetingUrl = meetingUrl;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.meeting_id</code>.
     */
    @Nullable
    public String getMeetingId() {
        return this.meetingId;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.meeting_id</code>.
     */
    public _AuditLogEducatorCalendarPojo setMeetingId(@Nullable String meetingId) {
        this.meetingId = meetingId;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.created_on</code>.
     */
    public _AuditLogEducatorCalendarPojo setCreatedOn(@Nullable OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.created_by</code>.
     */
    public _AuditLogEducatorCalendarPojo setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.updated_on</code>.
     */
    public _AuditLogEducatorCalendarPojo setUpdatedOn(@Nullable OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    /**
     * Getter for <code>public._audit_log_educator_calendar.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Setter for <code>public._audit_log_educator_calendar.updated_by</code>.
     */
    public _AuditLogEducatorCalendarPojo setUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("_AuditLogEducatorCalendarPojo (");

        sb.append(auditSeq);
        sb.append(", ").append(auditCreatedon);
        sb.append(", ").append(auditCreatedby);
        sb.append(", ").append(auditOperation);
        sb.append(", ").append(auditType);
        sb.append(", ").append(id);
        sb.append(", ").append(educatorProfileId);
        sb.append(", ").append(date);
        sb.append(", ").append(hourStart);
        sb.append(", ").append(hourEnd);
        sb.append(", ").append(bookingStatus);
        sb.append(", ").append(paymentTransactionId);
        sb.append(", ").append(meetingUrl);
        sb.append(", ").append(meetingId);
        sb.append(", ").append(createdOn);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedOn);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }
}
