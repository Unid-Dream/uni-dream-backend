package pwh.springFeign;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import feign.FeignException;
import feign.Response;
import feign.Retryer;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.httpclient.ApacheHttpClient;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignBuilderCustomizer;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import pwh.coreFeign.Constant;
import pwh.coreFeign.util.FeignUtil;
import pwh.springStarter.converter.GenericEnumConvertor;
import pwh.springStarter.converter.NamedEnumConvertor;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration("pwh.springFeign.mainConfig")
@ComponentScan
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
class Config {
    private final ObjectMapper objectMapper;

    @PostConstruct
    @SuppressWarnings("ConstantConditions")
    void postConstruct() {
        log.info("{} Started", AnnotationUtils.findAnnotation(this.getClass(), Configuration.class).value());
    }

    // override this to customize
    @Bean
    @Qualifier(Constant.PRIMARY_HTTP_CLIENT_QUALIFIER)
    public Client primaryClient() {
        return FeignUtil.defaultOkHttpClient();
    }

    // override this to customize
    @Bean
    @Qualifier(Constant.PRIMARY_RETRYER_QUALIFIER)
    public Retryer primaryRetryer() {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(1L), 2);
    }

    @Bean
    public FeignBuilderCustomizer feignBuilderCustomizer(
            @Qualifier(Constant.PRIMARY_HTTP_CLIENT_QUALIFIER) Client client,
            @Qualifier(Constant.PRIMARY_RETRYER_QUALIFIER) Retryer retryer
    ) {
        // inject bean & pass to util
        return c -> {
            c.queryMapEncoder(o -> objectMapper.convertValue(o, new TypeReference<>() {
            }));
            FeignUtil.defaultConfiguredBuilder(c, client, retryer);
        };
    }

    @Bean
    public FeignFormatterRegistrar feignFormatterRegistrar() {
        return formatterRegistry -> {
            log.info("Adding Formatter: {}", NamedEnumConvertor.class.getSimpleName());
            formatterRegistry.addConverterFactory(new NamedEnumConvertor.FromString());
            formatterRegistry.addConverterFactory(new NamedEnumConvertor.ToString());
            log.info("Adding Formatter: {}", GenericEnumConvertor.class.getSimpleName());
            formatterRegistry.addConverterFactory(new GenericEnumConvertor.FromString());
            formatterRegistry.addConverterFactory(new GenericEnumConvertor.ToString());
        };
    }
}
