DO
$$
    BEGIN
        -- tag
        CREATE TABLE "tag"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            description_i18n_id UUID UNIQUE,
            tag_category        "tag_category",
            PRIMARY KEY (id),
            CONSTRAINT fk_tag_desc
                FOREIGN KEY (description_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('tag');

        ALTER TABLE "tag"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "tag" TO duser_data_mono;

        PERFORM
            create_audit_for_table('tag');

        ALTER TABLE "_audit_log_tag"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_tag" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;