package unid.monoServerApp.queue.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import pwh.springAws.queue.model.Event;
import unid.jooqMono.model.enums.TagTargetEnum;
import unid.monoServerApp.database.table.tag.DbTag;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@Validated
public class TaggingRequestPayload extends Event {
    @NotNull
    private TagTargetEnum target;
    @NotNull
    private UUID targetId;
    @NotNull
    private Set<DbTag.Result> tags;

    @Override
    protected String getStaticUniqueName() {
        // must be globally unique
        // i.e. a static generated UUID would be good enough
        return "f620e591-8c1e-4b8f-8275-b023a3c84b88";
    }
}
