package com.example.testSpringApp.api;

import org.springframework.stereotype.Component;
import pwh.springWebStarter.customizer.WebContextHolderCustomizer;
import pwh.springWebStarter.holder.WebContextHolder;

@Component
public class ContextHolderCustomizer implements WebContextHolderCustomizer {
    @Override
    public ContextHolder customize(WebContextHolder.ContextHolder c) {
        return new ContextHolder(c.getTraceId(), c.getHttpRequest(), c.getHttpResponse());
    }
}
