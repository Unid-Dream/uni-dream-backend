package pwh.springFeign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration("pwh.springFeign.properties")
@ConfigurationProperties(prefix = "pwh.spring-feign")
@Data
@Validated
public class Properties {
}
