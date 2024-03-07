package pwh.coreJooqLibs.converter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Converter;

import javax.annotation.Nullable;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

@Slf4j
public class HashingConverter implements Converter<String, String> {
    private final SecretKeyFactory keyFactory;
    private final String separator;

    @SneakyThrows
    public HashingConverter() {
        this.keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        this.separator = ":";
    }

    public static boolean isValid(String hashedWithSalt, String rawInput) {
        var convertor = new HashingConverter();
        return convertor.compareIsTheSame(hashedWithSalt, rawInput);
    }

    public boolean compareIsTheSame(String hashedWithSalt, String rawInput) {
        var salt = retrieveBase64Salt(hashedWithSalt);
        var hashed = excludedSalt(hashedWithSalt);
        var hashedRawInput = hashed(rawInput, Base64.decodeBase64(salt));
        return hashed.equals(excludedSalt(hashedRawInput));
    }

    private byte[] getRandomSalt() {
        var random = new SecureRandom();
        var salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private PBEKeySpec getKeySpec(String input, byte[] salt) {
        return new PBEKeySpec(
                input.toCharArray(),
                salt,
                65536,
                128
        );
    }

    public String retrieveBase64Salt(String hashedWithSalt) {
        return StringUtils.substringAfterLast(hashedWithSalt, separator);
    }

    @SneakyThrows
    public String hashed(String rawInput, @Nullable byte[] salt) {
        var keySpec = getKeySpec(rawInput, salt == null ? getRandomSalt() : salt);
        return String.format(
                "%s%s%s",
                Base64.encodeBase64String(keyFactory.generateSecret(keySpec).getEncoded()),
                separator,
                Base64.encodeBase64String(keySpec.getSalt())
        );
    }

    public String excludedSalt(String hashedWithSalt) {
        return StringUtils.substringBeforeLast(hashedWithSalt, separator);
    }

    @Override
    public String from(String hashedWithSalt) {
        return hashedWithSalt;
    }

    @Override
    @SneakyThrows
    public String to(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        var parts = s.split(separator);
        if(parts.length == 2) {
            if(Base64.isBase64(parts[0]) && Base64.isBase64(parts[1])) {
                return s;
            }
        }
        return hashed(s, null);
    }

    @Override
    public final Class<String> fromType() {
        return String.class;
    }

    @Override
    public final Class<String> toType() {
        return String.class;
    }
}
