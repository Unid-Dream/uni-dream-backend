DO
$$
    BEGIN
        -- educ_cald
        CREATE TABLE "educator_calendar"
        (
            id                           UUID             DEFAULT gen_random_uuid(),

            educator_profile_id          UUID NOT NULL,
            "date"                       DATE NOT NULL,
            "hour_start"                 TIME NOT NULL, -- i.e. 00:00, 01:00 ... 23:00
            "hour_end"                   TIME NOT NULL,

            booking_status               "booking_status" DEFAULT 'AVAILABLE',

            payment_transaction_id UUID UNIQUE,
            meeting_url TEXT,
            meeting_id TEXT,

            PRIMARY KEY ("id"),
            CONSTRAINT fk_educ_cald_educ_prof
                FOREIGN KEY (educator_profile_id)
                    REFERENCES "educator_profile" (id),
            CONSTRAINT fk_educ_cald_stud_tran
                FOREIGN KEY (payment_transaction_id)
                    REFERENCES "student_payment_transaction" (id)
        );

        PERFORM
            finalise_table('educator_calendar');

        ALTER TABLE "educator_calendar"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "educator_calendar" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_calendar');

        ALTER TABLE "_audit_log_educator_calendar"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_calendar" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;