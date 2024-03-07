package internal.tools;

import internal.tools.util.CommandLineReader;
import internal.tools.util.DirUtil;
import internal.tools.util.MavenUtil;
import internal.tools.util.Printer;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CloneSelf {
    private static final String clonedOutputDir = ".clonedSelf";

    @SneakyThrows
    public static void main(String[] args) {
        Printer.println("********************************************************************************");
        Printer.println("Before continue, make sure you have clean (i.e. maven clean) all compiled stuff");
        Printer.println("********************************************************************************");
        if (!CommandLineReader.shouldContinue()) {
            return;
        }
        var parentPom = MavenUtil.readPom(new File(getFilePath(Constant.PROJECT_ROOT_DIR, Constant.MAVEN_POM_FILE)));
        Printer.println();
        Printer.println("Current Group ID Is:", parentPom.getGroupId());
        var groupId = CommandLineReader
                .reader("Clone Into New Group ID")
                .read();
        Printer.println();
        Printer.println("Cloning:", Constant.PROJECT_ROOT_DIR);
        var ignoredDir = List.of(".idea", "target", clonedOutputDir);
        var outputDir = new File(Constant.PROJECT_ROOT_DIR, clonedOutputDir);
        FileUtils.copyDirectory(
                new File(Constant.PROJECT_ROOT_DIR),
                new File(Constant.PROJECT_ROOT_DIR, clonedOutputDir),
                pathname -> {
                    if (pathname.isDirectory()) {
                        return !ignoredDir.contains(pathname.getName());
                    }
                    return true;
                }
        );
        DirUtil.replacePlaceholderOfFilesInDirectory(
                outputDir,
                Map.of(
                        parentPom.getGroupId(), groupId
                ),
                "java", "xml", "md", "factories"
        );
        DirUtil.renameAllSubDirectoriesWithPlaceHolder(
                outputDir,
                Map.of(
                        parentPom.getGroupId(), groupId
                )
        );
        var newGroupIdLength = groupId.split("\\.").length;
        var currentGroupIdLength = parentPom.getGroupId().split("\\.").length;
        if (currentGroupIdLength > newGroupIdLength) {

        }
        for (var category : new File(outputDir, "modules").listFiles(File::isDirectory)) {
            for (var module : category.listFiles(File::isDirectory)) {
                Paths.get(
                        module.getPath(),
                        ArrayUtils.addAll(
                                new String[]{"src", "main", "java"},
                                parentPom.getGroupId().split("\\.")
                        )
                ).toFile().renameTo(
                        Paths.get(
                                module.getPath(),
                                ArrayUtils.addAll(
                                        new String[]{"src", "main", "java"},
                                        groupId.split("\\.")
                                )
                        ).toFile()
                );
            }
        }
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

    private static Collection<File> listDir(File path) {
        return FileUtils.listFilesAndDirs(path,
                new NotFileFilter(TrueFileFilter.INSTANCE),
                DirectoryFileFilter.DIRECTORY);
    }
}
