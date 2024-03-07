package internal.tools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class GeneratorJson {
    private String destination;
    private String groupId;
    private String artifactIdPrefix;
    private boolean includeTool;
    private List<String> includeTemplates;
}
