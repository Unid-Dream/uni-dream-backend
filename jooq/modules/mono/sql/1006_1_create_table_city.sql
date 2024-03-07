DO
$$
    BEGIN
        -- city
        CREATE TABLE "city"
        (
            id           UUID DEFAULT gen_random_uuid(),

            country_id   UUID,

            name_i18n_id UUID UNIQUE,

            PRIMARY KEY (id),
            CONSTRAINT fk_city_cout
                FOREIGN KEY (country_id)
                    REFERENCES "country" (id),
            CONSTRAINT fk_city_name
                FOREIGN KEY (name_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('city');

        ALTER TABLE "city"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "city" TO duser_data_mono;

        PERFORM
            create_audit_for_table('city');

        ALTER TABLE "_audit_log_city"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_city" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;