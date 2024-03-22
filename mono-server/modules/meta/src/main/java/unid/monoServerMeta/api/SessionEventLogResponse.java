package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.SessionOpType;
import unid.monoServerMeta.model.UserRole;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@FieldNameConstants
@Data
public class SessionEventLogResponse {

    private List<SessionEventLogPayload> payload;

    @Data
    @FieldNameConstants
    public static class SessionEventLogPayload{
        private UUID id;
        private OffsetDateTime timeUtc;
        private UserPayload user;
        private BookingStatus status;
        private SessionOpType opType;



        @Data
        @FieldNameConstants
        public static class UserPayload{
            private UUID id;
            private I18n firstNameI18n;
            private I18n lastNameI18n;
            private UserRole userRole;
        }
    }
}
