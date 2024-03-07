DO
$$
    BEGIN
        -- edu_levl
        CREATE TABLE "education_level"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            grade               TEXT,
            school_level        "school_level",

            description_i18n_id UUID UNIQUE,

            PRIMARY KEY (id),
            CONSTRAINT uq_edu_levl_grad_schl_levl
                UNIQUE (grade, school_level),
            CONSTRAINT fk_edu_levl_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('education_level');

        ALTER TABLE "education_level"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "education_level" TO duser_data_mono;

        PERFORM
            create_audit_for_table('education_level');

        ALTER TABLE "_audit_log_education_level"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_education_level" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;