/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.records;


import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.PaymentMethodEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;


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
public class StudentPaymentTransactionRecord extends UpdatableRecordImpl<StudentPaymentTransactionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.student_payment_transaction.id</code>.
     */
    public StudentPaymentTransactionRecord setId(@Nonnull UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.student_profile_id</code>.
     */
    public StudentPaymentTransactionRecord setStudentProfileId(@Nonnull UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.student_profile_id</code>.
     */
    @NotNull
    @Nonnull
    public UUID getStudentProfileId() {
        return (UUID) get(1);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.transaction_amount</code>.
     */
    public StudentPaymentTransactionRecord setTransactionAmount(@Nonnull BigDecimal value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.transaction_amount</code>.
     */
    @NotNull
    @Nonnull
    public BigDecimal getTransactionAmount() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.transaction_currency</code>.
     */
    public StudentPaymentTransactionRecord setTransactionCurrency(@Nonnull CurrencyEnum value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.transaction_currency</code>.
     */
    @NotNull
    @Nonnull
    public CurrencyEnum getTransactionCurrency() {
        return (CurrencyEnum) get(3);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.transaction_item</code>.
     */
    public StudentPaymentTransactionRecord setTransactionItem(@Nonnull StudentTransactionItemEnum value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.transaction_item</code>.
     */
    @NotNull
    @Nonnull
    public StudentTransactionItemEnum getTransactionItem() {
        return (StudentTransactionItemEnum) get(4);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.transaction_item_ref_id</code>.
     */
    public StudentPaymentTransactionRecord setTransactionItemRefId(@Nullable UUID value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.transaction_item_ref_id</code>.
     */
    @Nullable
    public UUID getTransactionItemRefId() {
        return (UUID) get(5);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.transaction_personnel_ref_id</code>.
     */
    public StudentPaymentTransactionRecord setTransactionPersonnelRefId(@Nullable UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.transaction_personnel_ref_id</code>.
     */
    @Nullable
    public UUID getTransactionPersonnelRefId() {
        return (UUID) get(6);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.payment_method</code>.
     */
    public StudentPaymentTransactionRecord setPaymentMethod(@Nullable PaymentMethodEnum value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.payment_method</code>.
     */
    @Nullable
    public PaymentMethodEnum getPaymentMethod() {
        return (PaymentMethodEnum) get(7);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.payment_status</code>.
     */
    public StudentPaymentTransactionRecord setPaymentStatus(@Nonnull PaymentStatusEnum value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.payment_status</code>.
     */
    @NotNull
    @Nonnull
    public PaymentStatusEnum getPaymentStatus() {
        return (PaymentStatusEnum) get(8);
    }

    /**
     * Setter for <code>public.student_payment_transaction.cod_order_ref</code>.
     */
    public StudentPaymentTransactionRecord setCodOrderRef(@Nullable String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.cod_order_ref</code>.
     */
    @Nullable
    public String getCodOrderRef() {
        return (String) get(9);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.cod_out_trade_no</code>.
     */
    public StudentPaymentTransactionRecord setCodOutTradeNo(@Nullable String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.cod_out_trade_no</code>.
     */
    @Nullable
    public String getCodOutTradeNo() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.student_payment_transaction.cod_ref_id</code>.
     */
    public StudentPaymentTransactionRecord setCodRefId(@Nullable String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.cod_ref_id</code>.
     */
    @Nullable
    public String getCodRefId() {
        return (String) get(11);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.cod_transaction_id</code>.
     */
    public StudentPaymentTransactionRecord setCodTransactionId(@Nullable String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.cod_transaction_id</code>.
     */
    @Nullable
    public String getCodTransactionId() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.student_payment_transaction.cod_wallet</code>.
     */
    public StudentPaymentTransactionRecord setCodWallet(@Nullable String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.cod_wallet</code>.
     */
    @Nullable
    public String getCodWallet() {
        return (String) get(13);
    }

    /**
     * Setter for <code>public.student_payment_transaction.cod_expiry</code>.
     */
    public StudentPaymentTransactionRecord setCodExpiry(@Nullable OffsetDateTime value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.cod_expiry</code>.
     */
    @Nullable
    public OffsetDateTime getCodExpiry() {
        return (OffsetDateTime) get(14);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.cod_payment_url</code>.
     */
    public StudentPaymentTransactionRecord setCodPaymentUrl(@Nullable String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.cod_payment_url</code>.
     */
    @Nullable
    public String getCodPaymentUrl() {
        return (String) get(15);
    }

    /**
     * Setter for <code>public.student_payment_transaction.created_on</code>.
     */
    public StudentPaymentTransactionRecord setCreatedOn(@Nullable OffsetDateTime value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.created_on</code>.
     */
    @Nullable
    public OffsetDateTime getCreatedOn() {
        return (OffsetDateTime) get(16);
    }

    /**
     * Setter for <code>public.student_payment_transaction.created_by</code>.
     */
    public StudentPaymentTransactionRecord setCreatedBy(@Nullable String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.created_by</code>.
     */
    @Nullable
    public String getCreatedBy() {
        return (String) get(17);
    }

    /**
     * Setter for <code>public.student_payment_transaction.updated_on</code>.
     */
    public StudentPaymentTransactionRecord setUpdatedOn(@Nullable OffsetDateTime value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.updated_on</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedOn() {
        return (OffsetDateTime) get(18);
    }

    /**
     * Setter for <code>public.student_payment_transaction.updated_by</code>.
     */
    public StudentPaymentTransactionRecord setUpdatedBy(@Nullable String value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.updated_by</code>.
     */
    @Nullable
    public String getUpdatedBy() {
        return (String) get(19);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.transaction_submit_time</code>.
     */
    public StudentPaymentTransactionRecord setTransactionSubmitTime(@Nullable LocalDateTime value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.transaction_submit_time</code>.
     */
    @Nullable
    public LocalDateTime getTransactionSubmitTime() {
        return (LocalDateTime) get(20);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.process_status</code>.
     */
    public StudentPaymentTransactionRecord setProcessStatus(@Nullable BookingStatusEnum value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.process_status</code>.
     */
    @Nullable
    public BookingStatusEnum getProcessStatus() {
        return (BookingStatusEnum) get(21);
    }

    /**
     * Setter for <code>public.student_payment_transaction.reject_reason</code>.
     */
    public StudentPaymentTransactionRecord setRejectReason(@Nullable String value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>public.student_payment_transaction.reject_reason</code>.
     */
    @Nullable
    public String getRejectReason() {
        return (String) get(22);
    }

    /**
     * Setter for
     * <code>public.student_payment_transaction.transaction_serial_number</code>.
     */
    public StudentPaymentTransactionRecord setTransactionSerialNumber(@Nullable String value) {
        set(23, value);
        return this;
    }

    /**
     * Getter for
     * <code>public.student_payment_transaction.transaction_serial_number</code>.
     */
    @Size(max = 200)
    @Nullable
    public String getTransactionSerialNumber() {
        return (String) get(23);
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
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StudentPaymentTransactionRecord
     */
    public StudentPaymentTransactionRecord() {
        super(StudentPaymentTransactionTable.STUDENT_PAYMENT_TRANSACTION);
    }

    /**
     * Create a detached, initialised StudentPaymentTransactionRecord
     */
    @ConstructorProperties({ "id", "studentProfileId", "transactionAmount", "transactionCurrency", "transactionItem", "transactionItemRefId", "transactionPersonnelRefId", "paymentMethod", "paymentStatus", "codOrderRef", "codOutTradeNo", "codRefId", "codTransactionId", "codWallet", "codExpiry", "codPaymentUrl", "createdOn", "createdBy", "updatedOn", "updatedBy", "transactionSubmitTime", "processStatus", "rejectReason", "transactionSerialNumber" })
    public StudentPaymentTransactionRecord(@Nonnull UUID id, @Nonnull UUID studentProfileId, @Nonnull BigDecimal transactionAmount, @Nonnull CurrencyEnum transactionCurrency, @Nonnull StudentTransactionItemEnum transactionItem, @Nullable UUID transactionItemRefId, @Nullable UUID transactionPersonnelRefId, @Nullable PaymentMethodEnum paymentMethod, @Nonnull PaymentStatusEnum paymentStatus, @Nullable String codOrderRef, @Nullable String codOutTradeNo, @Nullable String codRefId, @Nullable String codTransactionId, @Nullable String codWallet, @Nullable OffsetDateTime codExpiry, @Nullable String codPaymentUrl, @Nullable OffsetDateTime createdOn, @Nullable String createdBy, @Nullable OffsetDateTime updatedOn, @Nullable String updatedBy, @Nullable LocalDateTime transactionSubmitTime, @Nullable BookingStatusEnum processStatus, @Nullable String rejectReason, @Nullable String transactionSerialNumber) {
        super(StudentPaymentTransactionTable.STUDENT_PAYMENT_TRANSACTION);

        setId(id);
        setStudentProfileId(studentProfileId);
        setTransactionAmount(transactionAmount);
        setTransactionCurrency(transactionCurrency);
        setTransactionItem(transactionItem);
        setTransactionItemRefId(transactionItemRefId);
        setTransactionPersonnelRefId(transactionPersonnelRefId);
        setPaymentMethod(paymentMethod);
        setPaymentStatus(paymentStatus);
        setCodOrderRef(codOrderRef);
        setCodOutTradeNo(codOutTradeNo);
        setCodRefId(codRefId);
        setCodTransactionId(codTransactionId);
        setCodWallet(codWallet);
        setCodExpiry(codExpiry);
        setCodPaymentUrl(codPaymentUrl);
        setCreatedOn(createdOn);
        setCreatedBy(createdBy);
        setUpdatedOn(updatedOn);
        setUpdatedBy(updatedBy);
        setTransactionSubmitTime(transactionSubmitTime);
        setProcessStatus(processStatus);
        setRejectReason(rejectReason);
        setTransactionSerialNumber(transactionSerialNumber);
    }

    /**
     * Create a detached, initialised StudentPaymentTransactionRecord
     */
    public StudentPaymentTransactionRecord(StudentPaymentTransactionPojo value) {
        super(StudentPaymentTransactionTable.STUDENT_PAYMENT_TRANSACTION);

        if (value != null) {
            setId(value.getId());
            setStudentProfileId(value.getStudentProfileId());
            setTransactionAmount(value.getTransactionAmount());
            setTransactionCurrency(value.getTransactionCurrency());
            setTransactionItem(value.getTransactionItem());
            setTransactionItemRefId(value.getTransactionItemRefId());
            setTransactionPersonnelRefId(value.getTransactionPersonnelRefId());
            setPaymentMethod(value.getPaymentMethod());
            setPaymentStatus(value.getPaymentStatus());
            setCodOrderRef(value.getCodOrderRef());
            setCodOutTradeNo(value.getCodOutTradeNo());
            setCodRefId(value.getCodRefId());
            setCodTransactionId(value.getCodTransactionId());
            setCodWallet(value.getCodWallet());
            setCodExpiry(value.getCodExpiry());
            setCodPaymentUrl(value.getCodPaymentUrl());
            setCreatedOn(value.getCreatedOn());
            setCreatedBy(value.getCreatedBy());
            setUpdatedOn(value.getUpdatedOn());
            setUpdatedBy(value.getUpdatedBy());
            setTransactionSubmitTime(value.getTransactionSubmitTime());
            setProcessStatus(value.getProcessStatus());
            setRejectReason(value.getRejectReason());
            setTransactionSerialNumber(value.getTransactionSerialNumber());
        }
    }
}
