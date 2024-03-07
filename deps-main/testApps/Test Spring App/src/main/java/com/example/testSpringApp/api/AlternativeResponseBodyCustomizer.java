package com.example.testSpringApp.api;

import lombok.extern.slf4j.Slf4j;
import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.customizer.ResponseBodyCustomizer;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

//@Component
@Slf4j
public class AlternativeResponseBodyCustomizer extends ResponseBodyCustomizer<AlternativeResponse> {

    @Override
    public Supplier<@NotNull Class<AlternativeResponse>> responseType() {
        return () -> AlternativeResponse.class;
    }

    @Override
    public Supplier<AlternativeResponse> initialResponseBody() {
        return () -> AlternativeResponse.builder()
                .requestedTime(Instant.now().toEpochMilli())
                .build();
    }

    @Override
    public BiConsumer<AlternativeResponse, Object> onBodyData() {
        return (b, d) -> {
            log.info("Setting {} to {}", d, b);
            b.setBody(d);
        };
    }

    @Override
    public BiConsumer<AlternativeResponse, UnifiedException> onError() {
        return (b, e) -> {
            b.setErrorText(e.getMessage());
        };
    }

    @Override
    public Object getBodyData(AlternativeResponse response) {
        return response.getBody();
    }

    @Override
    public Consumer<AlternativeResponse> atFinally() {
        return (b) -> {
        };
    }
}
