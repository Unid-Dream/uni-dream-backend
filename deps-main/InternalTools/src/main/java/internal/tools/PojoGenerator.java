package internal.tools;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import internal.tools.util.CommandLineReader;
import internal.tools.util.Printer;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PojoGenerator {

    @SneakyThrows
    public PojoGenerator(boolean alreadyPastedRawContent, String name, String inputRawContent) {
        Printer.println();
        Printer.printWarning("Generating A Pojo");

        // under /resource | classpath://
        var inputContentFile = "input/PojoRawContent";
        var autoInputParamFile = "input/PojoAutoInput.json";
        var outputTemplateFile = "output_template/Pojo";
        var typeMapFile = "input/PojoTypeMap.json";
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
                                PojoGenerator.class.getClassLoader()
                                        .getResource(inputContentFile)
                                        .getFile()
                        ),
                        StandardCharsets.UTF_8
                )
        );

        var templateFileContent = FileUtils.readFileToString(
                new File(
                        PojoGenerator.class.getClassLoader()
                                .getResource(outputTemplateFile)
                                .getFile()
                ),
                StandardCharsets.UTF_8
        );

        var typeMaps = objectMapper.readValue(
                new File(
                        PojoGenerator.class.getClassLoader()
                                .getResource(typeMapFile)
                                .getFile()
                ),
                TypeMaps.class
        );

        var autoInput = objectMapper.readValue(
                new File(
                        PojoGenerator.class.getClassLoader()
                                .getResource(autoInputParamFile)
                                .getFile()
                ),
                AutoInput.class
        );
        Printer.println("Auto Inputs", objectMapper.writeValueAsString(autoInput));

        var pojoName = CommandLineReader
                .reader("Pojo Name")
                .fromConfig(name)
                .read();

        var finalFile = new File(String.format("%s/%s.java", outputDir, pojoName));
        if (finalFile.exists()) {
            throw new RuntimeException("File Already Exists (or Already Generated). Delete it to generate a new one");
        }

        var rowSeparator = CommandLineReader
                .reader("Row Separator (e.g. \\n)")
                .fromConfig(autoInput.rowSeparator)
                .read();
        var colSeparator = CommandLineReader
                .reader("Column Separator (e.g. \\t or <space(s)>)")
                .fromConfig(autoInput.colSeparator)
                .read();
        var nameColIndex = Integer.parseInt(
                CommandLineReader
                        .reader("Name Column Index (e.g. 0)")
                        .fromConfig(String.valueOf(autoInput.nameColIndex))
                        .read()
        );
        var typeColIndex = Integer.parseInt(
                CommandLineReader
                        .reader("Type Column Index (e.g. 1)")
                        .fromConfig(String.valueOf(autoInput.typeColIndex))
                        .read()
        );
        var arrayTypeColIndex = Integer.parseInt(
                CommandLineReader
                        .reader("Array Type Column Index (e.g. 2) | OR <none>")
                        .allowBlankInput(true)
                        .fromConfig(String.valueOf(ObjectUtils.defaultIfNull(autoInput.arrayTypeColIndex, -1)))
                        .read()
        );
        var arrayIndicator = CommandLineReader
                .reader("How To Tell Is Array (e.g. <[]> or < []>")
                .defaultIfBlank("[]")
                .fromConfig(autoInput.arrayIndicator)
                .read();

        var finalContentList = new ArrayList<String>();
        var imports = new HashSet<>(List.of(
                "java.util.List"
        ));
        for (var row : rawContent.split(rowSeparator)) {
            var cols = row.split(colSeparator);
            if (cols.length <= 1) {
                continue;
            }

            // name column
            var nameColOriginal = cols[nameColIndex].trim();
            // sanitise into Java naming
            var nameCol = sanitiseForJava(nameColOriginal);
            nameCol = sanitisedToJavaNaming(nameCol).toLowerCase();
            nameCol = sanitisedToCamelCase(nameCol);

            // type column
            var typeArrayIndicator = arrayTypeColIndex >= 0
                    ? cols[arrayTypeColIndex].trim()
                    : arrayIndicator;
            var typeCol = StringUtils.remove(cols[typeColIndex].trim(), typeArrayIndicator);
            var typeIsArray = arrayTypeColIndex >= 0
                    ? cols[arrayTypeColIndex].trim().contains(arrayIndicator)
                    : cols[typeColIndex].trim().contains(arrayIndicator);
            var typeMap = typeMaps.getOrDefault(typeCol, TypeMap.builder()
                    .annotations(new ArrayList<>())
                    .javaImport(new ArrayList<>())
                    .javaType(typeCol)
                    .build());
            imports.addAll(typeMap.javaImport);
            var annotations = new HashSet<>(List.of(String.format("@JsonProperty(\"%s\")", nameColOriginal)));
            annotations.addAll(typeMap.annotations);
            finalContentList.add(String.format(
                    "%s\n\tprivate %s %s;",
                    String.join(
                            "\n\t",
                            annotations
                    ),
                    typeIsArray ? String.format("List<%s>", typeMap.javaType) : typeMap.javaType,
                    nameCol
            ));
        }
        var finalImports = imports.stream().map(i -> String.format("import %s;", i)).collect(Collectors.joining("\n"));
        var finalContent = String.join("\n\t", finalContentList);
        FileUtils.writeStringToFile(
                finalFile,
                templateFileContent
                        .replace("_IMPORTS_", finalImports)
                        .replace("_NAMED_", pojoName)
                        .replace("_FIELDS_", finalContent),
                StandardCharsets.UTF_8
        );
    }

    @SneakyThrows
    public static void main(String[] args) {
        new PojoGenerator(
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

    public String sanitisedToCamelCase(String input) {
        var matcher2 = Pattern.compile("(_)([a-z])")
                .matcher(input);
        while (matcher2.find()) {
            input = matcher2.replaceFirst(matcher2.group(2).toUpperCase());
            matcher2.reset(input);
        }
        return input;
    }

    @NoArgsConstructor
    static class AutoInput {
        @JsonProperty("rowSeparator")
        private String rowSeparator;
        @JsonProperty("colSeparator")
        private String colSeparator;
        @JsonProperty("nameColIndex")
        private Integer nameColIndex;
        @JsonProperty("typeColIndex")
        private Integer typeColIndex;
        @JsonProperty("arrayTypeColIndex")
        private Integer arrayTypeColIndex; // could be null
        @JsonProperty("arrayIndicator")
        private String arrayIndicator;
    }

    // rawType, TypeMap
    static class TypeMaps extends HashMap<String, TypeMap> {
    }

    @SuperBuilder
    @NoArgsConstructor
    static class TypeMap {
        @JsonProperty("javaImport")
        private List<String> javaImport;
        @JsonProperty("javaType")
        private String javaType;
        @JsonProperty("annotations")
        private List<String> annotations;
    }
}
