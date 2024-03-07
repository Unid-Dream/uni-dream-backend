DO
$$
    BEGIN

        CREATE ROLE "duser_data_mono";

        CREATE ROLE "duser_app_mono" login PASSWORD 'dev123456';

        GRANT
            CONNECT
            ON
            DATABASE "mono" TO "duser_app_mono";

        GRANT USAGE ON
            SCHEMA
            public TO "duser_app_mono";

        GRANT "duser_data_mono" TO "duser_app_mono";

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;