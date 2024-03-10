package pwh.

        springWebStarter.env;

import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import pwh.springStarter.env.EnvironPostProcessor;
import pwh.springStarter.util.SpringPropsUtil;
import pwh.springWebStarter.Properties;

import java.util.Optional;

@Component("pwh.springWebStarter.EnvironPostProcessor")
public class EnvPostProcessor extends EnvironPostProcessor<EnvPostProcessor> {
    private static final DeferredLog log = new DeferredLog();
    private final Properties props = new Properties(); // simulating default props (those with default value)

    @SneakyThrows
    private Optional<String> getPropValue(ConfigurableEnvironment environment, String propName) {
        var clazz = props.getClass();
        var propFullQualifiedName = SpringPropsUtil.getPropertyFullQualifiedName(clazz, propName);
        var userDefinedValue = environment.getProperty(propFullQualifiedName);
        if (StringUtils.isNotBlank(userDefinedValue)) {
            return Optional.of(userDefinedValue);
        }
        var defaultValue = FieldUtils.getDeclaredField(clazz, propName, true);
        if (ObjectUtils.isNotEmpty(defaultValue)) {
            return Optional.of(defaultValue.get(props).toString());
        }
        return Optional.empty();
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        overrideProperty(
                environment,
                "spring.mvc.static-path-pattern",
                "/static/**",
                false
        );
        overrideProperty(
                environment,
                "spring.mvc.throw-exception-if-no-handler-found",
                "true",
                false
        );
        // ref: https://springdoc.org/#properties
        overrideProperty(environment,
                "springdoc.swagger-ui.enabled",
                "true", //是否开启swagger-ui
                false
        );
        overrideProperty(environment,
                "springdoc.api-docs.enabled",
                getPropValue(environment, "adminDocEnabled").get(),// it has default value
                false
        );
        overrideProperty(environment,
                "springdoc.api-docs.path",
                String.format(
                        "%s%s%s/meta",
                        getPropValue(environment, "adminUrlPrefix").get(), // it has default value
                        getPropValue(environment, "adminDocPath").get(),// it has default value
                        getPropValue(environment, "adminDocApiPath").get()// it has default value
                ),
                false
        );
    }

    @Override
    protected DeferredLog getLogger() {
        return log;
    }

    @Override
    protected Class<EnvPostProcessor> getType() {
        return EnvPostProcessor.class;
    }
}
