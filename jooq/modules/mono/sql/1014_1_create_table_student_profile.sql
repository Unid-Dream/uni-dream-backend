DO
$$
    BEGIN
        -- stud_prof
        CREATE TABLE "student_profile"
        (
            id                                  UUID DEFAULT gen_random_uuid(),
            user_id                             UUID NOT NULL UNIQUE,

            date_of_birth                       DATE,
            gender                              "gender",
            country_id                          UUID,
            profile_picture                     TEXT,

            phone_country_code                  TEXT,
            phone                               TEXT,

            secondary_school_id                 UUID,
            secondary_school_education_level_id UUID,
            secondary_school_curriculum_id      UUID,

            preferred_university_1_id           UUID,
            preferred_university_2_id           UUID,
            preferred_university_3_id           UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_prof_user
                FOREIGN KEY (user_id)
                    REFERENCES "user" (id),
            CONSTRAINT fk_stud_prof_cout
                FOREIGN KEY (country_id)
                    REFERENCES "country" (id),
            CONSTRAINT fk_stud_prof_secd_schl
                FOREIGN KEY (secondary_school_id)
                    REFERENCES "school" (id),
            CONSTRAINT fk_stud_prof_secd_schl_edu_levl
                FOREIGN KEY (secondary_school_education_level_id)
                    REFERENCES "education_level" (id),
            CONSTRAINT fk_stud_prof_secd_schl_crcu
                FOREIGN KEY (secondary_school_curriculum_id)
                    REFERENCES "curriculum" (id),
            CONSTRAINT fk_stud_prof_pref_uniy_1
                FOREIGN KEY (preferred_university_1_id)
                    REFERENCES "school" (id),
            CONSTRAINT fk_stud_prof_pref_uniy_2
                FOREIGN KEY (preferred_university_2_id)
                    REFERENCES "school" (id),
            CONSTRAINT fk_stud_prof_pref_uniy_3
                FOREIGN KEY (preferred_university_3_id)
                    REFERENCES "school" (id)
        );

        PERFORM
            finalise_table('student_profile');

        ALTER TABLE "student_profile"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_profile" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_profile');

        ALTER TABLE "_audit_log_student_profile"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_profile" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;