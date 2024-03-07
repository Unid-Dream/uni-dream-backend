package pwh.coreStarter.annotation.processor;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class BaseProcessor extends AbstractProcessor {
    protected String toOneLine(Object... msg) {
        return StringUtils.join(Arrays.stream(msg).map(Object::toString).collect(Collectors.toList()), " ");
    }

    protected void log(Object... msg) {
        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.NOTE, toOneLine(msg)
        );
    }

    protected void error(Object... msg) {
        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.ERROR, toOneLine(msg)
        );
    }
}
