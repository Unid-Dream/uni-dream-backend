package pwh.springStarter.env;


import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

public abstract class EnvironPostProcessor<C extends EnvironPostProcessor<C>> implements EnvironmentPostProcessor, ApplicationListener<ApplicationEvent> {
    protected abstract DeferredLog getLogger();

    protected abstract Class<C> getType();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        getLogger().replayTo(getClass());
    }

    protected void overrideProperty(ConfigurableEnvironment environment, String key, String defaultValue, boolean overrideIfBlank) {
        getLogger().debug(String.format("Overriding '%s' %s to %s", key, overrideIfBlank ? "(if you have not set)" : "", defaultValue));
        if (overrideIfBlank && StringUtils.isBlank(environment.getProperty(key))) {
            environment.getSystemProperties().put(key, defaultValue);
        } else {
            environment.getSystemProperties().put(key, defaultValue);
        }
    }
}
