DO
$$
    BEGIN
        -- intv_topi
        CREATE TABLE "interview_topic"
        (
            id                  UUID DEFAULT gen_random_uuid(),
            topic_i18n_id       UUID,
            description_i18n_id UUID,
            price               NUMERIC,
            currency            "currency",

            PRIMARY KEY (id),
            CONSTRAINT fk_intv_topi_topi
                FOREIGN KEY (topic_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_intv_topi_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('interview_topic');

        ALTER TABLE "interview_topic"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "interview_topic" TO duser_data_mono;

        PERFORM
            create_audit_for_table('interview_topic');

        ALTER TABLE "_audit_log_interview_topic"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_interview_topic" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;