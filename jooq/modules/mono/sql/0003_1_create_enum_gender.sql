DO
$$
    BEGIN

        CREATE TYPE "gender" AS ENUM (
            'MALE',
            'FEMALE',
            'SECRET'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;