package pwh.springStarter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import pwh.coreStarter.type.NamedEnum;

@Slf4j
public class NamedEnumConvertor {

    public static class FromString implements ConverterFactory<String, NamedEnum> {
        @Override
        public <T extends NamedEnum> Converter<String, T> getConverter(Class<T> targetType) {
            return source -> {
                log.debug("Resolving Enum of NamedEnum");
                return NamedEnum.get(targetType, source);
            };
        }
    }

    public static class ToString implements ConverterFactory<NamedEnum, String> {
        @Override
        public <T extends String> Converter<NamedEnum, T> getConverter(Class<T> targetType) {
            return source -> (T) source.toNamedString();
        }
    }

}
