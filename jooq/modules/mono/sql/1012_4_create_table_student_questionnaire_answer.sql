DO
$$
    BEGIN
        -- stu_quet_ans
-- when question changed, answer will be copied into a new set to keep versioning
        CREATE TABLE "student_questionnaire_answer"
        (
            id                                UUID                                 DEFAULT gen_random_uuid(),

            student_questionnaire_question_id UUID                        NOT NULL,
            "order"                           INTEGER                     NOT NULL CHECK ("order" >= 1),

            description_i18n_id               UUID UNIQUE,
            tag_id                            UUID,
            score                             NUMERIC,
            "type"                            "questionnaire_answer_type" NOT NULL DEFAULT 'PROVIDED_CHOICE',

            PRIMARY KEY (id),
            CONSTRAINT uq_stu_quet_ans_ques
                UNIQUE (student_questionnaire_question_id, "order"),
            CONSTRAINT fk_stu_quet_ans_quet
                FOREIGN KEY (student_questionnaire_question_id)
                    REFERENCES "student_questionnaire_question" (id),
            CONSTRAINT fk_stu_quet_ans_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_stu_quet_ans_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('student_questionnaire_answer');

        ALTER TABLE "student_questionnaire_answer"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_questionnaire_answer" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_questionnaire_answer');

        ALTER TABLE "_audit_log_student_questionnaire_answer"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_questionnaire_answer" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;