package pwh.coreJooqLibs.converter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashingIgnoreCaseConverter extends HashingConverter {
    private final IgnoreCaseConverter ignoreCaseConvertor = new IgnoreCaseConverter();

    public HashingIgnoreCaseConverter() {
    }

    public static boolean isValid(String hashedWithSalt, String rawInput) {
        var convertor = new HashingIgnoreCaseConverter();
        return convertor.compareIsTheSame(hashedWithSalt, rawInput);
    }

    @Override
    public boolean compareIsTheSame(String hashedWithSalt, String rawInput) {
        return super.compareIsTheSame(hashedWithSalt, ignoreCaseConvertor.to(rawInput));
    }

    @Override
    @SneakyThrows
    public String to(String s) {
        return super.to(ignoreCaseConvertor.to(s));
    }
}
