DO
$$
    BEGIN
        -- application will set a session variable in pgsql
        CREATE
            OR REPLACE
            FUNCTION set_current_app_user(
            cu TEXT
        ) RETURNS VOID
            LANGUAGE plpgsql AS
        $set_current_app_user$
        BEGIN
            PERFORM
                set_config('currentapp.sessionuser', cu, TRUE);
        END;

        $set_current_app_user$;
        -- hence pgsql will know the current user detail of a application's
        CREATE
            OR REPLACE
            FUNCTION current_app_user() RETURNS TEXT
            LANGUAGE plpgsql AS
        $current_app_user$
        BEGIN
            RETURN COALESCE(NULLIF(current_setting('currentapp.sessionuser', TRUE), ''),
                            CONCAT('SYSTEM_', CURRENT_USER));
        END;

        $current_app_user$;
        -- to make table rows soft deletable
        CREATE
            OR REPLACE
            FUNCTION soft_delete_table(
            table_name TEXT
        ) RETURNS VOID
            LANGUAGE plpgsql AS
        $soft_delete_table$
        BEGIN

            EXECUTE format(
                    '
                        ALTER TABLE "%1$s"
                            ADD COLUMN deleted BOOLEAN DEFAULT FALSE;
                    ',
                    table_name
                    );
        END;

        $soft_delete_table$;
        -- for finalising table with audit meta
        CREATE
            OR REPLACE
            FUNCTION finalise_table(
            table_name TEXT
        ) RETURNS VOID
            LANGUAGE plpgsql AS
        $finalise_table$
        BEGIN

            EXECUTE format(
                    '
                        ALTER TABLE "%1$s"
                            ADD COLUMN created_on TIMESTAMPTZ,
                            ADD COLUMN created_by TEXT,
                            ADD COLUMN updated_on TIMESTAMPTZ,
                            ADD COLUMN updated_by TEXT;

                        CREATE INDEX "idx_%1$s_uq_sort" ON "%1$s" (created_on, id);

                        CREATE OR REPLACE
                            FUNCTION "%1$s_auto_meta"() RETURNS TRIGGER LANGUAGE plpgsql AS
                            $audit$
                            DECLARE
                                cur TIMESTAMPTZ;
                                cu TEXT;
                            BEGIN
                                cur := CURRENT_TIMESTAMP;

                                cu := current_app_user();

                                IF TG_OP = ''INSERT'' THEN
                                   NEW.created_on = cur;
                                   NEW.created_by = cu;
                                ELSE

                                END IF;

                                NEW.updated_on = cur;
                                NEW.updated_by = cu;

                                RETURN NEW;
                            END;

                            $audit$;

                            DROP TRIGGER IF EXISTS "%1$s_auto_meta_trigger" ON "%1$s";
                            CREATE TRIGGER "%1$s_auto_meta_trigger"
                                BEFORE
                                    INSERT
                                    OR
                                    UPDATE
                                ON
                                "%1$s"
                                FOR EACH ROW
                            EXECUTE PROCEDURE "%1$s_auto_meta"();
                            ',
                    table_name
                    );
        END;

        $finalise_table$;
        -- for creating an audit log table for a table & its related triggers
        CREATE
            OR REPLACE
            FUNCTION create_audit_for_table(
            table_name TEXT
        ) RETURNS VOID
            LANGUAGE plpgsql AS
        $create_audit_for_table$
        BEGIN

            EXECUTE format(
                    '
                        CREATE SEQUENCE "_audit_log_%1$s_seq";

                        CREATE TABLE "_audit_log_%1$s" (
                            audit_seq BIGINT NOT NULL DEFAULT NEXTVAL(''_audit_log_%1$s_seq''),
                            audit_createdOn TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            audit_createdBy TEXT NOT NULL,
                            audit_operation TEXT NOT NULL,
                            audit_type TEXT NOT NULL,
                            LIKE "%1$s" EXCLUDING ALL
                        );

                        ALTER SEQUENCE "_audit_log_%1$s_seq"
                            OWNED BY "_audit_log_%1$s".audit_seq;

                        CREATE OR REPLACE
                            FUNCTION "_audit_log_%1$s"() RETURNS TRIGGER LANGUAGE plpgsql AS
                            $audit$
                            DECLARE
                                cur TIMESTAMPTZ;
                                cu TEXT;
                            BEGIN
                                cur := CURRENT_TIMESTAMP;

                                cu := current_app_user();

                                IF TG_OP = ''UPDATE'' THEN
                                   INSERT
                                    INTO
                                    "_audit_log_%1$s"
                                SELECT
                                    NEXTVAL(''_audit_log_%1$s_seq''),
                                    cur,
                                    cu,
                                    TG_OP,
                                    ''OLD'',
                                    OLD.*;
                                END IF;

                                IF TG_OP = ''DELETE'' THEN
                                   INSERT
                                    INTO
                                    "_audit_log_%1$s"
                                SELECT
                                    NEXTVAL(''_audit_log_%1$s_seq''),
                                    cur,
                                    cu,
                                    TG_OP,
                                    ''OLD'',
                                    OLD.*;
                                ELSE
                                    INSERT
                                    INTO
                                    "_audit_log_%1$s"
                                SELECT
                                    NEXTVAL(''_audit_log_%1$s_seq''),
                                    cur,
                                    cu,
                                    TG_OP,
                                    ''NEW'',
                                    NEW.*;
                                END IF;

                                RETURN NULL;
                            END;

                            $audit$;

                            DROP TRIGGER IF EXISTS "_audit_log_%1$s_trigger" ON "%1$s";
                            CREATE TRIGGER "_audit_log_%1$s_trigger"
                                AFTER
                                    INSERT
                                    OR
                                    UPDATE
                                    OR
                                    DELETE
                                ON
                                "%1$s"
                                FOR EACH ROW
                            EXECUTE PROCEDURE "_audit_log_%1$s"();
                            ',
                    table_name
                    );
        END;

        $create_audit_for_table$;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;