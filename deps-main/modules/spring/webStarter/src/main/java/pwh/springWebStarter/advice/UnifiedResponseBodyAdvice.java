package pwh.springWebStarter.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pwh.springWebStarter.customizer.ResponseBodyCustomizer;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UnifiedResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private final ResponseBodyCustomizer responseBodyCustomizer;

    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        var requiredResponseType = (Class<?>) responseBodyCustomizer.responseType().get();
        return returnType.getMethod() != null
                && returnType.getMethod().getReturnType().isAssignableFrom(requiredResponseType);
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        var unifiedBody = responseBodyCustomizer.initialResponseBody().get();
        responseBodyCustomizer.onBodyData().accept(unifiedBody, responseBodyCustomizer.getBodyData(body));
        // error should be handled by any error resolver which also contains handling for atFinally()
        responseBodyCustomizer.atFinally().accept(unifiedBody);
        return unifiedBody;
    }
}
