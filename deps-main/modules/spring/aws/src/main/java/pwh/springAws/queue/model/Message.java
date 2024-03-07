package pwh.springAws.queue.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@FieldNameConstants
@Validated
public class Message<M extends Event> {
    @Nullable
    private SNS snsProperties;
    @NotNull
    @Valid
    private M message;

    public boolean isSnsMessage() {
        return snsProperties != null;
    }

    @Data
    @SuperBuilder
    @Jacksonized
    @NoArgsConstructor
    @FieldNameConstants
    public static class SNS {
        public static final String Type = "Type";
        public static final String MessageId = "MessageId";
        public static final String SequenceNumber = "SequenceNumber";
        public static final String TopicArn = "TopicArn";
        public static final String Timestamp = "Timestamp";
        public static final String UnsubscribeURL = "UnsubscribeURL";
        public static final String Message = "Message";
        @JsonProperty(Type)
        private String type;
        @JsonProperty(MessageId)
        private String messageId;
        @JsonProperty(SequenceNumber)
        private String sequenceNumber;
        @JsonProperty(TopicArn)
        private String topicArn;
        @JsonProperty(Timestamp)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private LocalDateTime timestamp;
        @JsonProperty(UnsubscribeURL)
        private String unsubscribeURL;
        // MessageAttributes: not handled
    }
}
