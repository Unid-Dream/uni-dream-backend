DO
$$
    BEGIN
        -- stud_prof_quet_ans_map
        CREATE TABLE "student_profile_questionnaire_answer_map"
        (
            id                                   UUID DEFAULT gen_random_uuid(),

            student_profile_questionnaire_map_id UUID    NOT NULL,

            questionnaire_question_id            UUID    NOT NULL,
            questionnaire_answer_id              UUID    NOT NULL,
            questionnaire_answer_order           INTEGER NOT NULL,
            questionnaire_answer_any_input       TEXT,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_prof_quet_ans_map_id
                FOREIGN KEY (student_profile_questionnaire_map_id)
                    REFERENCES "student_profile_questionnaire_map" (id),
            CONSTRAINT fk_stud_prof_quet_ans_map_quet
                FOREIGN KEY (questionnaire_question_id)
                    REFERENCES "student_questionnaire_question" (id),
            CONSTRAINT fk_stud_prof_quet_ans_map_quet_ans
                FOREIGN KEY (questionnaire_answer_id)
                    REFERENCES "student_questionnaire_answer" (id),
            CONSTRAINT fk_stud_prof_quet_ans_map_quet_ans_uq
                FOREIGN KEY (questionnaire_question_id, questionnaire_answer_order)
                    REFERENCES "student_questionnaire_answer" (student_questionnaire_question_id, "order")
        );

        PERFORM
            finalise_table('student_profile_questionnaire_answer_map');

        ALTER TABLE "student_profile_questionnaire_answer_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_profile_questionnaire_answer_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_profile_questionnaire_answer_map');

        ALTER TABLE "_audit_log_student_profile_questionnaire_answer_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_profile_questionnaire_answer_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;