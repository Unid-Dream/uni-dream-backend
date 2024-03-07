package internal.tools.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class NameUtil {
    private static final String groupIdRegex = "^([a-zA-Z.])+[^.]$";
    private static final String artifactIdRegex = "^[a-z0-9]([a-z0-9-])+[^-]$";

    public static void printGroupIdHints() {
        Printer.println("Allows Group ID Format:", groupIdRegex);
    }

    public static boolean isValidGroupId(String groupId) {
        if (StringUtils.isBlank(groupId)) {
            Printer.printWarning("Invalid Group ID Format (Blank):", groupId);
            return false;
        }
        var pattern = Pattern.compile(groupIdRegex);
        var matcher = pattern.matcher(groupId);
        if (!matcher.matches()) {
            Printer.printWarning("Invalid Group ID Format:", groupId);
            return false;
        }
        return true;
    }

    public static void printArtifactIdHints() {
        Printer.println("Allowed Artifact ID Format:", artifactIdRegex);
    }

    public static boolean isValidArtifactId(String artifactId) {
        if (StringUtils.isBlank(artifactId)) {
            Printer.printWarning("Invalid Artifact ID Format:", artifactId);
            return false;
        }
        var pattern = Pattern.compile(artifactIdRegex);
        var matcher = pattern.matcher(artifactId);
        if (!matcher.matches()) {
            Printer.printWarning("Invalid Artifact ID Format:", artifactId);
            return false;
        }
        return true;
    }
}
