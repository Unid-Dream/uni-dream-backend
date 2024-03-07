DO
$$
    BEGIN
        -- expt
        CREATE TABLE "expertise"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            description_i18n_id UUID UNIQUE,
            tag_id              UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_expt_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_expt_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('expertise');

        ALTER TABLE "expertise"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "expertise" TO duser_data_mono;

        PERFORM
            create_audit_for_table('expertise');

        ALTER TABLE "_audit_log_expertise"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_expertise" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;