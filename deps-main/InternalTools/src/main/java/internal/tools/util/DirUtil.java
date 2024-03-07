package internal.tools.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

public class DirUtil {

    public static void replacePlaceholderOfFilesInDirectory(
            File directory,
            Map<String, String> placeholders,
            String... fileExtensions
    ) throws Exception {
        for (var file : FileUtils.listFiles(
                directory,
                fileExtensions,
                true)
        ) {
            var content = Files.readString(file.toPath());

            for (var placeholder : placeholders.entrySet()) {
                content = content.replaceAll(placeholder.getKey(), placeholder.getValue());
            }

            Files.write(file.toPath(), content.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static void renameAllSubDirectoriesWithPlaceHolder(
            File masterDirectory,
            Map<String, String> placeholders
    ) throws Exception {
        var map = placeholders.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        b -> b.getValue().replaceAll("\\.", File.separator))
                );
        for (var dir : FileUtils.listFilesAndDirs(masterDirectory,
                new NotFileFilter(TrueFileFilter.INSTANCE),
                DirectoryFileFilter.DIRECTORY)
        ) {
            for (var placeholder : map.entrySet()) {
                if (dir.getName().contains(placeholder.getKey())) {
                    var dirname = dir.getName().replaceAll(placeholder.getKey(), map.get(placeholder.getKey()));
                    Printer.println("DIR", dir.getName(), "replaced with", dirname);
                    FileUtils.moveDirectory(dir, new File(dir.getParentFile().getPath(), dirname));
                }
            }
        }
    }
}
