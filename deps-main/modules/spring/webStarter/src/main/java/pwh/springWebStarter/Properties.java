package pwh.springWebStarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Configuration("pwh.springWebStarter.properties")
@ConfigurationProperties(prefix = "pwh.spring-web-starter")
@Data
@Validated
public class Properties {
    @NotBlank
    @Pattern(regexp = "^(/[a-zA-Z]+)+$")
    // sync with env post processor
    private final String staticUrlPrefix = "/static";
    private List<String> incomingRequestLogExclusions;
    @NotBlank
    @Pattern(regexp = "^(/[a-zA-Z]+)+$")
    private String adminUrlPrefix = "/admin";
    @NotBlank
    @Pattern(regexp = "^(/[a-zA-Z]+)+$")
    private String adminHealthCheckPath = "/healthCheck";
    private boolean adminDocEnabled = true;
    @NotBlank
    @Pattern(regexp = "^(/[a-zA-Z]+)+$")
    private String adminDocPath = "/doc";
    @NotBlank
    @Pattern(regexp = "^(/[a-zA-Z]+)+$")
    private String adminDocApiPath = "/api";
}
