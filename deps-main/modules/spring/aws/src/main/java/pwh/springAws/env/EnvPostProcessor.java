package pwh.springAws.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import pwh.springStarter.env.EnvironPostProcessor;

@Component("pwh.springAws.EnvironPostProcessor")
public class EnvPostProcessor extends EnvironPostProcessor<EnvPostProcessor> {
    private static final DeferredLog log = new DeferredLog();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        overrideProperty(
                environment,
                "cloud.aws.sqs.handler.default-deletion-policy",
                "ON_SUCCESS",
                true
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
