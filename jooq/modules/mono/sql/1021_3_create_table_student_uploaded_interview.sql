DO
$$
    BEGIN
        -- stud_upld_intv
        CREATE TABLE "student_uploaded_interview"
        (
            id                     UUID DEFAULT gen_random_uuid(),

            student_profile_id     UUID NOT NULL,
            interview_topic_id     UUID NOT NULL,
            payment_transaction_id UUID NOT NULL UNIQUE,

            uploaded_file          TEXT,

            content_review_id      UUID,
            clarity_review_id      UUID,
            charisma_review_id     UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_upld_intv_stud_prof
                FOREIGN KEY (student_profile_id)
                    REFERENCES "student_profile" (id),
            CONSTRAINT fk_stud_upld_intv
                FOREIGN KEY (interview_topic_id)
                    REFERENCES "interview_topic" (id),
            CONSTRAINT fk_stud_upld_intv_pay_tran
                FOREIGN KEY (payment_transaction_id)
                    REFERENCES "student_payment_transaction" (id),
            CONSTRAINT fk_stud_upld_intv_clar_revi
                FOREIGN KEY (clarity_review_id)
                    REFERENCES "student_uploaded_supervisor_review" (id),
            CONSTRAINT fk_stud_upld_intv_cont_revi
                FOREIGN KEY (content_review_id)
                    REFERENCES "student_uploaded_supervisor_review" (id),
            CONSTRAINT fk_stud_upld_intv_char_revi
                FOREIGN KEY (charisma_review_id)
                    REFERENCES "student_uploaded_supervisor_review" (id)
        );

        PERFORM
            finalise_table('student_uploaded_interview');

        ALTER TABLE "student_uploaded_interview"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_uploaded_interview" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_uploaded_interview');

        ALTER TABLE "_audit_log_student_uploaded_interview"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_uploaded_interview" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;