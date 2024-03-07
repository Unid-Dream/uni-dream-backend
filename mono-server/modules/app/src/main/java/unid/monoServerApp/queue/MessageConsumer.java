package unid.monoServerApp.queue;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pwh.springAws.queue.resolver.EventResolver;
import unid.monoServerApp.cron.AcceptedMeeting;
import unid.monoServerApp.cron.ExpiredMeeting;
import unid.monoServerApp.cron.PendingPayableMeeting;
import unid.monoServerApp.database.service.TagAppendService;
import unid.monoServerApp.queue.model.*;
import unid.monoServerApp.service.TeamsMeetingService;
import unid.monoServerApp.service.EmailService;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageConsumer {
    private final EventResolver eventResolver;
    private final EmailService emailService;
    private final TagAppendService tagAppendService;
    private final TeamsMeetingService teamsMeetingService;
    private final PendingPayableMeeting pendingPayableMeeting;
    private final AcceptedMeeting acceptedMeeting;
    private final ExpiredMeeting expiredMeeting;

    @SqsListener("${unid.monoserver-app.sqs-main}")
    public void consume(String input) {
        eventResolver.fromInput(input)
                .on(EmailRequestPayload.class, payload -> {
                    log.info("Received Email Request: {}", payload.getMessage().getSubject());
                    emailService.sendEmail(
                            payload.getMessage().getSubject(),
                            payload.getMessage().getContent(),
                            payload.getMessage().getRecipients().toArray(new String[]{})
                    );
                })
                .on(TaggingRequestPayload.class, payload -> {
                    log.info("Received Tagging Request: {}", payload.getMessage().getTargetId());
                    tagAppendService.refresh(
                            payload.getMessage().getTarget(),
                            payload.getMessage().getTargetId(),
                            payload.getMessage().getTags()
                    );
                })
                .on(EducatorMeetingRequestPayload.class, payload -> {
                    log.info("Received Educator Meeting Request: {}", payload.getMessage().getCalendarId());
                    teamsMeetingService.createMeetingWithStudent(payload.getMessage());
                })
                .on(PendingTransactionRequestPayload.class, payload -> {
                    log.info("Handling Pending Transaction: {}", payload.getMessage().getTransactionId());
                    pendingPayableMeeting.handle(payload.getMessage());
                })
                .on(PendingConfirmedMeetingRequestPayload.class, payload -> {
                    log.info("Handling Pending Confirmed Meeting: {}", payload.getMessage().getTransactionId());
                    acceptedMeeting.handle(payload.getMessage());
                })
                .on(ExpiredMeetingRequestPayload.class, payload -> {
                    log.info(
                            "Handling Expired Meeting: {} | {}",
                            payload.getMessage().getTransactionId(),
                            payload.getMessage().getCalendarId()
                    );
                    expiredMeeting.handle(payload.getMessage());
                })
                .onUnknown(jsonNode -> {
                    log.warn("Received Unknown Queue Message: {}", jsonNode);
                });
    }


}
