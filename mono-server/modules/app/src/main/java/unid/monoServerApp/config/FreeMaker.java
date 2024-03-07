package unid.monoServerApp.config;

import freemarker.cache.ClassTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreeMaker {
    @Bean
    public FreeMarkerConfigurer freemarkerClassLoaderConfig() {
        var configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27);
        var templateLoader = new ClassTemplateLoader(this.getClass(), "/email");
        configuration.setTemplateLoader(templateLoader);
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }
}
