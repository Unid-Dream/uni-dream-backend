package unid.monoServerApp.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pwh.springAws.queue.model.Action;
import pwh.springAws.queue.model.Destination;
import pwh.springAws.queue.model.EventType;
import pwh.springAws.queue.service.EventService;
import pwh.springStarter.service.ValidationService;
import unid.monoServerApp.Properties;
import unid.monoServerApp.queue.model.*;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageProducer {
    private final EventService eventService;
    private final Properties properties;
    private final ValidationService validationService;

    public void sendEmailRequest(EmailRequestPayload requestPayload) {
        eventService.send(
                () -> {
                    requestPayload.setAction(Action.CREATE);
                    validationService.validate(requestPayload);
                    return requestPayload;
                },
                (payload) -> payload.getCategory().toString(),
                null,
                (payload) -> Destination.builder()
                        .destination(properties.getSnsEmail())
                        .destinationType(EventType.SNS)
                        .build()
        );
    }

    public void sendTaggingRequest(TaggingRequestPayload requestPayload) {
        eventService.send(
                () -> {
                    requestPayload.setAction(Action.CREATE);
                    requestPayload.setLastUpdated(OffsetDateTime.now());
                    validationService.validate(requestPayload);
                    return requestPayload;
                },
                (payload) -> requestPayload.getTarget().toString(),
                null,
                (payload) -> Destination.builder()
                        .destination(properties.getSnsTagging())
                        .destinationType(EventType.SNS)
                        .build()
        );
    }

    public void sendEducatorMeeting(EducatorMeetingRequestPayload requestPayload) {
        eventService.send(
                () -> {
                    requestPayload.setAction(Action.CREATE);
                    requestPayload.setLastUpdated(OffsetDateTime.now());
                    validationService.validate(requestPayload);
                    return requestPayload;
                },
                (payload) -> String.format("%s-%s", requestPayload.getEducatorProfileId(), requestPayload.getCalendarId()),
                null,
                (payload) -> Destination.builder()
                        .destination(properties.getSnsEducatorMeeting())
                        .destinationType(EventType.SNS)
                        .build()
        );
    }

    public void sendPendingTransaction(PendingTransactionRequestPayload requestPayload) {
        eventService.send(
                () -> {
                    requestPayload.setAction(Action.CREATE);
                    requestPayload.setLastUpdated(OffsetDateTime.now());
                    validationService.validate(requestPayload);
                    return requestPayload;
                },
                (payload) -> "Pending Transaction",
                null,
//                (payload) -> requestPayload.getTransactionId().toString(),
                (payload) -> Destination.builder()
                        .destination(properties.getSnsPendingTransaction())
                        .destinationType(EventType.SNS)
                        .build()
        );
    }

    public void sendPendingConfirmedMeeting(PendingConfirmedMeetingRequestPayload requestPayload) {
        eventService.send(
                () -> {
                    requestPayload.setAction(Action.CREATE);
                    requestPayload.setLastUpdated(OffsetDateTime.now());
                    validationService.validate(requestPayload);
                    return requestPayload;
                },
                (payload) -> "Pending Confirmed Meeting",
                null,
//                (payload) -> requestPayload.getTransactionId().toString(),
                (payload) -> Destination.builder()
                        .destination(properties.getSnsPendingConfirmedMeeting())
                        .destinationType(EventType.SNS)
                        .build()
        );
    }

    public void sendExpiredMeeting(ExpiredMeetingRequestPayload requestPayload) {
        eventService.send(
                () -> {
                    requestPayload.setAction(Action.CREATE);
                    requestPayload.setLastUpdated(OffsetDateTime.now());
                    validationService.validate(requestPayload);
                    return requestPayload;
                },
                (payload) -> "Expired Meeting",
                null,
//                (payload) -> requestPayload.getTransactionId().toString(),
                (payload) -> Destination.builder()
                        .destination(properties.getSnsExpiredMeeting())
                        .destinationType(EventType.SNS)
                        .build()
        );
    }
}
