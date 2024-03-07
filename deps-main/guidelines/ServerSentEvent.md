# SSE
```java
package kingship.priceEngineApp.api.sse;

import kingship.priceEngineApp.feed.provider.RawPriceFeed;
import kingship.priceEngineApp.feed.provider.TradablePriceFeed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pwh.springWebStarter.response.UnifiedResponse;

@RestController
@RequestMapping("sse/pricefeed")
@Validated
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceFeedController {
    private final RawPriceFeed rawPriceFeed; // example injected dependency

    // start event
    @GetMapping("/raw")
    public SseEmitter getRawPriceFeed() {
        return null; // a valid SseEmitter instance;
    }

    // stop event
    @DeleteMapping("/raw")
    public UnifiedResponse<Void> removeRawPriceFeed() {
        // do your magic
        return UnifiedResponse.of(null);
    }
}

```