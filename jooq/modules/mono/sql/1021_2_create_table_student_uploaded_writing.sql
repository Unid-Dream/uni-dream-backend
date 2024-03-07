DO
$$
    BEGIN
        -- stud_upld_writ
        CREATE TABLE "student_uploaded_writing"
        (
            id                               UUID DEFAULT gen_random_uuid(),

            student_profile_id               UUID NOT NULL,
            writing_topic_id                 UUID NOT NULL,
            payment_transaction_id           UUID NOT NULL UNIQUE,

            uploaded_file                    TEXT,

            grammar_and_expression_review_id UUID,
            content_review_id                UUID,
            composition_review_id            UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_stud_upld_writ_stud_prof
                FOREIGN KEY (student_profile_id)
                    REFERENCES "student_profile" (id),
            CONSTRAINT fk_stud_upld_writ
                FOREIGN KEY (writing_topic_id)
                    REFERENCES "writing_topic" (id),
            CONSTRAINT fk_stud_upld_writ_pay_tran
                FOREIGN KEY (payment_transaction_id)
                    REFERENCES "student_payment_transaction" (id),
            CONSTRAINT fk_stud_upld_writ_gram_expe_revi
                FOREIGN KEY (grammar_and_expression_review_id)
                    REFERENCES "student_uploaded_supervisor_review" (id),
            CONSTRAINT fk_stud_upld_writ_cont_revi
                FOREIGN KEY (content_review_id)
                    REFERENCES "student_uploaded_supervisor_review" (id),
            CONSTRAINT fk_stud_upld_writ_gram_comp_revi
                FOREIGN KEY (composition_review_id)
                    REFERENCES "student_uploaded_supervisor_review" (id)
        );

        PERFORM
            finalise_table('student_uploaded_writing');

        ALTER TABLE "student_uploaded_writing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "student_uploaded_writing" TO duser_data_mono;

        PERFORM
            create_audit_for_table('student_uploaded_writing');

        ALTER TABLE "_audit_log_student_uploaded_writing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_student_uploaded_writing" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;