DO
$$
    BEGIN
        -- stud_book_surv_map
        CREATE TABLE "student_booking_survey_map"
        (
            id                                       UUID    DEFAULT gen_random_uuid(),

            educator_calendar_id                     UUID        NOT NULL,
            student_session_survey_id                UUID        NOT NULL,
            student_session_survey_version_asked     TIMESTAMPTZ NOT NULL,
            student_session_survey_version_completed TIMESTAMPTZ,

            completed                                BOOLEAN DEFAULT FALSE,

            PRIMARY KEY (id),
            CONSTRAINT uq_stud_book_surv_map
                UNIQUE (educator_calendar_id, student_session_survey_id, student_session_survey_version_asked),
            CONSTRAINT fk_stud_book_surv_map_educ_stud_book
                FOREIGN KEY (educator_calendar_id)
                    REFERENCES "educator_calendar" (id),
            CONSTRAINT fk_stud_book_surv_map_surv
                FOREIGN KEY (student_session_survey_id, student_session_survey_version_asked)
                    REFERENCES "student_session_survey" (id, "version"),
            CONSTRAINT fk_stud_book_surv_map_surv2
                FOREIGN KEY (student_session_survey_id, student_session_survey_version_completed)
                    REFERENCES "student_session_survey" (id, "version")
        );

        PERFORM
            finalise_table('student_booking_survey_map');

        ALTER TABLE "student_booking_survey_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_booking_survey_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_booking_survey_map');

        ALTER TABLE "_audit_log_student_booking_survey_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_booking_survey_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;