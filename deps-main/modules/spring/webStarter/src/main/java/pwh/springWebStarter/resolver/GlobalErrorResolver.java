package pwh.springWebStarter.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.ErrorCodeGlobal;
import pwh.springWebStarter.customizer.ResponseBodyCustomizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.function.Function;

@Slf4j
public class GlobalErrorResolver implements HandlerExceptionResolver {
    private final ObjectMapper objectMapper;
    private final ResponseBodyCustomizer responseBodyCustomizer;

    public GlobalErrorResolver(
            ObjectMapper objectMapper,
            ResponseBodyCustomizer responseBodyCustomizer
    ) {
        this.objectMapper = objectMapper;
        this.responseBodyCustomizer = responseBodyCustomizer;
    }

    @Override
    @SneakyThrows
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        log.error("Request Error", e);
        log.error("responseBodyCustomizer: {}", responseBodyCustomizer);
        log.error("Handler: {}", handler);
        var unifiedBody = responseBodyCustomizer.initialResponseBody().get();
        try {
            var error = e instanceof UnifiedException
                    ? (UnifiedException) e
                    : getErrors().getOrDefault(
                    e.getClass(),
                    (r) -> new UnifiedException(
                            ErrorCodeGlobal.ERROR_INTERNAL_ERROR,
                            r.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            r
                    )
            ).apply(e);
            response.setStatus(error.getHttpStatus());
            log.error("Final Error: {} | {}", error.getErrorCode(), error.getHttpStatus());
            responseBodyCustomizer.onError().accept(unifiedBody, error);
        } catch (Exception unexpected) {
            var ue = new UnifiedException(
                    ErrorCodeGlobal.ERROR_INTERNAL_ERROR, "Unexpected Error", 500, unexpected
            );
            responseBodyCustomizer.onError().accept(unifiedBody, ue);
            response.setStatus(ue.getHttpStatus());
        } finally {
            responseBodyCustomizer.atFinally().accept(unifiedBody);
        }
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        objectMapper.writeValue(response.getWriter(), unifiedBody);
        response.getWriter().flush();
        return new ModelAndView();
    }

    private HashMap<Class<? extends Exception>, Function<Exception, UnifiedException>> getErrors() {
        return new HashMap<>() {{
            put(
                    ResponseStatusException.class, (r) -> {
                        var e = (ResponseStatusException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INTERNAL_ERROR,
                                e.getReason(),
                                e.getRawStatusCode(),
                                e);
                    }
            );
            put(
                    MethodArgumentNotValidException.class, (r) -> {
                        var e = (MethodArgumentNotValidException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                e.getBindingResult().getFieldError() != null ?
                                        String.format(
                                                "%s - %s",
                                                e.getBindingResult().getFieldError().getField(),
                                                e.getBindingResult().getFieldError().getDefaultMessage()
                                        )
                                        : e.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    HttpRequestMethodNotSupportedException.class, (r) -> {
                        var e = (HttpRequestMethodNotSupportedException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_NOT_SUPPORTED,
                                e.getMethod(),
                                HttpStatus.METHOD_NOT_ALLOWED.value(),
                                e
                        );
                    }
            );
            put(
                    HttpMediaTypeNotSupportedException.class, (r) -> {
                        var e = (HttpMediaTypeNotSupportedException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_NOT_SUPPORTED,
                                e.getMessage(),
                                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                                e
                        );
                    }
            );
            put(
                    HttpMediaTypeNotAcceptableException.class, (r) -> {
                        var e = (HttpMediaTypeNotAcceptableException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                e.getMessage(),
                                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                                e
                        );
                    }
            );
            put(
                    MissingPathVariableException.class, (r) -> {
                        var e = (MissingPathVariableException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                String.format("Missing: %s", e.getVariableName()),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    MissingServletRequestParameterException.class, (r) -> {
                        var e = (MissingServletRequestParameterException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                String.format("Missing: %s", e.getParameterName()),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    ServletRequestBindingException.class, (r) -> {
                        var e = (ServletRequestBindingException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                e.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    ConversionNotSupportedException.class, (r) -> {
                        var e = (ConversionNotSupportedException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_NOT_SUPPORTED,
                                e.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    TypeMismatchException.class, (r) -> {
                        var e = (TypeMismatchException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                String.format("Invalid Type For %s", e.getPropertyName()),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    HttpMessageNotReadableException.class, (r) -> {
                        var e = (HttpMessageNotReadableException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                e.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    HttpMessageNotWritableException.class, (r) -> {
                        var e = (HttpMessageNotWritableException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                e.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    MissingServletRequestPartException.class, (r) -> {
                        var e = (MissingServletRequestPartException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                String.format("Missing: %s", e.getRequestPartName()),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    BindException.class, (r) -> {
                        var e = (BindException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                e.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
            put(
                    NoHandlerFoundException.class, (r) -> {
                        var e = (NoHandlerFoundException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_NOT_FOUND,
                                String.format("No '%s' for '%s'", e.getHttpMethod(), e.getRequestURL()),
                                HttpStatus.NOT_FOUND.value(),
                                e
                        );
                    }
            );
            put(
                    AsyncRequestTimeoutException.class, (r) -> {
                        var e = (AsyncRequestTimeoutException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_TIMEOUT,
                                e.getMessage(),
                                HttpStatus.REQUEST_TIMEOUT.value(),
                                e
                        );
                    }
            );
            // EXTRA belows
            put(
                    ConstraintViolationException.class, (r) -> {
                        var e = (ConstraintViolationException) r;
                        return new UnifiedException(
                                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                                e.getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                e
                        );
                    }
            );
        }};
    }
}
