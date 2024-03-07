package unid.monoServerApp.queue.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import pwh.springAws.queue.model.Event;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@Validated
public class EducatorMeetingRequestPayload extends Event {
    @NotNull
    private UUID calendarId;
    @NotNull
    private UUID educatorProfileId;

    @Override
    protected String getStaticUniqueName() {
        // must be globally unique
        // i.e. a static generated UUID would be good enough
        return "10386f42-ba51-43d3-b9d1-be5f7330d86f";
    }
}
