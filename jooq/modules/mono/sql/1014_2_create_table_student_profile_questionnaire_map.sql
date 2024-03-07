DO
$$
    BEGIN
        -- stud_prof_quet_map
        CREATE TABLE "student_profile_questionnaire_map"
        (
            id                                      UUID    DEFAULT gen_random_uuid(),

            student_profile_id                      UUID        NOT NULL,

            student_questionnaire_id                UUID        NOT NULL,
            student_questionnaire_version_asked     TIMESTAMPTZ NOT NULL,
            student_questionnaire_version_completed TIMESTAMP,

            completed                               BOOLEAN DEFAULT FALSE,

            PRIMARY KEY (id),
            CONSTRAINT uq_stud_prof_quet_map
                UNIQUE (student_questionnaire_id, student_questionnaire_version_asked),
            CONSTRAINT fk_stud_prof_quet_map_stud_prof
                FOREIGN KEY (student_profile_id)
                    REFERENCES "student_profile" (id),
            CONSTRAINT fk_stud_prof_quet_map_stud_quet
                FOREIGN KEY (student_questionnaire_id, student_questionnaire_version_asked)
                    REFERENCES "student_questionnaire" (id, "version"),
            CONSTRAINT fk_stud_prof_quet_map_stud_quet2
                FOREIGN KEY (student_questionnaire_id, student_questionnaire_version_completed)
                    REFERENCES "student_questionnaire" (id, "version")
        );

        PERFORM
            finalise_table('student_profile_questionnaire_map');

        ALTER TABLE "student_profile_questionnaire_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_profile_questionnaire_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_profile_questionnaire_map');

        ALTER TABLE "_audit_log_student_profile_questionnaire_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_profile_questionnaire_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;