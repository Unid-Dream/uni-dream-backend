package pwh.springWebStarter.customizer;

import pwh.springWebStarter.holder.WebContextHolder;

@FunctionalInterface
public interface WebContextHolderCustomizer {
    <CH extends WebContextHolder.ContextHolder> CH customize(WebContextHolder.ContextHolder contextHolder);
}
