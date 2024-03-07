package pwh.springStarter.env;


import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("StarterEnvPostProcessor")
public class EnvPostProcessor extends EnvironPostProcessor<EnvPostProcessor> {
    private static final DeferredLog log = new DeferredLog();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var springActiveProfile = "spring.profiles.active";
        var activeProfile = environment.getProperty(springActiveProfile);
        if (StringUtils.isBlank(activeProfile)) {
            throw new RuntimeException(
                    String.format("You have to assign a default active profile (%s) to the application!", springActiveProfile)
            );
        }
        var validProfiles = List.of("default", "local");
        if (!validProfiles.contains(activeProfile)) {
            throw new RuntimeException(
                    String.format("Valid values to active profile (%s): %s | Yours: %s", springActiveProfile, validProfiles, activeProfile)
            );
        }
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
