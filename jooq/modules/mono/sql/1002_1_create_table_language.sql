DO
$$
    BEGIN
        -- lang
        CREATE TABLE "language"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            description_i18n_id UUID UNIQUE,
            tag_id              UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_language_description
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_language_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('language');

        ALTER TABLE "language"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "language" TO duser_data_mono;

        PERFORM
            create_audit_for_table('language');

        ALTER TABLE "_audit_log_language"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_language" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;