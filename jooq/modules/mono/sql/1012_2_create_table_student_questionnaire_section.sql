DO
$$
    BEGIN
        -- stu_quet_sect
        CREATE TABLE "student_questionnaire_section"
        (
            id                            UUID DEFAULT gen_random_uuid(),

            student_questionnaire_id      UUID        NOT NULL,
            student_questionnaire_version TIMESTAMPTZ NOT NULL,
            "order"                       INTEGER     NOT NULL CHECK ( "order" >= 1 ),

            title_i18n_id                 UUID UNIQUE,

            PRIMARY KEY (id),
            CONSTRAINT uq_stu_quet_sect_quet
                UNIQUE (student_questionnaire_id, student_questionnaire_version, "order"),
            CONSTRAINT fk_stu_quet_sect_quet
                FOREIGN KEY (student_questionnaire_id, student_questionnaire_version)
                    REFERENCES "student_questionnaire" (id, "version"),
            CONSTRAINT fk_stu_quet_sect_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('student_questionnaire_section');

        ALTER TABLE "student_questionnaire_section"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_questionnaire_section" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_questionnaire_section');

        ALTER TABLE "_audit_log_student_questionnaire_section"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_questionnaire_section" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;