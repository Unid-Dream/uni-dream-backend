package pwh.springStarter.cron;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Configuration("pwh.springStarter.cron")
@EnableScheduling
@Slf4j
public class Config implements SchedulingConfigurer {
    private final HashMap<String, CronJobClient> crons = new HashMap<>();
    private final HashMap<String, Instant> cronsLastStart = new HashMap<>();
    private final HashMap<String, Boolean> cronsLastSucceed = new HashMap<>();

    @Autowired
    public Config(ApplicationContext applicationContext) {
        applicationContext.getBeansWithAnnotation(Cron.class).values().forEach(obj -> {
            if (!(obj instanceof CronJobClient)) {
                throw new RuntimeException(
                        String.format("%s has to be an instance of %s",
                                obj.getClass().getName(),
                                CronJobClient.class.getName()
                        )
                );
            }
            var cron = (CronJobClient) obj;
            if (StringUtils.isBlank(cron.cronName())) {
                throw new RuntimeException(
                        String.format("%s has invalid name value %s",
                                obj.getClass().getName(), cron.cronName()
                        )
                );
            }
            if (crons.containsKey(cron.cronName())) {
                throw new RuntimeException(
                        String.format("%s has duplicated name with %s",
                                obj.getClass().getName(),
                                crons.get(cron.cronName()).getClass().getName()
                        )
                );
            }
            crons.put(cron.cronName(), cron);
        });
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        crons.values().forEach(cron -> this.scheduleCron(taskRegistrar, cron));
    }

    public void scheduleCron(
            ScheduledTaskRegistrar taskRegistrar,
            CronJobClient cronJobClient
    ) {
        taskRegistrar.addTriggerTask(
                () -> {
                    cronsLastSucceed.put(cronJobClient.cronName(), false);
                    if (cronJobClient.cronShouldStart()) {
                        log.info("Starting Cron Task '{}'", cronJobClient.cronName());
                        try {
                            cronJobClient.cronStarts();
                            cronsLastSucceed.put(cronJobClient.cronName(), true);
                        } catch (Exception e) {
                            log.error("Cron Task '{}' Failed", cronJobClient.cronName(), e);
                        }
                    }
                },
                context -> {
                    if (cronsLastSucceed.getOrDefault(cronJobClient.cronName(), false)) {
                        log.info("Cron Task '{}' Completed At {} | Scheduled At: {} | Executed At: {}",
                                cronJobClient.cronName(),
                                context.lastCompletionTime(),
                                context.lastScheduledExecutionTime(),
                                context.lastActualExecutionTime()
                        );
                    }
                    var nextStart = cronJobClient.cronNextStart(context);
                    var now = Instant.now();
                    if (nextStart.isPresent()) {
                        // to prevent duplicated next start & cause rapid cron
                        // also to prevent any abnormal next start
                        if (now.compareTo(nextStart.get()) >= 0) {
                            return abnormalNextStart(cronJobClient);
                        }
                        if (cronsLastStart.containsKey(cronJobClient.cronName())) {
                            var lastStart = cronsLastStart.get(cronJobClient.cronName());
                            if (lastStart.compareTo(nextStart.get()) == 0) {
                                return abnormalNextStart(cronJobClient);
                            }
                        }
                        // end prevent any abnormal next start
                        cronsLastStart.put(cronJobClient.cronName(), nextStart.get());
                        return Date.from(nextStart.get());
                    }
                    return null;
                }
        );
    }

    private Date abnormalNextStart(CronJobClient cronJobClient) {
        log.warn("Abnormal Next Start Time For Task '{}'", cronJobClient.cronName());
        return Date.from(cronJobClient.defaultCronNextStart());
    }

}
