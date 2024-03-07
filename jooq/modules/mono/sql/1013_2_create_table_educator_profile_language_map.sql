DO
$$
    BEGIN
        -- educ_prof_lang_map
        CREATE TABLE "educator_profile_language_map"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            educator_profile_id UUID NOT NULL,
            language_id         UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_educ_prof_lang_map
                UNIQUE (educator_profile_id, language_id),
            CONSTRAINT fk_educ_prof_lang_map_educ_prof
                FOREIGN KEY (educator_profile_id)
                    REFERENCES "educator_profile" (id),
            CONSTRAINT fk_educ_prof_lang_map_lang
                FOREIGN KEY (language_id)
                    REFERENCES "language" (id)
        );

        PERFORM
            finalise_table('educator_profile_language_map');

        ALTER TABLE "educator_profile_language_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT,
            UPDATE,
            INSERT,
            DELETE
            ON
            TABLE "educator_profile_language_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_profile_language_map');

        ALTER TABLE "_audit_log_educator_profile_language_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_profile_language_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;