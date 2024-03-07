DO
$$
    BEGIN

        CREATE TYPE "tag_target" AS ENUM (
            'STUDENT_PROFILE',
            'EDUCATOR_PROFILE',
            'COURSE',
            'ECA'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;