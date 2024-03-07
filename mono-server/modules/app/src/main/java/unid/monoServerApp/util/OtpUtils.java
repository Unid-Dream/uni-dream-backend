package unid.monoServerApp.util;

import org.apache.commons.lang3.RandomStringUtils;

public class OtpUtils {
    public static String generate() {
        return RandomStringUtils.random(6, false, true);
    }
}
