DO
$$
    BEGIN
        -- cour
        CREATE TABLE "course"
        (
            id                  UUID DEFAULT gen_random_uuid(),
            title_i18n_id       UUID UNIQUE,
            description_i18n_id UUID UNIQUE,
            academic_major_id   UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_evet_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_evet_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_evet_acad_maj
                FOREIGN KEY (academic_major_id)
                    REFERENCES "academic_major" (id)
        );

        PERFORM
            finalise_table('course');

        ALTER TABLE "course"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "course" TO duser_data_mono;

        PERFORM
            create_audit_for_table('course');

        ALTER TABLE "_audit_log_course"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_course" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;