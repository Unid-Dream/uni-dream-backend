DO
$$
    BEGIN

        CREATE TYPE "event_type" AS ENUM (
            'WEBINAR',
            'COURSE'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;