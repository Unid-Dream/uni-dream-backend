DO
$$
    BEGIN

        CREATE TYPE "payment_method" AS ENUM (
            'COD_ALIPAY_HK',
            'COD_ALIPAY_CN'
            );

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;

            RAISE;

    END;

$$;