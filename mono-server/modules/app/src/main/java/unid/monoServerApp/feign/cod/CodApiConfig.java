package unid.monoServerApp.feign.cod;

import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import pwh.coreFeign.interceptor.UltraRequestInterceptor;

import java.util.HashMap;
import java.util.function.Consumer;

@Slf4j
public class CodApiConfig {

    @Bean
    public UltraRequestInterceptor requestInterceptor() {
        return new UltraRequestInterceptor() {
            @Override
            public void onRequest(RequestTemplate requestTemplate) {
                // do any normal request intercepts
            }

            // to work with @FeignAction
            @Override
            public void feignActions(HashMap<String, Consumer<RequestTemplate>> map) {
//                map.put(Actions.BASIC_AUTH, requestTemplate -> {
//                    new BasicAuthRequestInterceptor(
//                            properties.getAppKey(),
//                            properties.getAppSecrets().get(0)
//                    ).apply(requestTemplate);
//                });
            }
        };
    }
}
