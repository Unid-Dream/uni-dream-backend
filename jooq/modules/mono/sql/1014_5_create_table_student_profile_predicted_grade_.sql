DO
$$
    BEGIN
        -- stud_prof_pred_grad
        CREATE TABLE "student_profile_predicted_grade"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            student_profile_id  UUID NOT NULL,

            academic_subject_id UUID NOT NULL,
            grade               TEXT NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT uq_stud_prof_pred_grad
                UNIQUE (id, academic_subject_id),
            CONSTRAINT fk_stud_prof_pred_grad_stud_prof
                FOREIGN KEY (student_profile_id)
                    REFERENCES "student_profile" (id),
            CONSTRAINT fk_stud_prof_pred_grad_acad_sub
                FOREIGN KEY (academic_subject_id)
                    REFERENCES "academic_subject" (id)
        );

        PERFORM
            finalise_table('student_profile_predicted_grade');

        ALTER TABLE "student_profile_predicted_grade"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_profile_predicted_grade" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_profile_predicted_grade');

        ALTER TABLE "_audit_log_student_profile_predicted_grade"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_profile_predicted_grade" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;