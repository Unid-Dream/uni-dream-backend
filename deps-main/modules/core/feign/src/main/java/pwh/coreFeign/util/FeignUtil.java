package pwh.coreFeign.util;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.Decoder;
import feign.okhttp.OkHttpClient;
import lombok.extern.slf4j.Slf4j;
import pwh.coreFeign.interceptor.DefaultFeignGlobalInterceptor;
import pwh.coreFeign.logger.DefaultFeignLogger;

import java.util.concurrent.TimeUnit;

@Slf4j
public class FeignUtil {
    public static Feign.Builder defaultConfiguredBuilder(
            Feign.Builder builder,
            Client client,
            Retryer retryer
    ) {
        // mandatory config
        // create bean and pass in
        log.info("Feign Using Client: {}", client);
        return builder
                .decode404()
                .client(client)
                .requestInterceptor(new DefaultFeignGlobalInterceptor())
                .retryer(retryer)
                .logLevel(Logger.Level.BASIC)
                .logger(new DefaultFeignLogger());
    }

    public static OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient();
    }
}
