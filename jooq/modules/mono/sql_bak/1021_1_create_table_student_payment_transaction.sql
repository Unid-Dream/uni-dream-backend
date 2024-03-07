DO
$$
    BEGIN
        -- stud_paym_tran
        CREATE TABLE "student_payment_transaction"
        (
            id                                     UUID             DEFAULT gen_random_uuid(),
            student_profile_id                     UUID             NOT NULL,

            pricing_id                             UUID             NOT NULL,

            payment_method                         "payment_method" NOT NULL,
            payment_status                         "payment_status" DEFAULT 'UNPAID',
            payment_amount                         NUMERIC          NOT NULL,
            payment_currency                       "currency"       NOT NULL,
            payment_method_transaction_ref_id      TEXT,
            payment_method_transaction_ref_account TEXT,
            payment_method_transaction_datetime    TIMESTAMPTZ      NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_paym_tran_stud_prof
                FOREIGN KEY (student_profile_id)
                    REFERENCES "student_profile" (id),
            CONSTRAINT fk_stud_paym_tran_pric
                FOREIGN KEY (pricing_id)
                    REFERENCES "pricing" (id)
        );

        PERFORM
            finalise_table('student_payment_transaction');

        ALTER TABLE "student_payment_transaction"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_payment_transaction" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_payment_transaction');

        ALTER TABLE "_audit_log_student_payment_transaction"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_payment_transaction" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;