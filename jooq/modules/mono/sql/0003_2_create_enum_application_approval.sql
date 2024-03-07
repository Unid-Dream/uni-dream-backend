DO
$$
    BEGIN

        CREATE TYPE "application_approval" AS ENUM (
            'PENDING',
            'APPROVED',
            'REJECTED'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;