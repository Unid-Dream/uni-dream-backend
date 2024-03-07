DO
$$
    BEGIN
        -- educ_prof_expt_acad_map
        CREATE TABLE "educator_profile_expertise_academic_map"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            educator_profile_id UUID NOT NULL,
            expertise_id        UUID NOT NULL,
            academic_major_id   UUID,
            academic_subject_id UUID,

            PRIMARY KEY (id),
            CONSTRAINT ch_educ_prof_expt_acad_map_sub_maj
                CHECK ((academic_subject_id IS NOT NULL AND academic_major_id IS NOT NULL) OR TRUE),
            CONSTRAINT fk_educ_prof_expt_acad_map_educ_prof
                FOREIGN KEY (educator_profile_id)
                    REFERENCES "educator_profile" (id),
            CONSTRAINT fk_educ_prof_expt_acad_map_expt
                FOREIGN KEY (expertise_id)
                    REFERENCES "expertise" (id),
            CONSTRAINT fk_educ_prof_expt_acad_map_acad_maj
                FOREIGN KEY (expertise_id, academic_major_id)
                    REFERENCES "expertise_academic_major_map" (expertise_id, academic_major_id),
            CONSTRAINT fk_educ_prof_expt_acad_map_acad_maj_sub
                FOREIGN KEY (academic_major_id, academic_subject_id)
                    REFERENCES "academic_major_subject_map" (academic_major_id, academic_subject_id)
        );

        PERFORM
            finalise_table('educator_profile_expertise_academic_map');

        ALTER TABLE "educator_profile_expertise_academic_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT,
            UPDATE,
            INSERT,
            DELETE
            ON
            TABLE "educator_profile_expertise_academic_map" TO duser_data_mono;

        PERFORM
            create_audit_for_table('educator_profile_expertise_academic_map');

        ALTER TABLE "_audit_log_educator_profile_expertise_academic_map"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_educator_profile_expertise_academic_map" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;