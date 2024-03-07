package pwh.springAws.queue.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.util.Base64;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.core.TopicMessageChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import pwh.springAws.annotation.QueueEnabled;
import pwh.springAws.queue.model.Destination;
import pwh.springAws.queue.model.Event;
import pwh.springAws.queue.model.EventType;
import pwh.springStarter.service.ValidationService;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Slf4j
@QueueEnabled
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {
    private final NotificationMessagingTemplate notificationMessagingTemplate;
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ValidationService validationService;

    public <M extends Event> void sendAsync(
            Supplier<M> message,
            Function<M, String> groupId,
            @Nullable Function<M, String> deduplicateId,
            Function<M, Destination> destination
    ) {
        sendWithTransactionalAwareness(() -> CompletableFuture.runAsync(() -> sendToQueue(message, groupId, deduplicateId, destination)));
    }

    public <M extends Event> void send(
            Supplier<M> message,
            Function<M, String> groupId,
            @Nullable Function<M, String> deduplicateId,
            Function<M, Destination> destination
    ) {
        sendWithTransactionalAwareness(() -> sendToQueue(message, groupId, deduplicateId, destination));
    }

    private void sendWithTransactionalAwareness(Runnable runnable) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                public void afterCommit() {
                    runnable.run();
                }
            });
        } else {
            runnable.run();
        }
    }

    private <M extends Event> void sendToQueue(
            Supplier<M> message,
            Function<M, String> groupId,
            @Nullable Function<M, String> deduplicateId,
            Function<M, Destination> destination
    ) {
        var payload = message.get();
        validationService.validate(payload);
        var queueId = Base64.encodeAsString(groupId.apply(payload).getBytes(StandardCharsets.UTF_8));
        var dedupeId = Optional.ofNullable(deduplicateId)
                .map(dedupe -> Base64.encodeAsString(dedupe.apply(payload).getBytes(StandardCharsets.UTF_8)))
                .orElse(null);
        var target = destination.apply(payload);
        validationService.validate(target);
        log.debug("Sending Queue Message To Destination {} With Group ID {} & Dedupe ID: {}: {}",
                target, queueId, dedupeId, payload);
        var headers = new HashMap<String, Object>();
        headers.put(TopicMessageChannel.MESSAGE_GROUP_ID_HEADER, queueId);
        if(dedupeId != null) {
            headers.put(TopicMessageChannel.MESSAGE_DEDUPLICATION_ID_HEADER, dedupeId);
        }
        if (EventType.SNS.equals(target.getDestinationType())) {
            notificationMessagingTemplate.convertAndSend(target.getDestination(), payload, headers);
        } else {
            queueMessagingTemplate.convertAndSend(target.getDestination(), payload, headers);
        }
    }

}
