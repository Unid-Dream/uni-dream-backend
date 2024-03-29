/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.SessionOpTypeEnum;
import unid.jooqMono.model.tables.SessionOpLogTable;
import unid.jooqMono.model.tables.pojos.SessionOpLogPojo;


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
public class SessionOpLogRecord extends UpdatableRecordImpl<SessionOpLogRecord> implements Record6<UUID, OffsetDateTime, UUID, BookingStatusEnum, SessionOpTypeEnum, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.session_op_log.id</code>.
     */
    public SessionOpLogRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.session_op_log.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.session_op_log.time_utc</code>.
     */
    public SessionOpLogRecord setTimeUtc(@Nullable OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.session_op_log.time_utc</code>.
     */
    @Nullable
    public OffsetDateTime getTimeUtc() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public.session_op_log.user_id</code>.
     */
    public SessionOpLogRecord setUserId(@Nullable UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.session_op_log.user_id</code>.
     */
    @Nullable
    public UUID getUserId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.session_op_log.status</code>.
     */
    public SessionOpLogRecord setStatus(@Nullable BookingStatusEnum value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.session_op_log.status</code>.
     */
    @Nullable
    public BookingStatusEnum getStatus() {
        return (BookingStatusEnum) get(3);
    }

    /**
     * Setter for <code>public.session_op_log.op_type</code>.
     */
    public SessionOpLogRecord setOpType(@Nullable SessionOpTypeEnum value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.session_op_log.op_type</code>.
     */
    @Nullable
    public SessionOpTypeEnum getOpType() {
        return (SessionOpTypeEnum) get(4);
    }

    /**
     * Setter for <code>public.session_op_log.transaction_id</code>.
     */
    public SessionOpLogRecord setTransactionId(@Nullable UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.session_op_log.transaction_id</code>.
     */
    @Nullable
    public UUID getTransactionId() {
        return (UUID) get(5);
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
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    @Nonnull
    public Row6<UUID, OffsetDateTime, UUID, BookingStatusEnum, SessionOpTypeEnum, UUID> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    @Nonnull
    public Row6<UUID, OffsetDateTime, UUID, BookingStatusEnum, SessionOpTypeEnum, UUID> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    @Nonnull
    public Field<UUID> field1() {
        return SessionOpLogTable.SESSION_OP_LOG.ID;
    }

    @Override
    @Nonnull
    public Field<OffsetDateTime> field2() {
        return SessionOpLogTable.SESSION_OP_LOG.TIME_UTC;
    }

    @Override
    @Nonnull
    public Field<UUID> field3() {
        return SessionOpLogTable.SESSION_OP_LOG.USER_ID;
    }

    @Override
    @Nonnull
    public Field<BookingStatusEnum> field4() {
        return SessionOpLogTable.SESSION_OP_LOG.STATUS;
    }

    @Override
    @Nonnull
    public Field<SessionOpTypeEnum> field5() {
        return SessionOpLogTable.SESSION_OP_LOG.OP_TYPE;
    }

    @Override
    @Nonnull
    public Field<UUID> field6() {
        return SessionOpLogTable.SESSION_OP_LOG.TRANSACTION_ID;
    }

    @Override
    @Nonnull
    public UUID component1() {
        return getId();
    }

    @Override
    @Nullable
    public OffsetDateTime component2() {
        return getTimeUtc();
    }

    @Override
    @Nullable
    public UUID component3() {
        return getUserId();
    }

    @Override
    @Nullable
    public BookingStatusEnum component4() {
        return getStatus();
    }

    @Override
    @Nullable
    public SessionOpTypeEnum component5() {
        return getOpType();
    }

    @Override
    @Nullable
    public UUID component6() {
        return getTransactionId();
    }

    @Override
    @Nonnull
    public UUID value1() {
        return getId();
    }

    @Override
    @Nullable
    public OffsetDateTime value2() {
        return getTimeUtc();
    }

    @Override
    @Nullable
    public UUID value3() {
        return getUserId();
    }

    @Override
    @Nullable
    public BookingStatusEnum value4() {
        return getStatus();
    }

    @Override
    @Nullable
    public SessionOpTypeEnum value5() {
        return getOpType();
    }

    @Override
    @Nullable
    public UUID value6() {
        return getTransactionId();
    }

    @Override
    @Nonnull
    public SessionOpLogRecord value1(@Nonnull UUID value) {
        setId(value);
        return this;
    }

    @Override
    @Nonnull
    public SessionOpLogRecord value2(@Nullable OffsetDateTime value) {
        setTimeUtc(value);
        return this;
    }

    @Override
    @Nonnull
    public SessionOpLogRecord value3(@Nullable UUID value) {
        setUserId(value);
        return this;
    }

    @Override
    @Nonnull
    public SessionOpLogRecord value4(@Nullable BookingStatusEnum value) {
        setStatus(value);
        return this;
    }

    @Override
    @Nonnull
    public SessionOpLogRecord value5(@Nullable SessionOpTypeEnum value) {
        setOpType(value);
        return this;
    }

    @Override
    @Nonnull
    public SessionOpLogRecord value6(@Nullable UUID value) {
        setTransactionId(value);
        return this;
    }

    @Override
    @Nonnull
    public SessionOpLogRecord values(@Nonnull UUID value1, @Nullable OffsetDateTime value2, @Nullable UUID value3, @Nullable BookingStatusEnum value4, @Nullable SessionOpTypeEnum value5, @Nullable UUID value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SessionOpLogRecord
     */
    public SessionOpLogRecord() {
        super(SessionOpLogTable.SESSION_OP_LOG);
    }

    /**
     * Create a detached, initialised SessionOpLogRecord
     */
    @ConstructorProperties({ "id", "timeUtc", "userId", "status", "opType", "transactionId" })
    public SessionOpLogRecord(@Nonnull UUID id, @Nullable OffsetDateTime timeUtc, @Nullable UUID userId, @Nullable BookingStatusEnum status, @Nullable SessionOpTypeEnum opType, @Nullable UUID transactionId) {
        super(SessionOpLogTable.SESSION_OP_LOG);

        setId(id);
        setTimeUtc(timeUtc);
        setUserId(userId);
        setStatus(status);
        setOpType(opType);
        setTransactionId(transactionId);
    }

    /**
     * Create a detached, initialised SessionOpLogRecord
     */
    public SessionOpLogRecord(SessionOpLogPojo value) {
        super(SessionOpLogTable.SESSION_OP_LOG);

        if (value != null) {
            setId(value.getId());
            setTimeUtc(value.getTimeUtc());
            setUserId(value.getUserId());
            setStatus(value.getStatus());
            setOpType(value.getOpType());
            setTransactionId(value.getTransactionId());
        }
    }
}
