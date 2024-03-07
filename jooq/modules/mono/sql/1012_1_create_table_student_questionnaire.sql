DO
$$
    BEGIN
        -- stu_quet
        CREATE TABLE "student_questionnaire"
        (
            id                            UUID,
            "version"                     TIMESTAMPTZ NOT NULL DEFAULT NOW(),

            title_i18n_id                 UUID UNIQUE,
            sub_title_i18n_id             UUID UNIQUE,
            description_i18n_id           UUID UNIQUE,

            sections                      TEXT[],

            mandatory_on_new_join_student BOOLEAN              DEFAULT FALSE,

            PRIMARY KEY (id, "version"),
            CONSTRAINT fk_stu_quet_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_stu_quet_sub_titl
                FOREIGN KEY (sub_title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_stu_quet_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('student_questionnaire');

        ALTER TABLE "student_questionnaire"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_questionnaire" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_questionnaire');

        ALTER TABLE "_audit_log_student_questionnaire"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_questionnaire" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;