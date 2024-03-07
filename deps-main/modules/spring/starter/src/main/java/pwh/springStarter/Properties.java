package pwh.springStarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration("pwh.springStarter.properties")
@ConfigurationProperties(prefix = "pwh.spring-starter")
@Data
@Validated
public class Properties {
}
