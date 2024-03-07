package pwh.springAws.queue.resolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pwh.springAws.annotation.QueueEnabled;
import pwh.springAws.queue.model.Event;
import pwh.springAws.queue.model.Message;
import pwh.springStarter.service.ValidationService;

import java.util.function.Consumer;

@Component
@QueueEnabled
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EventResolver {
    private final ObjectMapper objectMapper;
    private final ValidationService validationService;

    @SneakyThrows
    public PayloadResolver fromInput(String input) {
        var node = objectMapper.readValue(input, JsonNode.class);
        return new PayloadResolver(node, objectMapper, validationService);
    }

    @RequiredArgsConstructor
    public static class PayloadResolver {
        private final JsonNode input;
        private final ObjectMapper objectMapper;
        private final ValidationService validationService;
        private JsonNode message;
        private boolean anyMatched = false;

        public <M extends Event> PayloadResolver on(Class<M> type, Consumer<Message<M>> action) {
            if (anyMatched) {
                return this;
            }
            if (message == null) {
                message = getMessage();
            }
            if (is(type)) {
                var pojo = from(type);
                validationService.validate(pojo.getMessage());
                action.accept(pojo);
                anyMatched = true;
            }
            return this;
        }

        public void onUnknown(Consumer<JsonNode> action) {
            if (anyMatched) {
                return;
            }
            action.accept(input);
        }

        @SneakyThrows
        private <M extends Event> boolean is(Class<M> type) {
            var messageType = message.get(Event.EventFields.messageType);
            if (messageType == null) {
                return false;
            }
            return messageType.textValue().equals(type.getDeclaredConstructor().newInstance().getMessageType());
        }

        @SneakyThrows
        private <M extends Event> Message<M> from(Class<M> type) {
            log.debug(
                    "Parsing ({}): {}",
                    type.getSimpleName(),
                    objectMapper.writeValueAsString(input)
            );
            var messageBuilder = Message.<M>builder();
            if (isNonRawSns()) {
                messageBuilder.snsProperties(objectMapper.convertValue(input, new TypeReference<>() {
                }));
            }
            messageBuilder.message(objectMapper.convertValue(message, type));
            return messageBuilder.build();
        }

        private boolean isNonRawSns() {
            return input.get(Message.SNS.UnsubscribeURL) != null
                    && input.get(Message.SNS.Type) != null
                    && input.get(Message.SNS.TopicArn) != null
                    && input.get(Message.SNS.MessageId) != null;
        }

        @SneakyThrows
        private JsonNode getMessage() {
            if (isNonRawSns()) {
                return objectMapper.readTree(input.get(Message.SNS.Message).textValue());
            }
            return input;
        }
    }
}
