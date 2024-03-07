package pwh.springWebStarter.holder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MEANT TO BE AVAILABLE PER HTTP REQUEST ONLY
 */

public class WebContextHolder {

    private static final InheritableThreadLocal<ContextHolder> contextHolderInheritableThreadLocal = new InheritableThreadLocal<>();

    public static boolean isInited() {
        return ObjectUtils.isNotEmpty(get()) && get().isInited();
    }

    public static void initEmpty() {
        set(new ContextHolder());
    }

    public static <CH extends ContextHolder> void set(CH contextHolder) {
        contextHolderInheritableThreadLocal.set(contextHolder);
    }

    public static ContextHolder get() {
        return contextHolderInheritableThreadLocal.get();
    }

    public static <CH extends ContextHolder> CH getAs(Class<CH> customHolder) {
        return (CH) contextHolderInheritableThreadLocal.get();
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ContextHolder {
        private String traceId;
        @JsonIgnore
        @Getter
        private HttpServletRequest httpRequest;
        @JsonIgnore
        @Getter
        private HttpServletResponse httpResponse;

        public boolean isInited() {
            return httpRequest != null && traceId != null;
        }

        public String getTraceId() {
            return traceId;
        }
    }
}
