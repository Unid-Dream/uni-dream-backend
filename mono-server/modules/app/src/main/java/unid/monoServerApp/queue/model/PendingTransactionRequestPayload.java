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
public class PendingTransactionRequestPayload extends Event {
    @NotNull
    private UUID transactionId;

    @Override
    protected String getStaticUniqueName() {
        // must be globally unique
        // i.e. a static generated UUID would be good enough
        return "f47ac10b-58cc-4372-a567-0e02b2c3d479";
    }
}
