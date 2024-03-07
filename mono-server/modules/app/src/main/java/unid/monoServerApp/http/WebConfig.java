package unid.monoServerApp.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import pwh.springWebStarter.WebConfigurer;
import pwh.springWebStarter.resolver.GlobalErrorResolver;

@Slf4j
public class WebConfig extends WebConfigurer {
    public WebConfig(GlobalErrorResolver globalErrorResolver) {
        super(globalErrorResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResponseInterceptor());
        super.addInterceptors(registry);
    }
}
