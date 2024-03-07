package unid.monoServerApp.api.microsoft;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MsGraphBuilder {
    public static <T> T build(T msGraphObject, Consumer<T> builder) {
        builder.accept(msGraphObject);
        return msGraphObject;
    }
}
