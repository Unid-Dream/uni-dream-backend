DO
$$
    BEGIN
        -- educ_sess_note_item
        CREATE TABLE "educator_session_note_item"
        (
            id                            UUID    DEFAULT gen_random_uuid(),

            educator_session_note_id      UUID        NOT NULL,
            "order"                       INTEGER     NOT NULL CHECK ( "order" >= 1 ),

            title_i18n_id                 UUID UNIQUE,

            mandatory                     BOOLEAN DEFAULT TRUE,

            PRIMARY KEY (id),
            CONSTRAINT uq_educ_sess_note_item
                UNIQUE (educator_session_note_id, "order"),
            CONSTRAINT fk_educ_sess_note_item_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_educ_sess_note_item_note
                FOREIGN KEY (educator_session_note_id)
                    REFERENCES "educator_session_note" (id)
        );

        PERFORM
            finalise_table('educator_session_note_item');

        ALTER TABLE "educator_session_note_item"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "educator_session_note_item" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_session_note_item');

        ALTER TABLE "_audit_log_educator_session_note_item"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_session_note_item" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;