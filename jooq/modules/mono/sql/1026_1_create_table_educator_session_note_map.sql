DO
$$
    BEGIN
        -- educ_sess_note_map
        CREATE TABLE "educator_session_note_map"
        (
            id                                      UUID    DEFAULT gen_random_uuid(),

            educator_calendar_id                    UUID        NOT NULL,
            educator_session_note_item_id           UUID        NOT NULL,
            note_item_input                         TEXT,

            PRIMARY KEY (id),
            CONSTRAINT fk_educ_sess_note_map_educ_cald
                FOREIGN KEY (educator_calendar_id)
                    REFERENCES "educator_calendar" (id),
            CONSTRAINT fk_educ_sess_note_map_note_item
                FOREIGN KEY (educator_session_note_item_id)
                    REFERENCES "educator_session_note_item" (id)
        );

        PERFORM
            finalise_table('educator_session_note_map');

        ALTER TABLE "educator_session_note_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "educator_session_note_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_session_note_map');

        ALTER TABLE "_audit_log_educator_session_note_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_session_note_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;