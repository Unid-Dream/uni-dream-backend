DO
$$
    BEGIN

        CREATE TYPE "payment_status" AS ENUM (
            'PENDING',
            'PAID',
            'EXPIRED'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;