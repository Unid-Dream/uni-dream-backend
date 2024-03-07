DO
$$
    BEGIN
        -- acad_maj
        CREATE TABLE "academic_major"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            title_i18n_id       UUID UNIQUE,
            description_i18n_id UUID UNIQUE,
            tag_id              UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_acad_maj_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_acad_maj_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_acad_maj_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('academic_major');

        ALTER TABLE "academic_major"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "academic_major" TO duser_data_mono;

        PERFORM
            create_audit_for_table('academic_major');

        ALTER TABLE "_audit_log_academic_major"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_academic_major" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;