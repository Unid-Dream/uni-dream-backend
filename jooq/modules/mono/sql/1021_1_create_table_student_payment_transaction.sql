DO
$$
    BEGIN
        -- stud_paym_tran
        CREATE TABLE "student_payment_transaction"
        (
            id                   UUID DEFAULT gen_random_uuid(),
            student_profile_id   UUID                       NOT NULL,

            transaction_amount   NUMERIC                    NOT NULL,
            transaction_currency "currency"                 NOT NULL,

            transaction_item     "student_transaction_item" NOT NULL,
            transaction_item_ref_id UUID,
            transaction_personnel_ref_id UUID,

            payment_method       "payment_method",
            payment_status       "payment_status"           NOT NULL,

            cod_order_ref        TEXT,
            cod_out_trade_no     TEXT,
            cod_ref_id           TEXT,
            cod_transaction_id   TEXT,
            cod_wallet           TEXT,
            cod_expiry           TIMESTAMPTZ,
            cod_payment_url      TEXT,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_paym_tran_stud_prof
                FOREIGN KEY (student_profile_id)
                    REFERENCES "student_profile" (id)
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