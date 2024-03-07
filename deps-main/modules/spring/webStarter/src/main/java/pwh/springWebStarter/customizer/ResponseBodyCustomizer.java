package pwh.springWebStarter.customizer;

import pwh.coreStarter.exception.UnifiedException;

import javax.validation.constraints.NotNull;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Inherit this customizer for your own Unified Response Body Pojo by having a component extending ResponseBodyCustomizer
 * Triggered by the return type = your own
 * <Error> response will always use this
 */
public abstract class ResponseBodyCustomizer<ResponseType> {

    public abstract @NotNull Supplier<@NotNull Class<ResponseType>> responseType();

    @NotNull
    public abstract @NotNull Supplier<@NotNull ResponseType> initialResponseBody();

    @NotNull
    public abstract @NotNull BiConsumer<ResponseType, Object> onBodyData();

    @NotNull
    public abstract @NotNull BiConsumer<ResponseType, UnifiedException> onError();

    @NotNull
    public abstract @NotNull Object getBodyData(ResponseType response);

    @NotNull
    public abstract @NotNull Consumer<ResponseType> atFinally();
}
