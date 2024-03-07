# Sample Usage

## Config
```yaml
cloud:
  aws:
      # below for local development | non-aws env only | or using a aws local profile (IDE Setting)
      credentials:
        access-key: ""
        secret-key: ""
      region:
        static: "ap-southeast-1"
      # above for local development | non-aws env only    
      sqs:
          handler:
            default-deletion-policy: ON_SUCCESS

pwh:
  aws:
    queue: ...
```

## Testing Properties
```java
import lombok.Data;

// no need to make it a component as it will be the 'extra' part of QueueProperties
@Data
public class TestQueueProperties {
    private String testDestination;
}
```

## Testing Payload
```java
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Jacksonized
@NoArgsConstructor
public class TestQueuePayload extends Event {
    @NotBlank
    private String testingMessage;

    @Override
    protected String getStaticUniqueName() {
        // must be globally unique
        // i.e. a static generated UUID would be good enough
        return "ff62a9b0-4805-4c92-8f58-35d61e28ae69";
    }
}
```

## Testing Consumer
```java
@Component
@Slf4j
public class TestQueueConsumer {
    private final EventResolver eventResolver; // inject packaged resolver for resolving input text message into pojo

    @Autowired
    public TestQueueConsumer(EventResolver eventResolver) {
        this.eventResolver = eventResolver;
    }

    // plain text value in demo only
    // in real world it should be a spring prop
    // i.e. "${.....}"
    @SqsListener("https://sqs.ap-northeast-2.amazonaws.com/195524947185/ck_test.fifo")
    public void consume(String input) {
        // globally receive string input
        // code level to determine payload type
        eventResolver.fromInput(input)
                .on(TestQueuePayload.class, payload -> {
                    log.info("Received Queue Message of {}: {}", TestQueuePayload.class.getSimpleName(), payload);
                    // do anything you want with the matched payload
                }) // ... and so on
                .onUnknown(map -> {
                    log.info("Received Unknown Queue Message: {}", map);
                });
    }
}
```

## Testing Producer
```java
@Component
@Slf4j
public class TestQueueProducer {
    private final EventService eventService; // inject packaged resolver for resolving input text message into pojo

    @Autowired
    public TestQueueProducer(EventService eventService) {
        this.eventService = eventService;
    }

    // one of your message sending
    // action specified
    public void sendTestMessage(Action action) {
        eventService.sendAsync(
                () -> {
                    // return your pojo
                    return TestQueuePayload.builder()
                            .testingMessage("yoyoyoTesting")
                            .action(action) // pass along your event action
                            .build();
                },
                (payload) -> {
                    // your payload returned from above will be here
                    // return a unique group id
                    return String.valueOf(payload.getTestingMessage());
                },
                (payload) -> {
                    // return destination
                    return Destination.builder()
                            .destination("")
                            .destinationType(EventType.SNS)
                            .build();
                }
        );
    }
}
```