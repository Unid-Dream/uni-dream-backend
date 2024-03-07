# Session Context Holder

## Extend Original Context Holder

### Holder Class
```java
/**
 * Custom Context Holder base on your requirements
 * with a sugar method, you can get your current session holder by ContextHolder.get().getTraceId()
 * */
public class ContextHolder extends WebContextHolder.ContextHolder {

    // sugar method
    public static ContextHolder get() {
        return WebContextHolder.getAs(ContextHolder.class);
    }

    public ContextHolder(String traceId, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        super(traceId, httpRequest, httpResponse);
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
```

### Customizer (activate your custom holder)
```java
@Component
public class ContextHolderCustomizer implements WebContextHolderCustomizer {
    @Override
    public ContextHolder customize(WebContextHolder.ContextHolder c) {
        return new ContextHolder(c.getTraceId(), c.getHttpRequest(), c.getHttpResponse());
    }
}
```