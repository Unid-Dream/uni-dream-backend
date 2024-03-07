//package pwh.springWebStarter.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import pwh.springWebStarter.customizer.ResponseBodyCustomizer;
//import pwh.springWebStarter.resolver.GlobalErrorResolver;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Very First Entry of HTTP Request
// */
//
//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Slf4j
//public class OncePerRequestFilter extends org.springframework.web.filter.OncePerRequestFilter {
//    private final GlobalErrorResolver globalErrorResolver;
//
//    public OncePerRequestFilter(
//            GlobalErrorResolver globalErrorResolver
//    ) {
//        this.globalErrorResolver = globalErrorResolver;
//    }
//
//    @Override
//    protected void doFilterInternal
//            (HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//            ) {
//        try {
//            log.debug("Doing filter: {}", request.getServletPath());
//            filterChain.doFilter(request, response); // going to WebFilter.class
//        } catch (Exception e) {
//            globalErrorResolver.resolveException(request, response, null, e);
//        }
//    }
//}
