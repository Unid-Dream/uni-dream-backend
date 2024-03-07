package pwh.springWebSocket;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration("pwh.springWebSocket.properties")
@ConfigurationProperties(prefix = "pwh.spring-web-socket")
@Data
@Validated
public class Properties {
}
