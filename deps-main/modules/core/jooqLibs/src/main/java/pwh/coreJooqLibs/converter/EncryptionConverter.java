package pwh.coreJooqLibs.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.StringFixedIvGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.jooq.Converter;

import javax.validation.constraints.NotBlank;

@Slf4j
public class EncryptionConverter implements Converter<String, String> {
    private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    public EncryptionConverter(
            @NotBlank String encryptionKey,
            @NotBlank String salt,
            String algo
    ) {
        if (StringUtils.isAnyBlank(encryptionKey, salt)) {
            throw new RuntimeException("Missing 'encryption key' or 'salt'");
        }
        encryptor.setAlgorithm(StringUtils.defaultIfBlank(algo, "PBEWITHHMACSHA1ANDAES_256"));
        encryptor.setIvGenerator(
                new StringFixedIvGenerator(
                        "1234567890!@#$%^&*()_+=-][{}|qwertyuioplkjhgfdsazxcvbnm/?QWERTYUIOPLKJHGFDSAZXCVBNM"
                )
        );
        encryptor.setSaltGenerator(new StringFixedSaltGenerator(salt));
        encryptor.setPassword(encryptionKey);
        encryptor.initialize();
    }

    @Override
    public String from(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        return encryptor.isInitialized() ? encryptor.decrypt(s) : s;
    }

    @Override
    public String to(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        return encryptor.isInitialized() ? encryptor.encrypt(s) : s;
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
