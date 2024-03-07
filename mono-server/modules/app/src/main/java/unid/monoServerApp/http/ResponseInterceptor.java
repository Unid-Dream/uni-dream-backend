package unid.monoServerApp.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.monoServerMeta.api.AuthLoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ResponseInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            var handlerMethod = (HandlerMethod) handler;
            var returnType = handlerMethod.getReturnType();
            var hostType = UnifiedResponse.class;
            var targetType = AuthLoginResponse.class;
            if (TypeUtils.isAssignable(hostType, returnType.getParameterType())) {
                if (TypeUtils.isAssignable(
                        returnType.getGenericParameterType(),
                        TypeUtils.parameterize(hostType, targetType)
                )) {
                    log.info("{} Detected", targetType.getSimpleName());
                }
            }
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
