DO
$$
    BEGIN
        -- schl
        CREATE TABLE "school"
        (
            id                       UUID DEFAULT gen_random_uuid(),

            school_level             "school_level",

            name_i18n_id             UUID UNIQUE,
            country_id               UUID,
            city_id                  UUID,
            longitude                TEXT,
            latitude                 TEXT,
            detailed_address_i18n_id UUID,
            tag_id                   UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_schl_name
                FOREIGN KEY (name_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_schl_cout
                FOREIGN KEY (country_id)
                    REFERENCES "country" (id),
            CONSTRAINT fk_schl_city
                FOREIGN KEY (city_id)
                    REFERENCES "city" (id),
            CONSTRAINT fk_schl_addr
                FOREIGN KEY (detailed_address_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_schl_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('school');

        ALTER TABLE "school"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "school" TO duser_data_mono;

        PERFORM
            create_audit_for_table('school');

        ALTER TABLE "_audit_log_school"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_school" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;