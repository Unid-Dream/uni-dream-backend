package pwh.springAws.queue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants(innerTypeName = "EventFields")
@Validated
public abstract class Event {
    private final String messageType = String.format("%s_%s",
            this.getClass().getSimpleName(),
            this.getStaticUniqueName()
    );
    @NotNull
    protected Action action;
    @Nullable
    protected OffsetDateTime lastUpdated;

    @JsonIgnore
    protected abstract String getStaticUniqueName();
}
