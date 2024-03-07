DO
$$
    BEGIN
        -- stud_upld_supv_revi
        CREATE TABLE "student_uploaded_supervisor_review"
        (
            id                               UUID DEFAULT gen_random_uuid(),

            supervisor_score                 INTEGER CHECK (supervisor_score BETWEEN 1 AND 5) NOT NULL,
            supervisor_commented_strength    TEXT,
            supervisor_commented_weakness    TEXT,
            supervisor_commented_improvement TEXT,

            PRIMARY KEY (id)
        );

        PERFORM
            finalise_table('student_uploaded_supervisor_review');

        ALTER TABLE "student_uploaded_supervisor_review"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_uploaded_supervisor_review" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_uploaded_supervisor_review');

        ALTER TABLE "_audit_log_student_uploaded_supervisor_review"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_uploaded_supervisor_review" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;