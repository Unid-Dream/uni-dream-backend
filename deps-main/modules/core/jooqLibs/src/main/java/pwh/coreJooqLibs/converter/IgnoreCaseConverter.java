package pwh.coreJooqLibs.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Converter;

@Slf4j
public class IgnoreCaseConverter implements Converter<String, String> {
    @Override
    public String from(String s) {
        return s;
    }

    @Override
    public String to(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        return s.toLowerCase();
    }

    @Override
    public Class<String> fromType() {
        return String.class;
    }

    @Override
    public Class<String> toType() {
        return String.class;
    }
}
