DO
$$
    BEGIN

        CREATE TYPE "user_role" AS ENUM (
            'ROOT',
            'ADMIN',
            'EDUCATOR',
            'STUDENT'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;