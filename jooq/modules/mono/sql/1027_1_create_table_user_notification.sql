DO
$$
    BEGIN
        -- user_noti
        CREATE TABLE "user_notification"
        (
            id                  UUID    DEFAULT gen_random_uuid(),

            owner_user_id       UUID                     NOT NULL,

            notification_type   "user_notification_type" NOT NULL,
            notification_ref_id UUID,

            "read"              BOOLEAN DEFAULT FALSE,

            PRIMARY KEY (id),
            CONSTRAINT fk_user_noti_own_user
                FOREIGN KEY (owner_user_id)
                    REFERENCES "user" (id)
        );

        PERFORM
            finalise_table('user_notification');

        ALTER TABLE "user_notification"
            OWNER TO duser_data_mono;

        GRANT
            SELECT
            ,
            UPDATE
            ,
            INSERT
            ON
            TABLE "user_notification" TO duser_data_mono;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;
    END;

$$;