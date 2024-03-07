DO
$$
    BEGIN
        -- stud_sess_surv_ans
        CREATE TABLE "student_session_survey_answer"
        (
            id                                 UUID    DEFAULT gen_random_uuid(),

            student_session_survey_question_id UUID    NOT NULL,
            "order"                            INTEGER NOT NULL CHECK ("order" >= 1),

            description_i18n_id                UUID UNIQUE,

            score                              NUMERIC CHECK (score BETWEEN 0 AND 5),
            require_user_input                 BOOLEAN DEFAULT TRUE,

            PRIMARY KEY (id),
            CONSTRAINT uq_stud_sess_surv_ans_ques
                UNIQUE (student_session_survey_question_id, "order"),
            CONSTRAINT fk_stud_sess_surv_ans_quet
                FOREIGN KEY (student_session_survey_question_id)
                    REFERENCES "student_session_survey_question" (id),
            CONSTRAINT fk_stud_sess_surv_ans_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('student_session_survey_answer');

        ALTER TABLE "student_session_survey_answer"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_session_survey_answer" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_session_survey_answer');

        ALTER TABLE "_audit_log_student_session_survey_answer"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_session_survey_answer" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;