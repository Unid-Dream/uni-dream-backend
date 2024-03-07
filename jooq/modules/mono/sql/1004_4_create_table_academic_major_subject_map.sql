DO
$$
    BEGIN
        -- acad_maj_sub_map
        CREATE TABLE "academic_major_subject_map"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            academic_major_id   UUID NOT NULL,
            academic_subject_id UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_acad_maj_sub_map
                UNIQUE (academic_major_id, academic_subject_id),
            CONSTRAINT fk_acad_maj_sub_map_maj
                FOREIGN KEY (academic_major_id)
                    REFERENCES "academic_major" (id),
            CONSTRAINT fk_acad_maj_sub_map_acad_sub
                FOREIGN KEY (academic_subject_id)
                    REFERENCES "academic_subject" (id)
        );

        PERFORM
            finalise_table('academic_major_subject_map');

        ALTER TABLE "academic_major_subject_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT,
            UPDATE,
            INSERT,
            DELETE
            ON
            TABLE "academic_major_subject_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('academic_major_subject_map');

        ALTER TABLE "_audit_log_academic_major_subject_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_academic_major_subject_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;