DO
$$
    BEGIN

        CREATE TYPE "currency" AS ENUM (
            'USD',
            'HKD'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;