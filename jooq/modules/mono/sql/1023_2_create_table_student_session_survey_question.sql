DO
$$
    BEGIN
        -- stud_sess_surv_ques
        CREATE TABLE "student_session_survey_question"
        (
            id                             UUID    DEFAULT gen_random_uuid(),

            student_session_survey_id      UUID        NOT NULL,
            student_session_survey_version TIMESTAMPTZ NOT NULL,
            "order"                        INTEGER     NOT NULL CHECK ( "order" >= 1 ),

            description_i18n_id            UUID UNIQUE,
            mandatory                      BOOLEAN DEFAULT TRUE,


            PRIMARY KEY (id),
            CONSTRAINT uq_stud_sess_surv_ques
                UNIQUE (student_session_survey_id, student_session_survey_version, "order"),
            CONSTRAINT fk_stud_sess_surv_ques_quet
                FOREIGN KEY (student_session_survey_id, student_session_survey_version)
                    REFERENCES "student_session_survey" (id, "version"),
            CONSTRAINT fk_stud_sess_surv_ques_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('student_session_survey_question');

        ALTER TABLE "student_session_survey_question"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_session_survey_question" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_session_survey_question');

        ALTER TABLE "_audit_log_student_session_survey_question"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_session_survey_question" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;