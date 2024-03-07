package pwh.springStarter.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

@Slf4j
public class GenericEnumConvertor {

    public static class FromString implements ConverterFactory<String, Enum> {
        @Override
        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
            return source -> {
                log.debug("Resolving Enum of Generic");
                return (T) EnumUtils.getEnum(targetType, source);
            };
        }
    }

    public static class ToString implements ConverterFactory<Enum, String> {
        @Override
        public <T extends String> Converter<Enum, T> getConverter(Class<T> targetType) {
            return source -> (T) source.name();
        }
    }
}
