DO
$$
    BEGIN
        -- educ_sess_note
        CREATE TABLE "educator_session_note"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            title_i18n_id       UUID UNIQUE,
            sub_title_i18n_id   UUID UNIQUE,
            description_i18n_id UUID UNIQUE,

            obsoleted           BOOLEAN              DEFAULT FALSE,
            mandatory           BOOLEAN              DEFAULT TRUE,

            PRIMARY KEY (id),
            CONSTRAINT fk_educ_sess_note_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_educ_sess_note_sub_titl
                FOREIGN KEY (sub_title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_educ_sess_note_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('educator_session_note');

        ALTER TABLE "educator_session_note"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "educator_session_note" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_session_note');

        ALTER TABLE "_audit_log_educator_session_note"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_session_note" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;