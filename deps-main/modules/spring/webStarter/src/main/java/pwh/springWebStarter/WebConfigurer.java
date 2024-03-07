package pwh.springWebStarter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pwh.springStarter.converter.GenericEnumConvertor;
import pwh.springStarter.converter.NamedEnumConvertor;
import pwh.springWebStarter.resolver.GlobalErrorResolver;

import java.util.List;

@Slf4j
public class WebConfigurer implements WebMvcConfigurer {
    private final GlobalErrorResolver globalErrorResolver;

    public WebConfigurer(
            GlobalErrorResolver globalErrorResolver
    ) {
        this.globalErrorResolver = globalErrorResolver;
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.clear();
        resolvers.add(0, globalErrorResolver);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.info("Adding Formatter: {}", NamedEnumConvertor.class.getSimpleName());
        registry.addConverterFactory(new NamedEnumConvertor.FromString());
        registry.addConverterFactory(new NamedEnumConvertor.ToString());
        log.info("Adding Formatter: {}", GenericEnumConvertor.class.getSimpleName());
        registry.addConverterFactory(new GenericEnumConvertor.FromString());
        registry.addConverterFactory(new GenericEnumConvertor.ToString());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
