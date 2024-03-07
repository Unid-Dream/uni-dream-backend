package pwh.springWebStarter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.SpringDocConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pwh.springWebStarter.controller.HealthController;
import pwh.springWebStarter.customizer.ResponseBodyCustomizer;
import pwh.springWebStarter.customizer.WebContextHolderCustomizer;
import pwh.springWebStarter.filter.AutoConfigFilter;
import pwh.springWebStarter.filter.WebFilter;
import pwh.springWebStarter.openapi.controller.DocumentController;
import pwh.springWebStarter.resolver.GlobalErrorResolver;
import pwh.springWebStarter.response.UnifiedResponseBodyCustomizer;

import javax.annotation.PostConstruct;

@Configuration("pwh.springWebStarter.mainConfig")
@AutoConfigureAfter({SpringDocConfigProperties.class})
@ComponentScan
@Slf4j
class Config {
    @PostConstruct
    @SuppressWarnings("ConstantConditions")
    void postConstruct() {
        log.info("{} Started", AnnotationUtils.findAnnotation(this.getClass(), Configuration.class).value());
    }

    /**
     * configs
     */

    @Bean
    AutoConfigFilter autoConfigFilter() {
        return new AutoConfigFilter();
    }

    @Bean
    WebFilter webFilter(ObjectMapper objectMapper, Properties properties,
                        @Autowired(required = false) WebContextHolderCustomizer webContextHolderCustomizer) {
        return new WebFilter(objectMapper, properties, webContextHolderCustomizer);
    }

    @Bean
    @ConditionalOnMissingBean(ResponseBodyCustomizer.class)
    UnifiedResponseBodyCustomizer responseBodyCustomizer() {
        log.info("Initialising Default Unified Response Body Customizer");
        return new UnifiedResponseBodyCustomizer();
    }

    @Bean
    GlobalErrorResolver globalErrorResolver(ObjectMapper objectMapper, ResponseBodyCustomizer responseBodyCustomizer) {
        log.info("Initialising Global Error Resolver");
        return new GlobalErrorResolver(objectMapper, responseBodyCustomizer);
    }

    @Bean
    WebMvcConfigurer webConfigurer(GlobalErrorResolver globalErrorResolver) {
        return new WebConfigurer(globalErrorResolver);
    }

    /**
     * controllers
     */

    @Bean
    HealthController healthController() {
        log.info("Initialising Health Check Controller");
        return new HealthController();
    }

    @Bean
    @ConditionalOnBean(SpringDocConfigProperties.class)
    DocumentController documentController(Properties properties) {
        log.info("Initialising API Doc Controller");
        return new DocumentController(properties);
    }

    /**
     * services
     * */
}
