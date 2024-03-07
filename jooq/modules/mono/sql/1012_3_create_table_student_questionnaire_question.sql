DO
$$
    BEGIN
        -- stu_quet_ques
-- when questionnaire changed, question will be copied into a new set to keep versioning
        CREATE TABLE "student_questionnaire_question"
        (
            id                               UUID                                   DEFAULT gen_random_uuid(),

            student_questionnaire_section_id UUID                          NOT NULL,
            "order"                          INTEGER                       NOT NULL CHECK ( "order" >= 1 ),

            description_i18n_id              UUID UNIQUE,
            mandatory                        BOOLEAN                                DEFAULT TRUE,
            "type"                           "questionnaire_question_type" NOT NULL DEFAULT 'SINGLE_CHOICE',

            PRIMARY KEY (id),
            CONSTRAINT uq_stu_quet_ques
                UNIQUE (student_questionnaire_section_id, "order"),
            CONSTRAINT fk_stu_quet_ques_sect
                FOREIGN KEY (student_questionnaire_section_id)
                    REFERENCES "student_questionnaire_section" (id),
            CONSTRAINT fk_stu_quet_ques_decc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('student_questionnaire_question');

        ALTER TABLE "student_questionnaire_question"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_questionnaire_question" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_questionnaire_question');

        ALTER TABLE "_audit_log_student_questionnaire_question"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_questionnaire_question" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;