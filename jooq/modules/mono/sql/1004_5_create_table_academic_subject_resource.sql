DO
$$
    BEGIN
        -- acad_sub_resc
        CREATE TABLE "academic_subject_resource"
        (
            id                    UUID DEFAULT gen_random_uuid(),

            "academic_subject_id" UUID NOT NULL,

            "type"                "academic_subject_resource_type",
            title_i18n_id         UUID UNIQUE,
            author                TEXT,
            URL                   TEXT,
            thumbnail             TEXT,

            PRIMARY KEY (id),
            CONSTRAINT fk_acad_sub_resc_acad_sub
                FOREIGN KEY (academic_subject_id)
                    REFERENCES "academic_subject" (id),
            CONSTRAINT fk_acad_sub_resc_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('academic_subject_resource');

        ALTER TABLE "academic_subject_resource"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "academic_subject_resource" TO duser_data_mono;

        PERFORM
            create_audit_for_table('academic_subject_resource');

        ALTER TABLE "_audit_log_academic_subject_resource"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_academic_subject_resource" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;