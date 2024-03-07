package internal.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import internal.tools.model.GeneratorJson;
import internal.tools.util.*;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

public class NewProjectFromTemplate {
    @SneakyThrows
    public static void main(String[] args) {
        Printer.println();
        Printer.printWarning("Generating A New Project | Module");

        // get parent pom
        var parentPom = MavenUtil.readPom(new File(getFilePath(Constant.PROJECT_ROOT_DIR, Constant.MAVEN_POM_FILE)));

        // get template
        var templatesPath = getFilePath(Constant.TEMPLATE_DIR);
        var availableTemplates = new File(templatesPath).list(DirectoryFileFilter.DIRECTORY);
        var targetTemplate = CommandLineReader
                .reader("Template")
                .options(availableTemplates)
                .read();
        var targetTemplateDir = new File(getFilePath(templatesPath, targetTemplate));

        // get config
        var config = new ObjectMapper().readValue(
                new File(
                        targetTemplateDir,
                        getFilePath(Constant.CONFIG_FILE)
                ),
                GeneratorJson.class
        );
        Printer.println("Config", config.toString());

        // get group id
        Printer.println();
        NameUtil.printGroupIdHints();
        var groupId = config.getGroupId();
        if (StringUtils.isBlank(groupId)) {
            groupId = CommandLineReader
                    .reader(String.format("Group ID (enter '%s' OR <others>):", Constant.PLACEHOLDER_INHERIT_GROUP_ID))
                    .read();
        }
        if (groupId.equals(Constant.PLACEHOLDER_INHERIT_GROUP_ID)) {
            groupId = parentPom.getGroupId();
        }
        if (!NameUtil.isValidGroupId(groupId)) {
            return;
        }
        Printer.println("Group ID Will Be:", groupId);

        // get artifact id
        Printer.println();
        var templatePom = MavenUtil.readPom(new File(targetTemplateDir, getFilePath(Constant.MAVEN_POM_FILE)));
        var artifactId = templatePom.getArtifactId();
        var artifactIdPrefix = StringUtils.defaultIfBlank(config.getArtifactIdPrefix(), "");
        if (artifactId.equals(VAR.PH_ARTIFACT_ID)) {
            NameUtil.printArtifactIdHints();
            artifactId = StringUtils.removeStart(
                    String.format(
                            "%s-%s",
                            artifactIdPrefix,
                            CommandLineReader
                                    .reader("Artifact ID")
                                    .read()
                    ),
                    "-"
            );
        }
        if (!NameUtil.isValidArtifactId(artifactId)) {
            return;
        }
        Printer.println("Artifact ID Will Be:", artifactId);

        // get package name
        var packageName = CaseUtils.toCamelCase(artifactId, false, '-');
        Printer.println("Package Name ID Will Be:", packageName);

        // get module (folder name)
        var moduleName = StringUtils.uncapitalize(
                StringUtils.removeStart(
                        packageName,
                        CaseUtils.toCamelCase(artifactIdPrefix, false, '-')
                )
        );
        Printer.println("Module (folder) Name Will Be:", moduleName);

        // start
        var generatedDestination = getFilePath(config.getDestination());
        var targetOutputDir = new File(getFilePath(generatedDestination, moduleName));

        if (Files.exists(targetOutputDir.toPath())) {
            Printer.printWarning("ALREADY EXISTS");
            return;
        }

        if (!CommandLineReader.shouldContinue()) {
            return;
        }

        try {
            FileUtils.copyDirectory(targetTemplateDir, targetOutputDir);

            // placeholders P
            var placeholderVar = Map.of(
                    VAR.PH_PARENT_GROUP_ID, parentPom.getGroupId(),
                    VAR.PH_PARENT_VERSION, parentPom.getVersion(),
                    VAR.PH_GROUP_ID, groupId,
                    VAR.PH_PACKAGE_NAME, packageName,
                    VAR.PH_ARTIFACT_ID, artifactId,
                    VAR.PH_VERSION, VersionUtil.generateNew(),
                    VAR.PH_MODULE_NAME, moduleName
            );
            replaceAll(targetOutputDir, placeholderVar);

            // placeholders X
            var placeholdersX = placeholderVar.entrySet().stream()
                    .collect(Collectors.toMap(
                            s -> StringUtils.replace(
                                    s.getKey(),
                                    String.valueOf(s.getKey().charAt(0)),
                                    Constant.PLACEHOLDER_VAR_PREFIX,
                                    1
                            ),
                            Map.Entry::getKey
                    ));
            replaceAll(targetOutputDir, placeholdersX);

            if (config.isIncludeTool()) {
                FileUtils.copyDirectory(
                        new File(getFilePath(Constant.INTERNAL_TOOL_DIR)),
                        new File(targetOutputDir, getFileName(Constant.INTERNAL_TOOL_DIR))
                );
            }

            if (ObjectUtils.isNotEmpty(config.getIncludeTemplates())) {
                for (var template : config.getIncludeTemplates()) {
                    var src = new File(getFilePath(Constant.TEMPLATE_DIR, template));
                    var dest = new File(targetOutputDir, getFilePath(getFileName(Constant.TEMPLATE_DIR), template));
                    FileUtils.copyDirectory(src, dest);
                }
            }

            HintsUtil.showHints(
                    NewProjectFromTemplate.class,
                    "ModuleAfterCreation",
                    placeholderVar
            );
        } catch (Exception e) {
            e.printStackTrace();
            FileUtils.deleteDirectory(targetOutputDir);
        }
    }

    @SneakyThrows
    private static void replaceAll(File targetOutputDir, Map<String, String> placeholders) {
        DirUtil.replacePlaceholderOfFilesInDirectory(
                targetOutputDir,
                placeholders,
                "java", "xml", "md", "factories"
        );

        DirUtil.renameAllSubDirectoriesWithPlaceHolder(
                targetOutputDir,
                placeholders
        );
    }

    private static String getFilePath(String... paths) {
        var path = String.join("/", paths);
        return path.replaceAll("/", File.separator);
    }

    private static String getFileName(String path) {
        return StringUtils.defaultIfBlank(
                StringUtils.substringAfterLast(path, "/"),
                StringUtils.substringAfterLast(path, File.separator)
        );
    }

}
