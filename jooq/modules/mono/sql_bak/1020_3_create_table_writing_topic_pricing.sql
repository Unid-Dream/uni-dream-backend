DO
$$
    BEGIN
        -- writ_topi_pric
        CREATE TABLE "writing_topic_pricing"
        (
            id               UUID DEFAULT gen_random_uuid(),
            pricing_id       UUID NOT NULL,
            writing_topic_id UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_writ_topi_pric
                UNIQUE (pricing_id, writing_topic_id),
            CONSTRAINT fk_writ_topi_pric_pric
                FOREIGN KEY (pricing_id)
                    REFERENCES "pricing" (id),
            CONSTRAINT fk_writ_topi_pric_writ_topi
                FOREIGN KEY (writing_topic_id)
                    REFERENCES "writing_topic" (id)
        );

        PERFORM
            finalise_table('writing_topic_pricing');

        ALTER TABLE "writing_topic_pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "writing_topic_pricing" TO duser_data_mono;

        PERFORM
            create_audit_for_table('writing_topic_pricing');

        ALTER TABLE "_audit_log_writing_topic_pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_writing_topic_pricing" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;