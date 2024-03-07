package pwh.springStarter.cron;

import org.springframework.scheduling.TriggerContext;

import java.time.Instant;
import java.util.Optional;

public interface CronJobClient {
    String cronName();

    boolean cronShouldStart();

    void cronStarts();

    Optional<Instant> cronNextStart(TriggerContext context); // return Optional.empty() = cancel task

    Instant defaultCronNextStart();

}
