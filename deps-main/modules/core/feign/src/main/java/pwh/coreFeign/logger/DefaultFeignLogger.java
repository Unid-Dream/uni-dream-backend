package pwh.coreFeign.logger;

import feign.Logger;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import pwh.coreStarter.MDCTags;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class DefaultFeignLogger extends Logger {
    private final ThreadLocal<Integer> retried = new ThreadLocal<>();
    private final String TRACE_ID = MDCTags.OUTBOUND_TRAFFIC_TRACE_ID;

    @Override
    protected void log(String s, String s1, Object... objects) {
        log.debug("{} {} | {}", s, s1, objects);
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        MDC.put(TRACE_ID, String.format("ob-%s-%s", UUID.randomUUID(), RandomStringUtils.randomAlphanumeric(4)).toLowerCase());
        retried.set(0);
        log.info("Outgoing Request: METHOD:{} | URI:{}", request.httpMethod(), request.url());
        log.info("Outgoing Request: HEADERS:{}", request.headers());
    }

    @Override
    protected void logRetry(String configKey, Level logLevel) {
        retried.set(retried.get() + 1);
        log.info("Retrying: {} attempt(s)", retried.get());
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        log.info("Incoming Response: Elapsed(ms):{} | Status:{} | Reason:{} | Retried:{}", elapsedTime, response.status(), response.reason(), retried.get());
        log.info("Incoming Response: Headers:{}", response.headers());
        MDC.remove(TRACE_ID);
        return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
    }
}
