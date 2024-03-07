package pwh.coreFeign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import pwh.coreFeign.annotation.FeignAction;

import java.util.HashMap;
import java.util.function.Consumer;

@Slf4j
public abstract class UltraRequestInterceptor implements RequestInterceptor {
    private final HashMap<String, Consumer<RequestTemplate>> FEIGN_ACTION_MAP = new HashMap<>();

    public UltraRequestInterceptor() {
        feignActions(FEIGN_ACTION_MAP);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // normal request
        onRequest(requestTemplate);
        // @FeignAction
        var method = requestTemplate.methodMetadata().method();
        var feignAction = method.getAnnotation(FeignAction.class);
        if (feignAction != null) {
            for (var action : feignAction.actions()) {
                if (FEIGN_ACTION_MAP.containsKey(action)) {
                    FEIGN_ACTION_MAP.get(action).accept(requestTemplate);
                }
            }
        }
        //
    }

    public abstract void onRequest(RequestTemplate requestTemplate);

    public abstract void feignActions(HashMap<String, Consumer<RequestTemplate>> map);
}
