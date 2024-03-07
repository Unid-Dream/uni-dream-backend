package internal.tools.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VersionUtil {
    public static String generateNew() {
        return String.format("%s.0", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
