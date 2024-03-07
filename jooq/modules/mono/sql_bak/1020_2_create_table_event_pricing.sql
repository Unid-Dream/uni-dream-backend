DO
$$
    BEGIN
        -- evet_pric
        CREATE TABLE "event_pricing"
        (
            id         UUID DEFAULT gen_random_uuid(),
            pricing_id UUID NOT NULL,
            event_id   UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_evet_pric
                UNIQUE (pricing_id, event_id),
            CONSTRAINT fk_evet_pric_pric
                FOREIGN KEY (pricing_id)
                    REFERENCES "pricing" (id),
            CONSTRAINT fk_evet_pric_evet
                FOREIGN KEY (event_id)
                    REFERENCES "event" (id)
        );

        PERFORM
            finalise_table('event_pricing');

        ALTER TABLE "event_pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "event_pricing" TO duser_data_mono;

        PERFORM
            create_audit_for_table('event_pricing');

        ALTER TABLE "_audit_log_event_pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_event_pricing" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;