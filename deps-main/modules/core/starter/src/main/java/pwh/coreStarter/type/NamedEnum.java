package pwh.coreStarter.type;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public interface NamedEnum extends Serializable {

    static <E extends NamedEnum> E get(Class<E> clazz, String name, boolean ignoreCase) {
        for (var e : clazz.getEnumConstants()) {
            var val = e.toNamedString();
            if (ignoreCase) {
                val = val.toLowerCase();
                name = name.toLowerCase();
            }
            if (val.equals(name)) {
                return e;
            }
        }
        return null;
    }

    static <E extends NamedEnum> E get(Class<E> clazz, String name) {
        return get(clazz, name, false);
    }

    // for jackson deserialize & serialize
    // for spring's controller request param (e.g. @RequestParam | @PathVariable), this will be used by ConverterFactory
    @JsonValue
    String toNamedString();
}
