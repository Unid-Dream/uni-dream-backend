DO
$$
    BEGIN
        -- cour_evet
        CREATE TABLE "course_event"
        (
            id        UUID DEFAULT gen_random_uuid(),
            event_id  UUID NOT NULL,
            course_id UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_cour_evet_evet -- for now each event has only one course associated
                UNIQUE (event_id, course_id),
            CONSTRAINT fk_cour_evet_evet
                FOREIGN KEY (event_id)
                    REFERENCES "event" (id),
            CONSTRAINT fk_cour_evet_acad_maj
                FOREIGN KEY (course_id)
                    REFERENCES "course" (id)
        );

        PERFORM
            finalise_table('course_event');

        ALTER TABLE "course_event"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "course_event" TO duser_data_mono;

        PERFORM
            create_audit_for_table('course_event');

        ALTER TABLE "_audit_log_course_event"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_course_event" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;