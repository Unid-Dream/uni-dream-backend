package unid.monoServerApp.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.context.ApplicationEvent;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.SessionOpType;
import unid.monoServerMeta.model.UserRole;

import java.time.OffsetDateTime;
import java.util.UUID;

public class SessionLogger extends ApplicationEvent {
    private final OpEvent event;

    public SessionLogger(Object source, OpEvent event) {
        super(source);
        this.event = event;
    }

    public OpEvent getEvent() {
        return event;
    }


    @Data
    @FieldNameConstants
    @Builder
    public static class OpEvent{
        private UUID transactionId;
        private UUID userId;
        private BookingStatus status;
        private OffsetDateTime timeUtc;
        private SessionOpType opType;
    }

}
