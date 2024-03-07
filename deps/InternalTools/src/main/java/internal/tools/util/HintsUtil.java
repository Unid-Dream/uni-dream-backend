package internal.tools.util;

import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HintsUtil {
    public static void showHints(
            Class<?> clazz,
            String fileName,
            Map<String, String> placeholders
    ) {
        try (var hintFile = clazz.getClassLoader().getResourceAsStream("hints/" + fileName)) {
            if (hintFile != null) {
                var hints = IOUtils.toString(hintFile, StandardCharsets.UTF_8);
                Printer.println();
                for (var placeholder : placeholders.entrySet()) {
                    hints = hints.replaceAll(placeholder.getKey(), placeholder.getValue());
                }
                Printer.println(hints);
            } else {
                throw new RuntimeException(String.format("Unknown Hints File: %s", fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Printer.println("Failed to show hints from file: ", fileName);
        }
    }
}
