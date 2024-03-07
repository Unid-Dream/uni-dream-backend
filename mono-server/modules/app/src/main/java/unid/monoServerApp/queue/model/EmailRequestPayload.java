package unid.monoServerApp.queue.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import pwh.springAws.queue.model.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Jacksonized
@NoArgsConstructor
@Validated
public class EmailRequestPayload extends Event {
    @NotNull
    private Category category;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    @NotNull
    @NotEmpty
    private List<String> recipients;

    @Override
    protected String getStaticUniqueName() {
        // must be globally unique
        // i.e. a static generated UUID would be good enough
        return "ff62a9b0-4805-4c92-8f58-35d61e28ae69";
    }

    public enum Category {
        VERIFY_EMAIL,
        SCHEDULE_CONFIRMATION
    }
}
