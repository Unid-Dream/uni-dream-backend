DO
$$
    BEGIN
        -- crcu
        CREATE TABLE "curriculum"
        (
            id           UUID DEFAULT gen_random_uuid(),

            school_level "school_level",

            name_i18n_id UUID UNIQUE,
            tag_id       UUID,

            PRIMARY KEY (id),
            CONSTRAINT fk_crcu_name
                FOREIGN KEY (name_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_crcu_tag
                FOREIGN KEY (tag_id)
                    REFERENCES "tag" (id)
        );

        PERFORM
            finalise_table('curriculum');

        ALTER TABLE "curriculum"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "curriculum" TO duser_data_mono;

        PERFORM
            create_audit_for_table('curriculum');

        ALTER TABLE "_audit_log_curriculum"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_curriculum" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;