# Spring Boot Cron Job

## Ordinary Spring Boot Cron

```java
@Scheduled(fixedDelay = 1000)
public void scheduleFixedDelayTask() {
        ...
}
```

## In-house Custom Dynamic Cron (use it if needed to dynamically initial a cron)

```java
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Cron // annotate with this annotation to enable cron client
// must implements `CronJobClient` otherwise error
public class RefreshTokenCron implements CronJobClient {
    private final Store store; // example injected logic service

    @Override
    public String cronName() {
        // global unique cron job name
        return "SAXO Refresh Token";
    }

    @Override
    public boolean cronShouldStart() {
        // to determine if this cron job should start
        return store.getData().get() != null;
    }

    @Override
    public void cronStarts() {
        // actions to perform when starts
        store.refreshToken();
    }

    @Override
    public Optional<Instant> cronNextStart(TriggerContext context) {
        // when this cron job finished, determine next start time
        if (store.getData().get() != null) {
            // example of determine next start time when your own required condition is valid
            return Optional.of(store.getData().get().getAccessTokenExpiry().minus(Duration.ofMinutes(5)));
        }
        /*
         * Example showing determine next start time when your own required condition is invalid
         * # return Optional.empty() to cancel the entire cron job
         * # otherwise return a custom value to periodically re-initiate cron job
         * */
        return Optional.of(Instant.now().plus(Duration.ofSeconds(5)));
        // or
        // return Optional.of(defaultCronNextStart());
    }

    // to handle any abnormal events
    @Override
    public Instant defaultCronNextStart() {
        return Instant.now().plus(Duration.ofSeconds(5));
    }
}
```