DO
$$
    BEGIN
        -- pric
        CREATE TABLE "pricing"
        (
            id       UUID DEFAULT gen_random_uuid(),
            price    NUMERIC,
            currency "currency" NOT NULL,

            PRIMARY KEY (id)
        );

        PERFORM
            finalise_table('pricing');

        ALTER TABLE "pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "pricing" TO duser_data_mono;

        PERFORM
            create_audit_for_table('pricing');

        ALTER TABLE "_audit_log_pricing"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_pricing" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;