DO
$$
    BEGIN
        -- acad_sub
        CREATE TABLE "academic_subject"
        (
            id                                UUID DEFAULT gen_random_uuid(),

            title_i18n_id                     UUID UNIQUE,
            description_i18n_id               UUID UNIQUE,
            description_master_degree_i18n_id UUID UNIQUE,
            description_phd_i18n_id           UUID UNIQUE,
            tag_id                            UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_acad_sub_titl
                FOREIGN KEY (title_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_acad_sub_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_acad_sub_desc_mast
                FOREIGN KEY (description_master_degree_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_acad_sub_desc_phd
                FOREIGN KEY (description_phd_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_acad_sub_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('academic_subject');

        ALTER TABLE "academic_subject"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "academic_subject" TO duser_data_mono;

        PERFORM
            create_audit_for_table('academic_subject');

        ALTER TABLE "_audit_log_academic_subject"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_academic_subject" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;