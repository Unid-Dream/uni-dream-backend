DO
$$
    BEGIN
        -- intv_topi_pric
        CREATE TABLE "interview_topic_pricing"
        (
            id                 UUID DEFAULT gen_random_uuid(),
            pricing_id         UUID NOT NULL,
            interview_topic_id UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_intv_topi_pric
                UNIQUE (pricing_id, interview_topic_id),
            CONSTRAINT fk_intv_topi_pric_pric
                FOREIGN KEY (pricing_id)
                    REFERENCES "pricing" (id),
            CONSTRAINT fk_intv_topi_pric_intv_topi
                FOREIGN KEY (interview_topic_id)
                    REFERENCES "interview_topic" (id)
        );

        PERFORM
            finalise_table('interview_topic_pricing');

        ALTER TABLE "interview_topic_pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "interview_topic_pricing" TO duser_data_mono;

        PERFORM
            create_audit_for_table('interview_topic_pricing');

        ALTER TABLE "_audit_log_interview_topic_pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_interview_topic_pricing" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;