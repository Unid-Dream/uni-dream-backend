DO
$$
    BEGIN
        -- schl_idty
        CREATE TABLE "school_identity"
        (
            id           UUID DEFAULT gen_random_uuid(),

            name_i18n_id UUID UNIQUE,

            PRIMARY KEY (id),
            CONSTRAINT fk_schl_idty_name
                FOREIGN KEY (name_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            finalise_table('school_identity');

        ALTER TABLE "school_identity"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "school_identity" TO duser_data_mono;

        PERFORM
            create_audit_for_table('school_identity');

        ALTER TABLE "_audit_log_school_identity"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_school_identity" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;