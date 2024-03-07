package internal.tools.util;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineReader {
    private static final InheritableThreadLocal<Scanner> scanner = new InheritableThreadLocal<>() {
        @Override
        protected Scanner initialValue() {
            return new Scanner(new InputStreamReader(System.in));
        }
    };

    public static boolean yesNo(String title) {
        return new Reader(title)
                .options("y", "n")
                .read()
                .equals("y");
    }

    public static boolean shouldContinue() {
        Printer.println();
        return yesNo("Continue?");
    }

    public static Reader reader(String title) {
        return new Reader(title);
    }

    @RequiredArgsConstructor
    public static class Reader {
        private final String title;
        private boolean allowBlankInput = false;
        private List<String> options;
        private String defaultIfBlank;
        private String configValue;

        private void invalidInput(String reason) {
            Printer.printWarning(String.format("Invalid Input (%s)", StringUtils.defaultIfBlank(reason, "")));
        }

        public Reader fromConfig(String configValue) {
            this.configValue = configValue;
            return this;
        }

        public Reader allowBlankInput(boolean allowBlankInput) {
            this.allowBlankInput = allowBlankInput;
            return this;
        }

        public Reader options(String... options) {
            this.options = Arrays.asList(options);
            return this;
        }

        public Reader options(List<String> options) {
            this.options = options;
            return this;
        }

        public Reader defaultIfBlank(String defaultIfBlank) {
            this.defaultIfBlank = defaultIfBlank;
            return this;
        }

        public String read() {
            print();
            Printer.print(">:\t");
            String input;
            if (configValue != null) {
                input = configValue;
            } else {
                input = scanner.get().nextLine();
            }
            ;
            if (allowBlankInput && StringUtils.isBlank(input)) {
                return StringUtils.defaultIfBlank(defaultIfBlank, input);
            }
            if (StringUtils.isNotBlank(defaultIfBlank) && StringUtils.isBlank(input)) {
                input = defaultIfBlank;
            }
            Printer.println("Your Input: ", input);
            if (!allowBlankInput && StringUtils.isBlank(input)) {
                invalidInput("empty input");
                return read();
            }
            if (ObjectUtils.isNotEmpty(options) && !options.contains(input)) {
                invalidInput("invalid option");
                return read();
            }
            return input;
        }

        private void print() {
            Printer.println(String.format("%s:", title));
            if (ObjectUtils.isNotEmpty(options)) {
                Printer.println(" [OPTIONS]");
                options.forEach(option -> Printer.println(String.format(" > %s", option)));
            }
        }
    }
}
