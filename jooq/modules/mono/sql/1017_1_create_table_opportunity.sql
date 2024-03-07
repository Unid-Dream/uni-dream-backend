DO
$$
    BEGIN
        -- oppo
        CREATE TABLE "opportunity"
        (
            id            UUID DEFAULT gen_random_uuid(),
            title_i18n_id UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_oppo_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('opportunity');

        ALTER TABLE "opportunity"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "opportunity" TO duser_data_mono;

        PERFORM
            create_audit_for_table('opportunity');

        ALTER TABLE "_audit_log_opportunity"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_opportunity" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;