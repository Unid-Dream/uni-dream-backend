DO
$$
    BEGIN
        -- evet
        CREATE TABLE "event"
        (
            id                    UUID DEFAULT gen_random_uuid(),
            title_i18n_id         UUID UNIQUE,
            description_i18n_id   UUID UNIQUE,
            event_type            "event_type" NOT NULL,

            start_date            DATE,
            end_date              DATE,

            start_time            TIME,
            end_time              TIME,

            max_number_of_student NUMERIC,
            fee                   NUMERIC,

            poster_image          TEXT,

            price                 NUMERIC,

            PRIMARY KEY (id),
            CONSTRAINT fk_evet_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_evet_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('event');

        ALTER TABLE "event"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "event" TO duser_data_mono;

        PERFORM
            create_audit_for_table('event');

        ALTER TABLE "_audit_log_event"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_event" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;