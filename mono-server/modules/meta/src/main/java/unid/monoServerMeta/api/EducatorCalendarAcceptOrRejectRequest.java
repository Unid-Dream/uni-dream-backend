package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@FieldNameConstants
@Validated
public class EducatorCalendarAcceptOrRejectRequest implements Serializable {

    private UUID educatorCalendarId;
    private List<RejectSession> sessions;


    @Data
    @FieldNameConstants
    public static class RejectSession {
        private UUID studentProfileId;
        private String reason;
    }
}
