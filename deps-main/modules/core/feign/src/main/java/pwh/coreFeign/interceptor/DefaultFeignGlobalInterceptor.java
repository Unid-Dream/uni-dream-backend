package pwh.coreFeign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/*
 * Can be stacked
 * This Interceptor should be applied at last
 * */
@Slf4j
public class DefaultFeignGlobalInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.debug("Applying Global Interceptor");
    }
}
