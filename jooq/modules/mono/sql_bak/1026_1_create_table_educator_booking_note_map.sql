DO
$$
    BEGIN
        -- educ_book_note_map
        CREATE TABLE "educator_booking_note_map"
        (
            id                                      UUID    DEFAULT gen_random_uuid(),

            educator_calendar_id                    UUID        NOT NULL,
            educator_session_note_id                UUID        NOT NULL,

            completed                               BOOLEAN DEFAULT FALSE,

            PRIMARY KEY (id),
            CONSTRAINT uq_educ_book_note_map
                UNIQUE (educator_calendar_id, educator_session_note_id),
            CONSTRAINT fk_educ_book_note_map_educ_stud_book
                FOREIGN KEY (educator_calendar_id)
                    REFERENCES "educator_calendar" (id),
            CONSTRAINT fk_educ_book_note_map_note
                FOREIGN KEY (educator_session_note_id)
                    REFERENCES "educator_session_note" (id)
        );

        PERFORM
            finalise_table('educator_booking_note_map');

        ALTER TABLE "educator_booking_note_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "educator_booking_note_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_booking_note_map');

        ALTER TABLE "_audit_log_educator_booking_note_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_booking_note_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;