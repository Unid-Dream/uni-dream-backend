DO
$$
    BEGIN

        CREATE TYPE "questionnaire_question_type" AS ENUM (
            'SINGLE_CHOICE',
            'MULTIPLE_CHOICE'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;