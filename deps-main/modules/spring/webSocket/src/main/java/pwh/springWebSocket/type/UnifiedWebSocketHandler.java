package pwh.springWebSocket.type;

import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import java.time.Duration;

public interface UnifiedWebSocketHandler extends WebSocketHandler, SuccessCallback<WebSocketSession>, FailureCallback {
    void onUnexpectedError(Throwable exception);

    Duration thresholdBeforeReconnecting();

    String name();
}
