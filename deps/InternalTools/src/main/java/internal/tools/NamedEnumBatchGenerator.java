package internal.tools;

import internal.tools.util.Printer;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class NamedEnumBatchGenerator {

    @SneakyThrows
    public static void main(String[] args) {
        Printer.println();
        Printer.printWarning("Generating Batch Named Enum");

        // under /resource | classpath://
        var batchDir = "input/NamedEnumBatch";
        for (var file : FileUtils.listFiles(
                new File(
                        NamedEnumBatchGenerator.class.getClassLoader()
                                .getResource(batchDir)
                                .getFile()
                ),
                TrueFileFilter.TRUE,
                DirectoryFileFilter.INSTANCE
        )) {
            new NamedEnumGenerator(
                    true,
                    file.getName(),
                    FileUtils.readFileToString(
                            file,
                            StandardCharsets.UTF_8
                    )
            );
        }
    }
}
