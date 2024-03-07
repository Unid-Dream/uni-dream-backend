DO
$$
    BEGIN

        CREATE TYPE "student_transaction_item" AS ENUM (
            'EDUCATOR_SCHEDULE',
            'WRITING',
            'INTERVIEW'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;