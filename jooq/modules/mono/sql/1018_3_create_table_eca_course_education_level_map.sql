DO
$$
    BEGIN
        -- eca_cour_edu_levl_map
        CREATE TABLE "eca_course_academic_major_education_level_map"
        (
            id                 UUID DEFAULT gen_random_uuid(),
            eca_course_id      UUID NOT NULL,
            education_level_id UUID NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_eca_cour_edu_levl_map
                UNIQUE (eca_course_id, education_level_id),
            CONSTRAINT fk_eca_cour_edu_levl_map_eca_cour
                FOREIGN KEY (eca_course_id)
                    REFERENCES "eca_course" (id),
            CONSTRAINT fk_eca_cour_edu_levl_map_edu_levl
                FOREIGN KEY (education_level_id)
                    REFERENCES "education_level" (id)
        );

        PERFORM
            finalise_table('eca_course_academic_major_education_level_map');

        ALTER TABLE "eca_course_academic_major_education_level_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "eca_course_academic_major_education_level_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('eca_course_academic_major_education_level_map');

        ALTER TABLE "_audit_log_eca_course_academic_major_education_level_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_eca_course_academic_major_education_level_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;