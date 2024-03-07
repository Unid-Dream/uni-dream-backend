package pwh.springWebSocket.client;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.TriggerContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import pwh.coreStarter.MDCTags;
import pwh.springStarter.cron.CronJobClient;
import pwh.springWebSocket.type.UnifiedWebSocketHandler;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public abstract class UnifiedWebSocketClient extends StandardWebSocketClient implements CronJobClient {
    protected final SuccessConnection successConnection;
    protected final FailConnection failConnection;
    protected UnifiedWebSocketHandler handler;
    protected boolean READY_TO_CONNECT = true;
    protected ListenableFuture<WebSocketSession> session;

    public UnifiedWebSocketClient() {
        super();
        this.successConnection = new SuccessConnection();
        this.failConnection = new FailConnection();
    }

    public UnifiedWebSocketClient(UnifiedWebSocketHandler handler) {
        this();
        this.handler = handler;
    }

    public static void handleLog(WebSocketSession session, Runnable action) {
        MDC.put(MDCTags.INBOUND_TRAFFIC_TRACE_ID, session.getId());
        action.run();
        MDC.remove(MDCTags.INBOUND_TRAFFIC_TRACE_ID);
    }

    public abstract URI getUri();

    public abstract Map<String, List<String>> getHeaders();

    public void connect() {
        if (!READY_TO_CONNECT) {
            return;
        }
        READY_TO_CONNECT = false;
        log.info("Connecting to WebSocket URI: {}", getUri());
        session = doHandshake(
                new Handler(),
                new WebSocketHttpHeaders(
                        new HttpHeaders(
                                new LinkedMultiValueMap<>(ObjectUtils.defaultIfNull(getHeaders(), new HashMap<>()))
                        )
                ),
                getUri()
        );
        session.addCallback(successConnection, failConnection);
    }

    @SneakyThrows
    public void disconnect(boolean forced) {
        if (READY_TO_CONNECT) {
            return;
        }
        if (session != null) {
            session.get().close();
            session.cancel(forced);
            session = null;
        }
        READY_TO_CONNECT = true;
    }

    @SneakyThrows
    private void reconnect() {
        disconnect(true);
        connect();
    }

    @Override
    public String cronName() {
        return handler.name();
    }

    // cron for auto reconnect

    @Override
    public boolean cronShouldStart() {
        return READY_TO_CONNECT;
    }

    @Override
    public void cronStarts() {
        if (READY_TO_CONNECT) {
            connect();
        } else {
            reconnect();
        }
    }

    @Override
    public Optional<Instant> cronNextStart(TriggerContext context) {
        if (READY_TO_CONNECT) {
            return Optional.of(Instant.now().plus(handler.thresholdBeforeReconnecting()));
        }
        return Optional.of(defaultCronNextStart());
    }

    @Override
    public Instant defaultCronNextStart() {
        return Instant.now().plus(Duration.ofSeconds(15));
    }

    private enum Status {
        CONNECTING, CONNECTED, DISCONNECTING, DISCONNECTED
    }

    private class Handler implements WebSocketHandler {

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            READY_TO_CONNECT = false;
            UnifiedWebSocketClient.handleLog(
                    session,
                    () -> {
                        try {
                            log.info("Established To WebSocket: {}", session.getUri());
                            handler.afterConnectionEstablished(session);
                        } catch (Throwable e) {
                            handler.onUnexpectedError(e);
                        }
                    }
            );
        }

        @Override
        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
            UnifiedWebSocketClient.handleLog(
                    session,
                    () -> {
                        try {
                            handler.handleMessage(session, message);
                        } catch (Throwable e) {
                            handler.onUnexpectedError(e);
                        }
                    }
            );
        }

        @Override
        public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
            UnifiedWebSocketClient.handleLog(
                    session,
                    () -> {
                        log.error("Error During WebSocket Session", exception);
                        try {
                            handler.handleTransportError(session, exception);
                        } catch (Throwable e) {
                            handler.onUnexpectedError(e);
                        }
                    }
            );
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
            READY_TO_CONNECT = true;
            UnifiedWebSocketClient.handleLog(
                    session,
                    () -> {
                        log.info("Disconnected With WebSocket With Status: {}", closeStatus);
                        try {
                            handler.afterConnectionClosed(session, closeStatus);
                        } catch (Throwable e) {
                            handler.onUnexpectedError(e);
                        }
                    }
            );
        }

        @Override
        public boolean supportsPartialMessages() {
            return handler.supportsPartialMessages();
        }
    }

    private class SuccessConnection implements SuccessCallback<WebSocketSession> {

        @Override
        public void onSuccess(WebSocketSession session) {
            READY_TO_CONNECT = false;
            UnifiedWebSocketClient.handleLog(
                    session,
                    () -> {
                        log.info("Connected To WebSocket: {}", session.getUri());
                        try {
                            handler.onSuccess(session);
                        } catch (Throwable e) {
                            handler.onUnexpectedError(e);
                        }
                    }
            );
        }
    }

    private class FailConnection implements FailureCallback {

        @Override
        public void onFailure(Throwable ex) {
            log.error("Failed To Connect WebSocket", ex);
            handler.onFailure(ex);
            READY_TO_CONNECT = true;
        }
    }
}
