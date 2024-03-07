DO
$$
    BEGIN

        CREATE TYPE "academic_subject_resource_type" AS ENUM (
            'READINGS',
            'VIDEO',
            'PODCAST'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;