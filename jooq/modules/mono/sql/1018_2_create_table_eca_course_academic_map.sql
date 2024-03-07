DO
$$
    BEGIN
        -- eca_cour_acad_map
        CREATE TABLE "eca_course_academic_map"
        (
            id                  UUID DEFAULT gen_random_uuid(),
            eca_course_id       UUID NOT NULL,
            academic_major_id   UUID NOT NULL,
            academic_subject_id UUID,

            PRIMARY KEY (id),
            CONSTRAINT uq_eca_cour_acad_map_acad
                UNIQUE (eca_course_id, academic_major_id, academic_subject_id),
            CONSTRAINT ch_eca_cour_acad_map_acad
                CHECK ((academic_subject_id IS NOT NULL AND academic_major_id IS NOT NULL) OR TRUE),
            CONSTRAINT ch_eca_cour_acad_map_eca
                CHECK ((academic_major_id IS NOT NULL AND eca_course_id IS NOT NULL) OR TRUE),
            CONSTRAINT fk_eca_cour_acad_map_eca_cour
                FOREIGN KEY (eca_course_id)
                    REFERENCES "eca_course" (id),
            CONSTRAINT fk_eca_cour_acad_map_acad_maj
                FOREIGN KEY (academic_major_id)
                    REFERENCES "academic_major" (id),
            CONSTRAINT fk_eca_cour_acad_map_acad_maj_sub
                FOREIGN KEY (academic_major_id, academic_subject_id)
                    REFERENCES "academic_major_subject_map" (academic_major_id, academic_subject_id)
        );

        PERFORM
            finalise_table('eca_course_academic_map');

        ALTER TABLE "eca_course_academic_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "eca_course_academic_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('eca_course_academic_map');

        ALTER TABLE "_audit_log_eca_course_academic_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_eca_course_academic_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;