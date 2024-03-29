/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.tables.EducatorCalendarTable;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;


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
public class EducatorCalendarRecord extends UpdatableRecordImpl<EducatorCalendarRecord> implements Record15<UUID, UUID, LocalDate, LocalTime, LocalTime, BookingStatusEnum, UUID, String, String, OffsetDateTime, String, OffsetDateTime, String, OffsetDateTime, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.educator_calendar.id</code>.
     */
    public EducatorCalendarRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.educator_calendar.educator_profile_id</code>.
     */
    public EducatorCalendarRecord setEducatorProfileId(@Nullable UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.educator_profile_id</code>.
     */
    @Nullable
    public UUID getEducatorProfileId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.educator_calendar.date</code>.
     */
    public EducatorCalendarRecord setDate(@Nullable LocalDate value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.date</code>.
     */
    @Nullable
    public LocalDate getDate() {
        return (LocalDate) get(2);
    }

    /**
     * Setter for <code>public.educator_calendar.hour_start</code>.
     */
    public EducatorCalendarRecord setHourStart(@Nullable LocalTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.hour_start</code>.
     */
    @Nullable
    public LocalTime getHourStart() {
        return (LocalTime) get(3);
    }

    /**
     * Setter for <code>public.educator_calendar.hour_end</code>.
     */
    public EducatorCalendarRecord setHourEnd(@Nullable LocalTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.hour_end</code>.
     */
    @Nullable
    public LocalTime getHourEnd() {
        return (LocalTime) get(4);
    }

    /**
     * Setter for <code>public.educator_calendar.booking_status</code>.
     */
    public EducatorCalendarRecord setBookingStatus(@Nullable BookingStatusEnum value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.booking_status</code>.
     */
    @Nullable
    public BookingStatusEnum getBookingStatus() {
        return (BookingStatusEnum) get(5);
    }

    /**
     * Setter for <code>public.educator_calendar.payment_transaction_id</code>.
     */
    public EducatorCalendarRecord setPaymentTransactionId(@Nullable UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.payment_transaction_id</code>.
     */
    @Nullable
    public UUID getPaymentTransactionId() {
        return (UUID) get(6);
    }

    /**
     * Setter for <code>public.educator_calendar.meeting_url</code>.
     */
    public EducatorCalendarRecord setMeetingUrl(@Nullable String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.meeting_url</code>.
     */
    @Nullable
    public String getMeetingUrl() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.educator_calendar.meeting_id</code>.
     */
    public EducatorCalendarRecord setMeetingId(@Nullable String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.meeting_id</code>.
     */
    @Nullable
    public String getMeetingId() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.educator_calendar.created_on</code>.
     */
    public EducatorCalendarRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(9);
    }

    /**
     * Setter for <code>public.educator_calendar.created_by</code>.
     */
    public EducatorCalendarRecord setCreatedBy(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.educator_calendar.updated_on</code>.
     */
    public EducatorCalendarRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(11);
    }

    /**
     * Setter for <code>public.educator_calendar.updated_by</code>.
     */
    public EducatorCalendarRecord setUpdatedBy(@Nullable String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.educator_calendar.start_time_utc</code>.
     */
    public EducatorCalendarRecord setStartTimeUtc(@Nullable OffsetDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.start_time_utc</code>.
     */
    @Nullable
    public OffsetDateTime getStartTimeUtc() {
        return (OffsetDateTime) get(13);
    }

    /**
     * Setter for <code>public.educator_calendar.end_time_utc</code>.
     */
    public EducatorCalendarRecord setEndTimeUtc(@Nullable OffsetDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>public.educator_calendar.end_time_utc</code>.
     */
    @Nullable
    public OffsetDateTime getEndTimeUtc() {
        return (OffsetDateTime) get(14);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row15<UUID, UUID, LocalDate, LocalTime, LocalTime, BookingStatusEnum, UUID, String, String, OffsetDateTime, String, OffsetDateTime, String, OffsetDateTime, OffsetDateTime> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row15<UUID, UUID, LocalDate, LocalTime, LocalTime, BookingStatusEnum, UUID, String, String, OffsetDateTime, String, OffsetDateTime, String, OffsetDateTime, OffsetDateTime> valuesRow() {
        return (Row15) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.ID;
    }

    @Override
    @Nonnull
    public Field<UUID> field2() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.EDUCATOR_PROFILE_ID;
    }

    @Override
    @Nonnull
    public Field<LocalDate> field3() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.DATE;
    }

    @Override
    @Nonnull
    public Field<LocalTime> field4() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.HOUR_START;
    }

    @Override
    @Nonnull
    public Field<LocalTime> field5() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.HOUR_END;
    }

    @Override
    @Nonnull
    public Field<BookingStatusEnum> field6() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.BOOKING_STATUS;
    }

    @Override
    @Nonnull
    public Field<UUID> field7() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.PAYMENT_TRANSACTION_ID;
    }

    @Override
    @Nonnull
    public Field<String> field8() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.MEETING_URL;
    }

    @Override
    @Nonnull
    public Field<String> field9() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.MEETING_ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field10() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.CREATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field11() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.CREATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field12() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.UPDATED_ON;
    }

    @Override
    @Nonnull
    public Field<String> field13() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.UPDATED_BY;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field14() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.START_TIME_UTC;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field15() {
        return EducatorCalendarTable.EDUCATOR_CALENDAR.END_TIME_UTC;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID component2() {
        return getEducatorProfileId();
    }

    @Override
    @Nullable
    public LocalDate component3() {
        return getDate();
    }

    @Override
    @Nullable
    public LocalTime component4() {
        return getHourStart();
    }

    @Override
    @Nullable
    public LocalTime component5() {
        return getHourEnd();
    }

    @Override
    @Nullable
    public BookingStatusEnum component6() {
        return getBookingStatus();
    }

    @Override
    @Nullable
    public UUID component7() {
        return getPaymentTransactionId();
    }

    @Override
    @Nullable
    public String component8() {
        return getMeetingUrl();
    }

    @Override
    @Nullable
    public String component9() {
        return getMeetingId();
    }

    @Override
    @Nullable
    public OffsetDateTime component10() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String component11() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component12() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String component13() {
        return getUpdatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime component14() {
        return getStartTimeUtc();
    }

    @Override
    @Nullable
    public OffsetDateTime component15() {
        return getEndTimeUtc();
    }

    @Override
    @Nonnull
    public UUID value1() {
        return getId();
    }

    @Override
    @Nullable
    public UUID value2() {
        return getEducatorProfileId();
    }

    @Override
    @Nullable
    public LocalDate value3() {
        return getDate();
    }

    @Override
    @Nullable
    public LocalTime value4() {
        return getHourStart();
    }

    @Override
    @Nullable
    public LocalTime value5() {
        return getHourEnd();
    }

    @Override
    @Nullable
    public BookingStatusEnum value6() {
        return getBookingStatus();
    }

    @Override
    @Nullable
    public UUID value7() {
        return getPaymentTransactionId();
    }

    @Override
    @Nullable
    public String value8() {
        return getMeetingUrl();
    }

    @Override
    @Nullable
    public String value9() {
        return getMeetingId();
    }

    @Override
    @Nullable
    public OffsetDateTime value10() {
        return getCreatedOn();
    }

    @Override
    @Nullable
    public String value11() {
        return getCreatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value12() {
        return getUpdatedOn();
    }

    @Override
    @Nullable
    public String value13() {
        return getUpdatedBy();
    }

    @Override
    @Nullable
    public OffsetDateTime value14() {
        return getStartTimeUtc();
    }

    @Override
    @Nullable
    public OffsetDateTime value15() {
        return getEndTimeUtc();
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value2(@Nullable UUID value) {
        setEducatorProfileId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value3(@Nullable LocalDate value) {
        setDate(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value4(@Nullable LocalTime value) {
        setHourStart(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value5(@Nullable LocalTime value) {
        setHourEnd(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value6(@Nullable BookingStatusEnum value) {
        setBookingStatus(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value7(@Nullable UUID value) {
        setPaymentTransactionId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value8(@Nullable String value) {
        setMeetingUrl(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value9(@Nullable String value) {
        setMeetingId(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value10(@Nullable OffsetDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value11(@Nullable String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value12(@Nullable OffsetDateTime value) {
        setUpdatedOn(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value13(@Nullable String value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value14(@Nullable OffsetDateTime value) {
        setStartTimeUtc(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord value15(@Nullable OffsetDateTime value) {
        setEndTimeUtc(value);
        return this;
    }

    @Override
    @Nonnull
    public EducatorCalendarRecord values(@Nonnull UUID value1, @Nullable UUID value2, @Nullable LocalDate value3, @Nullable LocalTime value4, @Nullable LocalTime value5, @Nullable BookingStatusEnum value6, @Nullable UUID value7, @Nullable String value8, @Nullable String value9, @Nullable OffsetDateTime value10, @Nullable String value11, @Nullable OffsetDateTime value12, @Nullable String value13, @Nullable OffsetDateTime value14, @Nullable OffsetDateTime value15) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EducatorCalendarRecord
     */
    public EducatorCalendarRecord() {
        super(EducatorCalendarTable.EDUCATOR_CALENDAR);
    }

    /**
     * Create a detached, initialised EducatorCalendarRecord
     */
    @ConstructorProperties({ "id", "educatorProfileId", "date", "hourStart", "hourEnd", "bookingStatus", "paymentTransactionId", "meetingUrl", "meetingId", "createdOn", "createdBy", "updatedOn", "updatedBy", "startTimeUtc", "endTimeUtc" })
    public EducatorCalendarRecord(@Nonnull UUID id, @Nullable UUID educatorProfileId, @Nullable LocalDate date, @Nullable LocalTime hourStart, @Nullable LocalTime hourEnd, @Nullable BookingStatusEnum bookingStatus, @Nullable UUID paymentTransactionId, @Nullable String meetingUrl, @Nullable String meetingId, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable OffsetDateTime startTimeUtc, @Nullable OffsetDateTime endTimeUtc) {
        super(EducatorCalendarTable.EDUCATOR_CALENDAR);

        setId(id);
        setEducatorProfileId(educatorProfileId);
        setDate(date);
        setHourStart(hourStart);
        setHourEnd(hourEnd);
        setBookingStatus(bookingStatus);
        setPaymentTransactionId(paymentTransactionId);
        setMeetingUrl(meetingUrl);
        setMeetingId(meetingId);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
        setStartTimeUtc(startTimeUtc);
        setEndTimeUtc(endTimeUtc);
    }

    /**
     * Create a detached, initialised EducatorCalendarRecord
     */
    public EducatorCalendarRecord(EducatorCalendarPojo value) {
        super(EducatorCalendarTable.EDUCATOR_CALENDAR);

        if (value != null) {
            setId(value.getId());
            setEducatorProfileId(value.getEducatorProfileId());
            setDate(value.getDate());
            setHourStart(value.getHourStart());
            setHourEnd(value.getHourEnd());
            setBookingStatus(value.getBookingStatus());
            setPaymentTransactionId(value.getPaymentTransactionId());
            setMeetingUrl(value.getMeetingUrl());
            setMeetingId(value.getMeetingId());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
            setStartTimeUtc(value.getStartTimeUtc());
            setEndTimeUtc(value.getEndTimeUtc());
        }
    }
}
