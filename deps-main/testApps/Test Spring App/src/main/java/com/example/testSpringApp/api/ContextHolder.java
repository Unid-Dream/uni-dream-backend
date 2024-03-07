package com.example.testSpringApp.api;

import org.apache.commons.lang3.StringUtils;
import pwh.springWebStarter.holder.WebContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom Context Holder base on your requirements
 */
public class ContextHolder extends WebContextHolder.ContextHolder {

    public ContextHolder(String traceId, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        super(traceId, httpRequest, httpResponse);
    }

    // sugar method
    public static ContextHolder get() {
        return WebContextHolder.getAs(ContextHolder.class);
    }

    @Override
    public String getTraceId() {
        return StringUtils.defaultIfBlank(super.getTraceId(), getHttpRequest().getHeader("some-header"));
    }

    // custom method
    public String getUserId() {
        return getHttpRequest().getHeader("some-user-id-header");
    }
}
