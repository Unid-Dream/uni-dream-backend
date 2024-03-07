package internal.tools;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import internal.tools.util.CommandLineReader;
import internal.tools.util.Printer;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class NamedEnumGenerator {

    @SneakyThrows
    public NamedEnumGenerator(boolean alreadyPastedRawContent, String name, String inputRawContent) {
        Printer.println();
        Printer.printWarning("Generating A Named Enum");

        // under /resource | classpath://
        var inputContentFile = "input/NamedEnumRawContent";
        var autoInputParamFile = "input/NamedEnumAutoInput.json";
        var outputTemplateFile = "output_template/NamedEnum";
        // under project's root
        var outputDir = ".generated";
        //helper
        var objectMapper = new ObjectMapper();

        // read raw input content
        if (!alreadyPastedRawContent) {
            var pastedRawContent = CommandLineReader
                    .yesNo(String.format("Pasted Raw Content (paste your content under %s )", inputContentFile));
            if (!pastedRawContent) {
                return;
            }
        }
        var rawContent = StringUtils.defaultIfBlank(
                inputRawContent,
                FileUtils.readFileToString(
                        new File(
                                NamedEnumGenerator.class.getClassLoader()
                                        .getResource(inputContentFile)
                                        .getFile()
                        ),
                        StandardCharsets.UTF_8
                )
        );

        // read output template content
        var templateFileContent = FileUtils.readFileToString(
                new File(
                        NamedEnumGenerator.class.getClassLoader()
                                .getResource(outputTemplateFile)
                                .getFile()
                ),
                StandardCharsets.UTF_8
        );

        // read default input
        var autoInput = objectMapper.readValue(
                new File(
                        PojoGenerator.class.getClassLoader()
                                .getResource(autoInputParamFile)
                                .getFile()
                ),
                AutoInput.class
        );
        Printer.println("Auto Inputs", objectMapper.writeValueAsString(autoInput));

        // ask for output name
        var enumName = CommandLineReader
                .reader("Enum Name")
                .fromConfig(name)
                .read();

        // prepare final output content
        var finalFile = new File(String.format("%s/%s.java", outputDir, enumName));
        if (finalFile.exists()) {
            throw new RuntimeException("File Already Exists (or Already Generated). Delete it to generate a new one");
        }

        // read from auto input or command line
        var rowSeparator = CommandLineReader
                .reader("Row Separator (e.g. \\n)")
                .fromConfig(autoInput.rowSeparator)
                .read();
        var colSeparator = CommandLineReader
                .reader("Column Separator (e.g. \\t or <space(s)>)")
                .fromConfig(autoInput.colSeparator)
                .read();
        var valueColIndex = Integer.parseInt(
                CommandLineReader
                        .reader("Value Column Index (e.g. 0)")
                        .fromConfig(String.valueOf(autoInput.valueColIndex))
                        .read()
        );

        // process
        var finalContentList = new ArrayList<String>();
        for (var row : rawContent.split(rowSeparator)) {
            var cols = row.split(colSeparator);
            if (cols.length <= 1) {
                continue;
            }

            var fieldValue = cols[valueColIndex].trim();
            // sanitise into Java naming
            Printer.println("Sanitising For Java", fieldValue);
            var fieldName = sanitiseForJava(fieldValue);
            Printer.println("Sanitised For Java", fieldName);
            fieldName = sanitisedToJavaNaming(fieldName);
            Printer.println("Sanitised To Java", fieldName);
            finalContentList.add(String.format("%s(\"%s\")", fieldName, fieldValue));
        }
        // finalise
        var finalContent = String.join(",\n\t", finalContentList);
        // output
        FileUtils.writeStringToFile(
                finalFile,
                templateFileContent
                        .replace("_NAMED_", enumName)
                        .replace("_ENUMS_", finalContent),
                StandardCharsets.UTF_8
        );
    }

    public static void main(String[] args) {
        new NamedEnumGenerator(
                args.length >= 1 && "Y".equalsIgnoreCase(args[0]),
                args.length >= 2 ? args[1] : null,
                null
        );
    }

    public String sanitiseForJava(String input) {
        // replace excessive underscores
        return Pattern.compile("_{2,}")
                .matcher(
                        // replace all disallowed symbol or letter
                        Pattern.compile("[^a-zA-Z0-9_]")
                                .matcher(
                                        // remove leading and trailing non letter
                                        Pattern.compile("^([^a-zA-Z0-9]*)([a-zA-Z0-9]+)([^a-zA-Z0-9]*)$")
                                                .matcher(input)
                                                .replaceAll("$2")
                                )
                                .replaceAll("_")
                )
                .replaceAll("_");
    }

    public String sanitisedToJavaNaming(String input) {
        return Pattern.compile("([a-z0-9])([A-Z])")
                .matcher(
                        Pattern.compile("([A-Z0-9])([A-Z0-9])([a-z])")
                                .matcher(input)
                                .replaceAll("$1_$2$3")
                )
                .replaceAll("$1_$2")
                .toUpperCase();
    }

    @NoArgsConstructor
    static class AutoInput {
        @JsonProperty("rowSeparator")
        private String rowSeparator;
        @JsonProperty("colSeparator")
        private String colSeparator;
        @JsonProperty("valueColIndex")
        private Integer valueColIndex;
    }
}
