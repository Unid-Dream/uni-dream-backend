package pwh.springStarter.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
public class SpringPropsUtil {

    public static String getPropertiesPrefix(Class<?> propertiesClass) {
        var annotations = propertiesClass.getDeclaredAnnotations();
        for (var annotation : annotations) {
            if (annotation.annotationType().isAssignableFrom(ConfigurationProperties.class)) {
                return ((ConfigurationProperties) annotation).prefix();
            }
        }
        throw new RuntimeException(
                String.format(
                        "Given class %s is not a valid Spring-Properties class",
                        propertiesClass
                )
        );
    }

    public static String getPropertyFullQualifiedName(Class<?> propertiesClass, String name) {
        var prefix = getPropertiesPrefix(propertiesClass);
        if (FieldUtils.getDeclaredField(propertiesClass, name, true) == null) {
            throw new RuntimeException(
                    String.format(
                            "Given properties class %s has no such field %s ",
                            propertiesClass,
                            name
                    )
            );
        }
        name = name.replaceAll("(.)(\\p{Upper})", "$1-$2").toLowerCase();
        return String.format("%s.%s", prefix, name);
    }
}
