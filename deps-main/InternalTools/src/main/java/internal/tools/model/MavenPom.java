package internal.tools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MavenPom {
    private MavenParentBlock parentBlock;
    private String groupId;
    private String artifactId;
    private String version;
    private Map<String, String> properties;

    public boolean hasParent() {
        return ObjectUtils.isNotEmpty(parentBlock);
    }

    public boolean hasProperties() {
        return ObjectUtils.isNotEmpty(properties);
    }

    public String getVersion() {
        if (StringUtils.isNotBlank(version)) {
            if (!isVarRef(version)) {
                return version;
            }
            return getInnerProperties(version);
        }
        return null;
    }

    private boolean isVarRef(String value) {
        var result = value.startsWith("${") && value.endsWith("}");
        return result;
    }

    private String extractVarRef(String value) {
        return StringUtils.substringBetween(value, "${", "}");
    }

    private String getInnerProperties(String ref) {
        if (isVarRef(ref)) {
            return getInnerProperties(extractVarRef(ref));
        }
        var value = properties.get(ref);
        if (isVarRef(value)) {
            return getInnerProperties(extractVarRef(value));
        }
        return value;
    }
}
