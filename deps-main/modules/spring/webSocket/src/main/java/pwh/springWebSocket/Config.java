package pwh.springWebSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;

@Configuration("pwh.springWebSocket.mainConfig")
@ComponentScan
@Slf4j
class Config {
    @PostConstruct
    @SuppressWarnings("ConstantConditions")
    void postConstruct() {
        log.info("{} Started", AnnotationUtils.findAnnotation(this.getClass(), Configuration.class).value());
    }
}
