DO
$$
    BEGIN
        -- educ_book_note_item_map
        CREATE TABLE "educator_booking_note_item_map"
        (
            id                               UUID DEFAULT gen_random_uuid(),

            educator_booking_note_map_id     UUID        NOT NULL,

            educator_session_note_item_id    UUID UNIQUE NOT NULL,
            educator_session_note_item_input TEXT,

            PRIMARY KEY (id),
            CONSTRAINT fk_educ_book_note_item_map_id
                FOREIGN KEY (educator_booking_note_map_id)
                    REFERENCES "student_booking_survey_map" (id),
            CONSTRAINT fk_educ_book_note_item_map_quet_item
                FOREIGN KEY (educator_session_note_item_id)
                    REFERENCES "educator_session_note_item" (id)
        );

        PERFORM
            finalise_table('educator_booking_note_item_map');

        ALTER TABLE "educator_booking_note_item_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "educator_booking_note_item_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_booking_note_item_map');

        ALTER TABLE "_audit_log_educator_booking_note_item_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_booking_note_item_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;