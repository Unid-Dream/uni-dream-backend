DO
$$
    BEGIN
        -- cout
        CREATE TABLE "country"
        (
            id           UUID DEFAULT gen_random_uuid(),

            name_i18n_id UUID UNIQUE,

            tag_id       UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_cout_name
                FOREIGN KEY (name_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_cout_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('country');

        ALTER TABLE "country"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "country" TO duser_data_mono;

        PERFORM
            create_audit_for_table('country');

        ALTER TABLE "_audit_log_country"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_country" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;