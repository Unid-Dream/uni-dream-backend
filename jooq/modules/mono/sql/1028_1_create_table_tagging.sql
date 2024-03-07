DO
$$
    BEGIN
        -- tagi
        CREATE TABLE "tagging"
        (
            id        UUID DEFAULT gen_random_uuid(),

            target    "tag_target" NOT NULL,
            target_id UUID         NOT NULL,
            tag_id    UUID         NOT NULL,

            PRIMARY KEY (id),
            CONSTRAINT fk_tagi_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('tagging');

        ALTER TABLE "tagging"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "tagging" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;