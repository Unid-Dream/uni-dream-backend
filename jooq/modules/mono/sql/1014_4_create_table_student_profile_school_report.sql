DO
$$
    BEGIN
        -- stud_prof_schl_rept
        CREATE TABLE "student_profile_school_report"
        (
            id                                    UUID DEFAULT gen_random_uuid(),
            student_profile_id                    UUID NOT NULL,

            secondary_school_report               TEXT,
            secondary_school_report_academic_year TEXT,
            secondary_school_report_semester      TEXT,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_prof_schl_rept_prof
                FOREIGN KEY (student_profile_id)
                    REFERENCES "student_profile" (id)
        );

        PERFORM
            finalise_table('student_profile_school_report');

        ALTER TABLE "student_profile_school_report"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_profile_school_report" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_profile_school_report');

        ALTER TABLE "_audit_log_student_profile_school_report"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_profile_school_report" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;