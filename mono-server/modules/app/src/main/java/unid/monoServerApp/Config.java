package unid.monoServerApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pwh.springWebStarter.resolver.GlobalErrorResolver;
import unid.monoServerApp.http.WebConfig;

import javax.annotation.PostConstruct;

@Configuration("unid.monoServerApp.mainConfig")
@ComponentScan
@Slf4j
class Config {
    @PostConstruct
    @SuppressWarnings("ConstantConditions")
    void postConstruct() {
        log.info("{} Started", AnnotationUtils.findAnnotation(this.getClass(), Configuration.class).value());
    }

    @Bean
    @Primary
    WebMvcConfigurer webMvcConfigurer(GlobalErrorResolver globalErrorResolver) {
        return new WebConfig(globalErrorResolver);
    }
}
