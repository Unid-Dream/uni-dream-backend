package pwh.springWebStarter.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.logging.MDC;
import org.springframework.http.server.PathContainer;
import org.springframework.web.util.pattern.PathPatternParser;
import pwh.coreStarter.MDCTags;
import pwh.springWebStarter.Properties;
import pwh.springWebStarter.customizer.WebContextHolderCustomizer;
import pwh.springWebStarter.holder.WebContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * MEANT TO BE THE FIRST FILTER OF ANY HTTP TRAFFIC
 * Sequence: FIRST PLACE
 */

@Slf4j
public class WebFilter implements Filter {
    private final String TRACE_ID_TAG = MDCTags.INBOUND_TRAFFIC_TRACE_ID;
    private final ObjectMapper objectMapper;
    private final Properties properties;
    private final WebContextHolderCustomizer webContextHolderCustomizer;

    public WebFilter(@NotNull ObjectMapper objectMapper, @NotNull Properties properties, WebContextHolderCustomizer webContextHolderCustomizer) {
        this.objectMapper = objectMapper;
        this.properties = properties;
        this.webContextHolderCustomizer = webContextHolderCustomizer;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        try {
            var request = (HttpServletRequest) servletRequest;
            var response = (HttpServletResponse) servletResponse;
            var shouldLog = shouldLog(request);
            if (!WebContextHolder.isInited()) {
                var holder = new WebContextHolder.ContextHolder(
                        String.format("ib-%s-%s", UUID.randomUUID(), RandomStringUtils.randomAlphanumeric(4).toLowerCase()),
                        request,
                        response
                );
                if (webContextHolderCustomizer != null) {
                    holder = webContextHolderCustomizer.customize(holder);
                }
                WebContextHolder.set(holder);
            }
            if (ObjectUtils.isEmpty(MDC.get(TRACE_ID_TAG))) {
                MDC.put(TRACE_ID_TAG, WebContextHolder.get().getTraceId());
            }
            if (shouldLog) {
                log.info("Incoming request | METHOD:{} | URI:{} | QUERY:{}",
                        request.getMethod(),
                        request.getRequestURI(),
                        request.getQueryString());
            }
            var headers = Collections.list(request.getHeaderNames()).stream()
                    .map(headerName -> List.of(headerName, request.getHeader(headerName)))
                    .collect(Collectors.toMap(header ->
                                    header.get(0),
                            header -> header.get(1),
                            (a, b) -> String.format("%s, %s", a, b)));
            if (shouldLog) {
                log.info("Incoming request | HEADERS:{}", objectMapper.writeValueAsString(headers));
            }

            filterChain.doFilter(servletRequest, servletResponse);

            if (shouldLog) {
                log.info("Outgoing response | STATUS:{}", response.getStatus());
            }
            var responseHeaders = response.getHeaderNames().stream()
                    .map(headerName -> List.of(headerName, response.getHeader(headerName)))
                    .collect(Collectors.toMap(
                            header -> header.get(0),
                            header -> header.get(1),
                            (a, b) -> String.format("%s, %s", a, b)));
            if (shouldLog) {
                log.info("Outgoing response | HEADERS:{}", objectMapper.writeValueAsString(responseHeaders));
            }
        } finally {
            WebContextHolder.set(null);
            MDC.clear();
        }
    }

    private boolean shouldLog(HttpServletRequest request) {
        if (ObjectUtils.isEmpty(properties.getIncomingRequestLogExclusions())) {
            return true;
        }
        var url = request.getRequestURI();
        var parsed = PathContainer.parsePath(url);
        log.debug("WebFilter Log Exclusions Target URL: {}", parsed);
        for (var exclusion : properties.getIncomingRequestLogExclusions()) {
            var rule = PathPatternParser.defaultInstance.parse(exclusion);
            log.debug("WebFilter Log Exclusions Rule: {}", rule);
            var matched = rule.matches(parsed);
            log.debug("WebFilter Log Exclusions Result: {}", matched);
            if (matched) {
                return false;
            }
        }
        return true;
    }
}
