# WebSocket Client


## Spring Component way
```java
@Component // optionally make it a spring component
@Cron // optionally annotated this to enable scheduled auto features like auto-reconnect
@Slf4j
public class SampleWebSocketClient extends UnifiedWebSocketClient {
    private final Store store; // example injecting dependency

    @Autowired
    public SampleWebSocketClient(
            Store store
    ) {
        super(
                new UnifiedWebSocketHandler() {

                    @Override
                    public void onUnexpectedError(Throwable throwable) {
                        // any abnormal error
                    }

                    @Override
                    public Duration thresholdBeforeReconnecting() {
                        return Duration.ofSeconds(10); // only works for when annotated with @Cron
                    }

                    @Override
                    public String name() {
                        return SaxoWebSocketClient.class.getSimpleName();
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        READY_TO_CONNECT = false; // you can always mark this as false to make auto-reconnect feature holds
                        // when disconnecting
                    }

                    @Override
                    public void onSuccess(WebSocketSession result) {
                        // when connected
                    }

                    @Override
                    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                        // after connected
                        // ... do anything
                    }

                    @Override
                    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                        
                    }

                    @Override
                    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
                        // error during connected session
                    }

                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                    }

                    @Override
                    public boolean supportsPartialMessages() {
                        return false;
                    }
                }
        );
        this.store = store;
    }

    @PostConstruct
    void pc() {
        // optionally start immediately on startup
        this.connect();
    }

    @Override
    public URI getUri() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("wss")
                .host("hahaha.com")
                .encode(StandardCharsets.UTF_8)
                .build(true)
                .toUri();
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        // return null if no headers
        return new HashMap<>() {{
            put(
                    "Authorization",
                    List.of(
                            String.format("Bearer %s",
                                    store.getData().get().getAccessToken())
                    )
            );
        }};
    }
}
```