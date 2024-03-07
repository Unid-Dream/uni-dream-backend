DO
$$
    BEGIN

        CREATE TYPE "tag_category" AS ENUM (
            'LANGUAGE',
            'EXPERTISE',
            'ACADEMIC_MAJOR',
            'ACADEMIC_SUBJECT',
            'COUNTRY',
            'SCHOOL',
            'CURRICULUM',
            'QUESTIONNAIRE_QUESTION_ANSWER'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;