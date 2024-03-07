DO
$$
    BEGIN
        -- i18n
        CREATE TABLE "i18n"
        (
            id                  UUID DEFAULT gen_random_uuid(),

            english             TEXT,
            chinese_traditional TEXT,
            chinese_simplified  TEXT,

            PRIMARY KEY (id)
        );

        PERFORM
            finalise_table('i18n');

        ALTER TABLE "i18n"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "i18n" TO duser_data_mono;

        PERFORM
            create_audit_for_table('i18n');

        ALTER TABLE "_audit_log_i18n"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_i18n" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;