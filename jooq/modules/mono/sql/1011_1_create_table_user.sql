DO
$$
    BEGIN
        -- user
        CREATE TABLE "user"
        (
            id                       UUID    DEFAULT gen_random_uuid(),

            last_name_i18n_id        UUID UNIQUE,
            fist_name_i18n_id        UUID UNIQUE,
            user_role                user_role NOT NULL,

            login_id                 TEXT UNIQUE, -- leaving a flexible option, current login_id = email, handled by app code
            login_password           TEXT,        -- hashed data

            email                    TEXT UNIQUE,
            email_verified           BOOLEAN DEFAULT FALSE,

            google_client_id         TEXT UNIQUE,
            tencent_wechat_client_id TEXT UNIQUE,

            PRIMARY KEY (id),
            CONSTRAINT fk_user_last_name
                FOREIGN KEY (last_name_i18n_id)
                    REFERENCES "i18n" (id),
            CONSTRAINT fk_user_first_name
                FOREIGN KEY (fist_name_i18n_id)
                    REFERENCES "i18n" (id)
        );

        PERFORM
            soft_delete_table('user');

        PERFORM
            finalise_table('user');

        ALTER TABLE "user"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "user" TO duser_data_mono;

        PERFORM
            create_audit_for_table('user');

        ALTER TABLE "_audit_log_user"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ON
            TABLE "_audit_log_user" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;