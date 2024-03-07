package pwh.springWebStarter.response;

import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.customizer.ResponseBodyCustomizer;
import pwh.springWebStarter.holder.WebContextHolder;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UnifiedResponseBodyCustomizer extends ResponseBodyCustomizer<UnifiedResponse> {

    @Override
    public Supplier<@NotNull Class<UnifiedResponse>> responseType() {
        return () -> UnifiedResponse.class;
    }

    @Override
    public Supplier<@NotNull UnifiedResponse> initialResponseBody() {
        return () ->
                new UnifiedResponse<>(
                        Instant.now().toEpochMilli(),
                        WebContextHolder.get().getTraceId(),
                        null,
                        null
                );
    }

    @Override
    public BiConsumer<UnifiedResponse, Object> onBodyData() {
        return UnifiedResponse::setData;
    }

    @Override
    public BiConsumer<UnifiedResponse, UnifiedException> onError() {
        return (response, error) -> {
            response.setError(
                    new UnifiedResponse.Error(
                            error.getErrorCode(),
                            WebContextHolder.get().getHttpRequest().getServletPath(),
                            List.of(error.getMessage())
                    )
            );
        };
    }

    @Override
    public Object getBodyData(UnifiedResponse response) {
        return response.getData();
    }

    @Override
    public Consumer<UnifiedResponse> atFinally() {
        return b -> {
        };
    }
}
