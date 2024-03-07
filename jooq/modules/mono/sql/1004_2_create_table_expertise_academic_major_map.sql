DO
$$
    BEGIN
        -- expt_acad_maj_map
        CREATE TABLE "expertise_academic_major_map"
        (
            id                UUID DEFAULT gen_random_uuid(),

            expertise_id      UUID NOT NULL,
            academic_major_id UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_expt_acad_maj_map_expt
                UNIQUE (expertise_id, academic_major_id),
            CONSTRAINT fk_expt_acad_maj_map_expt
                FOREIGN KEY (expertise_id)
                    REFERENCES "expertise" (id),
            CONSTRAINT fk_expt_acad_maj_map_acad_maj
                FOREIGN KEY (academic_major_id)
                    REFERENCES "academic_major" (id)
        );

        PERFORM
            finalise_table('expertise_academic_major_map');

        ALTER TABLE "expertise_academic_major_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT,
            UPDATE,
            INSERT,
            DELETE
            ON
            TABLE "expertise_academic_major_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('expertise_academic_major_map');

        ALTER TABLE "_audit_log_expertise_academic_major_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_expertise_academic_major_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;