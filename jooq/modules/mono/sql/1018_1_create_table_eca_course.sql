DO
$$
    BEGIN
        -- eca_cour
        CREATE TABLE "eca_course"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            title_i18n_id       UUID,
            description_i18n_id UUID,
            eligibility_i18n_id UUID,
            opportunity_id      UUID,
            ref_urls            TEXT[],

            PRIMARY KEY (id),
            CONSTRAINT fk_eca_cour_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_eca_cour_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_eca_cour_elig
                FOREIGN KEY (eligibility_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_eca_cour_oppo
                FOREIGN KEY (opportunity_id)
                    REFERENCES "opportunity" (id)
        );

        PERFORM
            finalise_table('eca_course');

        ALTER TABLE "eca_course"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "eca_course" TO duser_data_mono;

        PERFORM
            create_audit_for_table('eca_course');

        ALTER TABLE "_audit_log_eca_course"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_eca_course" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;