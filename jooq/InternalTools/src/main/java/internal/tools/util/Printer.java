package internal.tools.util;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;

public class Printer {

    private static String msg(String... msg) {
        if (ObjectUtils.isEmpty(msg)) {
            return "";
        }
        return Arrays.stream(msg).reduce((a, b) -> String.format("%s %s", a, b)).get();
    }

    public static void print(String... msg) {
        System.out.print(msg(msg));
    }

    public static void println(String... msg) {
        System.out.println(msg(msg));
    }

    public static void printWarning(String... msg) {
        var message = String.format("<<< %s >>>", msg(msg));
        System.out.println("==============================");
        System.out.println(message);
        System.out.println("==============================");
    }
}
