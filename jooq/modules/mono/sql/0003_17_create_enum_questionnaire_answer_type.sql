DO
$$
    BEGIN

        CREATE TYPE "questionnaire_answer_type" AS ENUM (
            'PROVIDED_CHOICE',
            'USER_INPUT_CHOICE'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;