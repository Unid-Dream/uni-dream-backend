DO
$$
    BEGIN
        -- educ_prof
        CREATE TABLE "educator_profile"
        (
            id                            UUID                            DEFAULT gen_random_uuid(),
            user_id                       UUID                   NOT NULL UNIQUE,

            application_approval          "application_approval" NOT NULL DEFAULT 'PENDING',

            microsoft_id       TEXT,
            microsoft_email       TEXT,

            country_id                    UUID,
            profile_picture               TEXT,

            phone_country_code            TEXT,
            phone                         TEXT,

            hourly_rate                   INTEGER,

            university_id                 UUID,
            university_education_level_id UUID,
            university_identity_id        UUID,


            PRIMARY KEY (id),
            CONSTRAINT fk_educ_prof_user
                FOREIGN KEY (user_id)
                    REFERENCES "user" (id),
            CONSTRAINT fk_educ_prof_cout
                FOREIGN KEY (country_id)
                    REFERENCES "country" (id),
            CONSTRAINT fk_educ_prof_uity
                FOREIGN KEY (university_id)
                    REFERENCES "school" (id),
            CONSTRAINT fk_educ_prof_edu_levl
                FOREIGN KEY (university_education_level_id)
                    REFERENCES "education_level" (id),
            CONSTRAINT fk_educ_prof_uity_idty
                FOREIGN KEY (university_identity_id)
                    REFERENCES "school_identity" (id)
        );

        PERFORM
            finalise_table('educator_profile');

        ALTER TABLE "educator_profile"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "educator_profile" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_profile');

        ALTER TABLE "_audit_log_educator_profile"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_profile" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;