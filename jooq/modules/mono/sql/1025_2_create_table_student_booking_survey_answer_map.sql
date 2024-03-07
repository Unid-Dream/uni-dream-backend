DO
$$
    BEGIN
        -- stud_book_surv_ans_map
        CREATE TABLE "student_booking_survey_answer_map"
        (
            id                                  UUID DEFAULT gen_random_uuid(),

            student_booking_survey_map_id       UUID    NOT NULL,

            student_session_survey_question_id  UUID    NOT NULL,
            student_session_survey_answer_id    UUID    NOT NULL,
            student_session_survey_answer_order INTEGER NOT NULL,
            student_session_survey_answer_input TEXT,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_book_surv_ans_map_id
                FOREIGN KEY (student_booking_survey_map_id)
                    REFERENCES "student_booking_survey_map" (id),
            CONSTRAINT fk_stud_book_surv_ans_map_quet
                FOREIGN KEY (student_session_survey_question_id)
                    REFERENCES "student_session_survey_question" (id),
            CONSTRAINT fk_stud_book_surv_ans_map_quet_ans
                FOREIGN KEY (student_session_survey_answer_id)
                    REFERENCES "student_session_survey_answer" (id),
            CONSTRAINT fk_stud_book_surv_ans_map_quet_ans_uq
                FOREIGN KEY (student_session_survey_question_id, student_session_survey_answer_order)
                    REFERENCES "student_session_survey_answer" (student_session_survey_question_id, "order")
        );

        PERFORM
            finalise_table('student_booking_survey_answer_map');

        ALTER TABLE "student_booking_survey_answer_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_booking_survey_answer_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_booking_survey_answer_map');

        ALTER TABLE "_audit_log_student_booking_survey_answer_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_booking_survey_answer_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;