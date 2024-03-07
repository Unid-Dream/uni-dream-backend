# Validation Service

```java
package kingship.priceEngineApp.fake9;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kingship.priceEngineApp.type.SystemConfig;
import kingship.priceEngineMeta.type.Symbol;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.SmartValidator;
import pwh.springStarter.service.ValidationService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SomeComponent {
    private final ValidationService validationService;

    @PostConstruct
    void pc() {
        validationService.validate(someObject); // validate & throw related errors automatically
    }
}
```