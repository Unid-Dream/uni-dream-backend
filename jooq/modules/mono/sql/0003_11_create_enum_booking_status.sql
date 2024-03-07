DO
$$
    BEGIN

        CREATE TYPE "booking_status" AS ENUM (
            'AVAILABLE',
            'RESERVED',
            'PENDING',
            'ACCEPTED',
            'REJECTED',
            'CANCELLED',
            'VOID',
            'FINISHED',
            'UNFINISHED'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;