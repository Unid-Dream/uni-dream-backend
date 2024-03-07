DO
$$
    BEGIN

        CREATE TYPE "school_level" AS ENUM (
            'SECONDARY_SCHOOL',
            'UNIVERSITY'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;