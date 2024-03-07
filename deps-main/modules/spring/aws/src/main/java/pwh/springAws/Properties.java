package pwh.springAws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration("pwh.springAws.properties")
@ConfigurationProperties(prefix = "pwh.spring-aws")
@Data
@Validated
public class Properties {
}
