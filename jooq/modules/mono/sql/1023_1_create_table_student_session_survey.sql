DO
$$
    BEGIN
        -- stud_sess_surv
        CREATE TABLE "student_session_survey"
        (
            id                  UUID,
            "version"           TIMESTAMPTZ NOT NULL DEFAULT NOW(),

            title_i18n_id       UUID UNIQUE,
            sub_title_i18n_id   UUID UNIQUE,
            description_i18n_id UUID UNIQUE,

            mandatory           BOOLEAN              DEFAULT FALSE,

            PRIMARY KEY (id, "version"),
            CONSTRAINT fk_stud_sess_surv_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_stud_sess_surv_sub_titl
                FOREIGN KEY (sub_title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_stud_sess_surv_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('student_session_survey');

        ALTER TABLE "student_session_survey"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_session_survey" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_session_survey');

        ALTER TABLE "_audit_log_student_session_survey"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_session_survey" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;