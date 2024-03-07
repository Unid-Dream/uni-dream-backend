package internal.tools.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import internal.tools.model.MavenPom;
import lombok.SneakyThrows;

import java.io.File;

public class MavenUtil {
    @SneakyThrows
    public static MavenPom readPom(File pom) {
        return new XmlMapper().readValue(pom, MavenPom.class);
    }
}
